package br.com.sgsistemas.controlerotabackend.repositories;

import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import br.com.sgsistemas.controlerotabackend.models.Visita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Long> {

    @Transactional(readOnly = true)
    Page<Visita> findByTecnico(Tecnico tecnico, Pageable pageRequest);
}
