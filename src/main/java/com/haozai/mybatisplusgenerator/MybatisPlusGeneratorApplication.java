package com.haozai.mybatisplusgenerator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gblfy.mybatisplus.generator.emp.mapper")
public class MybatisPlusGeneratorApplication {

    public static void main(String[] args) {

        SpringApplication.run(MybatisPlusGeneratorApplication.class, args);
    }

}
