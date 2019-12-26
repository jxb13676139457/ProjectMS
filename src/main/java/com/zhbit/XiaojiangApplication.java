package com.zhbit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhbit.xiaojiang.mapper")
public class XiaojiangApplication {

	public static void main(String[] args) {
		SpringApplication.run(XiaojiangApplication.class, args);
	}

}
