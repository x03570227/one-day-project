package net.caiban.pc.erp.enums;

/**
 * Created by mar on 16/5/14.
 */
public enum MsgTypeEnum implements BaseEnum {

    TEXT("text", "文本"),
    IMAGE("image", "图片"),
    LINK("link", "连接"),;

    private String code;

    private String message;

    private MsgTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public Integer getValue() {
        return null;
    }
}
