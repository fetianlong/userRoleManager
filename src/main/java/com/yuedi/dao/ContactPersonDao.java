package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.ContactPerson;
import com.yuedi.entity.MyPage;

@MyBatisRepository
public interface ContactPersonDao {
	
	public List<ContactPerson> findListContactPersonByLimit(MyPage<ContactPerson> page);
	
	public List<ContactPerson> findBySellerId(Long sellerId);
	
	public int insertContactPerson(ContactPerson contactPerson);
	
	public int updateContactPerson(ContactPerson contactPerson);
	
	public ContactPerson findContactPersonById(Long id);
	
    public int deleteContactPersonById(Long id);
}
