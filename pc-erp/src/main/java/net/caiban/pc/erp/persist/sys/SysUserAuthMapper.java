package net.caiban.pc.erp.persist.sys;

import net.caiban.pc.erp.domain.sys.SysUserAuthModel;

/**
 * Created by mar on 16/2/1.
 */
public interface SysUserAuthMapper {

    public Integer insertSelective(SysUserAuthModel auth);

    public Integer countByOpenid(String openid);

    public Integer deleteByOpenid(String openid);

    public Long queryUidByOpenid(String openid);

    public Integer updateUidByOpenid(Long uid, String openid);
}
