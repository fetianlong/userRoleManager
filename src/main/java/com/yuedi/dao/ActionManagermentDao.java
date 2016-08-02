package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.ActionManagerment;
import com.yuedi.entity.MyPage;

@MyBatisRepository
public interface ActionManagermentDao {
	//增加
	public long insertActionManagerment(ActionManagerment actionManager);
	//分页
	public List<ActionManagerment> selectActionManagermentLimit(MyPage<ActionManagerment> page);
	//根据id查询行动
	public ActionManagerment selectActionManagermentById(Long id);
	//更新
	public int updateActionManagermenById(ActionManagerment actionManagerment);
	//删除
	public int deleteActionManagermentbyId(long id);
}
