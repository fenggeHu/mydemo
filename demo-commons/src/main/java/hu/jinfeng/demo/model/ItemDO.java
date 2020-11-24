package hu.jinfeng.demo.model;

import lombok.Data;

/**
 * @Author hujinfeng  @Date 2020/11/19
 **/
@Data
public class ItemDO {

    private String id;

    private String name;

    private String category;

    /**
     * 一口价
     */
    private Double price;
    /**
     * 折扣价
     */
    private Double discountPrice;

    /**
     * 数量
     */
    private Double quantity;

}
