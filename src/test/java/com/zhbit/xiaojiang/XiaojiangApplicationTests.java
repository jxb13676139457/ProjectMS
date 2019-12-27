package com.zhbit.xiaojiang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XiaojiangApplicationTests {

	@Autowired
	DataSource dataSource;
	//单元测试，测试mysql数据库是否连接成功
	@Test
	public void test(){
		System.out.println(dataSource.getClass());
	}

}
