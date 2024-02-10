package com.musica.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.musica.model.entity.Musica;

public interface MusicaRepository  extends JpaRepository<Musica, Long> {
	
	@Query(value="SELECT u FROM Musica u WHERE 1=1 "
			+ "AND (:TITULO IS NULL OR UPPER(u.titulo) LIKE CONCAT('%',UPPER(:TITULO),'%')) "
			+ "AND (:DURACAO IS NULL OR u.duracao = :DURACAO) "
			)
	public abstract Page<Musica> filtrar(@Param("TITULO") String titulo, 
										@Param("DURACAO") int duracao,
										Pageable pageable);

}
