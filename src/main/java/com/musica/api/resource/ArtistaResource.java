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

import com.musica.api.dto.ArtistaDTO;
import com.musica.exception.RegraNegocioException;
import com.musica.model.entity.Artista;
import com.musica.service.ArtistaService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/artista")
@RequiredArgsConstructor
public class ArtistaResource {
	
	private final ArtistaService service;
	
	@PostMapping("/salvar")
	public ResponseEntity salvar(@RequestBody ArtistaDTO dto) {
		try {
			Artista artista = dtoToEntity(dto);
			Artista artistaSalvo = service.salvar(artista);
			return new ResponseEntity(artistaSalvo, HttpStatus.CREATED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}
	
	@GetMapping("/listar/{page}/{row}")
	public Page<ArtistaDTO> listar(@RequestBody ArtistaDTO dto, @PathVariable int page, @PathVariable int row) {
		Artista Artista = dtoToEntity(dto);
		
		Pageable pageable = PageRequest.of(page, row, Sort.Direction.DESC, "id");
		
		Page<Artista> lista = service.buscarPorParametros(Artista, pageable);
		
		List<ArtistaDTO> listaRetorno = new ArrayList<ArtistaDTO>();
		for(Artista emp: lista) {
			listaRetorno.add(entityToDTO(emp));
		}
		
		Page<ArtistaDTO> Artistas = new PageImpl<>(listaRetorno, pageable, lista.getTotalElements());
		return Artistas;
	}
	
	@GetMapping("/buscar-por-id/{id}")
	public ArtistaDTO buscarPorId(@PathVariable("id") long id) {
		Optional<Artista> artistaOpt = service.findById(id);
		return this.entityToDTO(artistaOpt.get());
	}
	
	
	@DeleteMapping("/remover/{id}")
	public ResponseEntity remover(@PathVariable("id") long id) {
		try {
			Artista artista = Artista.builder().id(id).build();
			this.service.remover(artista);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}
	
	private ArtistaDTO entityToDTO(Artista e) {
		return ArtistaDTO.builder()
				.id(e.getId())
				.nome(e.getNome())
				.nacionalidade(e.getNacionalidade())
				.urlSite(e.getUrlSite())
				.imagemPerfil(e.getImagemPerfil())
				.build();
	}
	
	private Artista dtoToEntity(ArtistaDTO d) {
		return Artista.builder()
//				.id(d.getId())
				.nome(d.getNome())
				.nacionalidade(d.getNacionalidade())
				.urlSite(d.getUrlSite())
				.imagemPerfil(d.getImagemPerfil())
				.build();
	}

}
