package br.edu.fatec.rachaai.controller;

import br.edu.fatec.rachaai.models.Usuario_DTO;
import br.edu.fatec.rachaai.services.CaronaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/caronas")
public class CaronasController {

    @Autowired
    private CaronaService caronaService;

    @GetMapping("/page={init}&size={quantity}")
    public ResponseEntity<List<Usuario_DTO>> findAll(@PathVariable int init, @PathVariable int quantity) {
        return ResponseEntity.ok(caronaService.findAll(init, quantity));
    }

    @GetMapping("/origem={origem}/page={init}&size={quantity}")
    public ResponseEntity<List<Usuario_DTO>> findByOrigem(@PathVariable String origem, @PathVariable int init, @PathVariable int quantity) {
        return ResponseEntity.ok(caronaService.findByOrigem(origem, init, quantity));
    }

}
