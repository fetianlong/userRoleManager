/**
 * @type com.yuedi.web.dao.AreasDao
 */
package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.Seller;

@MyBatisRepository
public interface SellerDao {

	public List<Seller> querySeller();
	//增加商家信息
	public long insertSeller(Seller seller);
	
	public Seller selectSellerById(Long id);
	
	public List<Seller> selectSellerLimit(MyPage<Seller> page);
	//修改Seller
	public int updateSellerBySellerId(Seller seller);
	
    public int deleteSellerById(Long id);
	
	/**
	 * 商品上线
	 * @param id
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2015年4月30日 下午2:16:37
	 */
	public int updateIsDeleteFlag(Long id);
	
	/**
	 * 潜在商家转成正式
	 * @param ids
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年6月4日 下午6:20:15
	 */
	public int editSellerisLatentByIds(String ids);
	
	/**
	 * 潜在商家转成正式时，增加用户名密码
	 * @param seller
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年6月24日 下午2:43:21
	 */
	public abstract int updateSellerUserName(Seller seller);
	
	/**
	 * 获取当前机构下面的子机构
	 * @param userFranchiseesId
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年6月24日 下午6:11:09
	 */
	public List<Seller> getSonSellerData(Long userFranchiseesId);
	/**
	 * 得到商家或加盟商
	 * @param sellerSign
	 * @return
	 */
	public List<Seller> getSellerSignData(Seller sellerSign);
	/**
	 * 得到商家或加盟商
	 * @param sellerSign
	 * @return
	 */
	public List<Seller> getSelledcntData(String selledcnt);
	
	/**
	 * 查询所有的信息
	 * @param se
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月14日 下午3:30:23
	 */
	public List<Seller> selectAll(Seller se);
	public long selectAllCountSeller(Seller se);
	
	public List<Seller> selectAllianceBusiness();
	/**
	 * 查询所有商家
	 * @return
	 */
	public List<Seller> selectAllSeller(Seller seller);
	
	/**
	 * 查找商家
	 * @return
	 */
	public List<Seller> selectSimpleSeller();
	
	/**
	 * 更新短信条数
	 * @param seller
	 * @return
	 */
	public Integer updateSellerMessageCountBySellerId(Seller seller);
	public List<Seller> getMyseller();

}
