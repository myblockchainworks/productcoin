/**
 * 
 */
package com.aequalis.dapps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aequalis.dapps.model.User;

import java.lang.String;
import java.lang.Boolean;
import java.util.List;

/**
 * @author anand
 *
 */
public interface UserRepository extends JpaRepository<User, Long>  {
	
	User findByUsernameAndPassword(String username, String password);
	
	User findByUserid(Long userid);
	
	User findByUsername(String username);
	
	List<User> findByIsadminuser(Boolean isadminuser);
	
}
