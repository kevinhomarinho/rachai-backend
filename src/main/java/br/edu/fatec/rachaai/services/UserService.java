package br.edu.fatec.rachaai.services;

import br.edu.fatec.rachaai.models.Usuario;
import br.edu.fatec.rachaai.models.Usuario_DTO;
import br.edu.fatec.rachaai.repositories.UserRepository;
import br.edu.fatec.rachaai.repositories.User_DTORespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private User_DTORespository user_DTORespository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void saveUser(Usuario user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        saveUser_DTO(user);
    }

    private void saveUser_DTO(Usuario user) {
        Usuario_DTO user_DTO = new Usuario_DTO();
        user_DTO.setId(user.getId());
        user_DTO.setUsername(user.getUsername());
        user_DTO.setEmail(user.getEmail());
        user_DTORespository.save(user_DTO);
    }

    public boolean findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .isPresent();
    }

    public Usuario_DTO findByEmailDTO(String email) {
        return Optional.ofNullable(user_DTORespository.findByEmail(email))
                .get().orElse(null);
    }

    public boolean findByEmail(String email, String senha) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .filter(user -> passwordEncoder.matches(senha, user.getPassword()))
                .isPresent();
    }

    public Usuario_DTO findUserById(Long id) {
        return Optional.of(user_DTORespository.findById(id))
                .get().orElse(null);
    }

    public List<Usuario_DTO> findAll() {
        return user_DTORespository.findAll();
    }

    public Usuario_DTO update(Usuario_DTO user, MultipartFile imagem_Perfil, String username, String origem, String destino, String horarios) {
        user.setImagem_perfil(saveFileLocally(imagem_Perfil));
        user.setUsername(username);
        user.setOrigem(origem);
        user.setDestino(destino);
        user.setHorarios(horarios);
        user_DTORespository.save(user);
        return user;
    }

    public String saveFileLocally(MultipartFile file) {
        try {
            String directory = "src/main/resources/imagem/";
            byte[] bytes = file.getBytes();
            File dir = new File(directory);
            if (!dir.exists()) dir.mkdirs();
            Path path = Paths.get(directory + file.getOriginalFilename());
            Files.write(path, bytes);
            return path.toString();
        } catch (IOException e) {
            return "Erro ao salvar imagem" + e.getMessage();
        }
    }

}
