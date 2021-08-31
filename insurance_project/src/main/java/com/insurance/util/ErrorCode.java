package com.insurance.util;

/**
 * 系统错误编码，根据业务定义如下
 * 用户部分编码以10000开始
 *
 *
 */
public class ErrorCode {

	/*认证模块错误码-start*/
	public final static String AUTH_UNKNOWN="10000";//异常
	public final static String AUTH_USER_ALREADY_EXISTS="10001";//注册失败，账号已存在
    public final static String AUTH_PARAMETER_IS_EMPTY="10003";//激活失败，传入参数为空
	public final static String AUTH_USER_NOT_EXIST="10004";//激活失败，用户不存在
	public final static String AUTH_ALREADY_ACTIVATED="10005";//激活失败，用户已激活
	public final static String AUTH_CODE_HAS_EXPIRED="10006";//激活失败，验证码失效
	public final static String AUTH_CODE_ERROR="10007";//激活失败，验证码错误
	public static final String AUTH_BACKGROUND_CODE_ERROR = "10008";//后台代码错误
	/*认证模块错误码-end*/

	/*登录模块错误码-start*/
	public final static String LOGIN_OTHER="20000";//其他原因
	public final static String LOGIN_INPUT_EMPTY="20001";//输入为空
	public final static String LOGIN_USER_NOT_EXIST="20002";//用户不存在
	public final static String LOGIN_BACKGROUND_CODE_ERROR="20003";//后台代码错误
	/*登录模块错误码-end*/

	/*忘记密码模块错误码-start*/
	public final static String FORGET_INPUT_EMPTY="30000";//输入为空
	public final static String FORGET_USER_NOT_EXIST="30001";//用户不存在
	public final static String FORGET_CODE_SEND_ERROR="30002";//验证码发送失败
	public final static String FORGET_BACKGROUND_CODE_ERROR="30003";//后台代码错误
	public final static String FORGET_CODE_ERROR="30004";//验证码错误
	public final static String FORGET_CODE_NOT_INVALID="30005";//验证码未失效
	/*忘记密码模块错误码-end*/

	/*退出登录错误码*/
	public final static String LOGINOUT_OTHER="40000";
	public final static String LOGINOUT_INPUT_EMPTY="40001";
	/*退出登录错误码-end*/
	/*用户信息查询*/
	public final static String FIND_USER_ERROR="50000";
	/*用户信息查询-end*/
	/*用户信息修改*/
	public final static String UPDATE_USER_ERROR="60000";
	/*用户信息修改-end*/
}
