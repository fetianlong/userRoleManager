/**
 * @type com.yuedi.web.dto.ResourceFileInfo
 */
package com.yuedi.entity;

import java.io.Serializable;

/**
 * @author sky
 *
 * @date 2013-11-3
 *
 */
public class ResourceFileInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fileName;
	private String fileFullName;
	private String fileType;
	/**
	 * 按字节计算
	 */
	private Long fileSize;
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileFullName
	 */
	public String getFileFullName() {
		return fileFullName;
	}
	/**
	 * @param fileFullName the fileFullName to set
	 */
	public void setFileFullName(String fileFullName) {
		this.fileFullName = fileFullName;
	}
	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * @return the fileSize
	 */
	public Long getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
}
