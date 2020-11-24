package hu.jinfeng.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author hujinfeng  @Date 2020/11/18
 **/
@Data
public class OrderDO implements Serializable {
    private static final long serialVersionUID = -8548629682867088185L;

    private long id;

    private String itemId;

    private Double price;

    private String msg;

    private Date orderDate;
}
