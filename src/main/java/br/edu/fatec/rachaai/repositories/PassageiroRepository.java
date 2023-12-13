package br.edu.fatec.rachaai.repositories;

import br.edu.fatec.rachaai.models.Usuario_DTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassageiroRepository extends JpaRepository<Usuario_DTO, Long> {
}
