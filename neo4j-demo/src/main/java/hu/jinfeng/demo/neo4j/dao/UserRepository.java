package hu.jinfeng.demo.neo4j.dao;

import hu.jinfeng.demo.neo4j.model.UserNode;
import hu.jinfeng.demo.neo4j.model.UserRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * //老版本的参数使用{},在新版本3.x 中使用$代替
 * Cypher语法参考：
 * MATCH(u:User) where id(u)=3  return u;  --查询nodeId=3
 * MATCH (n:User{name:$name}) RETURN n, id(n) as nodeId;  --返回nodeId
 * MATCH(u:User) where u.age>=$startAge and u.age<$endAge  return u; --where 条件查询
 * --创建关系
 * MATCH (fu:User),(su:User) where fu.name=$firstName and su.name=$secondName  create p=(fu)-[r:UserRelation]->(su) return p;
 *
 * @Author hujinfeng  @Date 2020/12/4
 **/
@Component
public interface UserRepository extends Neo4jRepository<UserNode, Long> {
    @Query("create (n:User{nodeId:$userNode.nodeId, userId:$userNode.userId, age:$userNode.age, name:$userNode.name}) RETURN n")
    List<UserNode> createUserNode(@Param("userNode") UserNode userNode);

    @Query("MATCH (n:User) RETURN n ")
    List<UserNode> getUserNodeList();

    @Query("MATCH (n:User{name:$name}) RETURN n")
    List<UserNode> getUserByName(@Param("name") String name);

    @Query("MATCH (n:User{name:$name}) RETURN n, id(n) as nodeId ")
    Long getNodeIdByName(@Param("name") String name);

    //    @Query("create (n:User{age:{age},name:{name}}) RETURN n ")
    @Query("create (n:User{age:$age,name:$name}) RETURN n ")
    List<UserNode> addUserNodeList(@Param("name") String name, @Param("age") int age);

    @Query("match(u:User) where u.age>=$startAge and u.age<$endAge  return u")
    List<UserNode> queryUserWithAge(@Param("startAge") Integer startAge, @Param("endAge") Integer endAge);

    @Query("match (fu:User),(su:User) where fu.name=$firstName and su.name=$secondName  create p=(fu)-[r:UserRelation]->(su) return p")
    List<UserRelation> addUserRelationList(@Param("firstName") String firstName, @Param("secondName") String secondName);

}
