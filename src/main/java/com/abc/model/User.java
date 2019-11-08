package com.abc.model;

public class User {

	private Long id;

	private String displayName;

	public User(Long id, String displayName) {
		this.id = id;
		this.displayName = displayName;
	}

	public User() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public boolean equals(Object obj) { 
		if (this == obj) 
			return true; 
		if (obj == null || obj.getClass() != this.getClass()) 
			return false; 
		
		User user = (User) obj; 
		if (!this.id.equals(user.getId()))
			return false;
		
		if (this.displayName == null && user.getDisplayName() != null)
			return false;
		if (!this.displayName.equals(user.getDisplayName()))
			return false;
		
		return true;
	}
	  
	@Override
	public int hashCode() {  
		int hash = 5;
		hash = 2 * hash + this.id.hashCode();
		hash = 2 * hash + (this.displayName != null ? this.getDisplayName().hashCode() : 0);
		
		return hash;
	} 
	
	@Override
	public String toString() {
		return id + ", " + displayName;
	}
}
