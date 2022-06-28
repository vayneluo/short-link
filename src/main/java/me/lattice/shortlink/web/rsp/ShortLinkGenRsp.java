package me.lattice.shortlink.web.rsp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 短链生成返回
 * @author: Lattice
 * @date 2022/6/27 10:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "短链生成返回")
public class ShortLinkGenRsp {

    @ApiModelProperty(value = "生成后的短链")
    private String shortLink;

    @ApiModelProperty(value = "原始长链接")
    private String originalUrl;
}
