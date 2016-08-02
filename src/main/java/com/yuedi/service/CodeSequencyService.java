package com.yuedi.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuedi.dao.CodeSequencyDao;
import com.yuedi.entity.CodeSequency;
import com.yuedi.util.Constants;

@Service
@Transactional
public class CodeSequencyService {
	@Autowired
	private CodeSequencyDao codeSequencyDao;
	/**
	 * 编号序号默认长度
	 */
	private static final int DEFAULT_SEQUENCY_LENGTH = 6;
	private static final String MUSIC_CATEGORY = "1001";
	private static final String DOC_CATEGORY = "1002";
	private static final String VIDEO_CATEGORY = "1003";
	private static final String PICTURE_CATEGORY = "1004";
	private static final String CUSTOMER_CATEGORY = "1005";
	private static final String EMPLOYEE_CATEGORY = "1006";
	
	
	/**
	 * @return the codeSequencyDao
	 */
	public CodeSequencyDao getCodeSequencyDao() {
		return codeSequencyDao;
	}
	
	/**
	 * @param codeSequencyDao the codeSequencyDao to set
	 */
	public void setCodeSequencyDao(CodeSequencyDao codeSequencyDao) {
		this.codeSequencyDao = codeSequencyDao;
	}
	
	public String musicCodeGenerator() {
		CodeSequency codeSequency = new CodeSequency();
		codeSequency.setCategory(MUSIC_CATEGORY);
		codeSequency.setRemark("音乐库编号");
		codeSequency.setPrefix("M" + Constants.dateFormat(new Date(), "yyyyMMdd"));
		
		return innerGenerator(codeSequency, DEFAULT_SEQUENCY_LENGTH);
	}
	
	public String docCodeGenerator() {
		CodeSequency codeSequency = new CodeSequency();
		codeSequency.setCategory(DOC_CATEGORY);
		codeSequency.setRemark("文档库编号");
		codeSequency.setPrefix("D" + Constants.dateFormat(new Date(), "yyyyMMdd"));
		
		return innerGenerator(codeSequency, DEFAULT_SEQUENCY_LENGTH);
	}

	public String videoCodeGenerator() {
		CodeSequency codeSequency = new CodeSequency();
		codeSequency.setCategory(VIDEO_CATEGORY);
		codeSequency.setRemark("视频库编号");
		codeSequency.setPrefix("V" + Constants.dateFormat(new Date(), "yyyyMMdd"));
		
		return innerGenerator(codeSequency, DEFAULT_SEQUENCY_LENGTH);
	}

	public String pictureCodeGenerator() {
		CodeSequency codeSequency = new CodeSequency();
		codeSequency.setCategory(PICTURE_CATEGORY);
		codeSequency.setRemark("图片库编号");
		codeSequency.setPrefix("P" + Constants.dateFormat(new Date(), "yyyyMMdd"));
		
		return innerGenerator(codeSequency, DEFAULT_SEQUENCY_LENGTH);
	}
	
	/* (non-Javadoc)
	 * @see com.yuedi.web.service.CodeSequencyService#customerCodeGenerator()
	 */
	public String customerCodeGenerator() {
		CodeSequency codeSequency = new CodeSequency();
		codeSequency.setCategory(CUSTOMER_CATEGORY);
		codeSequency.setRemark("客户编号");
		codeSequency.setPrefix("C" + Constants.dateFormat(new Date(), "yyyyMMdd"));
		
		return innerGenerator(codeSequency, DEFAULT_SEQUENCY_LENGTH);
	}
	
	public String memberCodeGenerator(Long franchiseesId) {
		CodeSequency codeSequency = new CodeSequency();
		codeSequency.setCategory(CUSTOMER_CATEGORY);
		codeSequency.setRemark("会员编号");
		Date d = new Date();
		String d1 = Constants.dateFormat(d, "yyyyMMdd");
		String da = d1 + d.getHours() + d.getMinutes() + d.getSeconds() + d.getMinutes() + franchiseesId.toString();
		codeSequency.setPrefix(da);
		
		return innerGenerator(codeSequency, DEFAULT_SEQUENCY_LENGTH);
	}

	/* (non-Javadoc)
	 * @see com.yuedi.web.service.CodeSequencyService#employeeCodeGenerator(java.lang.Long)
	 */
	public String employeeCodeGenerator(Long franchiseesId) {
		CodeSequency codeSequency = new CodeSequency();
		codeSequency.setCategory(EMPLOYEE_CATEGORY);
		codeSequency.setRemark("员工编号");
		// E201411120001001
		codeSequency.setPrefix(String.format("E%1$s%2$04d",  Constants.dateFormat(new Date(), "yyyyMMdd"), franchiseesId));
		
		return innerGenerator(codeSequency, DEFAULT_SEQUENCY_LENGTH);
	}

	/**
	 * 内容编号生成
	 * 
	 * @param codeSequency
	 * @return
	 */
	private String innerGenerator(CodeSequency codeSequency, int sequencySize) {
		
		CodeSequency last = getCodeSequencyDao().selectCodeSequencyByCategoryAndPrefix(codeSequency);
		
		if (last == null) {
			codeSequency.setSequency(0L);
			codeSequency.setIncrement(1L);
			
			getCodeSequencyDao().insertCodeSequency(codeSequency);
			getCodeSequencyDao().updateCodeSequencyByCategoryAndPrefix(codeSequency);
		}
		else {
			getCodeSequencyDao().updateCodeSequencyByCategoryAndPrefix(last);
		}
		
		last = getCodeSequencyDao().selectCodeSequencyByCategoryAndPrefix(codeSequency);
		String code = String.format("%1$s%2$0" + sequencySize + "d", last.getPrefix(), last.getSequency());
		
		return code;
	}
}
