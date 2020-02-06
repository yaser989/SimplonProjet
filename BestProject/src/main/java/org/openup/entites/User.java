package org.openup.entites;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable{
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUser;
	private String firstName;
	private String lastName;
	private String mail;
	private String password;
	
	@Lob
	private byte[] photo;
	
	@OneToMany(mappedBy = "user", cascade =  CascadeType.ALL)
	private List<AnimCaracter> characters;
	
	
}
