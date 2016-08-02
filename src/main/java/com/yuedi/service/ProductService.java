package com.yuedi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.ProductDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Product;

@Component
@Transactional
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public long insertProduct(Product product){
		return productDao.insertProduct(product);
	};
	
	public int updateProductbyId(Product product){
		return productDao.updateProductbyId(product);
	};
	
	public int deleteProductbyId(Long id){
		return productDao.deleteProductbyId(id);
	};
	//分页
	public List<Product> selectProductLimit(MyPage<Product> page){
		return productDao.selectProductLimit(page);
	};
	
	public Product selectProductById(Long productId){
		return productDao.selectProductById(productId);
	}
	
	public int updateIsDeleteFlag(Long id){
		return productDao.updateIsDeleteFlagbyId(id);
	}
}
