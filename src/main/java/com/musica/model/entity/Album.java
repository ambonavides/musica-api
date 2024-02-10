package com.musica.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "album")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4060893441911517859L;
	
	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Column(name = "ano_lancamento", nullable = false)
	private String anoLancamento;
	
	@Column(name = "imagem_capa", nullable = false)
	private String imagemCapa;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "id_artista")
	private Artista artista;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "id_musica")
	private Musica musica;
	
	@Column(name = "faixa", nullable = false)
	private int faixa;
	
}
