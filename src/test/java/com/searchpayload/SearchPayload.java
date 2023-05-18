package com.searchpayload;

import com.pojo.product.SearchProductInput_Pojo;

public class SearchPayload {
	public SearchProductInput_Pojo searchProduct(String text) {
		SearchProductInput_Pojo searchProductInput_Pojo  = new SearchProductInput_Pojo(text);
		return searchProductInput_Pojo;
	}

}
