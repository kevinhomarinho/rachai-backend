package br.edu.fatec.rachaai.repositories;

import br.edu.fatec.rachaai.models.Usuario_DTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Usuario_DTORepository extends JpaRepository<Usuario_DTO, Long> {

    Optional<Usuario_DTO> findByEmail(String email);

    Page<Usuario_DTO> findAllByMotoristaIsTrueAndOrigemIsLikeIgnoreCase(String origem, Pageable pageable);
    Page<Usuario_DTO> findAllByMotoristaIsFalseAndOrigemIsLikeIgnoreCase(String origem, Pageable pageable);

    Page<Usuario_DTO> findAllByMotoristaIsTrue(Pageable pageable);
    Page<Usuario_DTO> findAllByMotoristaIsFalse(Pageable pageable);
}
