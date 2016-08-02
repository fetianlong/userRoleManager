package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.LoginRecord;
import com.yuedi.entity.MyPage;

@MyBatisRepository
public interface LoginRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginRecord record);

    int insertSelective(LoginRecord record);

    LoginRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginRecord record);

    int updateByPrimaryKey(LoginRecord record);

	List<LoginRecord> selectLoginRecordLimit(MyPage<LoginRecord> page);
}