package net.caiban.pc.erp.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import main.java.com.UpYun;
import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.Everyday;
import net.caiban.pc.erp.domain.EverydayCond;
import net.caiban.pc.erp.domain.EverydayModel;
import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.UpyunNamespaceEnum;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.persist.EverydayMapper;
import net.caiban.pc.erp.service.EverydayService;
import net.caiban.pc.erp.service.WeixinService;
import net.caiban.pc.erp.utils.UpyunUtils;
import net.caiban.utils.DateUtil;
import net.caiban.utils.file.FileUtil;
import net.caiban.utils.http.HttpRequestUtil;
import weixin.popular.api.MediaAPI;
import weixin.popular.bean.EventMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;

@Component("everydayService")
public class EverydayServiceImpl implements EverydayService{

	@Resource
	private EverydayMapper everydayMapper;
	@Resource
	private WeixinService weixinService;
	
	final static String TAG_HTML_TPL="<span class='text-tag' >#{0}</span>";
	
	@Override
	public XMLTextMessage save(EventMessage message) throws ServiceException{
		
		EverydayModel everyday = new EverydayModel();
		everyday.setContent(message.getContent());
		everyday.setWxOpenid(message.getFromUserName());
		everyday.setWxMsgid(message.getMsgId());
		everyday.setWxDescription(message.getDescription());
		everyday.setWxLabel(message.getLabel());
		everyday.setWxLx(message.getLocation_X()==null?null:Double.valueOf(message.getLocation_X()));
		everyday.setWxLy(message.getLocation_Y()==null?null:Double.valueOf(message.getLocation_Y()));
		everyday.setWxMediaid(message.getMediaId());
		everyday.setWxMsgtype(message.getMsgType());
		everyday.setWxPicurl(message.getPicUrl());
		everyday.setWxScale(message.getScale());
		everyday.setWxThumbMediaId(message.getThumbMediaId());
		everyday.setWxTitle(message.getTitle());
		
		List<String> tags = parseTags(everyday.getContent());
		everyday.setTags(Joiner.on(",").join(tags));
		
		everyday.setUrl(parseURL(everyday.getContent()));
		
		everyday.setDayIndex(parseDayIdx(everyday.getWxOpenid()));
		everyday.setDayItemIndex(parseDayItemIdx(everyday.getWxOpenid()));
		
		everydayMapper.insertSelective(everyday);
		if(everyday.getId()==null || everyday.getId()<=0){
			throw new ServiceException("FAILURE_SAVE_EVERYDAY");
		}
		
		StringBuffer sb =new StringBuffer();
		sb.append("每1天,1件事,记1笔 \n")
		.append(Strings.nullToEmpty(everyday.getContent()))
		.append("(<a href='")
		.append(AppConst.getConfig("app.host"))
		.append("/f/feveryday/detail.do?viewWxOpenid=").append(message.getFromUserName())
		.append("&id=")
		.append(everyday.getId())
		.append("' >")
		.append("#D").append(everyday.getDayIndex())
		.append("</a>) \n");
		
		sb.append("<a href='").append(AppConst.getConfig("app.host")).append("/f/feveryday/index.do?wxOpenid=").append(message.getFromUserName()).append("'>查看更多</a>");
		
		return new XMLTextMessage(message.getFromUserName(), message.getToUserName(), sb.toString());
	}
	
