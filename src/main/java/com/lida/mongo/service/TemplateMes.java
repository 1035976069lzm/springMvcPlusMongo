package com.lida.mongo.service;

import com.lida.mongo.model.AccessToken;
import com.lida.mongo.uril.HttpClientUtils;
import com.lida.mongo.uril.WeixinUtil;

import java.util.Map;


/**
 * ģ����Ϣ
 * @author Administrator
 *
 */
public class TemplateMes {
	
	
	String appId = "wx247b80e8e951fe79";
	// �������û�Ψһƾ֤��Կ
	String appSecret = "4fc4112c0abac60c04997c8ac4d87b92";

	// ���ýӿڻ�ȡaccess_token
	AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
	

	// ������¶���֪ͨ
	private static String NEWORDERS = "9x4PdTRa_FehBFX2HCQHMXAGRshlFj5AFg6zUe3MAA4";
	// �����̶����������� 
	private static String ORDERSSHIPMENTS="9x4PdTRa_FehBFX2HCQHMXAGRshlFj5AFg6zUe3MAA4";
	// ������������� Earnings
	private static String EARNINGS="n5BDnJ787UBXuYdBVhZsGdcf-yv51kyz0roptFdC3S8";
	// ��Ʒȱ������ stockout
	private static String GOODSSTOCKOUT="Kfk7pITiatD9GiR84Hi99NU5jAFZ6ttBhI23elmOuIQ";
	
	private static String MESSAGE_TYPE_NEWORDERS = "neworders";
	private static String MESSAGE_TYPE_ORDERSSHIPMENTS = "ordersshipments";
	private static String MESSAGE_TYPE_EARNINGS = "earnings";
	private static String MESSAGE_TYPE_GOODSSTOCKOUT = "goodsstockout";

	public String sendWXMes(String type, Map<String, String> mesinfo)
			throws Exception {
		String jsoninfo = null;
		if (MESSAGE_TYPE_NEWORDERS.equals(type.trim())) {
			// ����֧���ɹ�֪ͨ
			jsoninfo = TemplateMes.sendCaptcha(mesinfo);
			System.out.println(jsoninfo);
		}else if(MESSAGE_TYPE_ORDERSSHIPMENTS.equals(type.trim())){
			// ������������
			jsoninfo = TemplateMes.sendRebate(mesinfo);
			System.out.println(jsoninfo);
		}else if(MESSAGE_TYPE_EARNINGS.equals(type.trim())){
			// ��������������
			jsoninfo = TemplateMes.sendgoodcode(mesinfo);
			System.out.println(jsoninfo);
		}else if(MESSAGE_TYPE_GOODSSTOCKOUT.equals(type.trim())){
			// ��������������
			jsoninfo = TemplateMes.sendgoodsstockout(mesinfo);
			System.out.println(jsoninfo);
		}
		String strUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+at.getToken();
		System.out.println(at.getToken());
		return HttpClientUtils.postJSON(strUrl, jsoninfo);

	}
	// �¶���֪ͨ
	private static String sendCaptcha(Map<String, String> mesinfo) {
		String jsoninfo = "{\"template_id\": \""+NEWORDERS+"\",\"topcolor\": \"#FF0000\",\"touser\": \""+mesinfo.get("openid")+"\",\"url\": \""+mesinfo.get("url")+"\", "
				+ "\"data\":"
				+ "{\"first\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("first")+"\" }," 
				+ "\"orderno\": {\"color\": \"#173177\",\"value\":\""+mesinfo.get("orderno")+"\"}, "
				+ "\"refundno\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("refundno")+"\"},"
				+ "\"refundproduct\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("refundproduct")+"\"},"
				+ "\"remark\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("remark")+"\" }}}";
		return jsoninfo;
	}
	// �����̶�����������
	private static String sendRebate(Map<String,String> mesinfo){
		String jsoninfo = "{\"template_id\": \""+ORDERSSHIPMENTS+"\",\"topcolor\": \"#FF0000\",\"touser\": \""+mesinfo.get("openid")+"\",\"url\": \""+mesinfo.get("url")+"\", "
				+ "\"data\":"
				+ "{\"first\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("first")+"\" },"
				+ "\"orderno\": {\"color\": \"#173177\",\"value\":\""+mesinfo.get("orderno")+"\"}, "
				+ "\"refundno\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("refundno")+"\"},"
				+ "\"refundproduct\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("refundproduct")+"\"},"
				+ "\"remark\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("remark")+"\" }}}";
		return jsoninfo;
	}
	//��������
	private static String sendgoodcode(Map<String ,String > mesinfo){
		String jsoninfo="{\"template_id\": \""+EARNINGS+"\",\"topcolor\": \"#FF0000\",\"touser\": \""+mesinfo.get("openid")+"\",\"url\": \""+mesinfo.get("url")+"\", "
				+ "\"data\":"
				+ "{\"first\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("first")+"\" },"
				+ "\"keyword1\": {\"color\": \"#173177\",\"value\":\""+mesinfo.get("keyword1")+"\"}, "
				+ "\"keyword2\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("keyword2")+"\"},"
				+ "\"keyword3\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("keyword3")+"\"},"
				+ "\"keyword4\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("keyword4")+"\"},"
				+ "\"remark\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("remark")+"\" }}}";
		return jsoninfo;
	}
	//��Ʒȱ������
	private static String sendgoodsstockout(Map<String ,String > mesinfo){
		String jsoninfo="{\"template_id\": \""+GOODSSTOCKOUT+"\",\"topcolor\": \"#FF0000\",\"touser\": \""+mesinfo.get("openid")+"\",\"url\": \""+mesinfo.get("url")+"\", "
				+ "\"data\":"
				+ "{\"first\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("first")+"\" },"
				+ "\"keyword1\": {\"color\": \"#173177\",\"value\":\""+mesinfo.get("keyword1")+"\"}, "
				+ "\"keyword2\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("keyword2")+"\"},"
				+ "\"remark\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("remark")+"\" }}}";
		return jsoninfo;
	}

}
