package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.StudentInfo;
import com.yuedi.entity.dto.StudentInfoDto;

@MyBatisRepository
public interface StudentInfoDao {
	/**
	 * 新增学员信息
	 * 
	 * @param studentInfo
	 * @return
	 */
	public Long insertStudentInfo(StudentInfo studentInfo);
	/**
	 * 修改学员信息
	 * 
	 * @param studentInfo
	 * @return
	 */
	public Integer updateStudentInfoById(StudentInfo studentInfo);
	/**
	 * 查询学员信息
	 * 
	 * @param id
	 * @return
	 */
	public StudentInfoDto selectStudentInfoById(Long id);
	/**
	 * 查询学员信息
	 * 
	 * @param franchiseesId 加盟商ID
	 * @return
	 */
	public List<StudentInfo> selectStudentInfoByFranchiseesId(Long franchiseesId);
	/**
	 * 查询学员信息
	 * 
	 * @param studentInfoPageDto
	 * 必须属性：franchiseesId, startRow, pageSize
	 * @return
	 */
	public List<StudentInfoDto> selectStudentInfoByLimit(MyPage<StudentInfo> page);
	/**
	 * 查询学员信息总条数
	 * 
	 * @param studentInfoPageDto
	 * 必须属性：franchiseesId
	 * @return
	 */
	public Long selectStudentInfoTatolByStudentInfoPageDto(StudentInfo studentInfo);
}
