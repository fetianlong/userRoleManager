/**
 * @type com.yuedi.web.service.impl.AreasServiceImpl
 */
package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.AreasDao;
import com.yuedi.entity.Areas;

/**
 * @author sky
 *
 * @date 2014年11月23日
 *
 */
@Service
@Transactional
public class AreasService {

	@Autowired
	private AreasDao areasDao;

	public List<Areas> findProvince() {
		return areasDao.selectAreasByParentId(0);
	}

	public List<Areas> findCityByProvince(Integer provinceId) {
		return this.areasDao.selectAreasByParentId(provinceId);
	}

	public List<Areas> findDistrictByCity(Integer cityId) {
		return this.areasDao.selectAreasByParentId(cityId);
	}

	public List<Areas> findAreas(Integer id) {
		return this.areasDao.selectAreas(id);
	}

	public List<Areas> findAreasByLevel(int level) {
		return areasDao.findAreasByLevel(level);
	}

	public Areas findAreasByName(String name) {
		return areasDao.findAreasByName(name,0);
	}

	public Areas findNameById(Integer id) {
		return areasDao.findNameById(id);
	}
}
