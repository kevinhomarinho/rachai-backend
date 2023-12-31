package br.edu.fatec.rachaai.services;

import br.edu.fatec.rachaai.enums.StatusCode;
import br.edu.fatec.rachaai.models.Usuario;
import br.edu.fatec.rachaai.models.Usuario_DTO;
import br.edu.fatec.rachaai.repositories.UserRepository;
import br.edu.fatec.rachaai.repositories.Usuario_DTORepository;
import br.edu.fatec.rachaai.utils.StatusError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static br.edu.fatec.rachaai.utils.GerarUsuariosTeste.gerarUsuarios;
import static br.edu.fatec.rachaai.utils.GerarUsuariosTeste.origem;
import static br.edu.fatec.rachaai.utils.UserValidator.validateUser;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Usuario_DTORepository usuarioDTORepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public int gerarTeste() {
        List<Usuario> users = gerarUsuarios(250);
        for (Usuario user : users) saveUser(user);
        gerarDTOs(users);
        return users.size();
    }

    public void gerarDTOs(List<Usuario> users) {
        Random random = new Random();
        for (int i = 0; i < users.size(); i++) {
            Usuario_DTO userDTO = findByEmailDTO(users.get(i).getEmail());
            update(userDTO, null, null, origem.get(random.nextInt(origem.size())), "Fatec Cotia", "Segunda a Sexta as 18:20", i%2==0);
        }
    }

    public void saveUser(Usuario user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        convertToDTO(user);
    }

    private void convertToDTO(Usuario user) {
        Usuario_DTO user_DTO = new Usuario_DTO();
        user_DTO.setId(user.getId());
        user_DTO.setUsername(user.getUsername());
        user_DTO.setEmail(user.getEmail());
        user_DTO.setMotorista(false);
        usuarioDTORepository.save(user_DTO);
    }

    public boolean findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .isPresent();
    }

    public Usuario_DTO findByEmailDTO(String email) {
        return Optional.of(usuarioDTORepository.findByEmail(email))
                .get();
    }

    public boolean findByEmail(String email, String senha) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .filter(user -> passwordEncoder.matches(senha, user.getPassword()))
                .isPresent();
    }

    @Transactional
    public void delete(String email) {
        userRepository.deleteByEmail(email);
        usuarioDTORepository.deleteByEmail(email);
    }

    public Usuario_DTO update(Usuario_DTO user, MultipartFile imagem_Perfil, String username, String origem, String destino, String horarios, boolean motorista) {
        String nameOld = user.getUsername();
        usuarioDTORepository.delete(user);
        if (username != null) user.setUsername(username);
        if (imagem_Perfil != null) user.setImagem_perfil(saveFileLocally(imagem_Perfil, user.getUsername(), nameOld));
        if (origem != null) user.setOrigem(origem);
        if (destino != null) user.setDestino(destino);
        if (horarios != null) user.setHorarios(horarios);
        if (motorista != user.isMotorista()) user.setMotorista(motorista);
        usuarioDTORepository.save(user);
        return user;
    }

    public StatusError userValid(Usuario user) {
        String Username = user.getUsername();
        String ra = user.getRa();
        String Email = user.getEmail();
        String Password = user.getPassword();
        if (findByEmail(Email)) return new StatusError(StatusCode.EMAIL_ALREADY_REGISTERED);
        return validateUser(Username, Email, ra, Password);
    }

    public String saveFileLocally(MultipartFile file, String username, String nameOld) {
        String directory = "/imagem/" + username + "/";
        String directoryOld = "/imagem/" + nameOld + "/";
        File dirOld = new File(directoryOld);
        if (dirOld.exists()) {
            for (File files : dirOld.listFiles()) if (!files.isDirectory()) files.delete();
            try {
                Files.delete(dirOld.toPath());
            } catch (IOException e) {
                return null;
            }
        }
        File dir = new File(directory);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                if (!dir.exists()) dir.mkdirs();
                Path path = Paths.get(directory + file.getOriginalFilename());
                Files.write(path, bytes);
                return directory + path.getFileName();
            } catch (IOException e) {
                return null;
            }
        } else {
            if (dir.exists()) {
                try {
                    for (File files : dir.listFiles()) if (!files.isDirectory()) files.delete();
                    Files.delete(dir.toPath());
                } catch (IOException e) {
                    return null;
                }
            }
            return null;
        }
    }
}
