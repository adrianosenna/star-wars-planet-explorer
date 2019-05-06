package br.com.adriano.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.adriano.model.Planet;
import br.com.adriano.service.PlanetService;

@RestController
@RequestMapping("/planets")
public class PlanetResource {

	@Autowired
	private PlanetService planetService;

	@PostMapping
	public ResponseEntity<Planet> create(@RequestBody @Valid Planet planet) {
		planet = planetService.save(planet);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(planet).toUri();
		return ResponseEntity.created(uri).body(planet);
	}

	@GetMapping
	public ResponseEntity<Page<Planet>> findAll(Pageable pageable,
			@RequestParam(name = "name", required = false) String name) {
		Page<Planet> page = planetService.findAll(name, pageable);
		return page.hasContent() ? ResponseEntity.ok().body(page) : ResponseEntity.notFound().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Planet> findById(@PathVariable("id") String id) {
		return planetService.findById(id).map(planet -> ResponseEntity.ok().body(planet))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		return planetService.remove(id).map(planet -> ResponseEntity.noContent().build())
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	/*
	  Endpoint for update the number of appearances of the planets saved.
	 
	@GetMapping
	public ResponseEntity gettUpdates() {
		return ResponseEntity.ok().build();
	}
	*/

}
