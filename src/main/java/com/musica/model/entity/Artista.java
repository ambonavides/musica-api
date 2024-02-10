package com.musica.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "artista")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artista implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7464941624043474089L;

	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "nacionalidade", nullable = false)
	private String nacionalidade;
	
	@Column(name = "url_site", nullable = false)
	private String urlSite;
	
	@Column(name = "imagem_perfil", nullable = false)
	private String imagemPerfil;

}
