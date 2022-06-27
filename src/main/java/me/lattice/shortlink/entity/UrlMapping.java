package me.lattice.shortlink.entity;

import lombok.Data;

import java.util.Date;

/**
 * @description: 短链映射
 * @author: Lattice
 * @date 2022/6/27 13:41
 */
@Data
public class UrlMapping {

    private Long urlId;

    private String domain;

    private String originUrl;

    private String originUrlMd5;

    private String shortLink;

    private int urlType;

    private Date createTime;

    private Date updateTime;

}
