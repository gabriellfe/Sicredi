package br.com.sicredi.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "TB_ASSEMBLEIA")
public class Assembleia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ASSEMBLEIA")
	private Long id;
	@Column(nullable = false, name = "TX_ASSEMBLEIA")
	private String nomeAssembleia;
	@Column(nullable = false, name = "TX_PAUTA")
	private String pauta;
	@Column(nullable = false, name = "TS_DESCRICAO")
	private String descricao;
	@Column(name = "DT_CREATED")
	private LocalDateTime datacreated;
	@Column(name = "DT_CLOSED", nullable = false)
	private LocalDateTime dataclosed;
	@Column(name = "DT_DURACAO", nullable = false)
	private int duracao;
	@Column(name = "TX_VOTOS_SIM", nullable = false)
	private int votoSim = 0;
	@Column(name = "TX_VOTOS_NAO", nullable = false)
	private int votoNao = 0;
	@Column(name = "TX_ClOSED")
	private boolean Closed;
}
