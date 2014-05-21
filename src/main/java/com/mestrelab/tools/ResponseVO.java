package com.mestrelab.tools;

public class ResponseVO {
	private Integer typeError = new Integer(-1);
	private TypeMessage typeMessage;
	private String message;


	public ResponseVO() {
		super();
	}

	public ResponseVO(int error, String mes,TypeMessage type) {
		setTypeError(error);
		setMessage(mes);		
		setTypeMessage(type);
	}
	

	public Integer getTypeError() {
		return typeError;
	}

	public void setTypeError(Integer typeError) {
		this.typeError = typeError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public TypeMessage getTypeMessage() {
		return typeMessage;
	}

	public void setTypeMessage(TypeMessage typeMessage) {
		this.typeMessage = typeMessage;
	}

}
