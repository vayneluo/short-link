package me.lattice.shortlink.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: 短链域名配置
 * @author: Lattice
 * @date 2022/6/27 14:19
 */
@Data
@Component
@ConfigurationProperties(prefix = "short.link.domain")
public class ShortLinkDomainProperty {


    /** 短链服务域名 **/
    private String domainUrl;

    public String convertShortLink(String shortLink){
        return domainUrl + shortLink;
    }
}
