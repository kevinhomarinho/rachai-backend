package br.edu.fatec.rachaai.repositories;

import br.edu.fatec.rachaai.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
