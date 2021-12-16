package br.com.sicredi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.sicredi.api.controller.AssembleiaController;
import br.com.sicredi.domain.model.Assembleia;
import br.com.sicredi.domain.model.votoDTO;

@SpringBootTest
class SicrediTestDevApplicationTests {


	@Autowired
	private AssembleiaController assembleiaController;

	@Test
	public void criarAssembleia() {
		Assembleia assembleia = new Assembleia();
		assembleia.setDescricao("teste");
		assembleia.setDuracao(1);
		assembleia.setNomeAssembleia("teste");
		assembleia.setPauta("teste");
		@SuppressWarnings("unchecked")
		ResponseEntity<Assembleia> ass = (ResponseEntity<Assembleia>) assembleiaController.adicionar(assembleia);
		assertTrue(ass.getBody().getId() != null);
	}

	@Test
	public void votarAssembleiaCpfInvalido() {
		Assembleia assembleia = new Assembleia();
		assembleia.setDescricao("teste");
		assembleia.setDuracao(1);
		assembleia.setNomeAssembleia("teste");
		assembleia.setPauta("teste");
		@SuppressWarnings("unchecked")
		ResponseEntity<Assembleia> ass = (ResponseEntity<Assembleia>) assembleiaController.adicionar(assembleia);
		votoDTO voto = new votoDTO();
		voto.setVoto("sim");

		assertTrue(assembleiaController.votar("4487059186", ass.getBody().getId(), voto).getStatusCode().equals(HttpStatus.BAD_REQUEST));
	}
}