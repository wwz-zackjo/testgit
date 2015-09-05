/**
 * 
 */
package com.wishwingz.model.board;

import org.joda.time.DateTime;

/**
 * @author zackjo
 *
 */
public class BoardComment {
	private String id;
	private String contentId;
	private String content;
	private String regName;
	private DateTime regDttm;
	private DateTime updDttm;
	private int order;
	private int grp;
	private int lvl;
	private boolean isPrivate;
	private int parentId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public DateTime getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(DateTime regDttm) {
		this.regDttm = regDttm;
	}
	public DateTime getUpdDttm() {
		return updDttm;
	}
	public void setUpdDttm(DateTime updDttm) {
		this.updDttm = updDttm;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getGrp() {
		return grp;
	}
	public void setGrp(int grp) {
		this.grp = grp;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}
