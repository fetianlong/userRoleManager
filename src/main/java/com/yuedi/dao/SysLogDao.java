package com.yuedi.dao;

import com.yuedi.entity.SysLog;

@MyBatisRepository
public interface SysLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}