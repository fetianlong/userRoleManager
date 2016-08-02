/**
 * @type com.yuedi.enums.ResourceType
 */
package com.yuedi.util;

/**
 * 资源类型：1-音乐、2-图片、3-文档、4-视频
 * 
 * @author sky
 *
 * @date 2013-11-3
 *
 */
public enum ResourceType {
	MUSIC(1),
	PICKTURE(2),
	DOC(3),
	VIDEO(4);
	
	private int value;
	
	private ResourceType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
