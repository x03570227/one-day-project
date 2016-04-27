package net.caiban.pc.erp.persist.sys;

import net.caiban.pc.erp.domain.sys.SysUserAuth;
import net.caiban.pc.erp.domain.sys.SysUserAuthModel;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mar on 16/2/1.
 */
public interface SysUserAuthMapper {

    public Integer insertSelective(SysUserAuthModel auth);

    public Integer countByOpenid(String openid);

    public Integer deleteByOpenid(String openid);

    public Long queryUidByOpenid(String openid);

    public Integer updateUidByOpenid(@Param("uid") Long uid, @Param("openid") String openid);

    public SysUserAuthModel queryByOpenid(@Param("openid") String openid, @Param("classify") String classify);
}
