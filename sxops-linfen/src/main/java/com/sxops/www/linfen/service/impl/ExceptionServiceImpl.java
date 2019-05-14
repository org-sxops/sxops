//package com.test.ep.ip.service.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.Lists;
//import NetUtil;
//import DateStyle;
//import DateUtils;
//import LogMessage;
//import ExceptionLog;
//import ExceptionLogService;
//import ExceptionService;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.net.InetAddress;
//import java.util.Date;
//import java.util.List;
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
///**
// * <p> Description: [异常处理服务]</p>
// * Created on: 2017/11/2 16:07
// *
// * @author <a href="mailto: liruifeng@sxops.com">尹归晋</a>
// * @version 1.0
// */
//@Component
//public class ExceptionServiceImpl implements ExceptionService {
//
//    protected static final Logger LOGGER = LoggerFactory.getLogger(ExceptionServiceImpl.class);
//
//    @Value("${xiaoju.meetingRoom.serverName}")
//    private String  serverName;
//
//    @Resource
//    private ExceptionLogService exceptionLogService;
//
//
//    private String mailTemplate = "操作人：%s\n" +
//            "系统：%s\n" +
//            "URL：%s\n" +
//            "IP：%s\n" +
//            "操作时间：%s\n" +
//            "错误描述：%s\n" +
//            "浏览器信息：%s\n" +
//            "错误详情：%s";
//
//    @Override
//    public void handler(User user, String exceptionMsg, Exception e, HttpServletRequest request) {
//        try {
//            // 如果异常时用来提示的，那么什么都不做
//            if(e instanceof WarnException){
//                return;
//            }
//            LOGGER.error(LogMessage.getNew().add(e).toString());
//            ExceptionLog exceptionLog = new ExceptionLog();
//            exceptionLog.setServerName(serverName);
//            exceptionLog.setOperator(serverName);
//            exceptionLog.setDescription(exceptionMsg);
//            if (request != null) {
//                exceptionLog.setOperatorIp(NetUtil.getIpAddr(request));
//                exceptionLog.setUri(request.getRequestURI());
//                exceptionLog.setBrowerMessage(request.getHeader("User-Agent"));
//            }
//            if (user != null) {
//                exceptionLog.setOperatorCode(StringUtils.defaultString(user.getEmplId()));
//            }
//            //输出错误堆栈
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            PrintStream ps = new PrintStream(outputStream);
//            e.printStackTrace(ps);
//            exceptionLog.setDetail(outputStream.toString());
//            exceptionLog.setCreateTime(new Date());
//            exceptionLog.setHostName(InetAddress.getLocalHost().getHostName());
//            exceptionLogService.insert(exceptionLog);
//            sendMail(exceptionLog);
//        } catch (Exception e1) {
//            LOGGER.warn(LogMessage.getNew().add("存储异常日志失败", e1.getMessage()).toString());
//        }
//    }
//
//    /**
//     * 发送报警邮件
//     * @param exceptionLog
//     */
//    @Override
//    public void sendMail(ExceptionLog exceptionLog){
//        Dictionary dictionary = dictionaryService.selectOneByClassCode(Dictionary.CLASS_CODE_SYSTEMERRORUSER);
//        String errorEmailGroup = dictionary.getDicNameZh();
//        List<User> groupUserList = userGroupService.getGroupUserList(Long.valueOf(errorEmailGroup));
//        List<String> emailList = Lists.newArrayList();
//        emailList.add(Const.MT_EMAIL);
//        if (CollectionUtils.isNotEmpty(groupUserList)) {
//            for (User item : groupUserList) {
//                emailList.add(item.getEmailAddr());
//            }
//        }
//        User currentUser = userService.queryByCode(exceptionLog.getOperatorCode());
//        String mailText = String.format(mailTemplate,currentUser==null?"系统调用":currentUser.getLdap(),exceptionLog.getServerName(), exceptionLog.getUri(), exceptionLog.getOperatorIp(),
//                DateUtils.DateToString(exceptionLog.getCreateTime(), DateStyle.YYYY_MM_DD_HH_MM_SS), exceptionLog.getDescription()==null?"":exceptionLog.getDescription(), exceptionLog.getBrowerMessage(), exceptionLog.getDetail());
//        MailInfo mailInfo = new MailInfo();
//        mailInfo.setSubject("会议室系统异常");
//        mailInfo.setBodyType(BodyType.Text);
//        mailInfo.setBodyText(mailText);
//        mailInfo.setTo(emailList.toArray(new String[]{}));
//        try {
//            mailUtils.doSend(mailInfo);
//        }catch (Exception e) {
//            LOGGER.error(LogMessage.getNew().add(e).toString());
//            String mailBusinessId = "EXCEPTION_NOTIFY:" +exceptionLog.getId();
//            mailCompensationService.collectException(mailBusinessId, "doSend", JSON.toJSONString(mailInfo), e);
//        }
//    }
//
//
//    @Override
//    public void handler(String exceptionMsg, Exception e, HttpServletRequest request) {
//        User currentUser = userService.getCurrentUser();
//        handler(currentUser, exceptionMsg, e,request);
//    }
//
//    @Override
//    public void handler(String userCode, String exceptionMsg, Exception e, HttpServletRequest request) {
//        User currentUser = userService.queryByLdap(userCode, RolesInfo.NO_ROLE_NAME);
//        handler(currentUser, exceptionMsg, e,request);
//    }
//
//    @Override
//    public void handler(String exceptionMsg, Exception e) {
//        handler(exceptionMsg, e, null);
//    }
//}
