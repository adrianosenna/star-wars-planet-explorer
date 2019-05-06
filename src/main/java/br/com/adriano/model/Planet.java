package br.com.adriano.model;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planets")
public class Planet {

	@Id
	private String id;
	@NotEmpty
	@Length(max=75)
	private String name;
	@NotEmpty
	private String climate;
	@NotEmpty
	private String terrain;
	
	private int numberOfAppearanceInStarWars;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public int getNumberOfAppearanceInStarWars() {
		return numberOfAppearanceInStarWars;
	}

	public void setNumberOfAppearanceInStarWars(int numberOfAppearanceInStarWars) {
		this.numberOfAppearanceInStarWars = numberOfAppearanceInStarWars;
	}

}
