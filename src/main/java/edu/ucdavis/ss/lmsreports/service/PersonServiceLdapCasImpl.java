package edu.ucdavis.ss.lmsreports.service;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ucdavis.its.authpack.ShibHeaderValues;
import edu.ucdavis.ss.lmsreports.data.ActivityDao;
import edu.ucdavis.ss.lmsreports.data.LocationDao;
import edu.ucdavis.ss.lmsreports.data.PersonDao;
import edu.ucdavis.ss.lmsreports.domain.Activity;
import edu.ucdavis.ss.lmsreports.domain.Location;
import edu.ucdavis.ss.lmsreports.domain.Person;
import edu.ucdavis.ss.lmsreports.domain.Role;



@Service("personService")
public class PersonServiceLdapCasImpl implements PersonService{

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PersonServiceLdapCasImpl.class);

	@Autowired
	private HeaderHandler headerHandler;

	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private LocationDao locationDao;
	
	@Autowired
	private ActivityDao activityDao;


	@Override
	public void reconcilePerson(HttpServletRequest request)
	throws Exception{
	   ShibHeaderValues headerattributes = null;	
	  
		headerattributes = headerHandler.handle(request);
	
		
       
       String shibIdProvider = headerattributes.getShibIdentityProvider();
       String shibUCNetId = headerattributes.getShibUcnetId();
       String shibEmail = headerattributes.getShibEmailAddress();
       String shibId = headerattributes.getShibUserId();
       if(shibIdProvider == null)
    	   return;
       
       request.getSession().setAttribute("shibIDProvider", shibIdProvider);
       
       //Access level is now defined by role.  If user has the user role domain level is determined in the person table
   	   //determine access level
   	   Person person = null;
   	   try{
   		 person = this.findPersonByEmail(shibEmail);
   	   }catch(Exception e){
   		   LOG.info(e.getMessage());
   		   LOG.info("no user found shib email");
   	   }
   	   
   	   if(person == null){
   		   try{
   		   person = this.findPersonByShibId(shibId);
   		   }catch(Exception e){
   			   LOG.info("no user found for shib id");
   		   }
   	   }
   	   if(person == null){
   		   try{
   			 person = this.findPersonByNetID(shibUCNetId);
   		    
   		   }catch(Exception e){
   			throw new IllegalAccessException("No Access Granted");
   		   }
   	   }
   	   
   	  // if (person == null){
   	//	   throw new IllegalAccessException("No Access Granted");
   	 //  }
   	   
   	   List<Activity> temp = this.findActivitiesForPerson(person.getId());
   	   
   	   person.setActivityList(this.findActivitiesForPerson(person.getId()));
   	 
   	   
   	 //request.getSession().setAttribute("role", person.getRoleName());  
   	person.setLocation(this.findLocationById(person.getLocation_id()));
   	 request.getSession().setAttribute("person", person);
   	request.getSession().setAttribute("user", person.getFullName());
   	request.getSession().setAttribute("role", person.getRoleName());
   	 //request.getSession().setAttribute("location", location);
   	 //request.getSession().setAttribute("user", person.getFirstName() + " " + person.getLastName());
     
	}


	@Override
	public int deletePerson(String id) throws Exception {
		return personDao.deletePerson(id);
	}
	

	@Override
	public Person findPersonById(String id) throws Exception {
		return personDao.getPersonById(id);
	}
	

	@Override
	public Person findPersonByNetID(String netID) throws Exception {
		return personDao.getPerson(netID);
		
	}




	@Override
	public Person findPersonByEmail(String mail) throws Exception {
		return personDao.getPersonByEmail(mail);
	}




	@Override
	public List<Person> findAll() throws Exception {
	
		return personDao.getAll();
	}


	@Override
	public Location findLocationbyAbbr(String abbr) throws Exception {
		
		return locationDao.getLocationByAbbr(abbr);
	}


	@Override
	public Location findLocationById(int id) throws Exception {
		
		return locationDao.getLocationById(id);
	}
	
	
	@Override
	public Location findLocationByLMSKey(int id) throws Exception {
		return locationDao.getLocationByLMSKey(id);
	}


	@Override
	public List<Location> findAllLocations() throws Exception {
		return locationDao.getAll();
	}

	
	@Override
	public int insertLocation(Location location) throws Exception {
		return locationDao.insertLocation(location);
	}



	@Override
	public Person findPersonByShibId(String id) throws Exception {
		return personDao.getPersonByShibId(id);
	}


	@Override
	public List<Activity> findActivitiesForPerson(int personId) throws Exception {
		List<String> list = personDao.getActivityListForPerson(Integer.toString(personId));
		
		StringBuilder activityList = new StringBuilder();
		if(list != null && list.size() > 0){
		for(String str : list){
			    activityList.append("\'");
				activityList.append(str);
				activityList.append("\'");
				activityList.append(",");
			}
		
		
		if (activityList.length() > 0){
			activityList.deleteCharAt(activityList.length() -1 );
		}
		
		return activityDao.getActivitiesForPerson(activityList.toString());
		} else {
			return null;
		}
		
	}


	@Override
	public int updatePerson(Person person) throws Exception {
		return personDao.updatePerson(person);
	}


	@Override
	public int insertPerson(Person person) throws Exception {
		return personDao.insertPerson(person);
	}


	@Override
	public List<Role> findAllRoles() throws Exception {
		return personDao.getAllRoles();
	}


	@Override
	public int deleteActivitiesForPerson(String id) {
		return personDao.deleteActivitiesForPerson(id);
	}

	
	@Override
	public int insertActivityForPerson(String personId, String activityCd) {
		return personDao.insertActivityForPerson(personId, activityCd);
	}
}
