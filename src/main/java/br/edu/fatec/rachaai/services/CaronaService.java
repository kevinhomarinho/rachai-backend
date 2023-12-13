package br.edu.fatec.rachaai.services;

import br.edu.fatec.rachaai.models.Usuario_DTO;
import br.edu.fatec.rachaai.repositories.MotoristaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CaronaService {

    @Autowired
    private MotoristaRespository motoristaRespository;

    public List<Usuario_DTO> findAll(int init, int quantity) {
        Pageable pageable = PageRequest.of(init, quantity);
        Page<Usuario_DTO> caronas = motoristaRespository.findAll(pageable);
        List<Usuario_DTO> caronasList = new ArrayList<>();
        caronas.forEach(caronasList::add);
        Collections.reverse(caronasList);
        return caronasList;
    }

    public List<Usuario_DTO> findByOrigem(String origem, int init, int quantity) {
        Pageable pageable = PageRequest.of(init, quantity);
        Page<Usuario_DTO> caronas = motoristaRespository.findByOrigem(origem, pageable);
        List<Usuario_DTO> caronasList = new ArrayList<>();
        caronas.forEach(caronasList::add);
        Collections.reverse(caronasList);
        return caronasList;
    }
}
