package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.CountSysDao;
import com.yuedi.vo.ClassCntVo;
import com.yuedi.vo.ClipCntVo;
import com.yuedi.vo.OrderCntVo;


@Service
@Transactional
public class CountSysService {
	
	@Autowired
	private CountSysDao countSysDao;
	
	//课程分类统计
	public List<ClassCntVo> ClassList(Integer status) {
		
		return countSysDao.Classcnt(status);
	}
	//孕症分类统计
	public List<OrderCntVo> OrderList(Integer status) {
		
		return countSysDao.Ordercnt(status);
	}
	
	//剪辑数统计
	public List<ClipCntVo> ClipList() {
		
		return countSysDao.Clipcnt();
	}
	//剪辑人数统计
	public List<ClipCntVo> PopList() {
		
		return countSysDao.Popcnt();
	}
	
	//测试人数统计
	public List<ClipCntVo> TestList() {
		
		return countSysDao.Testcnt();
	}
	//课程作业分类统计
	public List<ClassCntVo> WorkList(Integer status) {
		
		return countSysDao.Workcnt(status);
	}
	
	//胎教课程访问统计
	public List<ClipCntVo> classNum() {
		
		return countSysDao.classNum();
	}
	//胎教作业完成统计
	public List<ClipCntVo> workNum() {
		
		return countSysDao.workNum();
	}
}
