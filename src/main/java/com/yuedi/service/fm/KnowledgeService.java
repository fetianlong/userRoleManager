package com.yuedi.service.fm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.fm.KnowledgeDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.Knowledge;

@Component
@Transactional
public class KnowledgeService {
	@Autowired
	private KnowledgeDao knowledgeDao;

	public int deleteKnowledgeByPrimaryKey(Integer id) {
		return knowledgeDao.deleteKnowledgeByPrimaryKey(id);
	}

	public int insertKnowledge(Knowledge record) {
		return knowledgeDao.insertKnowledge(record);
	}

	public Knowledge selectKnowledgeByPrimaryKey(Integer id) {
		return knowledgeDao.selectKnowledgeByPrimaryKey(id);
	}

	public int updateKnowledgeByPrimaryKey(Knowledge record) {
		return knowledgeDao.updateKnowledgeByPrimaryKey(record);
	}

	public List<Knowledge> selectKnowledgeLimit(
			MyPage<Knowledge> page) {
		return knowledgeDao.selectKnowledgeLimit(page);
	}
}