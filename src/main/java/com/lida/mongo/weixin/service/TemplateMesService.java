package com.lida.mongo.weixin.service;


public class TemplateMesService {
    /*
	*//**
     * ������¶�������ģ����Ϣ
     *//*
	public void neworders(String order_sn){
		//��ȡ������Ϣ
		YlOrderInfo ylOrderInfo=ylOrderInfoDao.neworder(order_sn);
		//��ȡ�󶨸���������ҵ��Ա��Ϣ
		List<YeEtWxBind> list=yeEtWxBindDao.getopenid(ylOrderInfo.getService_id());
		String goodsnumber=String.valueOf(ylOrderInfo.getGoods_number());
		System.out.println(goodsnumber);
		//ʱ��ת��
		String addtime=yeEtWxBindDao.TimeStamp2Date(ylOrderInfo.getAdd_time());
		for(int i=0;i<list.size();i++){
			//��װģ����Ϣ
			Map<String, String> mesinfo = new HashMap<String, String>();
			mesinfo.put("openid", "oETn-sqq2T92ncxeAR_Xvdd12L68");//΢��openid oETn-sqq2T92ncxeAR_Xvdd12L68
			mesinfo.put("url", "http://m.ysh365.com");//����Ժ���ת�����ӵ�ַ
			mesinfo.put("first", "���ã����̳����յ�һ���¶����ȴ����");
			mesinfo.put("orderno", order_sn);//������
			mesinfo.put("refundno",goodsnumber);//��Ʒ����
			mesinfo.put("refundproduct", ylOrderInfo.getOrder_amount()+"Ԫ");//��Ʒ���
			mesinfo.put("remark", "�µ�ʱ��:"+addtime+","+"�����������µ磬С�ƽ���һʱ��Ϊ������");
			try {
				String res=	templateMes.sendWXMes("neworders", mesinfo);
				System.out.println(res);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	*//**
     * �����̷�������
     *//*
	public void ordersshipments(String order_sn) {
		Map<String, String> mesinfo = new HashMap<String, String>();
		//��ȡ�Ӷ�����Ϣ
		YeOrderInfo yeOrderInfo=yeOrderInfoDao.getyeordersn(order_sn);
		//��ѯ���������󶨵Ļ�Ա
		List<YeEtWxBind> list=yeEtWxBindDao.getopenid(yeOrderInfo.getSupply_id());
		//ʱ��ת��
		String addtime=yeEtWxBindDao.TimeStamp2Date(yeOrderInfo.getAdd_time());
		for(int i=0;i<list.size();i++){
			mesinfo.put("openid", list.get(i).getOpenid());//΢��openid
			mesinfo.put("url", "http://m.ysh365.com");//����Ժ���ת�����ӵ�ַ
			mesinfo.put("first", "���ã��������ѷ������뼰ʱ�ջ�Ŷ~");
			mesinfo.put("orderno", order_sn);//������
			mesinfo.put("refundno", yeOrderInfo.getOrder_amount()+"");//���ѽ��
			mesinfo.put("refundproduct", addtime);//ʱ��
			mesinfo.put("remark", "�����������µ�~~��ֱ����΢�����ԣ�С�ƽ���һʱ��Ϊ������");
			try {
				String res=	templateMes.sendWXMes("ordersshipments", mesinfo);
				System.out.println(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	*//**
     *�������������
     *//*
	public void Earnings(String order_sn) {
		YeOrderInfo yeOrderInfo= yeOrderInfoDao.getyeordersn(order_sn);
		//��ȡ�󶨸���������ҵ��Ա��Ϣ
		List<YeEtWxBind> list=yeEtWxBindDao.getopenid(yeOrderInfo.getSupply_id());
		//ʱ��ת��
		String addtime=yeEtWxBindDao.TimeStamp2Date(yeOrderInfo.getAdd_time());
		for(int i=0;i<list.size();i++){
			Map<String, String> mesinfo = new HashMap<String, String>();
			mesinfo.put("openid", "oETn-sqq2T92ncxeAR_Xvdd12L68");//΢��openid
			mesinfo.put("url", "http://m.ysh365.com");//����Ժ���ת�����ӵ�ַ
			mesinfo.put("first", "�𾴵��û�����,����һ�����涩��");
			mesinfo.put("keyword1", order_sn);//������
			mesinfo.put("keyword2", yeOrderInfo.getOrder_amount()+"");//���ѽ��
			mesinfo.put("keyword3", addtime);//֧��ʱ��
			mesinfo.put("keyword4", "");//�������
			mesinfo.put("remark", "�����������µ�~~��ֱ����΢�����ԣ�С�ƽ���һʱ��Ϊ������");
			try {
				String res=	templateMes.sendWXMes("earnings", mesinfo);
				System.out.println(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	*//**
     * ��������Ʒȱ��Ԥ��
     *//*
	public void goodsstockout(int product_id) {
		Map<String, String> mesinfo = new HashMap<String, String>();
		YeEtSupplyProducts yeEtSupplyProducts=yeEtSupplyProductsDao.stock(product_id);
		mesinfo.put("openid", "oETn-sqq2T92ncxeAR_Xvdd12L68");//΢��openid
		mesinfo.put("url", "http://m.ysh365.com");//����Ժ���ת�����ӵ�ַ
		mesinfo.put("first", "�𾴵Ĺ����̻�Ա����,����һ����Ʒ��汨��");
		mesinfo.put("keyword1", yeEtSupplyProducts.getGoods_name());//��Ʒ����
		mesinfo.put("keyword2", "ʣ����"+yeEtSupplyProducts.getStock());//��Ч��
		mesinfo.put("remark", "�뼰ʱ��ӿ��,�����������µ�~~��ֱ����΢�����ԣ�С�ƽ���һʱ��Ϊ������");
		try {
			String res=	templateMes.sendWXMes("goodsstockout", mesinfo);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	*/

}
