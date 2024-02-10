package com.musica.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.musica.model.entity.Album;

public interface AlbumRepository  extends JpaRepository<Album, Long> {
	
	@Query(value="SELECT u FROM Album u WHERE 1=1 "
			+ "AND (:TITULO IS NULL OR UPPER(u.titulo) LIKE CONCAT('%',UPPER(:TITULO),'%')) "
			+ "AND (:ANO_LANCAMENTO IS NULL OR UPPER(u.anoLancamento) LIKE CONCAT('%',UPPER(:ANO_LANCAMENTO),'%')) "
			)
	public abstract Page<Album> filtrar(@Param("TITULO") String titulo, @Param("ANO_LANCAMENTO") String anoLancamento, Pageable pageable);


}
