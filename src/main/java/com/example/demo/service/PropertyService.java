package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.bean.PropertyBean;

public class PropertyService {

    @Autowired
    private PropertyBean propBean;

    /**
     * プロパティーBeanからRedisリトライ上限回数を取得する
     * @return
     */
    public int getPropBeanRedisRetryLimitCount() {
    	return propBean.getRedisRetryLimitCount();
    }
}
