package br.edu.fatec.rachaai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImagesController {
    private static final String BASE_PATH = "imagem";
    
    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(BASE_PATH + "/{username}" + "/{imageName}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String username, @PathVariable String imageName) {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + BASE_PATH + "/" + username + "/" + imageName);
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok().body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}