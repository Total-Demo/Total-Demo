package com.barry.nacosprovider.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定义返回的实体类，字段根据需要添加
 */
@Data       // 生成getter/setter/tostring/equals
@AllArgsConstructor // 全参构造
@NoArgsConstructor  // 无参构造
public class ResponseData {
    private int code;
    private String message;
}
