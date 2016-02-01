package net.caiban.pc.erp.domain.sys;

import net.caiban.pc.erp.domain.BaseDomain;

import java.util.Date;

/**
 * Created by mar on 16/2/1.
 */
public class SysUserAuth extends BaseDomain{

    private Long uid;
    private String classify;
    private String openid;
    private String accessToken;
    private String refreshToken;
    private Date gmtAuth;
    private Integer expiresIn;
    private String scope;
    private String unionid;
    private String resp;
    private Date gmtExpires;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getGmtAuth() {
        return gmtAuth;
    }

    public void setGmtAuth(Date gmtAuth) {
        this.gmtAuth = gmtAuth;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public Date getGmtExpires() {
        return gmtExpires;
    }

    public void setGmtExpires(Date gmtExpires) {
        this.gmtExpires = gmtExpires;
    }
}
