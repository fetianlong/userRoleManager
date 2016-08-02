package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.ResourceInfo;
import com.yuedi.entity.Series;

@MyBatisRepository
public interface ResourceInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(ResourceInfo record);

    int insertSelective(ResourceInfo record);

    ResourceInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResourceInfo record);

    int updateByPrimaryKey(ResourceInfo record);

	List<Series> selectSeriesByBrandIdAndCategory(Series series);

	ResourceInfo selectSeriesNameById(ResourceInfo resourceInfo);
}