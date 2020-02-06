package org.openup.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.openup.entites.User;
import org.openup.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin(origins="*", maxAge = 3600)
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public ResponseEntity findAll() {
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	@GetMapping("/{idUser}")
	public ResponseEntity findUserById(@PathVariable (name = "idUser") Long idUser) {
		if (idUser == null) {
			return ResponseEntity.badRequest().body( "cannot retrieve user with null ID");
		}
		
		User user = userRepository.getOne(idUser);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/")
	public ResponseEntity createUser(@RequestBody User user) {
		if (user == null) {
			return ResponseEntity.badRequest().body("cannot create user with empty fields");
		}
		
		User createUser = userRepository.save(user);
		return ResponseEntity.ok(createUser);
		
	}
	@PostMapping("/login")
    public ResponseEntity login(@RequestParam(name = "mail",required=false) String mail, @RequestParam(name = "password",required=false) String password) {
		User authenticatedUser = userRepository.findByMailAndPassword(mail, password);
        if(StringUtils.isEmpty(mail) || StringUtils.isEmpty(password)) {
             return ResponseEntity.badRequest().body("Cannot login with empty user mail or password");
        }
        
        if (authenticatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authenticatedUser);
    }
}
