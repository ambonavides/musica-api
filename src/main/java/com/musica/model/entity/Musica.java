package com.musica.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "musica")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Musica implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2732981235112193721L;
	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Column(name = "duracao", nullable = false)
	private int duracao;
	
}
