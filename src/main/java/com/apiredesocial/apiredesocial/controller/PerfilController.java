package com.apiredesocial.apiredesocial.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.apiredesocial.apiredesocial.model.Perfil;
import com.apiredesocial.apiredesocial.service.PerfilService;

@RestController
@RequestMapping("/perfis")
public class PerfilController {
    
    @Autowired PerfilService perfilService;

    @PostMapping
    public ResponseEntity<Perfil> create (@RequestBody Perfil perfil){
            perfilService.create(perfil);
            if (!perfilService.validaPerfil(perfil)) {
                return ResponseEntity.badRequest().build();
            }
            URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(perfil.getId())
                        .toUri();
            return ResponseEntity.created(location).body(perfil);     
    }

    @GetMapping
    public ResponseEntity<List<Perfil>> getAll(){
        List<Perfil> perfis = perfilService.getAll();
        if (perfis.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(perfis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> getById(@PathVariable("id") Long id){
        Perfil perfil = perfilService.getById(id);
        if (perfil == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(perfil);
    }

    //Tentamos com a url sem "/data" mas não funcionou :((
    @GetMapping("/data")
    public ResponseEntity<List<Perfil>> findByDate(@RequestParam("dataCriacao") LocalDate dataCriacao){
        List<Perfil> perfis = perfilService.findByDataCriacao(dataCriacao);
        if (perfis.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(perfis);
    }

    @PutMapping
    public ResponseEntity<Perfil> Update(@RequestBody Perfil perfilEditado){
        Perfil perfil = perfilService.updatePerfil(perfilEditado);
        if (perfil == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(perfil);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable("id") Long id){
        Boolean perfilExcluir = perfilService.delete(id);
        if (!perfilExcluir) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
