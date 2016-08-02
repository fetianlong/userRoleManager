package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.GestationInfo;

@MyBatisRepository
public interface GestationInfoDao {
	/**
	 * 新增孕期信息
	 * 
	 * @param gestationInfo
	 * @return
	 */
	Long insertGestationInfo(GestationInfo gestationInfo);
	/**
	 * 修改孕期信息
	 * 
	 * @param gestationInfo
	 * 必须属性：LastMensesDate, MensesDays, ExpectedDate, RellyBornDate, BodySize, BornProvince, BornCity, BornHospital, ParturitionMode, OtherSpecification 
	 * @return
	 */
	Integer updateGestationInfoById(GestationInfo gestationInfo);
	/**
	 * 查询孕期信息
	 * 
	 * @param id ID
	 * @return
	 */
	GestationInfo selectGestationInfoById(Long id);
	/**
	 * 查询孕期信息
	 * 
	 * @param studentsInfoId 学员ID
	 * @return
	 */
	List<GestationInfo> selectGestationInfoByStudentInfoId(Long studentsInfoId);
}
