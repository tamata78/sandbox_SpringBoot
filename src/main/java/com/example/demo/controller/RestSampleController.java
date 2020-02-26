package com.example.demo.controller;

import java.io.IOException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Item;
import com.example.demo.service.RestSampleService;

// @Controller
@RestController // Bodyをレスポンスとする
public class RestSampleController {
	static Logger logger = LoggerFactory.getLogger(RestSampleController.class);

	@Autowired
	private RestSampleService restSampleService;

	@Autowired
	private Environment env;

	@RequestMapping(value="/test", method=RequestMethod.GET, headers="Accept=application/*", params="id=002")
	public String test() {
		return "test";
	}

	// リクエストメソッドの設定、必須パラメータの取得
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public Item getItemList(@RequestParam("userId") String userId, // 必須でないときは、引数にrequired=falseを追加
	@RequestParam("seq") String seq) {
		return new Item();
	}

	// 戻り値をそのままレスポンスのボディの内容とする
	@RequestMapping("/directWrite")
	public void directWrite(@RequestBody String body, Writer writer) throws IOException {
	   writer.write(body);
	}

	// View に対して渡す Model も同時に返す
	@RequestMapping("/modelAndView")
	public ModelAndView modelAndView(ModelAndView mav) {
	    mav.addObject("model", "model");
	    mav.setViewName("test");
	    return mav;
	}

	// mail送付テスト用
	@RequestMapping(value = "/sendTestMail", method = RequestMethod.POST)
	public void sendTestMail() {
		Boolean result = true;

		if (result) {
			restSampleService.sendMailComplete();
		} else {
			restSampleService.sendMailFailed();
		}
	}

}

