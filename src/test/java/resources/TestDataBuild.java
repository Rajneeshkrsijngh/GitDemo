package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace_Map;
import pojo.Location_Map;

public class TestDataBuild {
	
	
	public   AddPlace_Map addPlaceDataPayLoad(String name,String Language,String Address) {
		AddPlace_Map p = new AddPlace_Map();
		p.setAccuracy(20);
		p.setAddress(Address);
		p.setLanguage(Language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://google.com");
		p.setName(name);
		List<String> myList = new ArrayList<>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);

		Location_Map m = new Location_Map();
		m.setLat(-38.383494);
		m.setLng(33.427362);
		p.setLocation(m);
		return p;
	}
	public String deletePlacePayload(String placeID) {
		 return "{\r\n"
		 		+ " \"place_id\": \""+placeID+"\"\r\n"
		 		+ "}";
	}

}
