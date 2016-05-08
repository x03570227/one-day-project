/**
 * 
 */
package net.caiban.pc.erp.enums;

/**
 * @author mar
 *
 */
public enum UpyunNamespaceEnum {
	
	IMAGE("caiban-img","http://img.caiban.net"),
	UPLOAD("caiban-upload","http://su.caiban.net")
	;
	private String ns;
	private String host;
	
	private UpyunNamespaceEnum(String ns, String host){
		this.ns = ns;
		this.host=host;
	}
	
	public String getNamespace(){
		return ns;
	}
	
	public String getHost(){
		return host;
	}

}
