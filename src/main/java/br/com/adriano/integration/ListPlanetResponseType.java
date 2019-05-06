package br.com.adriano.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListPlanetResponseType {

	private long count;
	private String next;
	private String previous;
	List<PlanetResponseType> results;

	public List<PlanetResponseType> getResults() {
		if (this.results == null) {
			return new ArrayList<PlanetResponseType>();
		}
		return results;
	}

	public void setResults(List<PlanetResponseType> results) {
		this.results = results;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	@Override
	public String toString() {
		return results.stream().map(PlanetResponseType::toString).collect(Collectors.joining(","));
	}
}
