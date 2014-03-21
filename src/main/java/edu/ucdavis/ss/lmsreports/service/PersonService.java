package edu.ucdavis.ss.lmsreports.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.ucdavis.ss.lmsreports.domain.Activity;
import edu.ucdavis.ss.lmsreports.domain.Location;
import edu.ucdavis.ss.lmsreports.domain.Person;
import edu.ucdavis.ss.lmsreports.domain.Role;

public interface PersonService {
	void reconcilePerson(HttpServletRequest request) throws Exception;
	Person findPersonById(String id) throws Exception;
	Person findPersonByNetID(String netID) throws Exception;
	Person findPersonByEmail(String mail) throws Exception;
	Person findPersonByShibId(String id) throws Exception;
	List<Person> findAll() throws Exception;
	int deletePerson(String id) throws Exception;
	
	Location findLocationbyAbbr(String abbr) throws Exception;
	Location findLocationById(int id) throws Exception;
	Location findLocationByLMSKey(int id) throws Exception;
	List<Location> findAllLocations() throws Exception;
	int insertLocation(Location location) throws Exception;
	
	List<Activity> findActivitiesForPerson(int personId) throws Exception;
	int updatePerson(Person person) throws Exception;
	int insertPerson(Person person) throws Exception;
	List<Role> findAllRoles() throws Exception;
	int deleteActivitiesForPerson(String id);
	int insertActivityForPerson(String personId, String activityCd);
}