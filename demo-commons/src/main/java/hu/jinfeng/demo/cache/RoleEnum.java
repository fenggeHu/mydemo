package hu.jinfeng.demo.cache;

/**
 * @Author hujinfeng  @Date 2021/1/9
 **/
public enum RoleEnum {
    PERSONAL("personal", "个人用户"),
    VIP("vip", "个人vip用户"),
    COMPANY("company", "企业用户");

    private String code;
    private String name;
    RoleEnum(String code, String name){
        this.code = code;
        this.name = name;
    }
}
