package me.lattice.shortlink.service.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.lattice.shortlink.entity.UrlMapping;
import me.lattice.shortlink.mapper.UrlMappingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 短链业务查询
 * @author: Lattice
 * @date 2022/6/27 13:29
 */
@Service
public class ShortLinkBizService {

    @Autowired
    UrlMappingMapper urlMappingMapper;

    /***
     * @description: 根据MD5加密原始URL查询数据库Mapping
     * @param: [md5OriginUrl] MD5加密后的URL
     * @author: Lattice
     * @date: 2022/6/27 13:40
     */
    public UrlMapping findShortLinkByMD5(String md5OriginUrl) {
        QueryWrapper<UrlMapping> wrapper = new QueryWrapper<>();
        wrapper.eq("origin_url_md5", md5OriginUrl);
        return urlMappingMapper.selectOne(wrapper);
    }

    /***
     * @description: URL Mapping Insert
     * @param: [urlMapping] 链接对象
     * @author: Lattice
     * @date: 2022/6/27 14:57
     */
    public void insertUrlMapping(UrlMapping urlMapping) {
        urlMappingMapper.insert(urlMapping);
    }
}
