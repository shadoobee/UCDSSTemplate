package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucdavis.ss.lmsreports.domain.Person;

public class PersonRowMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		   Person person = new Person();
		    person.setId(rs.getInt("person_id")); 
		    person.setFirstName(rs.getString("first_name"));
		    person.setLastName(rs.getString("last_name"));
		    person.setUcnetId(rs.getString("ucnet_id"));
		    person.setEmail(rs.getString("email"));
		    person.setRoleId(rs.getInt("role_id"));
		    person.setRoleName(rs.getString("role_name"));
		    person.setLocation_id(rs.getInt("location_id"));
		    person.setShibUserId(rs.getString("shib_user_id"));
		    return person;
		
	}

}
