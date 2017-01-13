package com.icc.service;

import java.util.List;
import java.util.Map;

public interface PlaceService {

	public List<Map<String,String>> queryUserExpectBusiness(String rentUserId);
}
