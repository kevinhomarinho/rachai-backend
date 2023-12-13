package br.edu.fatec.rachaai.repositories;

import br.edu.fatec.rachaai.models.Usuario_DTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotoristaRespository extends JpaRepository<Usuario_DTO, Long> {
    @Query("SELECT u FROM Usuario_DTO u WHERE u.origem LIKE CONCAT('%', :origem, '%')")
    Page<Usuario_DTO> findByOrigem(String origem, Pageable pageable);

    Optional<Usuario_DTO> findByEmail(String email);
}
