package top.yh.email.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Properties;

/**
 * @author yu
 */
@SuppressWarnings("all")
public class SendEmailUtil {
    /**
     * 发送邮件
     *
     * @param to    收件人
     * @param text  邮件文本内容
     * @param title 邮件标题
     * @param user 哪个邮箱发送
     * @param password 密码
     * @return
     */
    public static Boolean sendEmail(String to, String text, String title, String user, String password) {
        Properties prop = new Properties();
        // 开启debug调试，以便在控制台查看
        prop.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");
        try {
            // 开启SSL加密，否则会失败
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);

            // 创建session
            Session session = Session.getInstance(prop);
            // 通过session得到transport对象
            Transport ts = session.getTransport();
            // 连接邮件服务器：邮箱类型，帐号，16位授权码
            ts.connect("smtp.qq.com",user,password);
            // 创建邮件
            Message message = createSimpleMail(session, to, text, title,user);
            // 发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (Exception e) {
            //发送失败
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param session
     * @param to      收件人
     * @param text    邮件文本内容
     * @param title   邮件标题
     * @param user 发件人
     * @return
     * @throws Exception
     */
    public static MimeMessage createSimpleMail(Session session, String to, String text, String title,String user) throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress(user));
        // 指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // 邮件的标题
        message.setSubject(title);
        // 邮件的文本内容
        message.setContent(text, "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }
}
