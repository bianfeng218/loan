package com.qp.loan.service.auth;

/**
 * 
 * java代码调用直接使用常量，其中的get方法主要提供给velocity使用
 * 权限码常量定义
 * @author yfliqiang 2015-09-18
 * 
 */
public class AuthCode {

	public static final String LOGIN = "登录";

	// <!-- 用户管理 -->
	public static final String ADD_USER = "";// <value>新增用户</value><!-- 0 -->
	public static final String MODIFY_USER = "";// <value>修改用户</value><!-- 1 -->
	public static final String DELETE_USER = "";// <value>删除用户</value><!-- 2 -->
	public static final String VIEW_USER_LIST = "";// <value>查看用户列表</value><!-- 3
										// -->
	public static final String VIEW_USER_DETAIL = "";// <value>查看用户详情</value><!-- 4
	// <!-- 应用管理 -->
	public static final String ADD_APP = "";// <value>录入应用</value><!-- 5 -->
	public static final String DELETE_APP = "";// <value>删除应用</value><!-- 6 -->
	public static final String MODIFY_APP = "";// <value>修改应用</value><!-- 7 -->
	public static final String VIEW_APP_LIST = "";// <value>查看应用列表</value><!-- 8 -->
	public static final String VIEW_APP_DETAIL = "";// <value>查看应用详情</value><!-- 9
	public static final String VIEW_APP_TOKEN = "";// <value>查看接入密钥</value><!-- 10
	// <!-- 视频管理 -->
	public static final String UPLOAD_VIDEO = "";// <value>上传视频</value><!-- 11 -->
	public static final String VIEW_VIDEO_LIST = "";// <value>查看视频列表</value><!-- 12
	public static final String VIEW_VIDEO_DETAIL = "";// <value>查看视频详情</value><!--
	public static final String LOCK_VIDEO = "";// <value>停用视频</value><!-- 14 -->
	public static final String UNLOCK_VIDEO = "";// <value>启用视频</value><!-- 15 -->
	public static final String VIEW_PLAY_CODE = "";// <value>查看播放代码</value><!-- 16
											// -->
	public static final String MODIFY_VIDEO = "";// <value>编辑视频</value><!-- 17 -->
	public static final String DELETE_VIDEO = "";// <value>删除视频</value><!-- 18 -->
	public static final String VIEW_AGENT_INFO = "";// <value>查看视频代理商相关信息</value><!--
	public static final String SET_VIDEO_IMG = "";// <value>设置视频封面</value><!-- 20
	public static final String TRACE_VIDEO_STATUS = "";// <value>视频状态追踪</value><!--
	public static final String VIEW_VIDEO = "";// <value>视频预览</value><!-- 22 -->
	// <!-- 数据统计 -->
	public static final String VIEW_VIDEO_PLAY_TIMES = "";// <value>查看单个视频播放量统计</value><!--
	public static final String VIEW_APP_PLAY_TIMES = "";// <value>查看应用视频播放量统计</value><!--
	// <!-- 系统管理 -->
	public static final String VIEW_VIDEO_BACKUP_LIST = "";// <value>查看视频备份列表</value><!--
	// <!-- 菜单的展示与否 -->
	public static final String MENU_APP_MANAGE = "";//<!-- 26应用管理-->
	public static final String MENU_ADD_APP = "";// <!-- 27录入应用 -->
	public static final String MENU_APP_LIST = "";// <!-- 28应用列表-->
	
	public static final String MENU_USER_MANAGE = "";// <!-- 29用户管理-->
	public static final String MENU_ADD_USER = "";// <!-- 30新增用户-->
	public static final String MENU_USER_LIST = "";// <!-- 31用户列表-->
	
	public static final String MENU_VIDEO_MANAGE = "";// <!-- 32视频管理-->
	public static final String MENU_UPLOAD_VIDEO = "";// <!-- 33视频上传-->
	public static final String MENU_VIDEO_LIST = "";// <!-- 34视频列表-->
	
	public static final String MENU_DATA_STATISTICS = "";// <!-- 35数据统计-->
	public static final String MENU_VIDEO_VIEW_STATISTICS = "";// <!-- 36视频播放量统计-->
	
	public static final String MENU_SYS_MANAGE = "";// <!-- 37系统管理-->
	public static final String MENU_VIDEO_BACKUP_LIST = "";// <!-- 38视频备份列表-->
	public static final String MENU_WORKER_LIST = "";// <!-- 39worker控制台-->
	public static final String MENU_UPLOAD_VIDEO_SIMPLE = "";// <!--40视频上传-简版-->
	public static final String MODIFY_VIDEO_VIEWS = "";// <!--41修改视频总播放量-->
	
	public static final String QUERY_BY_UPLOADPIN = "";// <!--41通过上传人查询-->
	public static final String VIDEO_MOVE = "";// <!--43视频搬家-->

	public String getLOGIN() {
		return LOGIN;
	}
	public String getADDWARE() {
		return "添加商品";
	}
}
