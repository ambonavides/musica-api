package com.musica.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.musica.model.entity.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
	
	@Query(value="SELECT u FROM Artista u WHERE 1=1 "
			+ "AND (:NOME IS NULL OR UPPER(u.nome) LIKE CONCAT('%',UPPER(:NOME),'%')) "
			+ "AND (:NACIONALIDADE IS NULL OR UPPER(u.nacionalidade) LIKE CONCAT('%',UPPER(:NACIONALIDADE),'%')) "
			+ "AND (:URL IS NULL OR UPPER(u.urlSite) LIKE CONCAT('%',UPPER(:URL),'%')) "
			)
	public abstract Page<Artista> filtrar(@Param("NOME") String nome, 
										  @Param("NACIONALIDADE") String nacionalidade, 
										  @Param("URL") String urlSite, 
										  Pageable pageable);

}
