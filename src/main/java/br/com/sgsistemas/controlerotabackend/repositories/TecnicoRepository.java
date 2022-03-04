package br.com.sgsistemas.controlerotabackend.repositories;

import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

    @Transactional(readOnly = true)
    Tecnico findByEmail(String email);
}
