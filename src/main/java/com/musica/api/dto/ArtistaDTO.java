package com.musica.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ArtistaDTO {
	
	private Long id;
	private String nome;
	private String nacionalidade;
	private String urlSite;
	private String imagemPerfil;
}
