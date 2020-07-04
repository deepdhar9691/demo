package me.test.demo.repo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import me.test.demo.model.Station;

@Mapper
public interface StationRepository {

	@Select("select * from station")
	public List<Station> findAll();

	@Insert("insert into station(stationid, name, hdenabled,callsign) "
			+ " values (#{stationId}, #{name}, #{hdEnabled}, #{callSign})")
	public int insert(Station station);

	@Update("update station set name=#{name}, "
			+ " hdenabled=#{hdEnabled}, callsign=#{callSign} where stationid=#{stationId}")
	public int update(Station station);

	@Select("select * from station where stationid = #{value} or name = #{value}")
	public List<Station> findByIdOrName(String value);

	@Select("select * from station where hdenabled is true")
	public List<Station> getHdEnabledStations();

	@Delete("delete from station where stationid = #{id}")
	public int deleteStationById(String id);
}
