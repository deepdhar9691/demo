package me.test.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import me.test.demo.model.Station;
import me.test.demo.repo.StationRepository;

@RestController
@Log4j2
public class StationController {

	@Autowired
	private StationRepository stationRepository;

	@GetMapping(value = "/")
	@ApiOperation(value = "This API is to get Swagger UI")
	public void swaggerHome(HttpServletResponse response) throws IOException {
		response.sendRedirect("swagger-ui.html");
	}

	@GetMapping(value = "/all", produces = "application/json")
	@ApiOperation(value = "This API returns all the stations available in the system")
	public List<Station> getAll() {
		return stationRepository.findAll();
	}

	@PostMapping(value = "/station", produces = "application/json")
	@ApiOperation(value = "adds station")
	public ResponseEntity<String> insertStation(@ApiParam(value = "Station attributes") @RequestBody Station station) {
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
	@ApiOperation(value = "updates station")
	public ResponseEntity<String> updateStation(@ApiParam(value = "Station attributes") @RequestBody Station station) {
		int result = stationRepository.update(station);
		if (result > 0) {
			return new ResponseEntity<String>("Station updated successfully", HttpStatus.OK);
		} else {

			return new ResponseEntity<String>("Failed to update station", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/station", produces = "application/json")
	@ApiOperation(value = "This API can be used to delete a station taking station id as input")
	public ResponseEntity<String> deleteStation(
			@ApiParam(value = "Station id") @RequestParam("stationId") String stationId) {

		int result = stationRepository.deleteStationById(stationId);
		if (result > 0) {
			return new ResponseEntity<String>("Station deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Station deletion failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/station", produces = "application/json")
	@ApiOperation(value = "This API is to get station(s) by name or id")
	public List<Station> findByIdOrName(@ApiParam(value = "Station id or name") @RequestParam("value") String value) {
		return stationRepository.findByIdOrName(value);
	}

	@GetMapping(value = "/stations", produces = "application/json")
	@ApiOperation(value = "This API gets all the HD enabled stations")
	public List<Station> getHdEnabledStations() {
		return stationRepository.getHdEnabledStations();
	}

}
