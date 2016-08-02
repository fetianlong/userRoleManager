/**
 * @type com.yuedi.web.service.impl.TaskServiceImpl
 */
package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.SellerDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Seller;

@Component
@Transactional
public class SellerService{

	@Autowired
	private SellerDao sellerDao;

	public List<Seller> querySeller() {
	
		return sellerDao.querySeller();
	}

	public long insertSeller(Seller seller) {
	
		return sellerDao.insertSeller(seller);
	}

	public Seller selectSellerById(Long id) {
	
		return sellerDao.selectSellerById(id);
	}
	
	public List<Seller> selectSellerLimit(MyPage<Seller> page){
		return sellerDao.selectSellerLimit(page);
	}
	
	public int updateSellerbyId(Seller seller) {
	
		return sellerDao.updateSellerBySellerId(seller);
	}

	public int deleteSellerById(Long id) {
	
		return sellerDao.deleteSellerById(id);
	}

	public int updateIsDeleteFlag(List<Long> list) {
		int rs = 0;
		
		for (Long id : list) {
			if (sellerDao.updateIsDeleteFlag(id)>0)
				rs++;
		}
		return rs;
	}

	public int editSellerisLatentByIds(String ids) {
		return sellerDao.editSellerisLatentByIds(ids);
	}

	public int updateSellerUserName(Seller seller) {
		return sellerDao.updateSellerUserName(seller);
	}

	public List<Seller> getSonSellerData(Long userFranchiseesId) {
		return sellerDao.getSonSellerData(userFranchiseesId);
	}

	public List<Seller> getSellerSignData(Seller sellerSign) {
		return sellerDao.getSellerSignData(sellerSign);
	}

	public List<Seller> getSelledcntData(String selledcnt) {
		return sellerDao.getSelledcntData(selledcnt);
	}
	
	public List<Seller> findAllSellerFranchisees() {
		return sellerDao.selectAllianceBusiness();
	}
	
	public List<Seller> selectAllSeller(Seller seller) {
		return sellerDao.selectAllSeller(seller);
	}
	
	public List<Seller> selectSimpleSeller(){
		return sellerDao.selectSimpleSeller();
	}
	public List<Seller> getMyseller(){
		return sellerDao.getMyseller();
	}
	
	public Integer updateSellerMessageCountBySellerId(Seller seller){
		return sellerDao.updateSellerMessageCountBySellerId(seller);
	}
}
