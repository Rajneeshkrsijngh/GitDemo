package resources;

// enum is special class in java which has collection of constant and methods
public enum APIResource_Enum {

	AddPlaceAPI("/maps/api/place/add/json"), 
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

	private String resource;

	APIResource_Enum(String resource) {
		// TODO Auto-generated constructor stub
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
/*
Cucumber
│
│ "AddPlaceAPI"
▼
valueOf("AddPlaceAPI")
│
▼
APIResource_Enum
│
▼
AddPlaceAPI("/maps/api/place/add/json")
│
│ Constructor stores URL
▼
resource = "/maps/api/place/add/json"
│
▼
getResource()
│
▼
"/maps/api/place/add/json"
│
▼
POST API Request


*/