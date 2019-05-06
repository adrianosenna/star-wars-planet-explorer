package br.com.adriano.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import br.com.adriano.integration.ListPlanetResponseType;
import br.com.adriano.integration.PlanetResponseType;
import br.com.adriano.integration.SwapiRestService;
import br.com.adriano.model.Planet;
import br.com.adriano.repository.PlanetRepository;

@Service
public class PlanetService {
	
	Logger logger = LoggerFactory.getLogger(PlanetService.class);

	@Autowired
	private PlanetRepository repository;
	@Autowired
	private SwapiRestService swapiService;

	public Page<Planet> findAll(String name, Pageable pageable) {
		logger.info("Searching for planets > name:" + name + "; pageable:" + pageable.toString()); 
		if (Strings.isNullOrEmpty(name)) {
			return repository.findAll(pageable);
		}
		return repository.findByName(name, pageable);
	}
	
	public Planet save(Planet planet) {
		setNumberOfAppearanceInStarWars(planet);
		logger.info("Saving planet > " + planet.toString());
		repository.save(planet);
		logger.info("Planet has been saved");
		return planet;
	}

	public Optional<Planet> remove(String id) {
		Optional<Planet> planet = repository.findById(id);
		planet.ifPresent(repository::delete);
		return planet;
	}

	public Optional<Planet> findById(String id) {
		return repository.findById(id);
	}

	private void setNumberOfAppearanceInStarWars(Planet planet) {
		logger.info("Recovering the number of appearence in star wars");
		ListPlanetResponseType listPlanetResponseType = swapiService.findByName(planet.getName());
		if (listPlanetResponseType != null && !listPlanetResponseType.getResults().isEmpty()) {
			PlanetResponseType planetType = listPlanetResponseType.getResults().get(0);
			planet.setNumberOfAppearanceInStarWars(planetType.getFilms().size());
		}
	}

}
