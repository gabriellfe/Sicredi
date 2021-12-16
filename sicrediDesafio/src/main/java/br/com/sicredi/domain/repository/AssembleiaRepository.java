package br.com.sicredi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.domain.model.Assembleia;

@Repository
public interface AssembleiaRepository extends JpaRepository<Assembleia, Long>{

}
