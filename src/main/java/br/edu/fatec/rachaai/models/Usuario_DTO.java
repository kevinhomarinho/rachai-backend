package br.edu.fatec.rachaai.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario_DTO {

    @Id
    private Long id;
    private String imagem_perfil;
    private String username;
    private String origem;
    private String destino;
    private String horarios;
    private String email;
}
