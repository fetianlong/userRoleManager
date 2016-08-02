package com.yuedi.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.StudentInfoDao;
import com.yuedi.entity.CustomerInfo;
import com.yuedi.entity.GestationInfo;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.StudentInfo;
import com.yuedi.entity.dto.StudentInfoDto;

@Component
@Transactional
public class StudentInfoService {
	@Autowired
	private StudentInfoDao studentInfoDao;
	@Autowired
	private CustomerInfoService customerInfoService;
	@Autowired
	private CodeSequencyService codeSequencyService;
	@Autowired
	private GestationInfoService gestationInfoService;
	
	
	public Long insertStudentInfo(StudentInfoDto studentInfoDto) throws Exception{
		CustomerInfo customerInfo = new CustomerInfo();
		StudentInfo studentInfo = new StudentInfo();
		GestationInfo gestationInfo = new GestationInfo();
		//转换信息
		acceptFormData(studentInfoDto,customerInfo, studentInfo, gestationInfo);
		
		// 添加客户信息
		if(null != customerInfo.getId()){
			customerInfoService.updateCustomerInfoById(customerInfo);
		}else{
			this.customerInfoService.insertCustomerInfo(customerInfo);
		}
		// 添加员工信息
		studentInfo.setCustomerInfoId(customerInfo.getId());
		String studentNo = this.codeSequencyService.employeeCodeGenerator(studentInfo.getFranchiseesId());
		studentInfo.setStudentNo(studentNo);
		studentInfo.setCustomerInfoCode(customerInfo.getCode());
		this.studentInfoDao.insertStudentInfo(studentInfo);
		// 添加孕期信息
		gestationInfo.setStudentInfoId(studentInfo.getId());
		gestationInfoService.insertGestationInfo(gestationInfo);
		
		return studentInfo.getId();
	}

	public Integer updateStudentInfoById(StudentInfoDto studentInfoDto) throws Exception{
		CustomerInfo customerInfo = new CustomerInfo();
		StudentInfo studentInfo = new StudentInfo();
		GestationInfo gestationInfo = new GestationInfo();
		
		//转换信息
		acceptFormData(studentInfoDto,customerInfo, studentInfo, gestationInfo);
		
		customerInfoService.updateCustomerInfoById(customerInfo);
		gestationInfoService.updateGestationInfoById(gestationInfo);
		
		return studentInfoDao.updateStudentInfoById(studentInfo);
	}

	public StudentInfoDto selectStudentInfoById(Long id){
		return studentInfoDao.selectStudentInfoById(id);
	}

	public List<StudentInfo> selectStudentInfoByFranchiseesId(Long franchiseesId){
		return studentInfoDao.selectStudentInfoByFranchiseesId(franchiseesId);
	}

	public List<StudentInfoDto> selectStudentInfoByLimit(MyPage<StudentInfo> page){
		return studentInfoDao.selectStudentInfoByLimit(page);
	}

	public Long selectStudentInfoTatolByStudentInfoPageDto(StudentInfo studentInfo){
		return studentInfoDao.selectStudentInfoTatolByStudentInfoPageDto(studentInfo);
	}
	
	/**
	 * 接收新增、编辑学员信息
	 * 
	 * @throws ParseException
	 */
	private void acceptFormData(StudentInfoDto studentInfoDto,CustomerInfo customerInfo, 
			StudentInfo studentInfo, GestationInfo gestationInfo) throws ParseException {
		// 客户信息
		if(studentInfoDto.getCustomerInfoId() != null) {
			customerInfo.setId(studentInfoDto.getCustomerInfoId());
		}
		customerInfo.setFranchiseesId(studentInfoDto.getFranchiseesId());
		customerInfo.setName(studentInfoDto.getName());
		customerInfo.setNickName(studentInfoDto.getNickName());
		customerInfo.setSex(studentInfoDto.getSex());
		customerInfo.setMobilePhone(studentInfoDto.getMobilePhone());
		customerInfo.setEmail(studentInfoDto.getEmail());
		customerInfo.setBornDate(studentInfoDto.getBornDate());
		customerInfo.setCardCode(studentInfoDto.getCardCode());
		customerInfo.setSource(1);
		customerInfo.setIsStudent(true);
		customerInfo.setCreaterId(studentInfoDto.getCreateId());
		customerInfo.setCreateDateTime(studentInfoDto.getCreateTimeDate());
		customerInfo.setCreaterName(studentInfoDto.getCreaterName());
		
		// 学员信息
		if(studentInfoDto.getId() != null) {
			studentInfo.setId(studentInfoDto.getId());
		}
		studentInfo.setFranchiseesId(studentInfoDto.getFranchiseesId());
		studentInfo.setEntranceDate(studentInfoDto.getEntranceDate());
		studentInfo.setEmail(studentInfoDto.getEmail());
		studentInfo.setMobilePhone(studentInfoDto.getMobilePhone());
		studentInfo.setFixedPhone(studentInfoDto.getFixedPhone());
		studentInfo.setQqAccount(studentInfoDto.getQqAccount());
		studentInfo.setMicroChartAccount(studentInfoDto.getMicroChartAccount());
		studentInfo.setProvince(studentInfoDto.getProvince());
		studentInfo.setCity(studentInfoDto.getCity());
		studentInfo.setDistrict(studentInfoDto.getDistrict());
		studentInfo.setSpecificationAddress(studentInfoDto.getSpecificationAddress());
		studentInfo.setContacts(studentInfoDto.getContacts());
		studentInfo.setContactsPhone(studentInfoDto.getContactsPhone());
		studentInfo.setSaler(studentInfoDto.getSaler());
		studentInfo.setCreaterId(studentInfoDto.getCreateId());
		studentInfo.setCreaterName(studentInfoDto.getCreaterName());
		
		// 孕期信息
		if(studentInfoDto.getGestationInfoId() != null) {
			gestationInfo.setId(studentInfoDto.getGestationInfoId());
		}
		gestationInfo.setLastMensesDate(studentInfoDto.getLastMensesDate());
		gestationInfo.setMensesDays(studentInfoDto.getMensesDays());
		gestationInfo.setExpectedDate(studentInfoDto.getExpectedDate());
		gestationInfo.setRellyBornDate(studentInfoDto.getRellyBornDate());
		gestationInfo.setBodySize(studentInfoDto.getBodySize());
		gestationInfo.setBornProvince(studentInfoDto.getBornProvince());
		gestationInfo.setBornCity(studentInfoDto.getBornCity());
		gestationInfo.setBornHospital(studentInfoDto.getBornHospital());
		gestationInfo.setParturitionMode(studentInfoDto.getParturitionMode());
		gestationInfo.setCreaterId(studentInfoDto.getCreateId());
		gestationInfo.setCreaterName(studentInfoDto.getCreaterName());
	}
}
