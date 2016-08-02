package com.yuedi.dao.fm;

import java.util.List;

import com.yuedi.dao.MyBatisRepository;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.Knowledge;
@MyBatisRepository
public interface KnowledgeDao {
	int deleteKnowledgeByPrimaryKey(Integer id);
    int insertKnowledge(Knowledge record);
    Knowledge selectKnowledgeByPrimaryKey(Integer id);
    int updateKnowledgeByPrimaryKey(Knowledge record);
    List<Knowledge> selectKnowledgeLimit(MyPage<Knowledge> page);}