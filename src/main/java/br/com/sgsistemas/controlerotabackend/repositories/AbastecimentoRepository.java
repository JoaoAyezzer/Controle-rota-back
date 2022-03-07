package br.com.sgsistemas.controlerotabackend.repositories;

import br.com.sgsistemas.controlerotabackend.models.Abastecimento;
import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {
    @Transactional(readOnly = true)
    Page<Abastecimento> findByTecnico(Tecnico tecnico, PageRequest pageRequest);
}
