package com.musica.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.musica.model.entity.Artista;

public interface ArtistaService {
	
	public abstract Optional<Artista> findById(Long id);
	
	public abstract Artista salvar(Artista artista);
	
	public abstract Page<Artista> buscarPorParametros(Artista artista, Pageable pageable);
	
	public abstract void remover(Artista artista);

}
