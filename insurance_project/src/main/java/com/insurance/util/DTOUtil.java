package com.insurance.util;

import com.insurance.pojo.DTO;

/**
 * 用于返回信息的工具类
 */
public class DTOUtil {

    static final String SUCCESS="TRUE";
    static final String FAIL="FALSE";
    static final String ERROR_CODE="0";
    private static DTO dto=new DTO();

    /**
     * 不带提示信息，不带数据（返回成功）
     * @return
     */
    public static DTO returnSucess(){
        dto.setStatus(SUCCESS);
        return dto;
    }

    /**
     * 带提示信息，带数据（返回成功）
     * @return
     */
    public static DTO returnSucess(String message,Object data){
        dto.setStatus(SUCCESS);
        dto.setData(data);
        dto.setMessage(message);
        return dto;
    }

    /**
     * 带提示信息，不带数据（返回成功）
     * @param message
     * @return
     */
    public static DTO returnSucess(String message){
        dto.setStatus(SUCCESS);
        dto.setMessage(message);
        return dto;
    }

    /**
     * 带数据，不带提示信息（返回成功）
     * @param data
     * @return
     */
    public static DTO returnSucess(Object data){
        dto.setStatus(SUCCESS);
        dto.setData(data);
        return dto;
    }

    /**
     * 带提示信息，带错误编码（返回失败）
     * @param message
     * @return
     */
    public static DTO returnFalse(String message,String errorCode){
        dto.setStatus(FAIL);
        dto.setMessage(message);
        dto.setErrorCode(errorCode);
        return dto;
    }
}
