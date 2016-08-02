package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.Product;

@MyBatisRepository
public interface ProductDao {
	
	public long insertProduct(Product product);
	
	public int updateProductbyId(Product product);
	
	public int deleteRoleById(Long id);
	//分页
	public List<Product> selectProductLimit(MyPage<Product> page);
	
	public Product selectProductById(Long productId);
	
	//商品下线
	public int deleteProductbyId(Long productId);
	
	/**
	 * 商品上线
	 * @param id
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2015年4月30日 下午2:16:37
	 */
	public int updateIsDeleteFlagbyId(Long id);
}
