package com.yuedi.service.fm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.fm.SrespirationDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.fm.Srespiration;

@Component
@Transactional
public class SrespirationService {
	
	@Autowired
	SrespirationDao srespirationDao;
	
	public List<Srespiration> selectCountSrespiration(MyPage<Srespiration> page){
		return srespirationDao.selectCountSrespiration(page);
	}
}
