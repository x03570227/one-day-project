/**
 * 
 */
package net.caiban.pc.erp.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.caiban.pc.erp.domain.EverydayCond;
import net.caiban.pc.erp.domain.EverydayModel;

/**
 * @author mar
 *
 */
public interface EverydayMapper {

	public Integer insertSelective(EverydayModel everyday);
	
	public List<EverydayModel> queryByCond(@Param("cond") EverydayCond cond);
}
