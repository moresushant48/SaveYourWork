package io.moresushant48.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.moresushant48.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}