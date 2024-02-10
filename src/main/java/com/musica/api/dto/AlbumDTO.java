package com.musica.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AlbumDTO {
	
	private Long id;
	private String titulo;
	private String anoLancamento;
	private String imagemCapa;
	private String musica;
	private String artista;	
	private String faixa;
	

}
