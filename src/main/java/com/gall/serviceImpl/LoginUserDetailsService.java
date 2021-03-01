package com.gall.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gall.dao.UserDao;
import com.gall.model.LoginUser;

@Service("userDetailsService")
public class LoginUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDao userdao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginUser user=userdao.getGetUserDetails(username);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		if(user.getUsername().equalsIgnoreCase(username)) {
			return new User(user.getUsername(),hashedPassword, new ArrayList<>());
		}
		return null;
	}
	
	/*
	 * private List<GrantedAuthority> getGrantedAuthorities(LoginUser user) {
	 * ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	 * String role = user.getUsername(); authorities.add(new
	 * SimpleGrantedAuthority(role)); return authorities; }
	 */

}
