package com.musica.api.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musica.api.dto.MusicaDTO;
import com.musica.exception.RegraNegocioException;
import com.musica.model.entity.Musica;
import com.musica.service.MusicaService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/musica")
@RequiredArgsConstructor
public class MusicaResource {
	
	private final MusicaService service;
	
	@PostMapping("/salvar")
	public ResponseEntity salvar(@RequestBody MusicaDTO dto) {
		try {
			Musica artista = dtoToEntity(dto);
			Musica artistaSalvo = service.salvar(artista);
			return new ResponseEntity(artistaSalvo, HttpStatus.CREATED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}
	
	@GetMapping("/listar/{page}/{row}")
	public Page<MusicaDTO> listar(@RequestBody MusicaDTO dto, @PathVariable int page, @PathVariable int row) {
		Musica musica = dtoToEntity(dto);
		
		Pageable pageable = PageRequest.of(page, row, Sort.Direction.DESC, "id");
		
		Page<Musica> lista = service.buscarPorParametros(musica, pageable);
		
		List<MusicaDTO> listaRetorno = new ArrayList<MusicaDTO>();
		for(Musica emp: lista) {
			listaRetorno.add(entityToDTO(emp));
		}
		
		Page<MusicaDTO> Musicas = new PageImpl<>(listaRetorno, pageable, lista.getTotalElements());
		return Musicas;
	}
	
	@GetMapping("/buscar-por-id/{id}")
	public MusicaDTO buscarPorId(@PathVariable("id") long id) {
		Optional<Musica> musicaOpt = service.findById(id);
		return this.entityToDTO(musicaOpt.get());
	}
	
	
	@DeleteMapping("/remover/{id}")
	public ResponseEntity remover(@PathVariable("id") long id) {
		try {
			Musica artista = Musica.builder().id(id).build();
			this.service.remover(artista);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}
	
	private MusicaDTO entityToDTO(Musica d) {
		return MusicaDTO.builder()
				.titulo(d.getTitulo())
				.duracao(d.getDuracao())
				.build();
	}
	
	private Musica dtoToEntity(MusicaDTO d) {
		return Musica.builder()
				.id(d.getId())
				.titulo(d.getTitulo())
				.duracao(d.getDuracao())
				.build();
	}

}
