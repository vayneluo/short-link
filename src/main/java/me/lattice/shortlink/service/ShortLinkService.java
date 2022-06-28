package me.lattice.shortlink.service;

import cn.hutool.crypto.SecureUtil;
import me.lattice.shortlink.common.constant.RedisConstant;
import me.lattice.shortlink.common.constant.RedisKeyConstant;
import me.lattice.shortlink.common.constant.ShortLinkConstant;
import me.lattice.shortlink.common.enums.UrlTypeEnum;
import me.lattice.shortlink.common.properties.ShortLinkDomainProperty;
import me.lattice.shortlink.common.utiils.RedisUtils;
import me.lattice.shortlink.common.utiils.ShortLinkUtils;
import me.lattice.shortlink.common.utiils.UrlUtil;
import me.lattice.shortlink.entity.UrlMapping;
import me.lattice.shortlink.service.biz.ShortLinkBizService;
import me.lattice.shortlink.web.req.ShortLinkGenReq;
import me.lattice.shortlink.web.rsp.ShortLinkGenRsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.util.Objects;

/**
 * @description: 短链生成服务
 * @author: Lattice
 * @date 2022/6/27 10:56
 */
@Service
public class ShortLinkService {

    @Autowired
    IdGenerateService idGenerateService;
    @Autowired
    ShortLinkBizService shortLinkBizService;
    @Autowired
    ShortLinkDomainProperty domainProperty;


    /***
     * @description: 短链生成
     * @param: [req] 原始URL
     * @author: Lattice
     * @date: 2022/6/27 11:40
     */
    @Transactional(rollbackFor = Exception.class)
    public ShortLinkGenRsp generateShortLink(ShortLinkGenReq req) {
        String originUrl = req.getOriginUrl();

        // 获取加密后的MD5值 用于检索校验重复
        String md5OriginUrl = SecureUtil.md5(originUrl);
        // 查询DB 当前URL是否存在
        UrlMapping urlMapping = shortLinkBizService.findShortLinkByMD5(md5OriginUrl);
        // 数据库已经存在该URL，直接返回
        if (Objects.nonNull(urlMapping)) {
            String shortLink = urlMapping.getShortLink();
            // set redis cache
            RedisUtils.setValueTimeout(RedisUtils.generateKey(shortLink), urlMapping.getOriginUrl(), RedisConstant.SHORT_LINK_TTL);

            return ShortLinkGenRsp.builder()
                    .shortLink(domainProperty.convertShortLink(shortLink))
                    .build();
        }
        return ShortLinkGenRsp.builder()
                .shortLink(genSystem(originUrl, md5OriginUrl))
                .build();
    }

    /***
     * @description: 短链生成
     * @param: [originUrl, md5OriginUrl] 原始长链接， MD5后的链接值
     * @author: Lattice
     * @date: 2022/6/27 14:55
     */
    private String genSystem(String originUrl, String md5OriginUrl) {
        // 发号器生成唯一ID by snowflake
        Long urlId = idGenerateService.genId();
        // 10进制转62进制，生成最终短链
        String shortLink = ShortLinkUtils.convert10to62(urlId, ShortLinkConstant.SHORT_LINK_LENGTH);

        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setUrlId(urlId);
        urlMapping.setShortLink(shortLink);
        urlMapping.setOriginUrl(originUrl);
        urlMapping.setOriginUrlMd5(md5OriginUrl);
        urlMapping.setUrlType(UrlTypeEnum.SYSTEM.getCode());
        try {
            // 设置顶级域名，用于统计
            urlMapping.setDomain(UrlUtil.getDomainName(originUrl));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);

        }
        shortLinkBizService.insertUrlMapping(urlMapping);
        // set redis cache
        RedisUtils.setValueTimeout(RedisUtils.generateKey(shortLink), urlMapping.getOriginUrl(), RedisConstant.SHORT_LINK_TTL);
        return domainProperty.convertShortLink(shortLink);
    }

}
