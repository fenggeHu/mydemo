package hu.jinfeng.demo.neo4j.model;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * 用户节点
 *
 * @Author hujinfeng  @Date 2020/12/4
 **/
@Data
@NodeEntity(label="User")
public class UserNode {

    @Id
    private Long nodeId;
    @Property
    private String userId;
    @Property
    private String name;
    @Property
    private Integer age;
}
