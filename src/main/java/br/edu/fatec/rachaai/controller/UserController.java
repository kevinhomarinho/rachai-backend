package br.edu.fatec.rachaai.controller;

import br.edu.fatec.rachaai.config.JwtUtil;
import br.edu.fatec.rachaai.enums.StatusCode;
import br.edu.fatec.rachaai.models.Usuario;
import br.edu.fatec.rachaai.models.Usuario_DTO;
import br.edu.fatec.rachaai.services.UserService;
import br.edu.fatec.rachaai.utils.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody Usuario user) {
        if (userService.findByEmail(user.getEmail())) {
            StatusResponse response = new StatusResponse(StatusCode.EMAIL_ALREADY_REGISTERED);
            return ResponseEntity.badRequest().body(response);
        }
        userService.saveUser(user);
        StatusResponse response = new StatusResponse(StatusCode.USER_CREATED);
        return ResponseEntity.ok()
            .header("Authorization", "Bearer " + jwtUtil.generateToken(user.getEmail()))
            .body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@RequestBody Usuario user) {
        if (userService.findByEmail(user.getEmail(), user.getPassword())){
            StatusResponse response = new StatusResponse(StatusCode.USER_LOGGED);
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + jwtUtil.generateToken(user.getEmail()))
                    .body(response);
        }
        StatusResponse response = new StatusResponse(StatusCode.EMAIL_OR_PASSWORD_INVALID);
        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/update")
    public ResponseEntity<Usuario_DTO> update(@RequestHeader("Authorization") String token,
                                              @RequestPart("imagem_perfil") MultipartFile imagem_perfil,
                                              @RequestPart("username") String username,
                                              @RequestPart("origem") String origem,
                                              @RequestPart("destino") String destino,
                                              @RequestPart("horarios") String horarios) {
        String email = jwtUtil.getEmailFromToken(token.substring(7));
        Usuario_DTO user = userService.findByEmailDTO(email);
        return ResponseEntity.ok(userService.update(user, imagem_perfil, username, origem, destino, horarios));
    }
}
