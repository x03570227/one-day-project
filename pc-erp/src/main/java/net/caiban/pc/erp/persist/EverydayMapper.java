/**
 * 
 */
package net.caiban.pc.erp.persist;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.caiban.pc.erp.domain.EverydayCond;
import net.caiban.pc.erp.domain.EverydayModel;
import net.caiban.pc.erp.domain.Pager;

/**
 * @author mar
 *
 */
public interface EverydayMapper {

	public Integer insertSelective(EverydayModel everyday);
	
	public List<EverydayModel> queryByCond(@Param("cond") EverydayCond cond);
	
	public List<EverydayModel> pagerByCond(@Param("cond") EverydayCond cond, @Param("pager") Pager<EverydayModel> pager);
	
	public Integer countByCond(@Param("cond") EverydayCond cond);
	
	public EverydayModel queryById(Long id);
	
	public Integer queryMaxDayIndex(@Param("openid") String openid, @Param("from") Date from, @Param("to")Date to);
	
	public Integer queryMaxItemIdx(@Param("openid") String openid, @Param("minGmtCreated") Date minGmtCreated);
}
