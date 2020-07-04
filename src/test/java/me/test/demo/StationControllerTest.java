package me.test.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import me.test.demo.model.Station;

public class StationControllerTest extends DemoAbstractTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getSwaggerHome() throws Exception {
		String uri = "/";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
	}

	@Test
	public void getAllStations() throws Exception {
		String uri = "/all";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Station[] stationList = super.mapFromJson(content, Station[].class);
		assertTrue(stationList.length > 0);
	}

	@Test
	public void insertStation() throws Exception {
		String uri = "/station";

		Station station = new Station();
		station.setStationId("111");
		station.setName("test station");
		station.setHdEnabled(false);
		station.setCallSign("yes");
		String request = super.mapToJson(station);
		MvcResult mvcResult = mvc
				.perform(
						MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(request))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Station inserted successfully");
	}

	@Test
	public void insertStationFail() throws Exception {
		String uri = "/station";

		Station station = new Station();
		station.setName("test station");
		station.setHdEnabled(false);
		station.setCallSign("yes");
		String request = super.mapToJson(station);
		MvcResult mvcResult = mvc
				.perform(
						MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(request))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(500, status);
	}

	@Test
	public void updateStation() throws Exception {
		String uri = "/station";
		Station station = new Station();
		station.setStationId("456");
		station.setName("Station1");
		station.setHdEnabled(true);
		station.setCallSign("sign3");

		String inputJson = super.mapToJson(station);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Station updated successfully");
	}

	@Test
	public void updateStationFail() throws Exception {
		String uri = "/station";
		Station station = new Station();
		station.setName("Station1");
		station.setHdEnabled(true);
		station.setCallSign("sign3");

		String inputJson = super.mapToJson(station);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(500, status);
	}

	@Test
	public void deleteStation() throws Exception {
		String uri = "/station";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).param("stationId", "123")).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Station deleted successfully");
	}

	@Test
	public void deleteStationFail() throws Exception {
		String uri = "/station";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).param("stationId", "abc")).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(500, status);
	}

	@Test
	public void getStationById() throws Exception {
		String uri = "/station";
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri).param("value", "789").accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Station[] stationList = super.mapFromJson(content, Station[].class);
		assertTrue(stationList.length > 0);
	}

	@Test
	public void getHdEnabledStations() throws Exception {
		String uri = "/stations";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Station[] stationList = super.mapFromJson(content, Station[].class);
		assertTrue(stationList.length > 0);
	}
}