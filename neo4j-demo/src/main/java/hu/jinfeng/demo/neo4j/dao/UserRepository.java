package hu.jinfeng.demo.neo4j.dao;

import hu.jinfeng.demo.neo4j.model.UserNode;
import hu.jinfeng.demo.neo4j.model.UserRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author hujinfeng  @Date 2020/12/4
 **/
@Component
public interface UserRepository extends Neo4jRepository<UserNode, Long> {
    @Query("MATCH (n:User) RETURN n ")
    List<UserNode> getUserNodeList();

    @Query("MATCH (n:User{name:$name}) RETURN n ")
    List<UserNode> getUserByName(@Param("name") String name);

    //老版本的参数使用{},在新版本3.x 中使用$代替
    //    @Query("create (n:User{age:{age},name:{name}}) RETURN n ")
    @Query("create (n:User{age:$age,name:$name}) RETURN n ")
    List<UserNode> addUserNodeList(@Param("name") String name, @Param("age") int age);

    @Query("match (fu:User),(su:User) where fu.name=$firstName and su.name=$secondName  create p=(fu)-[r:UserRelation]->(su) return p")
    List<UserRelation> addUserRelationList(@Param("firstName") String firstName, @Param("secondName") String secondName);

}
