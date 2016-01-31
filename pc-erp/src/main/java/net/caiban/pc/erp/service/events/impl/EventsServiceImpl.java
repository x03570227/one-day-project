/**
 * 
 */
package net.caiban.pc.erp.service.events.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.events.Events;
import net.caiban.pc.erp.persist.events.EventsMapper;
import net.caiban.pc.erp.service.events.EventsService;
import net.caiban.pc.erp.service.sys.SysUserService;
import net.caiban.utils.DateUtil;
import net.caiban.utils.lang.StringUtils;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

/**
 * @author mays
 *
 */
@Component("eventsService")
public class EventsServiceImpl implements EventsService {
	
	@Resource
	private SysUserService sysUserService;
	@Resource
	private EventsMapper eventsMapper;

	@Override
	public Integer[] changeToIntArray(String origin) {
		Integer ids[] = {};
		do {
			if (origin == null || origin.length() <= 0) {
				break;
			}
			String[] idstrArray = origin.split(",");
			if (idstrArray.length == 0) {
				break;
			}
			ids = new Integer[idstrArray.length];
			for (int i = 0; i < idstrArray.length; i++) {
				ids[i] = Integer.valueOf(idstrArray[i]);
			}
		} while (false);
		return ids;
	}

	@Override
	public Map<String, Long> filterAppendJoiner(String origin,
			String originId, String append) {
		
		origin = filterEmpty(origin);
		originId = filterEmpty(originId);
		append = filterEmpty(append);
		
		Integer[] idArr = changeToIntArray(originId);
		String[] accountArr = {};
		origin = replaceCN(origin);
		if(StringUtils.isNotEmpty(origin)){
			accountArr = origin.split(",");
		}
		
		if(idArr.length != accountArr.length){
			return null;
		}
		
		append = replaceCN(append);
		String[] appendArr = {};
		if(StringUtils.isNotEmpty(append)){
			appendArr = append.split(",");
		}
		
		Map<String, Long> map = new HashMap<String, Long>();
		for(int i=0; i<accountArr.length; i++ ){
			map.put(accountArr[i], Long.valueOf(idArr[i]));
		}
		
		for(String account: appendArr){
			if(map.get(account)==null){
				Long id = sysUserService.queryIdByAccount(account);
				if(id!=null && id.intValue()>0){
					map.put(account, id);
				}
			}
		}
		
		return map;
	}
	
	private String filterEmpty(String arg){
		if("".equals(arg)){
			return null;
		}
		return arg;
	}
	
	private String replaceCN(String arg){
		if(arg == null){
			return null;
		}
		
		return arg.replace("，",",");
	}

	@Override
	public void initGmt(Events events, String gmtStartStr, String gmtEndStr) {
		
		try {
			events.setGmtStart(DateUtil.getDate(gmtStartStr, AppConst.DATE_FORMAT_DATE));
			events.setGmtEnd(DateUtil.getDate(gmtEndStr, AppConst.DATE_FORMAT_DATE));
		} catch (ParseException e) {
			
		}
	}

	@Override
	public Long saveEvent(Events event) {
		if(event == null){
			return null;
		}
		
		if(Strings.isNullOrEmpty(event.getName())){
			return null;
		}
		
		event.setStatusPublic(event.getStatusPublic()==null?Events.STATUS_DEFAULT:event.getStatusPublic());
		
		//保存事件
		eventsMapper.save(event);
		
		return event.getId();
	}

	@Override
	public void appendJoiner(Integer eventId, String inviteAccountId) {
//		Integer[] ids = changeToIntArray(inviteAccountId);
		//TODO 添加参与人员
		
	}

}
