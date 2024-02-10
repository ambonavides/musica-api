package com.musica.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.musica.model.entity.Album;

public interface AlbumService {
	
	public abstract Optional<Album> findById(Long id);
	
	public abstract Album salvar(Album a);
	
	public abstract Page<Album> buscarPorParametros(Album a, Pageable p);
	
	public abstract void remover(Album a);
	
	public abstract Page<Album> listarMusicasComInfAlbum(Album a, Pageable p);
	
}
