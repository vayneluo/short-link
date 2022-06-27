package me.lattice.shortlink.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum UrlTypeEnum {

  // 系统
  SYSTEM(0, "system"),

  // 自定义
  CUSTOM(1, "custom"),
  ;

  private final int code;
  private final String type;

  public static UrlTypeEnum getByCode(int code) {
    return Stream.of(UrlTypeEnum.values())
        .filter(element -> element.ordinal() == code)
        .findFirst()
        .orElse(null);
  }
}