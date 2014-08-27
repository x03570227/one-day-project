/**
 * 
 */
package com.hlmm.main;

import com.hlmm.http.HttpRemoteControl;

/**
 * @author Administrator
 *
 */
public class HlMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		HttpRemoteControl httpRemoteControl=new HttpRemoteControl();
		try {
			//第一个参数是从婚礼妈妈访问过去的地址
			//第二个参数是进入淘宝后再次请求的地址
		//httpRemoteControl.exe("http://detail.1688.com/offer/37288752323.html", "http://detail.1688.com/offer/39474466439.html","",0);
			httpRemoteControl.exe("http://hunjia.hunlimama.com/item_161534.html", "http://detail.tmall.com/item.htm?id=35191024220","",0);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
