package model;

import java.io.Serializable;

public class ResultResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 363587994880042532L;

	private boolean flg;
	private String message;
	private Object data;
	
	public ResultResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public ResultResponse(boolean flg, String message) {
		super();
		this.flg = flg;
		this.message = message;
	}

	public boolean isFlg() {
		return flg;
	}
	public void setFlg(boolean flg) {
		this.flg = flg;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
