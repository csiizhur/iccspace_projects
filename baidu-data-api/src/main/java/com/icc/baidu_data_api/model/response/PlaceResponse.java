package com.icc.baidu_data_api.model.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PlaceResponse implements Serializable{

	private static final long serialVersionUID = -1136225788266744316L;
	
	private int officeBuildCount;
	private int residenceCount;
	private List<Map<String,Object>> residenceList;
	private List<Map<String,Object>> officeBuildList;
	public int getOfficeBuildCount() {
		return officeBuildCount;
	}
	public void setOfficeBuildCount(int officeBuildCount) {
		this.officeBuildCount = officeBuildCount;
	}
	public int getResidenceCount() {
		return residenceCount;
	}
	public void setResidenceCount(int residenceCount) {
		this.residenceCount = residenceCount;
	}
	public List<Map<String, Object>> getResidenceList() {
		return residenceList;
	}
	public void setResidenceList(List<Map<String, Object>> residenceList) {
		this.residenceList = residenceList;
	}
	public List<Map<String, Object>> getOfficeBuildList() {
		return officeBuildList;
	}
	public void setOfficeBuildList(List<Map<String, Object>> officeBuildList) {
		this.officeBuildList = officeBuildList;
	}
	
}
