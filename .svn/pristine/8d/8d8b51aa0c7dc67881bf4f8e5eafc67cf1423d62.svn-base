package edu.ucdavis.ss.lmsreports.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ucdavis.ss.lmsreports.domain.Person;
import edu.ucdavis.ss.lmsreports.domain.Role;
import edu.ucdavis.ss.lmsreports.rowmapper.PersonRowMapper;
import edu.ucdavis.ss.lmsreports.rowmapper.RoleRowMapper;
@Repository("PersonDao")
@Transactional
public class PersonDaoImpl implements PersonDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Person getPersonByEmail(String email) {
		
		StringBuilder sql= new StringBuilder();
		sql.append(" SELECT person_id, last_name, first_name, ucnet_id, shib_user_id, email, location_id, r.role_id, role_name FROM PERSON p ");
		 sql.append(" join role r on p.ROLE_ID = r.ROLE_ID ");
		 sql.append(" WHERE EMAIL = ?");
		 Person person =  (Person)jdbcTemplate.queryForObject(
			      sql.toString(), new Object[] { email }, new PersonRowMapper());
		 return  person;
	}

	

	@Override
	public List<Person> getAll() {
		
		StringBuilder sql= new StringBuilder();
//		sql.append(" SELECT * FROM PERSON ");
		sql.append("SELECT person_id, last_name, first_name, ucnet_id, shib_user_id, email, location_id, r.role_id, role_name FROM person p ");
			sql.append("INNER JOIN role r ON p.role_id = r.role_id ORDER BY first_name");
		return jdbcTemplate.query(sql.toString(), new PersonRowMapper());
	}

	

	@Override
	public Person getPerson(String id) {
		
	
		StringBuilder sql= new StringBuilder();
		sql.append(" SELECT person_id, last_name, first_name, ucnet_id, shib_user_id, email, location_id, r.role_id, role_name FROM PERSON p ");
		 sql.append(" join role r on p.ROLE_ID = r.ROLE_ID ");
		 sql.append(" WHERE UCNET_ID = ?");
		  Person person =  (Person)jdbcTemplate.queryForObject(
	      sql.toString(), new Object[] { id }, new PersonRowMapper());
		return  person;
	}

	@Override
	public Person getPersonById(String id) {
		StringBuilder sql= new StringBuilder();
		sql.append(" SELECT person_id, last_name, first_name, ucnet_id, shib_user_id, email, location_id, r.role_id, role_name FROM PERSON p ");
		sql.append(" join role r on p.ROLE_ID = r.ROLE_ID ");
		sql.append(" WHERE PERSON_ID = ?");
		Person person = (Person)jdbcTemplate.queryForObject(sql.toString(), new Object[] { id }, new PersonRowMapper());
		return person;
	}

	@Override
	public Person getPersonByShibId(String id) {
		
		StringBuilder sql= new StringBuilder();
		sql.append(" SELECT person_id, last_name, first_name, ucnet_id, shib_user_id, email, location_id, r.role_id, role_name FROM PERSON p ");
		 sql.append(" join role r on p.ROLE_ID = r.ROLE_ID ");
		 sql.append(" WHERE SHIB_USER_ID = ?");
		  Person person =  (Person)jdbcTemplate.queryForObject(
	      sql.toString(), new Object[] { id }, new PersonRowMapper());
		return  person;
	}

	@Override
	public List<String> getActivityListForPerson(String id) {
		 StringBuilder sql = new StringBuilder();
		 sql.append("SELECT activity_cd FROM person_activity" );
		 sql.append(" where person_id = ?");
		 return (List<String>) jdbcTemplate.queryForList(sql.toString(), new Object[] {id}, String.class);
		
	}
	
	
	@Override
	public int insertPerson(Person person) {
		return jdbcTemplate.queryForInt("insert into PERSON (LAST_NAME, FIRST_NAME, UCNET_ID, EMAIL, LOCATION_ID, ROLE_ID) values (?, ?, ?, ?, ?, ?); select SCOPE_IDENTITY();", 
			person.getLastName(), person.getFirstName(), person.getUcnetId(), person.getEmail(), person.getLocation_id(), person.getRoleId());
	}

	@Override
	public int updatePerson(Person person) {
		return jdbcTemplate.update("update PERSON set LAST_NAME = ?, FIRST_NAME = ?, UCNET_ID = ?, EMAIL = ?, LOCATION_ID = ?, ROLE_ID = ? where PERSON_ID = ?", 
				person.getLastName(), person.getFirstName(), person.getUcnetId(), person.getEmail(), person.getLocation_id(), person.getRoleId(), person.getId());
	}

	@Override
	public int deletePerson(String id) {
		return jdbcTemplate.update("delete from PERSON where PERSON_ID = ?", id);
	}

	@Override
	public List<Role> getAllRoles() {
		return (List<Role>) jdbcTemplate.query("select * from ROLE", new RoleRowMapper());
	}
	
	@Override
	public int deleteActivitiesForPerson(String id) {
		return jdbcTemplate.update("delete from person_activity where person_id = ?", id);
	}

	@Override
	public int insertActivityForPerson(String personId, String activityCd) {
		return jdbcTemplate.update("insert into person_activity (person_id, activity_cd) values (?, ?);", personId, activityCd);
	}

}
