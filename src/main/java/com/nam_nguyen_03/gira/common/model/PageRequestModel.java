package com.nam_nguyen_03.gira.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PageRequestModel {
    private int pageCurrent;
	private int itemPerPage;
	private String fieldNameSort;
	private boolean isIncrementSort = true;
	private String fieldNameSearch;
	private String valueSearch;

	public PageRequestModel(int pageCurrent, int itemPerPage, String fieldNameSort, boolean isIncrementSort, String fieldNameSearch, String valueSearch){
		this.pageCurrent = pageCurrent <=0 ? 1 : pageCurrent;
		this.itemPerPage = itemPerPage <=0 ? 10 : itemPerPage;
		this.fieldNameSort = fieldNameSort;
		this.isIncrementSort = isIncrementSort;
		this.fieldNameSearch = fieldNameSearch;
		this.valueSearch = valueSearch;
	}
}
