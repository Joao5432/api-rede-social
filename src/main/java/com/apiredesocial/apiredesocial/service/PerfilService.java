package com.apiredesocial.apiredesocial.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apiredesocial.apiredesocial.model.Perfil;

@Service
public class PerfilService {
    public static List<Perfil> perfis = new ArrayList<>();

    public Perfil create(Perfil perfil){
        Long newId = (long) (perfis.size() + 1);
        perfil.setId(newId);
        perfis.add(perfil);
        return perfil;
    }

    public List<Perfil> getAll(){
        return perfis;
    }

    public Perfil getById(Long id){
        for (Perfil perfil : perfis) {
            if (perfil.getId() == id) {
                return perfil;
            }
        }
        return null;
    }

    public List<Perfil> findByDataCriacao(LocalDate data){
        List<Perfil> dataPerfils = new ArrayList<>();
        for (Perfil perfil : perfis) {
            if (perfil.getDataCriacao().isEqual(data)) {
                dataPerfils.add(perfil);
            }
        }
        return dataPerfils;
    }

    public Perfil updatePerfil(Perfil perfilEditado) {
        for (Perfil perfil : perfis) {
            if (perfil.getId() == perfilEditado.getId()) {
                perfil.setNome(perfilEditado.getNome());
                perfil.setEmail(perfilEditado.getEmail());
                perfil.setBiografia(perfilEditado.getBiografia());
                perfil.setFotoPerfil(perfilEditado.getFotoPerfil());
                perfil.setDataCriacao(perfilEditado.getDataCriacao());

                return perfil;
            }

        }
        return null;
    }

    public Boolean delete(Long id){
        for (Perfil perfil : perfis) {
            if (perfil.getId() == id) {
                perfis.remove(perfil);
                return true;
            }
        }
        return false;
    }

    public Boolean validaPerfil(Perfil perfil){
        if (perfil.getNome() == null || perfil.getEmail() == null || perfil.getBiografia() == null || perfil.getFotoPerfil() == null || perfil.getDataCriacao() == null) {
            return false;
        }
        return true;
    }
    
}
