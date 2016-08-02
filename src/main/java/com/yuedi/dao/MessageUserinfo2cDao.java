package com.yuedi.dao;

import java.util.List;
import com.yuedi.entity.MessageUserinfo2c;

@MyBatisRepository
public interface MessageUserinfo2cDao {
	/**
	 * 维护短信和客户关系表
	 * @param messageUserinfo2c
	 * @return
	 */
	public long insertMessageUserinfo2c(MessageUserinfo2c messageUserinfo2c);
	/**
	 * 查找未发短信的客户
	 * @param messageUserinfo2c
	 * @return
	 */
	public List<MessageUserinfo2c> findUndistributedUserinfo2c(MessageUserinfo2c messageUserinfo2c);
	/**
	 * 查找已发短信的客户
	 * @param messageUserinfo2c
	 * @return
	 */
	public List<MessageUserinfo2c> findDistributedUserinfo2c(MessageUserinfo2c messageUserinfo2c);
	/**
	 * 查找所有初始未分配的客户
	 * @return
	 */
	public List<MessageUserinfo2c> findAllUndistributedUserinfo2c(Long sellerId);
	/**
	 * 通过当前短信id查找短信客户表
	 * @param messageId
	 * @return
	 */
	public List<MessageUserinfo2c> findUserinfo2cByMessageId(Long messageId);
	/**
	 * 通过短信id和客户名称获取客户短信信息
	 * @param messageUserinfo2c
	 * @return
	 */
	public MessageUserinfo2c findUserinfo2cByMessageIdAndUserinfo2cName(MessageUserinfo2c messageUserinfo2c);
	/**
	 * 通过短信id和客户名称更新客户短信信息
	 * @param messageUserinfo2c
	 * @return
	 */
	public Integer updateMessageUserinfo2cByMessageIdAndUserinfo2cName(MessageUserinfo2c messageUserinfo2c);
	/**
	 * 更新发短信时间
	 * @param messageUserinfo2c
	 * @return
	 */
	public Integer updateMessageUserinfo2cMessageDateTime(MessageUserinfo2c messageUserinfo2c);
	/**
	 * 根据短信id查询所有客户信息
	 * @param messageId
	 * @return
	 */
	public List<MessageUserinfo2c> findUserinfo2cListByMessageId(Long messageId);
}
