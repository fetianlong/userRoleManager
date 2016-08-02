package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.ContactPersonDao;
import com.yuedi.entity.ContactPerson;
import com.yuedi.entity.MyPage;

@Service
@Transactional
public class ContactPersonService {
	@Autowired
	private ContactPersonDao contactPersonDao;
	
	public List<ContactPerson> findListContactPersonByLimit(MyPage<ContactPerson> page){
		return contactPersonDao.findListContactPersonByLimit(page);
	}
	
	public List<ContactPerson> findBySellerId(Long sellerId)  {
		return contactPersonDao.findBySellerId(sellerId);
	}
	
	public int insertContactPerson(ContactPerson contactPerson){
		return contactPersonDao.insertContactPerson(contactPerson);
	}
	
	public int updateContactPerson(ContactPerson contactPerson){
		return contactPersonDao.updateContactPerson(contactPerson);
	}
	
	public ContactPerson findContactPersonById(Long id){
		return contactPersonDao.findContactPersonById(id);
	}
	
    public int deleteContactPersonById(Long id){
    	return contactPersonDao.deleteContactPersonById(id);
    }
}
