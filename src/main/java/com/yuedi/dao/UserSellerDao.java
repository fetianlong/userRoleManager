package com.yuedi.dao;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.Userseller;

@MyBatisRepository
public interface UserSellerDao {
	public long findClientUserCount(MyPage<Userseller> page);
}
