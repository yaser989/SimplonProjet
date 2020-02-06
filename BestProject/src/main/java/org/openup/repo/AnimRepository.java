package org.openup.repo;

import java.util.List;

import org.openup.entites.AnimCaracter;
import org.openup.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimRepository extends JpaRepository<AnimCaracter, Long>{
	
	List<AnimCaracter> findByUserOrShared(User user, boolean shared);
	
	List<AnimCaracter> findByUser(User user);
	
	List<AnimCaracter> findBySharedAndUserIsNotLike(boolean shared,User user);

}
