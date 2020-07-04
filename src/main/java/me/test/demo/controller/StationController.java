package me.test.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import me.test.demo.model.Station;
import me.test.demo.repo.StationRepository;

@RestController
@Log4j2
public class StationController {

	@Autowired
	private StationRepository stationRepository;

	@GetMapping(value = "/all", produces = "application/json")
	public List<Station> getAll() {
		return stationRepository.findAll();
	}

	@PostMapping(value = "/station", produces = "application/json")
	public ResponseEntity<String> insertStation(@RequestBody Station station) {
		int result = 0;
		try {
			result = stationRepository.insert(station);
		} catch (Exception e) {
			log.error("failed to insert station ", e);
		}
		if (result > 0) {
			return new ResponseEntity<String>("Station inserted successfully", HttpStatus.OK);
		} else {

			return new ResponseEntity<String>("Failed to insert station", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/station", produces = "application/json")
	public ResponseEntity<String> updateStation(@RequestBody Station station) {
		int result = stationRepository.update(station);
		if (result > 0) {
			return new ResponseEntity<String>("Station updated successfully", HttpStatus.OK);
		} else {

			return new ResponseEntity<String>("Failed to update station", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/station", produces = "application/json")
	public ResponseEntity<String> deleteStation(@RequestParam("stationId") String stationId) {

		int result = stationRepository.deleteStationById(stationId);
		if (result > 0) {
			return new ResponseEntity<String>("Station deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Station deletion failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/station", produces = "application/json")
	public List<Station> findByIdOrName(@RequestParam("value") String value) {
		return stationRepository.findByIdOrName(value);
	}

	@GetMapping(value = "/stations", produces = "application/json")
	public List<Station> getHdEnabledStations() {
		return stationRepository.getHdEnabledStations();
	}

}
