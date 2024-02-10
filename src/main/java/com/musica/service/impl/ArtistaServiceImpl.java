package com.musica.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.musica.model.entity.Artista;
import com.musica.model.repository.ArtistaRepository;
import com.musica.service.ArtistaService;

import jakarta.transaction.Transactional;

@Service
public class ArtistaServiceImpl implements ArtistaService {
	
	@Autowired
	private ArtistaRepository repository;
	
	public ArtistaServiceImpl(ArtistaRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public Optional<Artista> findById(Long id) {
		return repository.findById(id);
	}
	
	@Override
	@Transactional
	public Artista salvar(Artista artista) {
		return repository.save(artista);
	}
	
	@Override
	public Page<Artista> buscarPorParametros(Artista artista, Pageable pageable) {
		return repository.filtrar(artista.getNome(), artista.getNacionalidade(), artista.getUrlSite(), pageable);
	}
	
	@Override
	public void remover(Artista album) {
		repository.delete(album);
	}


}
