package br.edu.fatec.rachaai.services;

import br.edu.fatec.rachaai.models.Usuario;
import br.edu.fatec.rachaai.models.Usuario_DTO;
import br.edu.fatec.rachaai.repositories.PassageiroRepository;
import br.edu.fatec.rachaai.repositories.UserRepository;
import br.edu.fatec.rachaai.repositories.MotoristaRespository;
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
    private MotoristaRespository motoristaRespository;

    @Autowired
    private PassageiroRepository passageiroRepository;

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
        if (user_DTO.isMotorista()) {
            motoristaRespository.save(user_DTO);
            System.out.println("Motorista");
        }
        else {
            passageiroRepository.save(user_DTO);
            System.out.println("Passageiro");
        }
    }

    public boolean findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .isPresent();
    }

    public Usuario_DTO findByEmailDTO(String email) {
        return Optional.ofNullable(motoristaRespository.findByEmail(email))
                .get().orElse(null);
    }

    public boolean findByEmail(String email, String senha) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .filter(user -> passwordEncoder.matches(senha, user.getPassword()))
                .isPresent();
    }

    public Usuario_DTO findUserById(Long id) {
        return Optional.of(motoristaRespository.findById(id))
                .get().orElse(null);
    }

    public List<Usuario_DTO> findAll() {
        return motoristaRespository.findAll();
    }

    public Usuario_DTO update(Usuario_DTO user, MultipartFile imagem_Perfil, String username, String origem, String destino, String horarios, boolean motorista) {
        String nameOld = user.getUsername();
        if (username != null) user.setUsername(username);
        if (imagem_Perfil != null) user.setImagem_perfil(saveFileLocally(imagem_Perfil, user.getUsername(), nameOld));
        if (origem != null) user.setOrigem(origem);
        if (destino != null) user.setDestino(destino);
        if (horarios != null) user.setHorarios(horarios);
        if (motorista != user.isMotorista()) user.setMotorista(motorista);
        if (motorista) motoristaRespository.save(user);
        else passageiroRepository.save(user);
        return user;
    }

    public String saveFileLocally(MultipartFile file, String username, String nameOld) {
        String directory = "src/main/resources/imagem/" + username + "/";
        String directoryOld = "src/main/resources/imagem/" + nameOld + "/";
        File dirOld = new File(directoryOld);
        if (dirOld.exists()){
            for (File files : dirOld.listFiles()) if (!files.isDirectory()) files.delete();
            try { Files.delete(dirOld.toPath()); }
            catch (IOException e) { return "Arquivo não encontrado"; }
        }
        File dir = new File(directory);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                if (!dir.exists()) dir.mkdirs();
                Path path = Paths.get(directory + file.getOriginalFilename());
                Files.write(path, bytes);
                return path.toString();
            } catch (IOException e) { return ""; }
        } else {
            if (dir.exists()) {
                try {
                    for (File files : dir.listFiles()) if (!files.isDirectory()) files.delete();
                    Files.delete(dir.toPath());
                } catch (IOException e) { return "Arquivo não encontrado"; }
            }
            return "Ainda não possui imagem de perfil";
        }
    }

}
