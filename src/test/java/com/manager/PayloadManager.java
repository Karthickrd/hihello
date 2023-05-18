package com.manager;

import com.addresspayload.AddressPayload;
import com.searchpayload.SearchPayload;

public class PayloadManager {
	private AddressPayload addressPayload;
	private SearchPayload searchPayload;

	public AddressPayload getAddressPayload() {
		return (addressPayload == null) ? addressPayload = new AddressPayload() : addressPayload;
	}

	public SearchPayload getSearchPayload() {
		return (searchPayload == null) ? searchPayload = new SearchPayload() : searchPayload;
	}

}
