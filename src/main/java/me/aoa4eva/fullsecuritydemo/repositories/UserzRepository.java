package me.aoa4eva.fullsecuritydemo.repositories;

import me.aoa4eva.fullsecuritydemo.models.Userz;
import org.springframework.data.repository.CrudRepository;

public interface UserzRepository extends CrudRepository<Userz,Long> {
    Userz findByUsername(String username);
}
