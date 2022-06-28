package me.lattice.shortlink.web.controller;

import io.swagger.annotations.Api;
import me.lattice.shortlink.common.constant.HttpConstant;
import me.lattice.shortlink.service.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description: 重定向转发
 * @author: Lattice
 * @date 2022/6/28 13:46
 */
@Api(tags = "重定向转发服务")
@Controller
public class RedirectController {

    @Autowired
    ShortLinkService shortLinkService;

    @GetMapping("/{shortLink}")
    public String sendRedirect(@PathVariable(value = "shortLink") String shortLink) {
        String redirectUrl = shortLinkService.getShortLink(shortLink);
        return HttpConstant.REDIRECT_PREFIX + redirectUrl;
    }
}
