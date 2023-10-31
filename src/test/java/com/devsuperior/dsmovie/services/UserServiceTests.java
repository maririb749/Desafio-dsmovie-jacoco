package com.devsuperior.dsmovie.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperior.dsmovie.entities.UserEntity;
import com.devsuperior.dsmovie.projections.UserDetailsProjection;
import com.devsuperior.dsmovie.repositories.UserRepository;
import com.devsuperior.dsmovie.tests.UserDetailsFactory;
import com.devsuperior.dsmovie.tests.UserFactory;
import com.devsuperior.dsmovie.utils.CustomUserUtil;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class UserServiceTests {

	@InjectMocks
	private UserService service;
	
	@Mock
	private UserRepository repository;
	
	@Mock
	private CustomUserUtil userUtil;
	
	private String existingUsername;
	private String nonExistingUsername;
	private UserEntity user;
	
	
	
	@BeforeEach
	public void setUp()throws Exception  {
		existingUsername = "maria@gmail.com";
		nonExistingUsername = "amora@gmail.com";
		
		user = UserFactory.createUserEntity();
		
		
	
		
		Mockito.when(repository.findByUsername(existingUsername)).thenReturn(Optional.of(user));
		Mockito.when(repository.findByUsername(nonExistingUsername)).thenReturn(Optional.empty());
		
		Mockito.when(userUtil.getLoggedUsername()).thenReturn(existingUsername);
		Mockito.when(userUtil.getLoggedUsername()).thenReturn(nonExistingUsername);
		
		
		
	}

	@Test
	public void authenticatedShouldReturnUserEntityWhenUserExists() {
		
		Mockito.when(userUtil.getLoggedUsername()).thenReturn(existingUsername);
		

		UserEntity user = service.authenticated();
		
		Assertions.assertNotNull(user);
		Assertions.assertEquals(user.getUsername(), existingUsername);
	}

	@Test
	public void authenticatedShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {
		
		 Assertions.assertThrows(UsernameNotFoundException.class, () -> {
	            service.authenticated();
	        });
	}

	@Test
	public void loadUserByUsernameShouldReturnUserDetailsWhenUserExists() {
		
		
	}

	@Test
	public void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {
	}
}
