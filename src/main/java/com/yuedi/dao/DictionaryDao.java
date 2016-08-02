package com.yuedi.dao;

import java.util.List;

import com.yuedi.entity.MyPage;
import com.yuedi.entity.Dictionary;

@MyBatisRepository
public interface DictionaryDao {

	Long add(Dictionary model);

	Integer update(Dictionary model);

	Dictionary findById(Long id);

	Integer deleteById(Long id);

	int findCountByDictionary(Dictionary model);

	List<Dictionary> selectDictionaryLimit(MyPage<Dictionary> page);

	Dictionary getDictionaryByCode(String code);

	List<Dictionary> getListDictionary(Dictionary dic);
	
	Dictionary getDictionaryById(Long id);
	
	List<Dictionary> getParentDictionary();

}