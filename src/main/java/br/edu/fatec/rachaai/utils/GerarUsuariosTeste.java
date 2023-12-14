package br.edu.fatec.rachaai.utils;

import br.edu.fatec.rachaai.models.Usuario;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class GerarUsuariosTeste {

    public static List<String> origem = Arrays.asList("Itapevi", "Embu", "Jandira", "Barueri", "Carapicuíba",
            "Taboão da Serra", "Itapecerica da Serra", "Osasco", "Santana de Parnaíba", "São Roque");

    public static Usuario gerarUsuarioAleatorio(int id) {
        Usuario usuario = new Usuario();
        usuario.setUsername("usuario" + id);
        usuario.setRa("ra" + id);
        usuario.setEmail("usuario" + id + "@gmail.com");
        usuario.setPassword("Teste@1234");
        return usuario;
    }

    public static List<Usuario> gerarUsuarios(int quantidade) {
        List<Usuario> usuarios = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            usuarios.add(gerarUsuarioAleatorio(i+1));
        }
        return usuarios;
    }
}
