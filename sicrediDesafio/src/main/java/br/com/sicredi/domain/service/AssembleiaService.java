package br.com.sicredi.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.domain.model.Assembleia;
import br.com.sicredi.domain.repository.AssembleiaRepository;
import br.com.sicredi.listener.QueueConsumer;

/**
 * Classe Service para Controller de Assembleia
 * 
 * @author Gabriell Marques de Felipe {11/12/2021}
 * @version 1.0
 *
 */
@Service
public class AssembleiaService {

	@Autowired
	private QueueConsumer queueConsumer;

	@Autowired
	private AssembleiaRepository assembleiaRepository;

	public List<Assembleia> findAll() {
		return assembleiaRepository.findAll();
	}

	public Optional<Assembleia> findByID(Long id) {
		return assembleiaRepository.findById(id);
	}
	
	public Assembleia save(Assembleia assembleia) {
		return assembleiaRepository.save(assembleia);
	}

	public Assembleia saveAssembleia(Assembleia assembleia) {
		Assembleia assembleiaSave = assembleiaRepository.save(assembleia);
		new Thread() {
			public void run() {
				while (LocalDateTime.now().getMinute() != assembleiaSave.getDataclosed().getMinute()
						|| LocalDateTime.now().getSecond() != assembleiaSave.getDataclosed().getSecond()) {
				}
				Assembleia assembleiaAtt = assembleiaRepository.findById(assembleiaSave.getId()).get();
				assembleiaAtt.setClosed(true);
				assembleiaRepository.save(assembleiaAtt);
				queueConsumer.send(assembleiaAtt);
			}
		}.start();

		return assembleiaSave;
	}

}
