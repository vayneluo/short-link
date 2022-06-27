package me.lattice.shortlink.service;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import org.springframework.stereotype.Service;

/**
 * @description: 发号器
 * @author: Lattice
 * @date 2022/6/27 11:30
 */
@Service
public class IdGenerateService {

    private static final SnowflakeGenerator generator = new SnowflakeGenerator();

    /***
     * @description: 雪花算法 生成唯一ID
     * @author: Lattice
     * @date: 2022/6/27 11:32
     */
    public Long genId() {
        return generator.next();
    }
}
