package me.lattice.shortlink.common.result;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @description: 返回
 * @author: Lattice
 * @date 2022/6/24 11:46
 */
@Data
public class Result<T> implements Serializable {

   private static Result<Object> result = new Result<>();

   /**
    * 失败消息
    */
   private String message;

   /**
    * 返回代码  
    */
   private Integer code;

   /**
    * 时间戳
    */
   private long timestamp = System.currentTimeMillis();

   /**
    * 结果对象
    */
   private T data;

   private Result() {}

   public Result(T data) {
      this.code = HttpStatus.OK.value();
      this.message = "success";
      this.data = data;
   }

   public static Result success() {
      Result result = new Result();
      result.setCode(HttpStatus.OK.value());
      return result;
   }

   public static Result<Object> success(Object data) {
      result.setCode(HttpStatus.OK.value());
      result.setData(data);
      return result;
   }

    public static Result<Object> success(Object data, String message) {
        result.setCode(HttpStatus.OK.value());
        result.setData(data);
        result.setMessage(message);
        return result;
    }

   public static Result<Object> failure(String message) {
      result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      result.setMessage(message);
      return result;
   }

   public static Result<Object> failure(Integer code, String message) {
      result.setCode(code);
      result.setMessage(message);
      return result;
   }
}