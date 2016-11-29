package com.lida.mongo.weixin.service;

import com.lida.mongo.weixin.message.resp.TextMessage;
import com.lida.mongo.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;


/**
 * ���ķ�����
 */

@Repository("CoreService")
public class CoreService {
    private static Logger log = LoggerFactory.getLogger(CoreService.class);

    private static String emoji(int codePoint) {
        return String.valueOf(Character.toChars(codePoint));
    }

    /**
     * ����΢�ŷ���������
     *
     * @param request
     * @return
     */

    public String processRequest(HttpServletRequest request, HttpServletResponse response) {

        // Ĭ�Ϸ��ص��ı���Ϣ����
        String respMessage = null;
        try {
            String respContent = null;
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            String fromUserName = requestMap.get("FromUserName");
            String toUserName = requestMap.get("ToUserName");
            String msgType = requestMap.get("MsgType");
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
            String openid = requestMap.get("FromUserName");
            String Content = requestMap.get("Content");
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                if ("1".equals(Content)) {
                    respContent = "����ûɶ��,������İ�";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
//            		// ����ͼ����Ϣ
//					NewsMessage newsMessage = new NewsMessage();
//					newsMessage.setToUserName(fromUserName);
//					newsMessage.setFromUserName(toUserName);
//					newsMessage.setCreateTime(new Date().getTime());
//					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
//					newsMessage.setFuncFlag(0);
//					
//					//1
//					List<Article> articleList = new ArrayList<Article>();
//					Article article1 = new Article();
//					article1.setTitle("С��");
//					article1.setDescription("����С��Ϸ");
//					article1.setPicUrl("http://resource.duopao.com/sg/image/20140221170627.jpg");
//					article1.setUrl("http://www.duopao.com/games/info?game_code=g20140212153040377809");
//					//2
//					Article article2 = new Article();
//					article2.setTitle("����");
//					article2.setDescription("����С��Ϸ");
//					article2.setPicUrl("http://resource.duopao.com/sg/image/20140120233041.jpg");
//					article2.setUrl("http://www.duopao.com/games/info?game_code=g20140120233048400063");
//
//					articleList.add(article1);
//					articleList.add(article2);
//					newsMessage.setArticleCount(articleList.size());
//					newsMessage.setArticles(articleList);
//					respMessage = MessageUtil.newsMessageToXml(newsMessage);

                } else if ("2".equals(Content)) {
                    respContent = MessageUtil.youxi();
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                } else if ("��".equals(Content) || "?".equals(Content)) {
                    respContent = MessageUtil.getMainMenu();
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                } else {
                    respContent = "����������";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
            }
            // ͼƬ��Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                String MediaId = requestMap.get("MediaId");
                respContent = "ͼƬ��ַ�ǣ�" + MediaId;
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }
            // ����λ����Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {

            }
            // ������Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "�����˵����в���";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }
            // ��Ƶ��Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "�����˵����в���";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }
            // �¼�����         
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // �¼�����
                String eventType = requestMap.get("Event");
                // ����
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = emoji(0x1F334) + "лл��עСС�" + MessageUtil.getMainMenu();
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
                // �Զ���˵�����¼�
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    String eventKey = requestMap.get("EventKey");
                    if (eventKey.equals("11")) {
                        respContent = emoji(0x274C) + "����ǰ��û�а�������˻�����ֱ�ӻظ�������������ҵ�û������а󶨡�";
                        textMessage.setContent(respContent);
                        respMessage = MessageUtil.textMessageToXml(textMessage);

                    } else if (eventKey.equals("12")) {
                    } else if (eventKey.equals("13")) {
                    } else if (eventKey.equals("14")) {
                    } else if (eventKey.equals("21")) {
                    } else if (eventKey.equals("22")) {
                    } else if (eventKey.equals("23")) {
                    } else if (eventKey.equals("24")) {
                    } else if (eventKey.equals("31")) {
                    } else if (eventKey.equals("32")) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }

}