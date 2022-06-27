package me.lattice.shortlink.web.controller;

import io.swagger.annotations.Api;
import me.lattice.shortlink.annotation.SimpleController;
import me.lattice.shortlink.common.result.Result;
import me.lattice.shortlink.service.ShortLinkService;
import me.lattice.shortlink.web.req.ShortLinkGenReq;
import me.lattice.shortlink.web.rsp.ShortLinkGenRsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: 短链生成器
 * @author: Lattice
 * @date 2022/6/24 09:31
 */
@Api(tags = "短链生成服务")
@SimpleController
public class ShortLinkController {

    @Autowired
    ShortLinkService shortLinkService;

    @PostMapping(value = "/link/generate")
    public Result<ShortLinkGenRsp> generateShortLink(@RequestBody ShortLinkGenReq req) {
        return new Result<>(shortLinkService.generateShortLink(req));
    }

}
