package com.yuedi.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.CustomerInfoDao;
import com.yuedi.entity.CustomerInfo;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.StudentInfo;
import com.yuedi.entity.TrackRecord;

@Service
@Transactional
public class CustomerInfoService {
	@Autowired
	private CustomerInfoDao customerInfoDao;
	@Autowired
	private CodeSequencyService codeSequencyService;
	
	public Long insertCustomerInfo(CustomerInfo customerInfo) {
		String code = this.codeSequencyService.customerCodeGenerator();
		customerInfo.setCode(code);
		return customerInfoDao.insertCustomerInfo(customerInfo);
	}
	
	public Integer updateCustomerInfoById(CustomerInfo customerInfo){
		return customerInfoDao.updateCustomerInfoById(customerInfo);
	}
	
	public CustomerInfo selectCustomerInfoById(Long id){
		return customerInfoDao.selectCustomerInfoById(id);
	}
	
	public long findCustomerInfoCount(StudentInfo studentInfo){
		return customerInfoDao.findCustomerInfoCount(studentInfo);
	}
	
	public List<CustomerInfo> selectCustomerInfoByLimit(MyPage<CustomerInfo> page){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<CustomerInfo> customerInfoList = customerInfoDao.selectCustomerInfoByLimit(page);
		
		for(int i=0; i<customerInfoList.size(); i++) {
			Date createDateTime = customerInfoList.get(i).getCreateDateTime();
			String createDateTimeString = formatter.format(createDateTime);
			customerInfoList.get(i).setCreateDateTimeString(createDateTimeString);
		}
		return customerInfoList;
	}
	
	public List<TrackRecord> findTrackRecordById(Long customerId){
		return customerInfoDao.findTrackRecordById(customerId);
	}
	
	public Long addTrackRecord(TrackRecord trackRecord){
		return customerInfoDao.addTrackRecord(trackRecord);
	}
	
	public List<CustomerInfo> selectCustomerInfoByFranchiseesId(Long franchiseesId){
		return customerInfoDao.selectCustomerInfoByFranchiseesId(franchiseesId);
	}
}
