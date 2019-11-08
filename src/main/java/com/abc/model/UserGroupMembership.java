package com.abc.model;

public class UserGroupMembership {

	private Long id;
	private String groupName;

	public UserGroupMembership(Long id, String groupName) {
		this.id = id;
		this.groupName = groupName;
	}

	public UserGroupMembership() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public boolean equals(Object obj) { 
		if (this == obj) 
			return true; 
		if (obj == null || obj.getClass() != this.getClass()) 
			return false; 
		
		UserGroupMembership ugm = (UserGroupMembership) obj; 
		if (!this.id.equals(ugm.getId()))
			return false;
		
		if (this.groupName == null && ugm.getGroupName() != null)
			return false;
		if (!this.groupName.equals(ugm.getGroupName()))
			return false;
		
		return true;
	}
	  
	@Override
	public int hashCode() {  
		int hash = 5;
		hash = 3 * hash + this.id.hashCode();
		hash = 3 * hash + (this.groupName != null ? this.groupName.hashCode() : 0);
		
		return hash;
	} 
	
	@Override
	public String toString() {
		return id + ", " + groupName;
	}
}
