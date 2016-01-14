/**
 * 
 */
package net.caiban.pc.erp.persist;

import java.util.List;

import net.caiban.pc.erp.domain.EverydayCond;
import net.caiban.pc.erp.domain.EverydayModel;

/**
 * @author mar
 *
 */
public interface EverydayMapper {

	public Integer insertSelective(EverydayModel everyday);
	
	public List<EverydayModel> queryByCond(EverydayCond cond);
}
