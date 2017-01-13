package com.icc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseDao<T, K> {

    T read(@Param(value="id")K id);

    List<T> findAll();

    int create(T t);

    int update(T t);

    void delete(long id);
    
    /**
     * 需要使用乐观锁更新使用此方法
     * @param t
     * @return
     */
    int updateForOptimisticLocking(T t);
}
