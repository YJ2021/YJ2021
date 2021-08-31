package com.insurance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;



@Configuration
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {
     @Bean
     //配置了swagger的docker的bean实例
    public Docket docket(Environment environment){
          //设置要显示的swagger的环境
         Profiles profiles = Profiles.of("dev", "pro");
         //通过environment.acceptsProfile判断是否处在自己设定的环境
         boolean flag = environment.acceptsProfiles(profiles);

         return  new Docket(DocumentationType.SWAGGER_2)
                     .enable(true)//flag是否启动swagger
                     .groupName("YJ")//首页右上角信息 Select a spec分组配置
                     .apiInfo(apiInfo())//http://localhost:8089/swagger-ui.html/页面信息配置
                     .select()
                     //any()全部扫描
                     //none()全部不扫描
                     //basePackage()扫描指定包
                     //withClassAnnotation()扫描指定注解的类
                     //withMethodAnnotation()扫描指定注解的方法
                     .apis(RequestHandlerSelectors.basePackage("com.shiro.controller"))
                     //过滤，要扫描哪些请求
                     //.paths(PathSelectors.ant("/"))
                     .build();
    }

    //配置swagger信息
    private ApiInfo apiInfo(){
         //作者信息
        Contact contact = new Contact("易炯", "localhost", "123456789@qq.com");
        return  new ApiInfo(
                     "易炯的个人 Swagger",
                     "个人 Swagger",
                     "v1.0",
                     "http://baidu.com",
                     contact,
                     "Apache 2.0",
                     "http://www.apache.org/licenses/LICENSE-2.0",
                     new ArrayList());

    }
     @Bean
    //配置多个分组，管理不同的业务
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("张三");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("李四");
    }
    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("王五");
    }

}
