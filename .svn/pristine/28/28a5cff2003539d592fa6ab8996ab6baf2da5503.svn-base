package edu.ucdavis.ss.lmsreports.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ucdavis.its.authpack.ShibHeaderValues;
import edu.ucdavis.ss.lmsreports.data.PersonDao;
import edu.ucdavis.ss.lmsreports.data.PingDao;
import edu.ucdavis.ss.lmsreports.domain.Activity;
import edu.ucdavis.ss.lmsreports.domain.Domain;
import edu.ucdavis.ss.lmsreports.domain.Location;
import edu.ucdavis.ss.lmsreports.domain.Person;
import edu.ucdavis.ss.lmsreports.domain.Role;
import edu.ucdavis.ss.lmsreports.service.LMSReportService;
import edu.ucdavis.ss.lmsreports.service.UserSpoofingDetailsService;
import edu.ucdavis.ss.lmsreports.service.PersonService;
import edu.ucdavis.ss.lmsreports.ui.UserFormUi;

@Controller
public class AdminController {
	
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AdminController.class);
	
	@Autowired
	PersonService personService;
	
	@Autowired
	PersonDao personDao;
	
	@Autowired
	LMSReportService reportService;
	
	@Autowired
	PingDao pingDao;
	
	@Autowired
	private UserSpoofingDetailsService userSpoofingDetailsService;

	@RequestMapping(value="ping.htm")
	public String pingDb(HttpServletRequest request, HttpServletResponse response) throws Exception{
	   String str = pingDao.pingDB();
	   if(str == null){
		   return "error";
	   }
		return "ping";
	}

	@RequestMapping(value="login.htm")
	public String shibSpoof( HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
	
		
		
		
		boolean spoof = false;
		List<ShibHeaderValues> spoofUsers = null;
		
		if (UserSpoofingDetailsService.spoofCheck) {
			spoofUsers = userSpoofingDetailsService.getSpoofableUsersList();
			spoof = true;
		}
				
		model.addAttribute("spoofUsers", spoofUsers);
		model.addAttribute("spoof", spoof);
		
		return "login";

	}

	
	@RequestMapping(value="listusers.htm")
	public String listUsers(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
	
		Person person = (Person)request.getSession().getAttribute("person");
		
		if(person == null || !"Admin".equals(person.getRoleName()))
			return "login";

		List<Person> users = personService.findAll();
		List<Location> campusList = personService.findAllLocations();
		List<Role> roleList = personService.findAllRoles();
		
		LOG.info("Number of users found: " + users.size());
		LOG.info("Number of campuses found: " + campusList.size());
		LOG.info("Number of roles found: " + roleList.size());
		
//		LOG.info("Looking up locations for users ...");
//		
//		for( int i = 0; i < users.size(); i++ )
//			users.get(i).setLocation(personService.findLocationById(users.get(i).getLocation_id()));
		
		UserFormUi userForm = new UserFormUi();
		userForm.setCampusList(campusList);
		userForm.setRoleList(roleList);
		
	    model.addAttribute("userForm", userForm);
		model.addAttribute("users", users);
		model.addAttribute("isAdmin", true);
		
		return "listusers";
			
	}

	
	@RequestMapping(value="updateuser.htm", method = RequestMethod.POST)
	public @ResponseBody void updateUser(@ModelAttribute("userForm") @Valid UserFormUi userForm, HttpServletRequest request, OutputStream outputStream) throws Exception{
	
		Person person = (Person)request.getSession().getAttribute("person");
		
		if(person == null || !"Admin".equals(person.getRoleName())) {
			statusUpdateJSON("error", outputStream);
			return;
		}
			
		LOG.info("--- First Name: [" + userForm.getFirstName() + "]");
		LOG.info("--- Last Name: [" + userForm.getLastName() + "]");
		LOG.info("--- Email: [" + userForm.getEmail() + "]");
		LOG.info("--- UCNetID: [" + userForm.getUcnetId() + "]");
		LOG.info("--- Campus ID: [" + userForm.getCampusId() + "]");
		LOG.info("--- Location ID: [" + userForm.getLocation_id() + "]");
		LOG.info("--- Role ID: [" + userForm.getRoleId() + "]");
		LOG.info("--- Activities: [" + userForm.getActivities() + "]");
		
		
		if(	userForm.getFirstName() == null || userForm.getFirstName().trim().isEmpty() ||
			userForm.getLastName() == null || userForm.getLastName().trim().isEmpty() ||
			userForm.getEmail() == null || userForm.getEmail().trim().isEmpty() ||
			userForm.getCampusId() == 0 ||
			userForm.getLocation_id() == 0 ||
			userForm.getRoleId() == 0
		) {
				LOG.info("Error: Validation didn't pass!");
				statusUpdateJSON("error", outputStream);
				return;
		}
		
		Person user = new Person();
		
		user.setId(userForm.getPersonId());
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setUcnetId(userForm.getUcnetId());
		user.setEmail(userForm.getEmail());
		user.setRoleId(userForm.getRoleId());
		
		Location location = personService.findLocationByLMSKey(userForm.getLocation_id());
		
		if(location == null) {
			LOG.info("Cound NOT find the location. Adding it...");
			
			Domain domain = reportService.getDomainByLMSKey(String.valueOf(userForm.getLocation_id()));
			
			if(domain == null) {
				statusUpdateJSON("error", outputStream);
				return;
			}

			location = new Location();
			location.setLocationName(domain.getDomainName());
			location.setLmsKey(domain.getDomainId());
			location.setTopLevelLMSKey(userForm.getCampusId());
			
			personService.insertLocation(location);
			
			location.setLocationId(personService.findLocationByLMSKey(userForm.getLocation_id()).getLocationId());
		}
		
		user.setLocation_id((int)location.getLocationId());

		Integer user_id = userForm.getPersonId();

		if(user_id != null && user_id != 0) {
			if(personService.findPersonById(String.valueOf(user_id)) == null) {
				LOG.info("Error: User doesn't exist!");
				statusUpdateJSON("error", outputStream);
				return;
			}
			
			LOG.info("Updating user with person_id = [" + user_id + "] ...");
			personService.updatePerson(user);
			LOG.info("... Updated user with person_id = [" + user_id + "]");

		} else {
			LOG.info("Adding a new user ...");
			int newUserId = personService.insertPerson(user);
			user.setId(newUserId);
			LOG.info("... Added a new user with person_id = [" + newUserId + "]");
		}
		
		List<String> activityList = Arrays.asList(userForm.getActivities().split("\\s*(,|\\s)\\s*"));
		
		if(activityList != null && activityList.size() > 0) {
			personService.deleteActivitiesForPerson(String.valueOf(user.getId()));
			
			for( int i = 0; i < activityList.size(); i++ ) {
				String activityCd = activityList.get(i).trim();
				if(!activityCd.isEmpty()) {
					LOG.info("----- Inserting activity: [" + activityCd + "]");
					personService.insertActivityForPerson(String.valueOf(user.getId()), activityCd);
				}
			}
		}

		statusUpdateJSON("success", outputStream);
		
	}
	
	
	@RequestMapping(value = "deleteuser.htm", method = RequestMethod.GET)
	public @ResponseBody void deleteUser(HttpServletRequest request, OutputStream outputStream) throws Exception{
	
		Person person = (Person)request.getSession().getAttribute("person");
		
		if(person == null || !"Admin".equals(person.getRoleName()))
			return;

		
		String personId = request.getParameter("personId");
		
		LOG.info("Deleting user with personID: [" + personId + "] ...");
		
		personService.deletePerson(personId);
		
		statusUpdateJSON("success", outputStream);
		
		LOG.info("Exiting deleteUser()");
	}
	
	
	@RequestMapping(value = "getdomains.htm", method = RequestMethod.GET)
	public @ResponseBody void getDomains(HttpServletRequest request, OutputStream outputStream) throws JsonGenerationException, IOException{
	
		Person person = (Person)request.getSession().getAttribute("person");
		
		if(person == null || !"Admin".equals(person.getRoleName()))
			return;
		
		
		String campusId = request.getParameter("campusId");
		
		LOG.info("Getting a list of domains for campusID: [" + campusId + "] ...");
		
		List<Domain> locationList = reportService.getDomainsByLocation(campusId);

		LOG.info("Number of domains found: " + locationList.size());
		
		JsonFactory factory = new JsonFactory();
		JsonGenerator json = factory.createJsonGenerator(outputStream);
		json.writeStartArray();
		Iterator iterator = locationList.iterator();

		while (iterator.hasNext()) {
		    Domain domain = (Domain) iterator.next();
			json.writeStartObject();
			json.writeNumberField("domainId", domain.getDomainId());
			json.writeStringField("domainName", domain.getDomainName());
			json.writeNumberField("level", domain.getLevel());
			json.writeStringField("domainNamePadded", domain.getDomainNamePadded());
			json.writeEndObject();
		}
		json.writeEndArray();
		json.close();
		
		LOG.info("Exiting getDomains()");
	}

	
	@RequestMapping(value = "getuserinfo.htm", method = RequestMethod.GET)
	public @ResponseBody void getUserInfo(HttpServletRequest request, OutputStream outputStream) throws Exception{
	
		Person person = (Person)request.getSession().getAttribute("person");
		
		if(person == null || !"Admin".equals(person.getRoleName()))
			return;

		
		String personId = request.getParameter("personId");
		
		LOG.info("Getting info for personID: [" + personId + "] ...");
		
		Person user = personService.findPersonById(personId);
		
		if(user == null) {
			LOG.info("Did NOT find a user for personID: [" + personId + "]");
			return;
		}
		
		Location location = personService.findLocationById(user.getLocation_id());
//		List<Activity> activityList = personService.findActivitiesForPerson(user.getId());
		List<String> activityList = personDao.getActivityListForPerson(String.valueOf(user.getId()));
		
		JsonFactory factory = new JsonFactory();
		JsonGenerator json = factory.createJsonGenerator(outputStream);
		
		json.writeStartObject();
		json.writeNumberField("personId", user.getId());
		json.writeStringField("firstName", user.getFirstName());
		json.writeStringField("lastName", user.getLastName());
		json.writeStringField("email", user.getEmail());
		json.writeStringField("ucnetId", user.getUcnetId());
		json.writeNumberField("campusId", location.getTopLevelLMSKey());
		json.writeNumberField("location_id", location.getLmsKey());
		json.writeNumberField("roleId", user.getRoleId());
		json.writeFieldName("activities");
		json.writeStartArray();

		if(activityList != null) {
			LOG.info("Number of activities found for this user: " + activityList.size());
			for( int i = 0; i < activityList.size(); i++ ) {
//				json.writeString(activityList.get(i).getActivityCd());
				json.writeString(activityList.get(i));
			}
		}
	 
		json.writeEndArray();
		json.writeEndObject();
		json.close();
		
		LOG.info("Exiting getUserInfo()");
	}
	
	
	private void statusUpdateJSON(String status, OutputStream outputStream) throws IOException {
		JsonFactory factory = new JsonFactory();
		JsonGenerator json = factory.createJsonGenerator(outputStream);
		
		json.writeStartObject();
		json.writeStringField("status", status);
		json.writeEndObject();

		json.close();
		
		LOG.info("Status: " + status);
	}

}
