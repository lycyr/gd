package com.gameserver.gd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2//启动Swagger
@MapperScan("com.gameserver.gd.mapper")
public class GdApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdApplication.class, args);
    }

}
