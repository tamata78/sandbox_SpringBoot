package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Autowired
    private MailSender sender;
    
	@Autowired
	private Environment env;
    
	static Logger logger = LoggerFactory.getLogger(MailSenderService.class);

    public void sendMail(String subject, String textBody) {
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setFrom(env.getProperty("mail.xxx.from"));
        msg.setTo(env.getProperty("mail.xxx.to"));
        msg.setSubject(subject); //タイトルの設定
        msg.setText(textBody); //本文の設定

        sender.send(msg);
        
        logger.info(subject + "：メール送信しました");
    }

}
