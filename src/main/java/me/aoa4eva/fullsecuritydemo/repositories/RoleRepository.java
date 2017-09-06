package me.aoa4eva.fullsecuritydemo.repositories;

import me.aoa4eva.fullsecuritydemo.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByRole(String r);
}
