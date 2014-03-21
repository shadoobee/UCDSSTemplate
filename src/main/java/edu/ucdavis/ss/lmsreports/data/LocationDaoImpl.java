package edu.ucdavis.ss.lmsreports.data;





import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import edu.ucdavis.ss.lmsreports.domain.Location;
import edu.ucdavis.ss.lmsreports.rowmapper.LocationRowMapper;
@Repository("LocationDao")
@Transactional
public class LocationDaoImpl implements LocationDao {
	
@Autowired
private JdbcTemplate jdbcTemplate;




public JdbcTemplate getJdbcTemplate() {
	return jdbcTemplate;
}


public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}


public List<Location> getAll() {
	
	StringBuilder sql= new StringBuilder();
	sql.append(" SELECT * FROM LOCATION WHERE TOP_LEVEL_LMS_KEY = LMS_KEY ");
	return jdbcTemplate.query(sql.toString(), new LocationRowMapper());
	
}


@Override
public Location getLocationByAbbr(String abbr) {

	StringBuilder sql= new StringBuilder();
	sql.append(" SELECT * FROM LOCATION WHERE LOCATION_ABBR = ? ");
	Location location = (Location) jdbcTemplate.queryForObject(sql.toString(), new Object[]{abbr} , new LocationRowMapper());
	return location;
}


@Override
public Location getLocationById(int Id) {
	
	StringBuilder sql= new StringBuilder();
	sql.append(" SELECT * FROM LOCATION WHERE LOCATION_ID = ? ");
	Location location = (Location) jdbcTemplate.queryForObject(sql.toString(), new Object[]{Id} , new LocationRowMapper());
	return location;
}


@Override
public Location getLocationByLMSKey(int Id) {
	List<Location> locationList = jdbcTemplate.query("SELECT * FROM LOCATION WHERE LMS_KEY = " + String.valueOf(Id), new LocationRowMapper());
	if(locationList != null && locationList.size() > 0)
		return locationList.get(0);
	else
		return null;
}


@Override
public int insertLocation(Location location) {
	return jdbcTemplate.update("insert into LOCATION (LOCATION_NAME, LMS_KEY, TOP_LEVEL_LMS_KEY) values (?, ?, ?)", 
			location.getLocationName(), location.getLmsKey(), location.getTopLevelLMSKey());
}
	
}
