package com.nfehost.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.nfehost.util.NullUtil;

@MappedSuperclass
public abstract class Persistent implements Serializable {

	private static final long serialVersionUID = -6719982330451700163L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Serializable getKey() {
		return this.getId();
	}
	
	public void setKey(Serializable key) {
		if(key instanceof Long) {
			this.setId((Long)key);
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Persistent) {
			Persistent pojo = (Persistent) obj;
			if(NullUtil.isNull(this.getId()) || NullUtil.isNull(pojo.getId())){
				return super.equals(obj);
			}
			else return this.getId().equals(pojo.getId());
		}
		return false;
	}

}
