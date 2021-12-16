package br.com.sicredi.domain.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_VOTOS")
public class Associado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ASSOCIADO")
	private Long id;
	@Column(nullable = false, name = "TX_CPF")
	private String cpf;
	@Column(nullable = false, name = "TX_VOTO_DESCRICAO")
	private String tipoVoto;
	@Column(nullable = false, name = "ID_ASSEMBLEIA")
	private Long idAssembleia;
	
}
