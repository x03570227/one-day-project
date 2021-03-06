/**
 * 
 */
package net.caiban.pc.erp.controller.f;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.EverydaySubjectModel;
import net.caiban.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.caiban.pc.erp.controller.BaseController;
import net.caiban.pc.erp.domain.EverydayCond;
import net.caiban.pc.erp.domain.EverydayModel;
import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.EverydayService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * @author mar
 *
 */
@Controller
public class FeverydayController extends BaseController {
	
	@Resource
	private EverydayService everydayService;

    /**
     * 每1天首页
     *
     * @param request
     * @param response
     * @param model
     * @param cond
     * @param pager
     * @param viewWxOpenid
     * @return
     */
    @RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,
			ModelMap model, EverydayCond cond, Pager<EverydayModel> pager, String viewWxOpenid){

        accessSession(request);

		try {
			pager = everydayService.pagerRecent(cond, pager);
			model.put("pager", pager);
			model.put("cond", cond);
		} catch (ServiceException e) {
			serverError(request, response, e.getMessage());
		}
		return new ModelAndView();
	}

    /**
     * 每1天详细信息页面
     *
     * @param request
     * @param response
     * @param model
     * @param id
     * @param viewWxOpenid
     * @param day
     * @param wxOpenid
     * @return
     */
    @RequestMapping
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response,
							   ModelMap model, Long id, String viewWxOpenid,
							   @DateTimeFormat(pattern = "yyyy-MM-dd")Date day, String wxOpenid){

        accessSession(request);

		try {
			// XXX 利用 wxopenid 判断用户与 everyday 的关系，判断用户绑定状态
			if(day!=null){

				List<EverydayModel> list = everydayService.queryTheDayByDay(day, wxOpenid, null);
				model.put("todays", list);
				model.put("wxOpenid", wxOpenid);
				model.put("pre", DateUtil.toString(DateUtil.getDateAfterDays(day, -1), AppConst.DATE_FORMAT_DATE));
				model.put("next", DateUtil.toString(DateUtil.getDateAfterDays(day, 1), AppConst.DATE_FORMAT_DATE));
				model.put("todayEveryday", (list==null || list.size()<=0)?null:everydayService.rebuildEveryday(list.get(0)));

			}else {
				EverydayModel everydayModel = everydayService.queryById(id);
				model.put("everyday", everydayModel);
				model.put("todayEveryday", everydayModel);
				model.put("todays", everydayService.queryTheDayByEveryday(everydayModel));
				model.put("wxOpenid", everydayModel.getWxOpenid());
				model.put("pre", DateUtil.toString(DateUtil.getDateAfterDays(everydayModel.getGmtCreated(), -1), AppConst.DATE_FORMAT_DATE));
				model.put("next", DateUtil.toString(DateUtil.getDateAfterDays(everydayModel.getGmtCreated(), 1), AppConst.DATE_FORMAT_DATE));
			}

		} catch (ServiceException e) {
			serverError(request, response, e.getMessage());
		}
		return new ModelAndView();
	}

    /**
     * 每1天主题页面
     *
     * @param request
     * @return
     */
    @RequestMapping
    public ModelAndView subject(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                                Long id, Long everydayId, @DateTimeFormat(pattern = "yyyy-MM-dd") Date day) {

        accessSession(request);

        day = day == null ? new Date() : day;
        model.put("pre", DateUtil.toString(DateUtil.getDateAfterDays(day, -1), AppConst.DATE_FORMAT_DATE));
        model.put("next", DateUtil.toString(DateUtil.getDateAfterDays(day, 1), AppConst.DATE_FORMAT_DATE));

        try {

            if(everydayId!=null && everydayId.longValue()>0){
                model.put("everyday", everydayService.queryById(everydayId));
            }

            EverydaySubjectModel subjectModel = everydayService.querySubject(id);
            List<EverydayModel> everydays = everydayService.queryBySubject(id, day);

            if(everydays.size()>0){
                subjectModel.setSubjectIndex(everydays.get(0).getSubjectIndex());
            }else {
                subjectModel.setSubjectIndex(0);
            }

            model.put("everydays", everydays);
            model.put("day", day);

            model.put("subject", everydayService.rebuildEverydaySubject(subjectModel));

            return new ModelAndView();
        } catch (ServiceException e) {
            model.put("errorCode",e.getMessage());
        } catch (Exception e){
            model.put("errorCode", "e.global.server.error");
        }

        return new ModelAndView("redirect:/error_wx.do");
    }

    @RequestMapping
    public ModelAndView subjectDetail(HttpServletRequest request, ModelMap model,
                                      Long id){

        model.put("subject", everydayService.querySubject(id));

        return null;
    }

    @RequestMapping
    public ModelAndView search(HttpServletRequest request, ModelMap model, String q, Pager<EverydayModel> page) throws UnsupportedEncodingException {

        String query = URLDecoder.decode(q, "UTF-8");

        page = everydayService.search(query, page);

        model.put("page", page);

        return null;
    }

}
