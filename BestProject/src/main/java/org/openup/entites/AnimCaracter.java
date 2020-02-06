package org.openup.entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.ws.BindingType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimCaracter implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCaracter;
	private String name;
	private String category;
	private String strength;
	private String legend;
	private boolean shared;
	

	@Lob
	private byte[] photo;
	
	
	
	@Transient
	private Long idOwner;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name ="idUser")
	private User user;
}
