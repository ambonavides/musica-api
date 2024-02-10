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

import com.musica.api.dto.AlbumDTO;
import com.musica.exception.RegraNegocioException;
import com.musica.model.entity.Album;
import com.musica.model.entity.Artista;
import com.musica.model.entity.Musica;
import com.musica.service.AlbumService;
import com.musica.service.ArtistaService;
import com.musica.service.MusicaService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/album")
@RequiredArgsConstructor
public class AlbumResource {
	
	private final AlbumService albumService;
	private final ArtistaService artistaService;
	private final MusicaService musicaService;
	
	@PostMapping("/salvar")
	public ResponseEntity salvar(@RequestBody AlbumDTO dto) {
		try {
			Album artista = dtoToEntity(dto);
			Album artistaSalvo = albumService.salvar(artista);
			return new ResponseEntity(artistaSalvo, HttpStatus.CREATED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}
	
	@GetMapping("/listar/{page}/{row}")
	public Page<AlbumDTO> listar(@RequestBody AlbumDTO dto, @PathVariable int page, @PathVariable int row) {
		Album musica = dtoToEntity(dto);
		
		Pageable pageable = PageRequest.of(page, row, Sort.Direction.DESC, "id");
		
		Page<Album> lista = albumService.buscarPorParametros(musica, pageable);
		
		List<AlbumDTO> listaRetorno = new ArrayList<AlbumDTO>();
		for(Album emp: lista) {
			listaRetorno.add(entityToDTO(emp));
		}
		
		Page<AlbumDTO> Albums = new PageImpl<>(listaRetorno, pageable, lista.getTotalElements());
		return Albums;
	}
	
	@GetMapping("/buscar-por-id/{id}")
	public AlbumDTO buscarPorId(@PathVariable("id") long id) {
		Optional<Album> artistaOpt = albumService.findById(id);
		return this.entityToDTO(artistaOpt.get());
	}
	
	
	@DeleteMapping("/remover/{id}")
	public ResponseEntity remover(@PathVariable("id") long id) {
		try {
			Album artista = Album.builder().id(id).build();
			this.albumService.remover(artista);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}
	
	private AlbumDTO entityToDTO(Album d) {
		return AlbumDTO.builder()
				.id(d.getId())
				.titulo(d.getTitulo())
				.anoLancamento(d.getAnoLancamento())
				.imagemCapa(d.getImagemCapa())
				.musica(null)
				.build();
	}
	
	private Album dtoToEntity(AlbumDTO d) {
		Artista artista = artistaService.findById(Long.valueOf(d.getArtista())).get();
		Musica musica = musicaService.findById(Long.valueOf(d.getMusica())).get();
		return Album.builder()
				.id(d.getId())
				.titulo(d.getTitulo())
				.anoLancamento(d.getAnoLancamento())
				.imagemCapa(d.getImagemCapa())
				.idArtista(artista)
				.idMusica(musica)
				.faixa(Integer.valueOf(d.getFaixa()))
				.build();
	}

}
