package br.com.ufc.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufc.model.Users;
import br.com.ufc.repository.UsersRepository;

@Repository
@Transactional
public class UserDetailsServiceImplementation implements UserDetailsService{
	
	@Autowired
	private UsersRepository userRepo;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException{
		Users user = userRepo.findByLogin(login);
		
		if(user == null) {
			throw new UsernameNotFoundException("Username not found!");
		}
		session.setAttribute("user", user);
		return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
	}

}
