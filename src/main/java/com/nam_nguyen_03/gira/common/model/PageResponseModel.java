package com.nam_nguyen_03.gira.common.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResponseModel<T> {
	private int pageCurrent;
	private int totalPage;
	private List<T> items = new ArrayList<>();

}