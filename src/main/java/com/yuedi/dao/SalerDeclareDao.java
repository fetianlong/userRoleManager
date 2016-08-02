package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.SalerDeclare;

@MyBatisRepository
public interface SalerDeclareDao {
	//申请二维码
	public long insertSalerDeclare(SalerDeclare salerDeclare);
	//分页
	public List<SalerDeclare> selectSalerDeclareLimit(MyPage<SalerDeclare> page);
	//分页(总部查询)
	public List<SalerDeclare> selectCenterSalerLimit(MyPage<SalerDeclare> page);
	//修改二维码申报状态
	public int updatesalerDeclareById(Long id);
	
	public SalerDeclare selectSalerDeclareById(Long salerDeclareId);
}
