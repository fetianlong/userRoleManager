package com.yuedi.dao.fm;

import java.util.List;

import com.yuedi.dao.MyBatisRepository;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.Srespiration;

@MyBatisRepository
public interface SrespirationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Srespiration record);

    int insertSelective(Srespiration record);

    Srespiration selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Srespiration record);

    int updateByPrimaryKey(Srespiration record);
    
    List<Srespiration> selectCountSrespiration(MyPage<Srespiration> page);
}