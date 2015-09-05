/**
 * 
 */
package com.wishwingz.model.board;

import java.util.Date;

import org.joda.time.DateTime;

/**
 * @author zackjo
 *
 */
public class Board {
	private String id;
	private String title;
	private String content;
	private int hit;
	private String regName;
	private DateTime regDttmForView;
	private DateTime updDttmForView;
	private Date regDttm;
	private Date updDttm;
	private int order;
	private int grp;
	private int lvl;
	private boolean isPrivate;
	private int parentId;
	private int totalCommentCount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public DateTime getRegDttmForView() {
		regDttmForView = regDttm == null ? null : new DateTime(regDttm);
		
		return regDttmForView;
	}
	public void setRegDttmForView(DateTime regDttmForView) {
		this.regDttmForView = regDttmForView;
	}
	public DateTime getUpdDttmForView() {
		updDttmForView = updDttm == null ? null : new DateTime(updDttm);
		
		return updDttmForView;
	}
	public void setUpdDttmForView(DateTime updDttmForView) {
		this.updDttmForView = updDttmForView;
	}
	public Date getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(Date regDttm) {
		this.regDttm = regDttm;
	}
	public Date getUpdDttm() {
		return updDttm;
	}
	public void setUpdDttm(Date updDttm) {
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
	public int getTotalCommentCount() {
		return totalCommentCount;
	}
	public void setTotalCommentCount(int totalCommentCount) {
		this.totalCommentCount = totalCommentCount;
	}
}
