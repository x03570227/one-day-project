/**
 * 
 */
package net.caiban.pc.erp.domain;

/**
 * @author mar
 *
 */
public class EverydayCond extends BaseCond {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String wxOpenid;
	private String wxMsgtype;

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

	public String getWxMsgtype() {
		return wxMsgtype;
	}

	public void setWxMsgtype(String wxMsgtype) {
		this.wxMsgtype = wxMsgtype;
	}
	

}
