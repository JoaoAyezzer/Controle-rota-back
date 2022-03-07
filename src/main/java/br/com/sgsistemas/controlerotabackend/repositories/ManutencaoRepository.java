package br.com.sgsistemas.controlerotabackend.repositories;

import br.com.sgsistemas.controlerotabackend.models.Manutencao;
import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

    @Transactional(readOnly = true)
    Page<Manutencao> findBySolicitante(Tecnico solicitante, PageRequest pageRequest);
}
