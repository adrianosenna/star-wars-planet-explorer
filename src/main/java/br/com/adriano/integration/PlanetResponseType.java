package br.com.adriano.integration;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetResponseType {

	private String climate;
	private String diameter;
	private String gravity;
	private String name;
	private String orbitalPeriod;
	private String population;
	private List<String> residents;
	private List<String> films;
	private String rotationPeriod;
	private String surfaceWater;
	private String terrain;
	private String url;

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getDiameter() {
		return diameter;
	}

	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}

	public String getGravity() {
		return gravity;
	}

	public void setGravity(String gravity) {
		this.gravity = gravity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("orbital_period")
	public String getOrbitalPeriod() {
		return orbitalPeriod;
	}

	@JsonProperty("orbital_period")
	public void setOrbitalPeriod(String orbitalPeriod) {
		this.orbitalPeriod = orbitalPeriod;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public List<String> getResidents() {
		return residents;
	}

	public void setResidents(List<String> residents) {
		this.residents = residents;
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}

	@JsonProperty("rotation_period")
	public String getRotationPeriod() {
		return rotationPeriod;
	}

	@JsonProperty("rotation_period")
	public void setRotationPeriod(String rotationPeriod) {
		this.rotationPeriod = rotationPeriod;
	}

	@JsonProperty("surface_water")
	public String getSurfaceWater() {
		return surfaceWater;
	}

	@JsonProperty("surface_water")
	public void setSurfaceWater(String surfaceWater) {
		this.surfaceWater = surfaceWater;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "planet:{climate: " + climate + ", diameter: " + diameter + 
				", gravity: " + gravity + ", name: " + name + ", orbitalPeriod: " + orbitalPeriod + 
				", population: " + population + ", residents: " + residents.toString() + 
				", films: " + films.toString() + ", rotationPeriod: " + rotationPeriod + 
				", surfaceWater: " + surfaceWater + ", terrain: " + terrain + ", url: " + url + "}";
	}
	
}
