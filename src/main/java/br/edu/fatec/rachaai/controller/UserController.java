package br.edu.fatec.rachaai.controller;

import br.edu.fatec.rachaai.config.JwtUtil;
import br.edu.fatec.rachaai.models.Usuario;
import br.edu.fatec.rachaai.models.Usuario_DTO;
import br.edu.fatec.rachaai.services.UserService;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

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
            Map<String, String> response = new HashMap<>();
            response.put("error", "EMAIL_ALREADY_REGISTERED");
            response.put("message", "Email já cadastrado");
            return ResponseEntity.badRequest().body(response);
        }
        
        userService.saveUser(user);
        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok()
            .header("Authorization", "Bearer " + token)
            .body(null);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody Usuario user) {
        if (userService.findByEmail(user.getEmail(), user.getPassword())){
            String token = jwtUtil.generateToken(user.getEmail());
            System.out.printf("Token: %s\n", token);
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token)
                    .build();

        }
        return ResponseEntity.badRequest().body("Email não cadastrado");
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
