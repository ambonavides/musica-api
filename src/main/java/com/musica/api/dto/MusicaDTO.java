package com.musica.api.dto;

import com.musica.model.entity.Artista;
import com.musica.model.entity.Musica;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MusicaDTO {
	
	private Long id;
	private String titulo;
	private int duracao;
	private Musica idMusica;
	private Artista idArtista;	
	private int faixa;

}
