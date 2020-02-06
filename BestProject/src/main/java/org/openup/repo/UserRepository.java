package org.openup.repo;

import org.openup.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByMailAndPassword(String mail, String password);
}
