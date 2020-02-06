package org.openup.controller;

import java.util.List;
import java.util.Optional;

import org.openup.entites.AnimCaracter;
import org.openup.entites.User;
import org.openup.repo.AnimRepository;
import org.openup.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/v1/caracter")
@CrossOrigin(origins="*")
public class AnimalController {

	@Autowired
	private AnimRepository animRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	@GetMapping("/")
	public ResponseEntity findAll() {
		return ResponseEntity.ok(animRepository.findAll());
	}
	
	@GetMapping("/all/{idUser}")
	public ResponseEntity findAllUserCharacters (@PathVariable Long idUser) {
		if (idUser == null ) {
			return ResponseEntity.badRequest().body("cannot find character with null user");
		}
		User user = userRepository.getOne(idUser);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<AnimCaracter> userChars = animRepository.findByUser(user);
		List<AnimCaracter> chredChars = animRepository.findBySharedAndUserIsNotLike(true ,user); //true her for shared
		userChars.forEach(character -> character.setIdOwner(idUser));
		chredChars.forEach(character -> character.setIdOwner(-1L));
		userChars.addAll(chredChars);
		return ResponseEntity.ok(userChars);
	}
	
	@GetMapping("/{idCaracter}")
	public ResponseEntity<AnimCaracter> findCaracterById(@PathVariable("idCaracter")Long idCaracter) {
		

		
		Optional<AnimCaracter> animCaracters = animRepository.findById(idCaracter);
		if(animCaracters.isPresent()) {
			AnimCaracter animCaracter = animCaracters.get();
		return new ResponseEntity<AnimCaracter>(animCaracter,HttpStatus.OK);
		}
		return new ResponseEntity<AnimCaracter>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/create/{idUser}")
	public AnimCaracter createCaracter(@RequestBody AnimCaracter caracter, @PathVariable("idUser") Long idUser) {

		return userRepository.findById(idUser).map(user -> {
			caracter.setUser(user);
		   return animRepository.save(caracter);
		}).orElseThrow(() -> new ResourceNotFound("Not found"));
	}
	
	@PutMapping("/update/{idCaracter}")
	public ResponseEntity<AnimCaracter> updateCharacter(@PathVariable Long idCaracter, @RequestBody AnimCaracter caracter) {
		
		Optional<AnimCaracter> animCaracters = animRepository.findById(idCaracter);
		
		if(animCaracters.isPresent()) {
			AnimCaracter animCaracter = animCaracters.get();
			animCaracter.setCategory(caracter.getCategory());
			animCaracter.setName(caracter.getName());
			animCaracter.setPhoto(caracter.getPhoto());
			animCaracter.setLegend(caracter.getLegend());
			
			return new ResponseEntity<AnimCaracter>(animRepository.save(animCaracter),HttpStatus.OK);
		}
		return new ResponseEntity<AnimCaracter>(HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/{idCaracter}")
	public ResponseEntity deletCaracter (@PathVariable(name ="idCaracter")Long idCaracter) { //her we can change the name of the id because we put it inside the @DeleteMapinng so it will make the mapping with out any problem 
		 if(idCaracter == null ) {
			 return ResponseEntity.badRequest().body("cannot remove character with null id");
		 }
		 
		 AnimCaracter caracter = animRepository.getOne(idCaracter);
		 if (caracter == null) {
			 return ResponseEntity.notFound().build();
		 }
		 animRepository.delete(caracter);
		 return ResponseEntity.ok("character removed with success");
	}
	
	@GetMapping("/share/{idCaracter}/{isShared}")
	public ResponseEntity shareCharacter (@PathVariable (name = "idCaracter") Long idCaracter,@PathVariable(name ="isShared") boolean isShared ) {
		
		if (idCaracter == null ) {
			 return ResponseEntity.badRequest().body("cannot remove character with null id");
		 }
		 
		 AnimCaracter caracter = animRepository.getOne(idCaracter);
		 if (caracter == null) {
			 return ResponseEntity.notFound().build();
		 }
		 caracter.setShared(!isShared);
		 return ResponseEntity.ok(animRepository.save(caracter));
	}
}
