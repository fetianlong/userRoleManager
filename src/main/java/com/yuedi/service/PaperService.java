package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.PaperDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Paper;

@Component
@Transactional
public class PaperService {
	@Autowired
	private PaperDao paperDao;

	public int deletePaperByPrimaryKey(Integer id) {
		return paperDao.deletePaperByPrimaryKey(id);
	}

	public int insertPaper(Paper record) {
		return paperDao.insertPaper(record);
	}

	public Paper selectPaperByPrimaryKey(Integer id) {
		return paperDao.selectPaperByPrimaryKey(id);
	}

	public int updatePaperByPrimaryKey(Paper record) {
		return paperDao.updatePaperByPrimaryKey(record);
	}

	public List<Paper> selectPaperLimit(MyPage<Paper> page) {
		return paperDao.selectPaperLimit(page);
	}
}