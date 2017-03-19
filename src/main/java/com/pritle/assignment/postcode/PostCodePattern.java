package com.pritle.assignment.postcode;

import java.util.ArrayList;
import java.util.List;

public class PostCodePattern {
	
	// return list of post code, we can get this list from any external source
	// like database, file system, property files, web API
	public List<String> getPostcodeList() {
		List<String> list = new ArrayList<>();
		list.add("2288 EL");
		list.add("2522WG");
		list.add("2522WH");
		list.add("2522WI");
		list.add("2522WJ");

		return list;
	}
}
