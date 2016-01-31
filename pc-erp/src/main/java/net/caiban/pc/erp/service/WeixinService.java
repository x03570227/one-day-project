/**
 * 
 */
package net.caiban.pc.erp.service;

import java.io.InputStream;

import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.exception.ServiceException;

/**
 * @author mar
 *
 */
public interface WeixinService {

	public boolean validSign(String signature, String timestamp, String nonce);
	
	public String autoResp(InputStream is);
	
	public String genAccessToken() throws ServiceException;
	
	public String remoteAccessToken() throws ServiceException;

	public SessionUser oauth(String code) throws ServiceException;
	
}
