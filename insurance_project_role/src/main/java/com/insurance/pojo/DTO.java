package com.insurance.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTO<T>{

    private  String status;//判断系统是否出错做出相应的true或者false的返回
    private  String errorCode;//自定义错误码，0表示无错
    private  String message;//提示信息
    private  T data;//具体返回数据内容

}
