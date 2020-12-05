package hu.jinfeng.demo.neo4j.model;

import lombok.Data;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * @Author hujinfeng  @Date 2020/12/4
 **/
@Data
@RelationshipEntity(type = "UserRelation")
public class UserRelation {

    @Id
    private Long id;

    @StartNode
    private UserNode startNode;

    @EndNode
    private UserNode endNode;
}
