package br.com.sicredi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.domain.model.Associado;


@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
	
	
	
	List<Associado> findByCpfAndIdAssembleia(String cpf, Long idAssembleia);

}
