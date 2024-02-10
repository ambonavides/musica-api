package com.musica.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "musica")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Musica implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2732981235112193721L;
	@Id
	@Column(name ="id_musica")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Column(name = "duracao", nullable = false)
	private int duracao;
	
	@OneToMany(orphanRemoval=false)
	@JoinColumn(name = "id_musica")
	private Set<Album> albuns = new HashSet<Album>(0);

	
}
