package controller;

import java.io.Serializable;
import java.util.List;

public class DataList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3658134004338137133L;
	
	private List<String> data;

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}
	
	
}
