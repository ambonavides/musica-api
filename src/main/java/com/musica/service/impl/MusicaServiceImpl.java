package com.musica.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.musica.model.entity.Musica;
import com.musica.model.repository.MusicaRepository;
import com.musica.service.MusicaService;

import jakarta.transaction.Transactional;

@Service
public class MusicaServiceImpl implements MusicaService {
	
	@Autowired
	private MusicaRepository repository;
	
	public MusicaServiceImpl(MusicaRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public Optional<Musica> findById(Long id) {
		return repository.findById(id);
	}
	
	@Override
	@Transactional
	public Musica salvar(Musica artista) {
		return repository.save(artista);
	}
	
	@Override
	public Page<Musica> buscarPorParametros(Musica m, Pageable p) {
		return repository.filtrar(m.getTitulo(), m.getDuracao(), p);
	}
	
	@Override
	public void remover(Musica m) {
		repository.delete(m);
	}

}
