package com.drive.dao;

import com.drive.entity.Role;
import com.drive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserDao extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);
    List<User> findAllByOrganization(String organization);
    @Override
    List<User> findAll();
//    @Transactional
//    @Modifying
//    @Query("UPDATE User e SET e.role = :role WHERE e.email = :email")
//    void updateRole(@Param("email") String email, @Param("role")Set<Role> role);
}
