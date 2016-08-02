package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.Knowledge;
import com.yuedi.entity.MyPage;
@MyBatisRepository
public interface KnowledgeDao {
	int deleteKnowledgeByPrimaryKey(Integer id);
    int insertKnowledge(Knowledge record);
    Knowledge selectKnowledgeByPrimaryKey(Integer id);
    int updateKnowledgeByPrimaryKey(Knowledge record);
    List<Knowledge> selectKnowledgeLimit(MyPage<Knowledge> page);}