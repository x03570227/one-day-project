package net.caiban.pc.erp.enums;

/**
 * Created by mar on 16/4/18.
 */
public enum UserClassifyEnum implements BaseEnum{

    /**
     * 普通账户（默认情况）
     */
    ACCOUNT("A", "普通账户（默认情况）"),

    /**
     * 手机号码(国内)
     */
    MOBILE("M", "手机号码(国内)"),

    /**
     * Email
     */
    EMAIL("E", "Email"),

    /**
     * 微信 oauth 账户
     */
    WEIXIN_OAUTH("WA", "微信 oauth 账户"),

    /**
     * 微信公众号粉丝ID
     */
    WEIXIN_FOLLOW("WF", "微信公众号粉丝ID"),
    ;

    private String code;

    private String message;

    private UserClassifyEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Integer getValue() {
        return null;
    }


}
