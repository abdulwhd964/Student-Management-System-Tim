/**
 * Author: Abdul Wahid
 * Date:24/02/2024
 * Time:1:15 PM
 */
package com.studentmanagement.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentmanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserName(String username);

	Optional<User> findByPassword(String password);
}
