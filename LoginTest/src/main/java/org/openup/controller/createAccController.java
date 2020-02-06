package org.openup.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.openup.model.CreateAcc;
import org.openup.repo.CreateAccRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class createAccController {
	
	public String message  ="";
	private static List<CreateAcc> C = new ArrayList<>();

	@Autowired
	private CreateAccRepository createAccRepository;
	
	@GetMapping("/home")
    public String test(Model model) throws ClassNotFoundException, SQLException {
		return "home";
    	
    }
	
	@RequestMapping(value="/create" , method = RequestMethod.POST)
	public String Create(Model model,CreateAcc CA ) {
		createAccRepository.save(CA);
		C.add(CA);
		model.addAttribute("C", CA);
		return "created";
	}
	
	@PostMapping("/login")
	public String logi(Model model,CreateAcc CA,@RequestParam(name = "nom", required = false)String nom,@RequestParam(name = "pas", required = false)String pas) throws Exception {
	
	CreateAcc user =	createAccRepository.findByName(nom);
	if(user==null) {
		this.message = "ce utilisateur n'existe pas ";
		return "error";
	}

	
	if(!user.getPass().equals(pas)) 
	{
		this.message = "Mot de passe incorrect ";

		model.addAttribute("message", this.message);
		return "error";
	}
	
	
		
		
	


	
	return "created";	
	}
}
