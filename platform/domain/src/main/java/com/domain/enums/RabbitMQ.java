package com.domain.enums;

import com.domain.interfaces.QueueUtil;

public enum RabbitMQ implements QueueUtil{
	
	/**
	 * direct
	 */
	SYNC_DATA("sync_data"),
	
	/**
	 * topic
	 */
	
	NOTICE_A("notice_a","notice", RabbitMQ.TOPIC),
	
	NOTICE_B("notice_b","notice", RabbitMQ.TOPIC),
	
	REAULT("result"),
	;
	
	public static final String DIRECT = "dir";
	public static final String TOPIC = "topic";
	public static final String FANOUT = "fanout";
	public static final String HEADERS = "headers";
	
	/**
	 * exchange
	 */
	public static final String QUE_EXCHANGE = "que_exchange";
	public static final String TOPIC_EXCHANGE = "topic_exchage";
	
	private String name;
	
	private String key;
	
	private String type;
	
	RabbitMQ(String name){
		this.name = name;
		this.type = DIRECT;
		this.key = name;
	}
	
	RabbitMQ(String name,String key,String type){
		this.name = name;
		this.type = type;
		this.key = key;
	}
	
	public String getExchage(){
		switch(type){
			case DIRECT:
				return QUE_EXCHANGE;
			case TOPIC :
				return TOPIC_EXCHANGE;
			default:
				return null;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
