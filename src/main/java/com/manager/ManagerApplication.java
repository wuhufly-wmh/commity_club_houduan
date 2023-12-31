/**
 * 程序入口
 */

package com.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class ManagerApplication {


	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
		System.out.println("系统启动成功");

	}

}