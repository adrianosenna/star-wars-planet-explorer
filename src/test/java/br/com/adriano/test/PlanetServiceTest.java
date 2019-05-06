package br.com.adriano.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.adriano.integration.ListPlanetResponseType;
import br.com.adriano.integration.SwapiRestService;
import br.com.adriano.model.Planet;
import br.com.adriano.service.PlanetService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetServiceTest {

	@Autowired
	private PlanetService planetService;
	@Autowired
	private SwapiRestService swapiService;

	private List<Planet> planets;
	private ObjectMapper objectMapper;
	private Pageable pageable;

	@Before
	public void init() throws JsonParseException, JsonMappingException, IOException {
		Planet planet;
		pageable = Pageable.unpaged();
		objectMapper = new ObjectMapper();
		planets = new ArrayList<Planet>();
		planet = objectMapper.readValue(new File("src/test/resources/tatooine.json"), Planet.class);
		planets.add(planet);
		planet = objectMapper.readValue(new File("src/test/resources/alderaan.json"), Planet.class);
		planets.add(planet);
		planet = objectMapper.readValue(new File("src/test/resources/yaviniv.json"), Planet.class);
		planets.add(planet);
		planet = objectMapper.readValue(new File("src/test/resources/hoth.json"), Planet.class);
		planets.add(planet);
	}

	@Test
	public void testSave() {
		planets.forEach(item -> planetService.save(item));
		planets.forEach(item -> assertNotNull(item.getId()));
		planets.forEach(item -> assertEquals(item.getNumberOfAppearanceInStarWars(),
				recoverNumberOfAppearanceInStarWars(item.getName())));
	}

	@Test
	public void testFindAll() {
		Page<Planet> page = planetService.findAll(null, pageable);
		assertNotNull(page);
		assertTrue(page.hasContent());
	}

	@Test
	public void testFindByName() {
		Page<Planet> page = planetService.findAll("Tatooine", pageable);
		assertNotNull(page);
		assertTrue(page.hasContent());
	}

	@Test
	public void testFindByNameNoResult() {
		Page<Planet> page = planetService.findAll("TESTE#1234@QAZ", pageable);
		assertNotNull(page);
		assertFalse(page.hasContent());
	}

	@Test
	public void testFindById() {
		planets.forEach(item -> planetService.save(item));
		planets.forEach(item -> assertNotNull(planetService.findById(item.getId())));
		planets.forEach(item -> planetService.remove(item.getId()));
	}

	@Test
	public void testRemove() {
		planets.forEach(item -> planetService.save(item));
		planets.forEach(item -> assertNotNull(planetService.remove(item.getId())));
	}

	private int recoverNumberOfAppearanceInStarWars(String planetName) {
		ListPlanetResponseType response = swapiService.findByName(planetName);
		if (response != null && !response.getResults().isEmpty()) {
			return response.getResults().get(0).getFilms().size();
		}
		return 0;
	}

}
