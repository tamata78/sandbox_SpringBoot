package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class RestSampleService {
	@Autowired
	private MailSenderService mailSenderService;
    
	@Autowired
	private Environment env;
    
	static Logger logger = LoggerFactory.getLogger(RestSampleService.class);
	
    public void sendMailComplete() {
		String subject = "完了_月別集計バッチ処理";
		StringBuilder builder = new StringBuilder();
		builder.append("月別集計バッチ処理が正常に完了しました。");
		builder.append("1件登録、実行時間100秒");
		String body = builder.toString();
		
		mailSenderService.sendMail(subject, body);
		
        logger.info("月別集計バッチ処理が正常に完了しました。");
    }

	public void sendMailFailed() {
		String subject = "異常終了_月別集計バッチ処理";
		StringBuilder builder = new StringBuilder();
		builder.append("月別集計バッチ処理が異常終了しました。");
		builder.append("内容です。");
		String body = builder.toString();
		
		mailSenderService.sendMail(subject, body);
		
        logger.info("月別集計バッチ処理が異常終了しました。");
		
	}

}
