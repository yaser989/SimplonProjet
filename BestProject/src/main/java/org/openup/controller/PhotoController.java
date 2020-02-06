package org.openup.controller;

import java.io.ByteArrayInputStream;

import org.openup.entites.AnimCaracter;
import org.openup.entites.User;
import org.openup.repo.AnimRepository;
import org.openup.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/photos")
@CrossOrigin(origins="*")
public class PhotoController {
	
	@Autowired
	private AnimRepository animRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/character/{idCaracter}")
	public ResponseEntity photoCharacter(@PathVariable Long idCaracter) {
		
		if (idCaracter == null) {
			return ResponseEntity.badRequest().body("cannot get character photo with null id");
		}
		
		AnimCaracter character = animRepository.getOne(idCaracter);
		if (character == null) {
			return ResponseEntity.notFound().build();
		}
		if (character.getPhoto()==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_GIF)
				.contentType(MediaType.IMAGE_JPEG)
				.contentType(MediaType.IMAGE_PNG)
				
				.body(new InputStreamResource(new ByteArrayInputStream(character.getPhoto())));
	}
	
	
	@GetMapping("/user/{idUser}")
	public ResponseEntity photoUser(@PathVariable Long idUser) {
		
		if (idUser == null) {
			return ResponseEntity.badRequest().body("cannot get user photo with null id");
		}
		
		User user = userRepository.getOne(idUser);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		if (user.getPhoto()==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_GIF)
				.contentType(MediaType.IMAGE_JPEG)
				.contentType(MediaType.IMAGE_PNG)
				.body(new InputStreamResource(new ByteArrayInputStream(user.getPhoto())));
	}

}
