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

    @GetMapping("/teste/{quantidade}")
    public ResponseEntity<String> teste(@PathVariable int quantidade) {
        return ResponseEntity.ok("Usuarios gerados: " + userService.gerarTeste(quantidade));
    }

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

    @PatchMapping("/update")
    public ResponseEntity<Usuario_DTO> update(@RequestHeader(value = "Authorization", required = false) String token,
                                              @RequestPart(value = "imagem_perfil", required = false) MultipartFile imagem_perfil,
                                              @RequestPart(value = "username", required = false) String username,
                                              @RequestPart(value = "origem", required = false) String origem,
                                              @RequestPart(value = "destino", required = false) String destino,
                                              @RequestPart(value = "horarios", required = false) String horarios,
                                              @RequestPart(value = "motorista", required = false) String motorista) {
        boolean motorista_bool = motorista.equals("true");
        String email = jwtUtil.getEmailFromToken(token.substring(7));
        Usuario_DTO user = userService.findByEmailDTO(email);
        return ResponseEntity.ok(userService.update(user, imagem_perfil, username, origem, destino, horarios, motorista_bool));
    }

    @GetMapping("/read")
    public ResponseEntity<Usuario_DTO> read(@RequestHeader("Authorization") String token) {
        String email = jwtUtil.getEmailFromToken(token.substring(7));
        Usuario_DTO user = userService.findByEmailDTO(email);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token) {
        String email = jwtUtil.getEmailFromToken(token.substring(7));
        Usuario_DTO user = userService.findByEmailDTO(email);
        userService.delete(user);
        return ResponseEntity.ok().build();
    }
}
