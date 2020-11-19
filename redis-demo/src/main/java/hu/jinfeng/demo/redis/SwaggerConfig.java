package hu.jinfeng.demo.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 描述:
 *
 * @author ningfeng.hjf   @date 2020-04-19 9:46
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("hu.jinfeng.demo"))
            .paths(PathSelectors.any())
            //.paths(Predicates.or(PathSelectors.ant("/user/add"),
            //    PathSelectors.ant("/user/find/*")))
            .build();
    }
}
