package com.insurance.util;

/**
 * 系统错误编码，根据业务定义如下
 * 用户部分编码以10000开始
 *
 *
 */
public class ErrorCode {

	/*角色控制模块错误码-start*/
	public final static String ROLE_OTHER="10000";//其他原因
	public final static String ROLE_INPUT_EMPTY="10001";//输入为空
	public final static String ROLE_USER_NOT_EXIST="10002";//用户不存在
	public final static String ROLE_USER_NOT_ROLES="10003";//用户没有授权角色
	public final static String ROLE_USER_NOT_MENUS="10004";//用户没有授权菜单
	public final static String ROLE_BACKGROUND_CODE_ERROR="10005";//后台代码错误
	/*登录模块错误码-end*/

	/*保险产品*/
	public final static String PRO_OTHER="20000";
	public final static String PRO_INPUT_EMPTY="20001";
	public final static String PRO_SEARCH_EMPTY="20002";
    public final static String PRO_NOT_EXIST="20003";
	/*保险产品-end*/
	/*订单模块*/
	public final static String ORDER_OTHER="20000";
	public final static String ORDER_INPUT_EMPTY="20001";
	public final static String ORDER_USER_NOT_EXIST="20002";
	public final static String ORDER_PRO_NOT_EXIST="20003";
	public final static String ORDER_LIST_NOT_EXIST="20004";

	/*订单模块-end*/


}
