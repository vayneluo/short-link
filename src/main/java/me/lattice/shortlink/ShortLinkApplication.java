package me.lattice.shortlink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "me.lattice.shortlink.mapper")
@SpringBootApplication
public class ShortLinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortLinkApplication.class, args);
	}

}
