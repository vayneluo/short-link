package me.lattice.shortlink.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.lattice.shortlink.annotation.SimpleController;
import me.lattice.shortlink.common.result.Result;
import me.lattice.shortlink.common.utiils.QRCodeUtil;
import me.lattice.shortlink.service.ShortLinkService;
import me.lattice.shortlink.web.req.ShortLinkGenReq;
import me.lattice.shortlink.web.rsp.ShortLinkGenRsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @ApiOperation(value = "短链生成")
    @PostMapping(value = "/link/generate")
    public Result<ShortLinkGenRsp> generateShortLink(@RequestBody ShortLinkGenReq req) {
        return new Result<>(shortLinkService.generateShortLink(req));
    }

    @ApiOperation(value = "短链二维码生成")
    @RequestMapping(value = "/link/qrcode")
    public void qrcode(String content, @RequestParam(defaultValue = "300", required = false) int width
            , @RequestParam(defaultValue = "300", required = false) int height
            , HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            QRCodeUtil.writeToStream(content, outputStream, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
