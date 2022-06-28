package me.lattice.shortlink;

import me.lattice.shortlink.common.utiils.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Import;

@MapperScan(basePackages = "me.lattice.shortlink.mapper")
@SpringBootApplication(exclude={
		RedisAutoConfiguration.class,
		RedisRepositoriesAutoConfiguration.class})
@Import(SpringContextUtil.class)
public class ShortLinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortLinkApplication.class, args);
	}

}
