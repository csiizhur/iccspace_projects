package com.icc.dao;

import com.icc.entity.EntityAccessLog;
import com.icc.entity.User;

public interface EntityAccessLogDao extends BaseDao<EntityAccessLog, Long>  {

	public void createwe(EntityAccessLog ea);
	
	
	public User queryUser();
}
