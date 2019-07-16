package io.moresushant48.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.moresushant48.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}