	private Integer parseDayIdx(String openid){
		Date today=null;
		try {
			today = DateUtil.getDate(new Date(), AppConst.DATE_FORMAT_DATE);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date yestoday = DateUtil.getDateAfterDays(today, -1);
		
		Integer lastdayIdx=everydayMapper.queryMaxDayIndex(openid, yestoday, today);
		
		if (lastdayIdx==null) {
			return 0;
		}
		return lastdayIdx+1;
	}
	private Integer parseDayItemIdx(String openid){
		
		Date today=null;
		try {
			today = DateUtil.getDate(new Date(), AppConst.DATE_FORMAT_DATE);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer maxidx = everydayMapper.queryMaxItemIdx(openid, today);
		
		if(maxidx==null){
			return 0;
		}
		return maxidx+1;
		
	}
	
	private List<String> parseTags(String content){
		
		if(Strings.isNullOrEmpty(content)){
			return Lists.newArrayList();
		}
		
		Pattern p=Pattern.compile("#[A-Za-z\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5]*");
		
		Matcher m=p.matcher(content);
		
		List<String> tags = Lists.newArrayList();
		while(m.find()){
			tags.add(m.group().replace("#", ""));
		}
		
		return tags;
	}
	
	private String parseURL(String content){
		if(Strings.isNullOrEmpty(content)){
			return null;
		}
		
		Pattern p=Pattern.compile("(?i)http://[^\u4e00-\u9fa5]+");
		
		Matcher m=p.matcher(content);
		
		if(m.find()){
			return m.group();
		}
		
		return null;
	}
	
//	private List<String> parseUrl(String content){
//		
//		String regEx = "(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
//				+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
//				+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
//				+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
//				+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
//				+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
//				+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
//				+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*";
//		
//		Pattern p=Pattern.compile(regEx);
//		
//		Matcher m=p.matcher(content);
//		
//		List<String> urls = Lists.newArrayList();
//		while(m.find()){
//			urls.add(m.group());
//		}
//		
//		return urls;
//	}

	@Override
	public XMLTextMessage queryRecent(EventMessage message) {
		
		EverydayCond cond = new EverydayCond();
		cond.setLimit(5);
		
		StringBuffer sb = buildRespByCond(cond);
		
		sb.append("<a href='").append(AppConst.getConfig("app.host")).append("/f/feveryday/index.do?viewWxOpenid=").append(message.getFromUserName()).append("'>查看更多</a>");
		
		return new XMLTextMessage(message.getFromUserName(), message.getToUserName(), sb.toString());
	}

	@Override
	public XMLTextMessage queryMy(EventMessage message) {
		
		EverydayCond cond = new EverydayCond();
		cond.setWxOpenid(message.getFromUserName());
		cond.setLimit(5);
		
		StringBuffer sb = buildRespByCond(cond);
		
		sb.append("<a href='").append(AppConst.getConfig("app.host")).append("/f/feveryday/index.do?wxOpenid=")
				.append(message.getFromUserName())
				.append("&viewWxOpenid=").append(message.getFromUserName())
				.append("'>查看更多</a>");
		
		return new XMLTextMessage(message.getFromUserName(), message.getToUserName(), sb.toString());
	}
	
	private StringBuffer buildRespByCond(EverydayCond cond){
		
		List<EverydayModel> list = everydayMapper.queryByCond(cond);
		
		StringBuffer sb = new StringBuffer();
		
		int idx = 1;
		for(EverydayModel everyday: list){
			sb.append(idx).append(". <a href='")
				.append(AppConst.getConfig("app.host"))
				.append("/f/feveryday/detail.do?id=")
				.append(everyday.getId())
				.append("' >");
			if(everyday.getContent().length()<=14){
				sb.append(everyday.getContent());
			}else{
				sb.append(everyday.getContent().substring(0, 14));
			}
				sb.append("</a> \n");
			idx++;
		}
		
		return sb;
	}
	
	public static void main(String[] args) {
//		Pattern p=Pattern.compile("#[A-Za-z\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5]*"); 
//		Matcher m=p.matcher("我的QQ是:456456 #我的电话是:05#32214 我的#邮箱#是:a#aa123@aaa.com");
//		
//		while(m.find()) { 
//			System.out.println(m.group()); 
//		} 
		
//		String url = EverydayServiceImpl.parseURL("java获取一段文字中的url地址并且Http://forum.csdn.net/PointForum/Forum/PostTopic.aspx?forumID=467d91e3-dd10-480b-a322-71b65e66c736在网页中以链接http://forum.csdn.net/PointForum/Forum/PostTopic.a");
//		System.out.println(url);
		
try {
	
//	HttpRequestUtil.httpGetResponse("http://mmbiz.qpic.cn/mmbiz/bAvvgWOL0YicaIO7bmJnKsakXnOljav3tRPiaG2R4qpbzUstA8ZJPFYJn5ItzLRQrryToibEBNcRe79ZxOia2syjag/0");
//	HttpGet httpGet = new HttpGet("http://mmbiz.qpic.cn/mmbiz/bAvvgWOL0YicaIO7bmJnKsakXnOljav3tRPiaG2R4qpbzUstA8ZJPFYJn5ItzLRQrryToibEBNcRe79ZxOia2syjag/0");
////	HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/media/get?access_token=H74SoNhrHslx5qGML1lb5xYLkfH35bd-UO87KTLAT6OGf_owCl45pi7JXrIb9DCjsIXLRHOy6SGRNbucpbYqwkLUFsZSXOx3MjeMSizTAoUDZGbAHAZVC&media_id=u-Cti2LtFUP4wMdXmQDkfBHe9gILdGf-Aqm5E5_Y6i1D9sFcEEvsdPMbnUoI5qyN");
//	HttpContext context = HttpClientContext.create();
//	
//	PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//	cm.setMaxTotal(20);
//	cm.setDefaultMaxPerRoute(20);
//	
//	HttpHost localhost = new HttpHost("localhost", 80);
//	cm.setMaxPerRoute(new HttpRoute(localhost), 50);
//	
//	CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).build();
//	
//	CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).build().execute(httpGet, context);
	
			CloseableHttpResponse response = HttpRequestUtil.httpGetResponse("http://mmbiz.qpic.cn/mmbiz/bAvvgWOL0YicaIO7bmJnKsakXnOljav3tDrPu0msibKdLtibzlYeQGNm5LwkBYPDWKE43ouNlz4ibqBc4yqY89XFTg/0");
			
			try {
				
				HttpEntity entity = response.getEntity();
				entity.getContentType();
				if(entity!=null){
					InputStream is = entity.getContent();
					
					String tmpfilepath = "/tmp/"+UUID.randomUUID()+".jpg";
					
					FileOutputStream fos = new FileOutputStream(new File(tmpfilepath));
					byte[] b = new byte[1024];
					while((is.read(b)) != -1){
					fos.write(b);
					}
					
					is.close();
					fos.close();
//					return httpResponseAsString(is, encode);
					
				}
				
			} finally{
				response.close();
			}
			
		} catch (ClientProtocolException e) {
//			LOG.error("Error occurred when get url. Message:"+e.getMessage(), e);
		} catch (IOException e) {
//			LOG.error("Error occurred when get url. Message:"+e.getMessage(), e);
		}


	}

	@Override
	public Pager<EverydayModel> pagerRecent(EverydayCond cond, Pager<EverydayModel> pager) throws ServiceException {
		
		Preconditions.checkNotNull(cond);
		Preconditions.checkNotNull(pager);
		
		cond.setWxOpenid(Strings.emptyToNull(cond.getWxOpenid()));
		
		pager.setDir("desc");
		pager.setSort("gmt_created");
		
		List<EverydayModel> records = everydayMapper.pagerByCond(cond, pager);
		
		records = rebuildEveryday(records);
		
		pager.setRecords(records);
		pager.setTotals(everydayMapper.countByCond(cond));
		return pager;
	}

	@Override
	public EverydayModel queryById(Long id) throws ServiceException {
		Preconditions.checkNotNull(id);
		
		EverydayModel everyday = everydayMapper.queryById(id);
		
		if(everyday==null){
			throw new ServiceException("INVALID_ID");
		}
		
		if(!Strings.isNullOrEmpty(everyday.getContent())){
			everyday.setPageTitle(Jsoup.clean(everyday.getContent(), Whitelist.none()));
		}
		
		everyday.setMaxDayIndex(everydayMapper.queryMaxDayIndex(everyday.getWxOpenid(), null, null));
		if (everyday.getMaxDayIndex() == null || everyday.getMaxDayIndex() <= 0
				|| everyday.getDayIndex() > everyday.getMaxDayIndex()) {
			everyday.setNowDayPercent(new BigDecimal("100"));
		}else{
			everyday.setNowDayPercent(new BigDecimal(String.valueOf(everyday.getDayIndex())).divide(new BigDecimal(String.valueOf(everyday.getMaxDayIndex())), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
		}
		
		everyday.setContent(buildContentHtml(everyday.getContent()));
		return everyday;
	}
	
	private String buildContentHtml(String content){
		
		if(Strings.isNullOrEmpty(content)){
			return "";
		}
		
		List<String> tags = parseTags(content);
		
		for(String tag: tags){
			content = content.replace("#"+tag, MessageFormat.format(TAG_HTML_TPL, tag));
		}
		
		content = content.replace("\n", "<br />");
		
		return content;
	}

	@Override
	public List<EverydayModel> queryTheDayByEveryday(Everyday everyday) {
		Preconditions.checkNotNull(everyday);
		
		Date from=null;
		try {
			from = DateUtil.getDate(everyday.getGmtCreated(), AppConst.DATE_FORMAT_DATE);
		} catch (ParseException e) {
		}
		Date to =DateUtil.getDateAfterDays(from, 1);
		
		EverydayCond cond = new EverydayCond();
		cond.setGmtCreatedMin(from);
		cond.setGmtCreatedMax(to);
		cond.setExcludeId(Long.valueOf(everyday.getId()));
		cond.setWxOpenid(everyday.getWxOpenid());
		
		List<EverydayModel> list = everydayMapper.queryByCond(cond);
		return rebuildEveryday(list);
	}
	
	private List<EverydayModel> rebuildEveryday(List<EverydayModel> list){
		if(list==null){
			return Lists.newArrayList();
		}
		for(EverydayModel everyday: list){
			everyday.setContent(buildContentHtml(everyday.getContent()));
		}
		return list;
	}

	@Override
	public String saveImage(EventMessage message) throws ServiceException {
		Preconditions.checkNotNull(message);
		
		if(Strings.isNullOrEmpty(message.getMediaId())){
			return null;
		}
		
		//"R-767F1kwehf5F02TSrIqv4PiIVXY2aup0KkYG_E_iZfXqZGwoc_k2q5dRPaw52L"
		
//		byte[] mediaByte = MediaAPI.mediaGet(weixinService.remoteAccessToken(), message.getMediaId());
		byte[] mediaByte = mediaGet(message.getPicUrl());
		
		
//		FileOutputStream fos;
//		try {
//			fos = new FileOutputStream(new File("/Users/mar/a1.jpg"));
//			fos.write(mediaByte);
//			fos.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		if(mediaByte.length==0){
			throw new ServiceException("INVALID_MEDIA_ID");
		}
		
		UpYun upYun = UpyunUtils.getClient(UpyunNamespaceEnum.IMAGE.getNamespace());
		String fullpath = UpyunUtils.getMonthPath(message.getMediaId()+".jpg");
		Boolean result = upYun.writeFile(fullpath, mediaByte, true);
		
		if(!result){
			throw new ServiceException("UPLOAD_FAILURE");
		}
		
		return fullpath;
	}
	
	private byte[] mediaGet(String url) {
		CloseableHttpResponse response = null;
		try {

			response = HttpRequestUtil.httpGetResponse(url);

			try {
				HttpEntity entity = response.getEntity();
				
				if (entity != null) {
					InputStream is = null;
					FileOutputStream fos = null;
					
					FileInputStream fis=null;
					ByteArrayOutputStream bos=null;
					try {
						is = entity.getContent();
						
						String tmpfilepath = "/mnt/tmp/"+UUID.randomUUID()+".jpg";
						fos = new FileOutputStream(new File(tmpfilepath));
						byte[] b = new byte[1024];
						while((is.read(b)) != -1){
							fos.write(b);
						}
						
						fis = new FileInputStream(new File(tmpfilepath));
						bos = new ByteArrayOutputStream();
						
						byte[] buff = new byte[fis.available()];
						fis.read(buff);
						bos.write(buff);
						
						return bos.toByteArray();
//						ByteArrayOutputStream bos = new ByteArrayOutputStream();
//						byte[] buff = new byte[1024];
//						
//						while((is.read(buff)) != -1){
//							bos.write(buff);
//						}
//						
//						return bos.toByteArray();
					} finally {
						is.close();
						fos.close();
						
						fis.close();
						bos.close();
					}

					// return httpResponseAsString(is, encode);
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new byte[0];
	}
	
	
}
