package com.domain.common;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import org.springframework.util.StringUtils;

public class Result<T> implements Serializable {

	private static final long serialVersionUID = 6339343661171742854L;

	public static final String MESSAGE_SERVER_SUCCESS = "msg.operation.success";

	public static final String MESSAGE_SERVER_SUCCESS_DEFAULT = "Operation Successful";

	@ApiModelProperty(value = "处理状态（success or error）", required = true)
	private String status = "success";

	@ApiModelProperty(value = "消息提示")
	private String message = MESSAGE_SERVER_SUCCESS;

	@ApiModelProperty(value = "数据", required = true)
	private T data = null;

	public Result() {

		this.message = getLocaleMessage(message);
	}

	public Result(T data) {

		this.data = data;
		this.message = getLocaleMessage(message);
	}

	public Result(T data, String message, Object... args) {

		this.data = data;
		this.message = getLocaleMessage(message, args);
	}

	public static String getLocaleMessage(String message, Object... args) {

		try {
			if (StringUtils.hasText(message)) {
				message = I18nUtils.getMessage(message, args, message);
			} else {
				message = I18nUtils.getMessage(MESSAGE_SERVER_SUCCESS, null,
						MESSAGE_SERVER_SUCCESS_DEFAULT);
			}
		} catch (Exception e) {
			// do nothing.
		}
		return message;
	}

	public static String getLocaleMessageWithDefault(String message,
			String defaultMessage) {

		try {
			if (StringUtils.hasText(message)) {
				message = I18nUtils.getMessage(message, null, defaultMessage);
			} else {
				message = I18nUtils.getMessage(MESSAGE_SERVER_SUCCESS, null,
						MESSAGE_SERVER_SUCCESS_DEFAULT);
			}
		} catch (Exception e) {
			// do nothing.
		}
		return message;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

	public T getData() {

		return data;
	}

	public void setData(T data) {

		this.data = data;
	}
}
