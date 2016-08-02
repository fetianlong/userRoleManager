package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.SalerDeclareDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.SalerDeclare;

@Service
@Transactional
public class SalerDeclareService {
	
	@Autowired
	private SalerDeclareDao salerDeclareDao;

	public long insertSalerDeclare(SalerDeclare salerDeclare) {
		return salerDeclareDao.insertSalerDeclare(salerDeclare);
	}

	public List<SalerDeclare> selectSalerDeclareLimit(MyPage<SalerDeclare> page) {
		return salerDeclareDao.selectSalerDeclareLimit(page);
	}


	public List<SalerDeclare> selectCenterSalerLimit(MyPage<SalerDeclare> page) {
		return salerDeclareDao.selectCenterSalerLimit(page);
	}

	public int updatesalerDeclareById(Long id) {
		return salerDeclareDao.updatesalerDeclareById(id);
	}
	
	public SalerDeclare selectSalerDeclareById(Long salerDeclareId) {
		return salerDeclareDao.selectSalerDeclareById(salerDeclareId);
	}

}
