package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Users;

public interface UsersRepository extends JpaRepository<Users, String>{
	@Query("SELECT u FROM Users u WHERE u.role = ?1 ORDER BY emp_id")
    List<Users> findAllByRole(String role);
}
