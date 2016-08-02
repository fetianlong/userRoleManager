package com.yuedi.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088121753597318";
	
	// 收款支付宝账号，一般情况下收款账号就是签约账号
	public static String seller_email = "yuedizhengyuan@163.com";
	// 商户的私钥
//	public static String key = "MIICXQIBAAKBgQC6b/jOcqtSKv0fLXItIsATqWsEN48egN0VsO3xCpODUvmtldDQRj0gjN5IwPj6tFwxR/7nHvtCdCzKRUehkiWx2iP+GIx5N5fMbA1mf7cDRjVLd0OHtKXYrIu1JD5V0InTBcbXHS/7YPTrBG1L5wotkpryfxaXFzeaYFsGGn3oOwIDAQABAoGBALCr2wMH9wFua19YGZJcE9iN+zOL0rv9MYSUnWaFtzQDNUchaTd02Ag4dYxMEaTBtXud1hf+a+uVW7xx2mdprv00Hgc3AHe/PWBiHiACzLYsTazyJVBYoTp27hBIkF5pxsnDZIbnz05J5umSiCvlXuCHp+i2ym5whQwS/gbMWrhBAkEA5xFsU/OrlRAasw1WU5nwk6diuQ0gaXSKmPKbIDTZR751J+x5oihmKMynM4Z3Yk8kxYUsz1ByGFskq6WbUq7Z7wJBAM6Nwe0xXBiyw3/TCnanq84j4hUY8d8OAcRrEa2igPHFg+FKJcPAS2l4J1nZBPBSq8d6Jwo3f2f6L6E1675WknUCQDYDxKb7VyjJUvR4Ma3pVmutvU4021bLf3qxe8HTmNdElm52wRg/CWKQNKW+0TmR9q6VqxvrWkCHTdMyK1TwutECQQDGV/GkpwVc4ixBDpK5pgCgjmyG/dEk8/pWdsofBjDz+UrOh7kD5B+GQ87lxDOt4nT5tB7Q/MHnQqOCFpktyUwRAkAChdoCjHB5ZWzvnkNk+Wq2M/UIiHdUaAGpuWdZ8DtB4DjWvYdI/SgKaBQtE3TlvCOYQE2Rt2Hg+1a/wNVhQGim";
	public static String key = "8mijrpo7iairtn8iu8ncm2kh36k8ogu1";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:/alipayLog/";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "MD5";

}
