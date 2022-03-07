package br.com.sgsistemas.controlerotabackend.repositories;

import br.com.sgsistemas.controlerotabackend.models.Despesa;
import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    @Transactional(readOnly = true)
    Page<Despesa> findByTecnico(Tecnico tecnico, Pageable pageRequest);
}
