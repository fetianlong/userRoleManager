package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.CustomerInfo;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.StudentInfo;
import com.yuedi.entity.TrackRecord;

@MyBatisRepository
public interface CustomerInfoDao {
	/**
	 * 新增客户信息
	 * 
	 * @param customerInfo
	 * @return
	 */
	public Long insertCustomerInfo(CustomerInfo customerInfo);
	/**
	 * 修改客户信息
	 * 
	 * @param customerInfo
	 * 必须属性：Code,	Name, Nickname, Sex, MobilePhone, Email, BornDate, CardCode, Source
	 * @return
	 */
	public Integer updateCustomerInfoById(CustomerInfo customerInfo);
	/**
	 * 查询客户信息
	 * 
	 * @param id 客户ID
	 * @return
	 */
	public CustomerInfo selectCustomerInfoById(Long id);
	
	/**
	 * 查询该机构下的客户信息总数
	 * @param studentInfoPageDto
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2015年4月22日 下午4:23:43
	 */
	public long findCustomerInfoCount(StudentInfo studentInfo);
	
	/**
	 * 分页获取当前机构下的所有客户信息
	 * @param
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2015年4月22日 下午4:23:49
	 */
	public List<CustomerInfo> selectCustomerInfoByLimit(MyPage<CustomerInfo> page);
	
	/**
	 * 获取当前客户的跟踪记录
	 * @param customerId	客户ID
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2015年4月23日 下午4:06:24
	 */
	public List<TrackRecord> findTrackRecordById(Long customerId);
	
	/**
	 * 新增跟踪记录
	 * @param trackRecord
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2015年4月23日 下午4:34:01
	 */
	public Long addTrackRecord(TrackRecord trackRecord);
	/**
	 * 根据加盟商id获得潜在客户
	 * @param customerId
	 * @return
	 */
	public List<CustomerInfo> selectCustomerInfoByFranchiseesId(Long franchiseesId);
}
