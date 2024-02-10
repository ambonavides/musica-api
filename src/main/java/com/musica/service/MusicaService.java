package com.musica.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.musica.model.entity.Musica;

public interface MusicaService {
	
	public abstract Optional<Musica> findById(Long id);
	
	public abstract Musica salvar(Musica artista);
	
	public abstract Page<Musica> buscarPorParametros(Musica musica, Pageable pageable);
	
	public abstract void remover(Musica musica);
	
}
