package hu.jinfeng.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author hujinfeng  @Date 2020/11/18
 **/
@Data
public class OrderDO implements Serializable {
    private static final long serialVersionUID = -8548629682867088185L;

    private long id;

    private Long itemId;

    private double price;

    private String msg;
}