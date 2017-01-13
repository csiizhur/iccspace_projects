package com.icc.common.model.baidu;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
/**
 * 周边住宅
 * @description
 * @author zhurun
 * @date 2016年12月1日下午4:54:45
 */
public class PlaceResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2957371831958684397L;
	private int officeBuildCount;
	private int residenceCount;
	private int parkBuildCount;
	private HashSet<ResidenceModel> residenceList;
	private List<Map<String,Object>> officeBuildList;
	private List<Map<String,Object>> parkBuildList;
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
	public int getParkBuildCount() {
		return parkBuildCount;
	}
	public void setParkBuildCount(int parkBuildCount) {
		this.parkBuildCount = parkBuildCount;
	}
	public HashSet<ResidenceModel> getResidenceList() {
		return residenceList;
	}
	public void setResidenceList(HashSet<ResidenceModel> residenceList) {
		this.residenceList = residenceList;
	}
	public List<Map<String, Object>> getOfficeBuildList() {
		return officeBuildList;
	}
	public void setOfficeBuildList(List<Map<String, Object>> officeBuildList) {
		this.officeBuildList = officeBuildList;
	}
	public List<Map<String, Object>> getParkBuildList() {
		return parkBuildList;
	}
	public void setParkBuildList(List<Map<String, Object>> parkBuildList) {
		this.parkBuildList = parkBuildList;
	}
	
	
}
