package com.icc.baidu_data_webapi.model;

import java.util.ArrayList;
import java.util.List;

public class ResultTest {

	public static void main(String[] args) {
		Result<OrderCoupon> result = new ResultSupport<OrderCoupon>();
		List<OrderCoupon> orderCoupons=new ArrayList<OrderCoupon>();
		result.setModels(orderCoupons);
		result.setTotalRows(orderCoupons.size());
		
		System.err.println(result);
	}
}
