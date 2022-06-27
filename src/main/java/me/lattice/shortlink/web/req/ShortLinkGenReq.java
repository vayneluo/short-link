package me.lattice.shortlink.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * @description: 短链生成
 * @author: Lattice
 * @date 2022/6/27 10:18
 */
@Data
@ApiModel(value = "短链生成")
public class ShortLinkGenReq {

    @NotBlank(message = "原始长链接不能为空")
    @Length(max = 300, message = "长链接最大长度不能超过300个字符")
    @Pattern(regexp = "^(http|https)://([^/:]+)(:\\d*)?(.*)$", message = "长链接必须以http或https打头")
    @ApiModelProperty(value = "原始链接URL")
    private String originUrl;
}
