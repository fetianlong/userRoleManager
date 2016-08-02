package com.yuedi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.UserSellerDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Userseller;

@Component
@Transactional
public class UserSellerService {
	@Autowired
	public UserSellerDao userSellerDao;

	public long findClientUserCount(MyPage<Userseller> page) {
		Long total = userSellerDao.findClientUserCount(page);
		if (total == null) return 0;
		return total.longValue();
	}
}
