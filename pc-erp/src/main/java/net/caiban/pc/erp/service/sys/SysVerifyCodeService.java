package net.caiban.pc.erp.service.sys;

public interface SysVerifyCodeService {
	
	/**
	 * 获取手机验证码
	 * @param mobileNum
	 * @return
	 */
	public Integer mobileVerifyCode(String mobileNum);
	
	
	/**
	 * 验证手机验证码
	 * @param mobileNum
	 * @return
	 */
	public Integer checkMobileVerifyCode(String mobileNum,String verifyCode);
}
