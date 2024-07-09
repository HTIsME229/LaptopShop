package vn.hoidanit.laptopshop.repository;

import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRespository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    // Role FindById(long id);
}
