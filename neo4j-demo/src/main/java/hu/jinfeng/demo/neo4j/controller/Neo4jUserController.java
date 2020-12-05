package hu.jinfeng.demo.neo4j.controller;

import hu.jinfeng.demo.neo4j.model.UserNode;
import hu.jinfeng.demo.neo4j.model.UserRelation;
import hu.jinfeng.demo.neo4j.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author hujinfeng  @Date 2020/12/5
 **/
@RestController
public class Neo4jUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/saveUser")
    public String addUserNode(String name, Integer age) {
        UserNode userNode = new UserNode();
        userNode.setName(name);
        userNode.setAge(age);

        userService.addUserNode(userNode);

        return "success";
    }

    @GetMapping("/saveRelation")
    public List<UserRelation> addUserRelation(String firstName, String lastName) {
        return userService.addUserRelation(firstName, lastName);
    }
}
