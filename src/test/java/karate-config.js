function configuration() {
	
	karate.log("Loading karate-config");
	
	var config = {
			
	}
	
	config.baseURL = karate.properties['karate.baseURL'];
	karate.log("Karate baseURL:" + karate.properties['karate.baseURL']);
	
	karate.configure('connectTimeout', 60000);
	karate.configure('readTimeout', 60000);
	return config;
}