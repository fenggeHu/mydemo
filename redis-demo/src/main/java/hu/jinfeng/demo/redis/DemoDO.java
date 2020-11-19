package hu.jinfeng.demo.redis;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author hujinfeng  @Date 2020/11/14
 **/
@Data
public class DemoDO  implements Serializable {
    private static final long serialVersionUID = -7533570658826527656L;

    private Long id;

    private String name;

    private String description;
}
