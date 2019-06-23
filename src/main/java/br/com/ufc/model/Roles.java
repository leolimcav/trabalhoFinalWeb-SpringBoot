package br.com.ufc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Roles implements GrantedAuthority{
	
	@Id
	private String roleName;
	
	@OneToMany
	private List<Users> user;
	
	public Roles(String roleName) {
		this.roleName = roleName;
	}

	public Roles() {}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Users> getUser() {
		return user;
	}

	public void setUser(List<Users> user) {
		this.user = user;
	}

	@Override
	public String getAuthority() {
		return this.getRoleName();
	}
	
}
