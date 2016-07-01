package edu.whu.irlab.client.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Roger on 2016/5/22.
 */
@Entity
@Table(name = "base_cls")
public class BaseCls {
	private int id;
	private Integer clsMode;
	private String clsTitle;
	private String clsDesc;
	private String clsOrder;
	private String clsStatus;
	private String clsIcon;
	private Timestamp clsUpdatetime;
	private String text1;
	private String text2;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cls_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "cls_mode")
	public Integer getClsMode() {
		return clsMode;
	}

	public void setClsMode(Integer clsMode) {
		this.clsMode = clsMode;
	}

	@Basic
	@Column(name = "cls_title")
	public String getClsTitle() {
		return clsTitle;
	}

	public void setClsTitle(String clsTitle) {
		this.clsTitle = clsTitle;
	}

	@Basic
	@Column(name = "cls_desc")
	public String getClsDesc() {
		return clsDesc;
	}

	public void setClsDesc(String clsDesc) {
		this.clsDesc = clsDesc;
	}

	@Basic
	@Column(name = "cls_order")
	public String getClsOrder() {
		return clsOrder;
	}

	public void setClsOrder(String clsOrder) {
		this.clsOrder = clsOrder;
	}

	@Basic
	@Column(name = "cls_status")
	public String getClsStatus() {
		return clsStatus;
	}

	public void setClsStatus(String clsStatus) {
		this.clsStatus = clsStatus;
	}

	@Basic
	@Column(name = "cls_icon")
	public String getClsIcon() {
		return clsIcon;
	}

	public void setClsIcon(String clsIcon) {
		this.clsIcon = clsIcon;
	}

	@Basic
	@Column(name = "cls_updatetime")
	public Timestamp getClsUpdatetime() {
		return clsUpdatetime;
	}

	public void setClsUpdatetime(Timestamp clsUpdatetime) {
		this.clsUpdatetime = clsUpdatetime;
	}

	@Basic
	@Column(name = "text1")
	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	@Basic
	@Column(name = "text2")
	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

}
