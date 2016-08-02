/**
 * @type com.yuedi.web.dao.AreasDao
 */
package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.Saler;

@MyBatisRepository
public interface SalerDao {

	public List<Saler> selectsaler();
	//增加业务员信息
	public long insertSaler(Saler saler);
	
    public int deleteSalerbyId(Long id);
    
	public Saler selectSalerById(Long id);
	
	public Saler selectSalerByUserId(Long userId);
	
	public Saler selectSalerBysellerId(Long sid);
	//修改Seller
	public int updateSalerById(Saler saler);
	
	public int updatetwocodeUrl(Saler saler);	
	//分页
	public abstract List<Saler> selectSalerLimit(MyPage<Saler> page);
	//查询二维码编码
	public abstract Saler selectMaxSalerCardCode();
}
