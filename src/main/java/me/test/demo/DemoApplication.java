package me.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;
import me.test.demo.model.Station;
import me.test.demo.repo.StationRepository;

@Log4j2
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	StationRepository stationRepository;

	@Override
	public void run(String... args) throws Exception {
		log.info(stationRepository.insert(new Station("123", "Deepak", true, "deepak@gmail.com")));
		log.info(stationRepository.insert(new Station("456", "ajay", false, "ajay@gmail.com")));
		log.info(stationRepository.insert(new Station("789", "raj", true, "raj@gmail.com")));

	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
