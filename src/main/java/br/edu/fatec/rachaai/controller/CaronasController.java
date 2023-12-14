package br.edu.fatec.rachaai.controller;

import br.edu.fatec.rachaai.config.JwtUtil;
import br.edu.fatec.rachaai.models.Usuario_DTO;
import br.edu.fatec.rachaai.services.CaronaService;
import br.edu.fatec.rachaai.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caronas")
public class CaronasController {

    @Autowired
    private CaronaService caronaService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/page={init}&size={quantity}")
    public ResponseEntity<List<Usuario_DTO>> findAll(@RequestHeader("Authorization") String token, @PathVariable int init, @PathVariable int quantity) {
        String email = jwtUtil.getEmailFromToken(token.substring(7));
        Usuario_DTO user = userService.findByEmailDTO(email);
        return ResponseEntity.ok(caronaService.findAll(user, init, quantity));
    }

    @GetMapping("/origem={origem}/page={init}&size={quantity}")
    public ResponseEntity<List<Usuario_DTO>> findByOrigem(@RequestHeader("Authorization") String token, @PathVariable String origem, @PathVariable int init, @PathVariable int quantity) {
        String email = jwtUtil.getEmailFromToken(token.substring(7));
        Usuario_DTO user = userService.findByEmailDTO(email);
        return ResponseEntity.ok(caronaService.findByOrigem(user, origem, init, quantity));
    }

}
