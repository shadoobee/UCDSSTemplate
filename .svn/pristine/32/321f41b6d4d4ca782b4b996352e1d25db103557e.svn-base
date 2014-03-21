package edu.ucdavis.ss.lmsreports.data;

import java.util.List;



import javax.sql.DataSource;



import edu.ucdavis.ss.lmsreports.domain.Person;
import edu.ucdavis.ss.lmsreports.domain.Role;

public interface PersonDao  {
	public List<Person>getAll();
	public Person getPerson(String id);
	public Person getPersonById(String id);
	public Person getPersonByEmail(String email);
	public Person getPersonByShibId(String id);
	public List<String> getActivityListForPerson(String id);
	public int insertPerson(Person person);
	public int updatePerson(Person person);
	public List<Role> getAllRoles();
	int deletePerson(String id);
	int deleteActivitiesForPerson(String id);
	int insertActivityForPerson(String personId, String activityCd);
}
