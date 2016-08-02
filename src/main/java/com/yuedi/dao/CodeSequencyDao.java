package com.yuedi.dao;

import com.yuedi.entity.CodeSequency;

@MyBatisRepository
public interface CodeSequencyDao {
	public long insertCodeSequency(CodeSequency codeSequency);
	/**
	 * 生成代码序号最大值
	 * 
	 * @param codeSequency
	 * @return
	 */
	public int updateCodeSequencyByCategoryAndPrefix(CodeSequency codeSequency);
	/**
	 * 根据类型与前缀获取顺序号最大值
	 * 
	 * @param codeSequency
	 * @return
	 */
	public CodeSequency selectCodeSequencyByCategoryAndPrefix(CodeSequency codeSequency);
}
