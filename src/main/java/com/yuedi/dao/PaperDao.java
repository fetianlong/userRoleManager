package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.Paper;
import com.yuedi.entity.Paper;
@MyBatisRepository
public interface PaperDao {
	int deletePaperByPrimaryKey(Integer id);
    int insertPaper(Paper record);
    Paper selectPaperByPrimaryKey(Integer id);
    int updatePaperByPrimaryKey(Paper record);
    List<Paper> selectPaperLimit(MyPage<Paper> page);
}