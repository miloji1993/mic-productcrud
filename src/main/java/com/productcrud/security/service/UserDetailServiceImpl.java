/**
 * 
 */
package com.productcrud.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.productcrud.security.model.User;
import com.productcrud.security.repository.UserRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findOneByUsername(username)
						.orElseThrow(() -> new UsernameNotFoundException("User not exist"));
		
		return new UserDetailsImpl(user);
	}

}
