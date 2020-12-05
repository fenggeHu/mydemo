package hu.jinfeng.demo.neo4j.service;

import hu.jinfeng.demo.neo4j.dao.UserRepository;
import hu.jinfeng.demo.neo4j.model.UserNode;
import hu.jinfeng.demo.neo4j.model.UserRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author hujinfeng  @Date 2020/12/5
 **/
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserNode> addUserNode(UserNode user) {
        return userRepository.createUserNode(user);
    }

    public List<UserRelation> addUserRelation(String firstName, String lastName){
        UserNode firstUser = userRepository.getUserByName(firstName).get(0);
        UserNode secondUser = userRepository.getUserByName(lastName).get(0);

        return userRepository.addUserRelationList(firstUser.getName(), secondUser.getName());
    }

}
