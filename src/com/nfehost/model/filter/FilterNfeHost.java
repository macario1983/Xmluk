package com.nfehost.model.filter;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public abstract class FilterNfeHost {

	private Integer firstRegister;
	private Integer maxRegister;
	private Map<String, ModeOrder> order = new HashMap<>(5);
	private boolean onlyDataList; //retornar somente os dados para a listagem registros
	
	public enum ModeOrder {
		ASCENDING,
		DESCENDING;
	}

	
	public final void addOrder(String property, ModeOrder mode) {
		this.getOrder().put(property, mode);
	}
	
	/**
	 * ASCENDING por default
	 * @param property
	 */
	public final void addOrder(String property) {
		this.getOrder().put(property, ModeOrder.ASCENDING);
	}
	
}
