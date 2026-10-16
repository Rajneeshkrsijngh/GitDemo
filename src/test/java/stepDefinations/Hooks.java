package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		// execute this code when place id is null
		//write a code that will give you place id
		StepDefinations m= new StepDefinations();
		if(StepDefinations.place_id==null) {
		m.add_place_payload("Rahul", "Spanish", "Europe");
		m.user_calls_with_post_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Rahul", "getPlaceAPI");
		
		}
	}

}
