package com.icc.dao;

import com.icc.entity.EntrustLease;

public interface EntrustLeaseDao {

	//insert
	public int insertEntrustLease(EntrustLease entrustLease);
	//exist
	public int queryEntrustLeaseExist(String shopsId);
}
