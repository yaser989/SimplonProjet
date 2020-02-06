package org.openup.repo;

import java.util.List;

import org.openup.model.CreateAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface CreateAccRepository extends JpaRepository<CreateAcc, Long>{

	

	public CreateAcc findByName(String name);

}
