package com.jk.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

public class JSONUtil implements Serializable {
	private static final long serialVersionUID = -4029868444840200552L;
	
	public  static   String   toJson(Object  obj){
		
		 JSONObject.DEFFAULT_DATE_FORMAT="yyyy-MM-dd";
		 
		return  JSON.toJSONString(obj,SerializerFeature.WriteDateUseDateFormat);
	}

}
