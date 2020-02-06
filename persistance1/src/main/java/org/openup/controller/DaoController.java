package org.openup.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openup.dao.DAO;
import org.openup.dao.Names;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DaoController {
	
	private static List<Names> listName = new ArrayList<>();
	DAO dao = new DAO ();

	
	@GetMapping("/insert")
	public String insert() {
		return "insert";
	}
	@GetMapping("/seeTheName")
	public String vu(Model model) throws ClassNotFoundException, SQLException {
		listName = dao.getAllName();
		if (listName.isEmpty()) {
			return "insert";
		}	
		model.addAttribute("listName",listName);
		return "seeTheName";
	}
	@PostMapping("/insert")
	public String send(Model model,@RequestParam(name = "id", required = false)Long id,@RequestParam(name = "name", required = false)String name) throws ClassNotFoundException, SQLException {
		dao.insert(id, name);
		listName = dao.getAllName();
		model.addAttribute("listName",listName);
		return "seeTheName";
	}
	
}
