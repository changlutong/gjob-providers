package com.jk.model;

import org.springframework.data.annotation.Id;

public class Logs {
	@Id
	private String logId;
	private String clazzName;
	private String methodName;
	private String params;
	private String doituser;

	public String getDoituser() {
		return doituser;
	}

	public void setDoituser(String doituser) {
		this.doituser = doituser;
	}

	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getClazzName() {
		return clazzName;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	@Override
	public String toString() {
		return "Logs [logId=" + logId + ", clazzName=" + clazzName + ", methodName=" + methodName + "]";
	}
	
	
}
