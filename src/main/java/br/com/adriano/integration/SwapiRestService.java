package br.com.adriano.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;

@Component
public class SwapiRestService {

	Logger logger = LoggerFactory.getLogger(SwapiRestService.class);
	
	@Value("${swapi.base.url}")
	private String swapiBaseURL;
	private RestTemplate restTemplate;
	private HttpEntity<String> entity;
	
	public SwapiRestService() {
		logger.info("::: initializing the service");
		this.restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
        headers.add("User-Agent", "AdrianoJunior/adr.senna@gmail.com");
        headers.add("Cache-Control", "no-cache");
        headers.add("Connection", "keep-alive");
        this.entity = new HttpEntity<String>("parameters", headers);
	}
	
	public ListPlanetResponseType findByName(String name) {
		ResponseEntity<ListPlanetResponseType> response = this.restTemplate.exchange(search(getPlanetsEndpoint(), name), HttpMethod.GET, entity, ListPlanetResponseType.class);
		return response.getBody();
	}
	
	public String search(String endpoint, String filter) {
		logger.info("::: planet searching by name >>> " + endpoint.concat("?search=").concat(filter));
		return endpoint.concat("?search=").concat(filter);
	}
	
	public String getPlanetsEndpoint() {
		logger.info("::: planet endpoint >>> " + swapiBaseURL.concat("planets"));
		return swapiBaseURL.concat("planets");
	}
	
}
