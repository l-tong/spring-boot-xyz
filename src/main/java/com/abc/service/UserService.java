package com.abc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.dao.UserDaoImpl;
import com.abc.model.User;
import com.abc.model.UserGroupMembership;

@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDaoImpl userDao;
	
	public UserService(UserDaoImpl userDao) {
		this.userDao = userDao;
	}
 
	public List<User> getAllSystem1User() {
		return userDao.getAllSystem1User();
	}

	public List<User> getAllSystem2User() {
		return userDao.getAllSystem2User();
	}
 
	 public void printDiffUsers() {
		List<User> system1Users = userDao.getAllSystem1User();
		List<User> system2Users = userDao.getAllSystem2User();
		 
		LOGGER.info("Users are in System 1 but not in System2 in CSV format.  They will be add/update in System2");
		LOGGER.info("SYSTEM1_USER_ID, SYSTEM1_DISPLAYNAME");
		system1Users.forEach(user->{
			if (!system2Users.contains(user))
				System.out.println(user.getId() + ", " + user.getDisplayName());
		});
		
		LOGGER.info("-------");
		LOGGER.info("Users are in System2 but not in System1 in CSV format.  They will be remove from System2");
		LOGGER.info("SYSTEM2_USER_ID, SYSTEM1_DISPLAYNAME");
		system2Users.forEach(user->{
			if (!system1Users.contains(user))
				System.out.println(user.getId() + ", " + user.getDisplayName());
		});
		 
		List<UserGroupMembership> system1UGMs = userDao.getAllSystem1UserGroupMembership();
		List<UserGroupMembership> system2UGMs = userDao.getAllSystem2UserGroupMembership();
		
		LOGGER.info("-------");
		LOGGER.info("User Group Memberships are in System1 but not in System2 in CSV format.  They will be add/update in System2");
		LOGGER.info("SYSTEM1_USER_ID, SYSTEM1_GROUP_NAME");
		system1UGMs.forEach(ugm->{
			if (!system2UGMs.contains(ugm))
				System.out.println(ugm.getId() + ", " + ugm.getGroupName());
		});
		
		LOGGER.info("-------");
		LOGGER.info("User Group Memberships are in System 1 but not in System2 in CSV format.  They will be removed in System2");
		LOGGER.info("SYSTEM2_USER_ID, SYSTEM2_GROUP_NAME");
		system2UGMs.forEach(ugm->{
			if (!system1UGMs.contains(ugm))
				System.out.println(ugm.getId() + ", " + ugm.getGroupName());
		});
		
	}

	 public void fixDiffUsers() {
		List<User> system1Users = userDao.getAllSystem1User();
		List<User> system2Users = userDao.getAllSystem2User();
		//User idMatchUser = null;
		LOGGER.info("Sync system1 users to system2");
		system1Users.forEach(user->{
			// find if there is exact match
			if (!system2Users.contains(user)) {
				// find if ID exists
				final User idMatchUser = system2Users.stream()
				  .filter(user2 -> user.getId().equals(user.getId()))
				  .findAny()
				  .orElse(null);
				if (idMatchUser == null) {
					LOGGER.info("Inserting: " + user);
					userDao.insert(user);
				}
				else {
					LOGGER.info("Updating: " + user);
					userDao.update(user);
				}
			}
		});
		 
		LOGGER.info("Deleting users no longer in System1 from System2");
		LOGGER.info("SYSTEM2_USER_ID, SYSTEM2_DISPLAYNAME");
		system2Users.forEach(user->{
			final User idMatchUser = system1Users.stream()
			  .filter(user2 -> user.getId().equals(user.getId()))
			  .findAny()
			  .orElse(null);
			if (idMatchUser == null) {
				LOGGER.info("Deleting: " + user);
				userDao.delete(user);
			}
		});
		 
		List<UserGroupMembership> system1UGMs = userDao.getAllSystem1UserGroupMembership();
		List<UserGroupMembership> system2UGMs = userDao.getAllSystem2UserGroupMembership();
		
		LOGGER.info("Add/Update User Group Memberships in System2");
		system1UGMs.forEach(ugm->{
			// since ID and Group name are both PK, both have to match
			if (!system2UGMs.contains(ugm))
				userDao.insertUserGroupMembership(ugm);
		});
		 
		LOGGER.info("Deleting User Group Memberships no longer in System1 from System2");
		system2UGMs.forEach(ugm->{
			if (!system1UGMs.contains(ugm))
				userDao.deleteUserGroupMembership(ugm);
		});
		
	 }
}