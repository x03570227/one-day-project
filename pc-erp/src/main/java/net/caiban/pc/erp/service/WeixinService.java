/**
 * 
 */
package net.caiban.pc.erp.service;

import java.io.InputStream;

/**
 * @author mar
 *
 */
public interface WeixinService {

	public boolean validSign(String signature, String timestamp, String nonce);
	
	public String autoResp(InputStream is);
	
}
