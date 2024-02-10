package com.musica.model.entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Column(name = "ano_lancamento", nullable = false)
	private String anoLancamento;
	
	@Column(name = "imagem_capa", nullable = false)
	private String imagemCapa;
	
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_musica")
	private Musica idMusica;
	
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_artista")
	private Artista idArtista;	
	
	@Column(name = "faixa", nullable = false)
	private int faixa;
	
}
