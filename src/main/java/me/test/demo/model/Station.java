package me.test.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Station {
	private String stationId;
	private String name;
	private Boolean hdEnabled;
	private String callSign;
}
