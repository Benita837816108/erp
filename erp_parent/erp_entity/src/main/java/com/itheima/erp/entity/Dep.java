package com.itheima.erp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "dep")
public class Dep {
	@Id
	@GeneratedValue(generator = "myKeyGenerator", strategy = GenerationType.SEQUENCE)
	@GenericGenerator(name = "myKeyGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "dep_seq") })
	// @SequenceGenerator(name="myKeyGenerator",sequenceName="dep_seq")
	private Long uuid;// 部门编号
	private String name;// 部门编号
	private String tele;// 部门编号

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	@Override
	public String toString() {
		return "Dep [uuid=" + uuid + ", name=" + name + ", tele=" + tele + "]";
	}

}
