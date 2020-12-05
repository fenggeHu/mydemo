package hu.jinfeng.demo.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author hujinfeng  @Date 2020/12/4
 **/
@SpringBootApplication(scanBasePackages = "hu.jinfeng.demo.neo4j")
@EnableNeo4jRepositories(basePackages = "hu.jinfeng.demo.neo4j.dao")
@EntityScan(basePackages = "hu.jinfeng.demo.neo4j")
@EnableTransactionManagement
public class Neo4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(Neo4jApplication.class, args);
    }
}
