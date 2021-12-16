package br.com.sicredi.api.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.sicredi.domain.model.Assembleia;
import br.com.sicredi.domain.model.Associado;
import br.com.sicredi.domain.model.StatusDTO;
import br.com.sicredi.domain.model.votoDTO;
import br.com.sicredi.domain.repository.AssociadoRepository;
import br.com.sicredi.domain.service.AssembleiaService;

/**
 * Classe responsável pelos endpoints para cadastro e votação de Assembleias
 * 
 * 
 * @author Gabriell Marques de Felipe {15/12/2021}
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/assembleia")
public class AssembleiaController {

	@Autowired
	private AssembleiaService assembleiaService;

	@Autowired
	private AssociadoRepository associadoRepository;

	@GetMapping("/{assembleiaID}")
	public ResponseEntity<Assembleia> buscar(@PathVariable Long assembleiaID) {
		try {
			Optional<Assembleia> veiculo = assembleiaService.findByID(assembleiaID);
			return ResponseEntity.ok(veiculo.get());
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Assembleia assembleia) {
		try {
			assembleia.setDatacreated(LocalDateTime.now());
			if (assembleia.getDuracao() == 0) {
				assembleia.setDuracao(1);
			}
			assembleia.setDataclosed(LocalDateTime.now().plusMinutes(assembleia.getDuracao()));
			assembleia.setClosed(false);
			assembleia = assembleiaService.saveAssembleia(assembleia);

			return ResponseEntity.status(HttpStatus.CREATED).body(assembleia);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/{assembleiaID}/{cpf}")
	public ResponseEntity<?> votar(@PathVariable String cpf, @PathVariable Long assembleiaID,
			@RequestBody votoDTO voto) {
		try {

			if (voto.getVoto().toUpperCase().equals("SIM") || voto.getVoto().toUpperCase().equals("NAO")) {

				String URI = "https://user-info.herokuapp.com/users/" + cpf;
				RestTemplate restTemplate = new RestTemplate();
				StatusDTO statusDTO = restTemplate.getForEntity(URI, StatusDTO.class).getBody();
				Optional<Assembleia> assembleia = assembleiaService.findByID(assembleiaID);
				
				if (assembleia.get().isClosed()) {
					return ResponseEntity.badRequest().body("Assembleia está fechada");
				}
				if (!associadoRepository.findByCpfAndIdAssembleia(cpf, assembleiaID).isEmpty()) {
					return ResponseEntity.badRequest().body("Já existe voto para esse CPF");
				}
					if (assembleia.get().isClosed()) {
						return ResponseEntity.badRequest().body("Assembleia está fechada");
					}

				if (statusDTO.getStatus().equals("UNABLE_TO_VOTE")) {
					return ResponseEntity.badRequest().body(statusDTO.getStatus());
				}

				if (voto.getVoto().toUpperCase().equals("SIM")) {
					assembleia.get().setVotoSim(assembleia.get().getVotoSim() + 1);
				} else {
					assembleia.get().setVotoNao(assembleia.get().getVotoNao() + 1);
				}
				associadoRepository.save(Associado.builder().cpf(cpf).idAssembleia(assembleiaID)
						.tipoVoto(voto.getVoto().toUpperCase()).build());
				return ResponseEntity.status(HttpStatus.CREATED).body(assembleiaService.save(assembleia.get()));
			}
			return ResponseEntity.badRequest().body("Voto não pode ser nulo");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
