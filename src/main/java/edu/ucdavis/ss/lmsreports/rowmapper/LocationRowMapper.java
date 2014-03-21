package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucdavis.ss.lmsreports.domain.Location;

public class LocationRowMapper implements RowMapper<Location> {
	
	@Override
	public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
		Location location = new Location();
		location.setLocationId(rs.getInt("location_id"));
		location.setLocationAbbr(rs.getString("location_abbr"));
		location.setLocationCdHdr(rs.getString("location_cd_hr"));
		location.setLocationNameLong(rs.getString("location_name_long"));
		location.setShibIdProvider(rs.getString("shib_id_provider"));		
		location.setLocationCd(rs.getString("location_cd" ));
		location.setLocationName(rs.getString("location_name"));
		location.setLmsKey(rs.getInt("lms_key"));
		location.setTopLevelLMSKey(rs.getInt("top_level_lms_key"));
		return location;
	}
	
}