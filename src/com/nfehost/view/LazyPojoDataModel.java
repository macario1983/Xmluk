package com.nfehost.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import com.nfehost.model.Persistent;
import com.nfehost.model.filter.FilterNfeHost;
import com.nfehost.service.Service;
import com.nfehost.util.NullUtil;
import com.nfehost.util.StringUtil;

public class LazyPojoDataModel<Pojo extends Persistent, Services extends Service<Pojo>, Filter extends FilterNfeHost> 
		extends LazyDataModel<Pojo> {

	private static final long serialVersionUID = -3187647482703616415L;
	
	private List<Pojo> pojos;
	private Services service;
	private Filter filter;
	
	public LazyPojoDataModel(Services service, Filter filter) {
		this.service = service;
		this.filter = filter;
	}

	@Override
	public Pojo getRowData(String rowKey) {
		for(Pojo pojo : this.pojos) {  
            if(pojo.getId().equals(Long.valueOf(rowKey))) {  
                return pojo;  
            }
        }
		return null;
	}
	

	@Override
	public Object getRowKey(Pojo pojo) {
		return pojo.getId();
	}
	
	public void setRowIndex(int rowIndex) {
		if (getPageSize() == 0) {
			rowIndex = -1;
		}
		super.setRowIndex(rowIndex);
	}

	@Override
	public List<Pojo> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		//obtem a quantidade total de registros
		this.filter.setOnlyDataList(false);
		super.setRowCount(NullUtil.longNotNull(this.service.getTotalRows(this.filter)).intValue());
		//seta os dados de paginacao e ordem no filter
		this.filter.setFirstRegister(Integer.valueOf(first));
		this.filter.setMaxRegister(Integer.valueOf(pageSize));
		this.filter.setOnlyDataList(true);
		if (!StringUtil.isStringNullOrEmpty(sortField)) {
			this.filter.setOrder(new HashMap<String, FilterNfeHost.ModeOrder>());
			this.filter.getOrder().put(sortField, sortOrder.equals(SortOrder.ASCENDING) ? FilterNfeHost.ModeOrder.ASCENDING : FilterNfeHost.ModeOrder.DESCENDING);
		}
		
		List<Pojo> list = (List<Pojo>) this.service.findByFilter(this.filter);
		
		return list;
		
	}
	
	public List<Pojo> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String,String> filters) {
        throw new UnsupportedOperationException("Necessaria a implementacao do load.");
    }
	
}
