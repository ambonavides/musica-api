package com.musica.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.musica.model.entity.Album;
import com.musica.model.repository.AlbumRepository;
import com.musica.service.AlbumService;

import jakarta.transaction.Transactional;

@Service
public class AlbumServiceImpl implements AlbumService {
	
	@Autowired
	private AlbumRepository repository;
	
	public AlbumServiceImpl(AlbumRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public Optional<Album> findById(Long id) {
		return repository.findById(id);
	}
	
	@Override
	@Transactional
	public Album salvar(Album artista) {
		Album album = null;
		try {
			album = repository.save(artista);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return album;
	}
	
	@Override
	public Page<Album> buscarPorParametros(Album a, Pageable p) {
		return repository.filtrar(a.getTitulo(), a.getAnoLancamento(), p);
	}
	
	@Override
	public void remover(Album album) {
		repository.delete(album);
	}

	@Override
	public Page<Album> listarMusicasComInfAlbum(Album a, Pageable p) {
		return repository.filtrar(a.getTitulo(), a.getAnoLancamento(), p);
	}
	
}
