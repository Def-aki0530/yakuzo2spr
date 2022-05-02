package com.example.yakuzo2.data;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class MenuData {
	private String kengen_code;
	private List<Map<String,Object>> dutiesList;
	private List<Map<String,Object>> stockList;
	private List<Map<String,Object>> systemList;
}
