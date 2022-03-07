package br.com.sgsistemas.controlerotabackend.repositories;

import br.com.sgsistemas.controlerotabackend.models.Limpeza;
import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LimpezaRepository extends JpaRepository<Limpeza, Long> {

    @Transactional(readOnly = true)
    Page<Limpeza> findBySolicitante(Tecnico solicitante, Pageable pageRequest);
}
