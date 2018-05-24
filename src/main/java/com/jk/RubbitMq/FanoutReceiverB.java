package com.jk.RubbitMq;

import com.jk.model.Company;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.jk.util.EmailUtil.sendHtmlMail;

@Component
@RabbitListener(queues = "fanout.B")
public class FanoutReceiverB {


    @RabbitHandler
    public void process(List<Company> list) throws UnsupportedEncodingException, MessagingException {

        String email = list.get(0).getEmail();
        String companyname = list.get(0).getCompanyname();
        sendHtmlMail(email,"注册信息",companyname+", 你好,龙盾招聘提醒你,你刚刚登陆了你的账户,请确认是否为本人操作!");
        System.out.println("fanout Receiver B: " + email);
    }

}
