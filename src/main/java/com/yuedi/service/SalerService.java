/**
 * @type com.yuedi.web.service.impl.TaskServiceImpl
 */
package com.yuedi.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.SalerDao;
import com.yuedi.dao.SalerUserinfoDao;
import com.yuedi.entity.MyPage;
import com.yuedi.entity.Saler;
import com.yuedi.entity.SalerUserinfo;
//import com.yuedi.util.twoDimension.QRCodeEncoderHandler;

@Service
@Transactional
public class SalerService {

	@Autowired
	private SalerDao salerDao;
	@Autowired
	private SalerUserinfoDao salerUserInfoDao;
	@Autowired
	private SalerDeclareService salerDeclareService;
	@Autowired
	SalerUserinfoService salerUserinfoService;

	public List<Saler> querySaler() {
		return salerDao.selectsaler();
	}

	public Boolean saveSaler(Saler saler, int declareNumber, Long id) {
		try{
			for(int i=0; i<declareNumber; i++) {
				
				Saler salerParam = new Saler();
				salerParam.setSellerId(saler.getSellerId());
				salerParam.setOrdIndex("1");
				salerParam.setIsDeleteFlag(false);
				salerParam.setCreateDateTime(new Date());
				salerParam.setCreaterId(saler.getCreaterId());
				salerParam.setCreaterName(saler.getCreaterName());
				//得到二维码编码
				Saler salerMaxCode = salerDao.selectMaxSalerCardCode();
				if(salerMaxCode == null) {
					salerParam.setCardCode("1000001");
				}else {
					String cardCode = salerMaxCode.getCardCode();
					Integer cardNumber = Integer.parseInt(cardCode) + 1;
					salerParam.setCardCode(cardNumber.toString());
				}
				//跳转路径+商家ID+用户ID(扫描后跳转路径)
				String tdCode = "http://www.mumfans.com/xianfengYan/" + "?";
				String TwocodeUrl = tdCode + "sjid=" + saler.getSellerId() + "&salerid" + "=" + salerParam.getCardCode();
				salerParam.setTdCodeURL(TwocodeUrl);
				
				salerParam.setTwocodeurl("http://www.mumfans.com/yuedi-resource/resources/twocode/" + salerParam.getCardCode() + ".jpg");	//服务器中存放路径
				
//				QRCodeEncoderHandler twoCode = new QRCodeEncoderHandler();
				
				String imgPath = "D:/yuedi-resource/resources/twocode/" + salerParam.getCardCode() + ".jpg";
				
//				twoCode.ZxCode(salerParam.getSellerId().toString(), TwocodeUrl, imgPath);
				salerDao.insertSaler(salerParam);
			}
			if(id != null) {
				salerDeclareService.updatesalerDeclareById(id);
			}
			
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int deleteSalerById(Long id) {
		return salerDao.deleteSalerbyId(id);
	}

	public Saler findSalerbyId(Long id) {
		return salerDao.selectSalerById(id);
	}
	
	public Saler findSalerbyUserId(Long userId) {
		return salerDao.selectSalerByUserId(userId);
	}

	public int updateSalerbyId(Saler saler,SalerUserinfo salerUserinfo) {
		try{
			salerDao.updateSalerById(saler);
			List<SalerUserinfo> salerUserinfos = salerUserinfoService.querySalerUserinfoBySalerId(salerUserinfo.getSallerId());
			if(salerUserinfos != null && salerUserinfos.size() != 0) {
				SalerUserinfo salerUserinfoParam = new SalerUserinfo();
				salerUserinfoParam.setSallerId(salerUserinfo.getSallerId());
				salerUserinfoParam.setUpdateDateTime(new Date());
				salerUserinfoService.updateUserInfoBySalerId(salerUserinfoParam);
			}
			salerUserinfoService.insertSalerUserinfo(salerUserinfo);
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int updateTwocodeUrl(Saler saler) {
		return salerDao.updatetwocodeUrl(saler);
	}

	public int deleteSalerBysellerId(Long sellerid) {
		return salerDao.deleteSalerbyId(sellerid);
	}

	public List<Saler> selectSalerLimit(MyPage<Saler> salerPage) {
		List<Saler> salers = salerDao.selectSalerLimit(salerPage);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0; i<salers.size(); i++) {
			SalerUserinfo salerUserInfo = salerUserInfoDao.selectSalerBySalerID(salers.get(i).getId());
			if(salerUserInfo != null) {
				salers.get(i).setName(salerUserInfo.getUserName());
			}
			salers.get(i).setCreateDateTimeString(sdf.format(salers.get(i).getCreateDateTime()));
		}
		return salers;
	}

	public Saler selectMaxSalerCardCode() {
		return salerDao.selectMaxSalerCardCode();
	}
	
	public Saler selectSalerByUserId(Long userId) {
		return salerDao.selectSalerByUserId(userId);
	}
}
