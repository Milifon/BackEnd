package com.portfolio.Mili.Service;

import com.portfolio.Mili.Entity.Educacion;
import com.portfolio.Mili.Repository.iEducacionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpEducacionService {
     @Autowired
     iEducacionRepository ieducacionRepository;
     
     public List<Educacion> list(){
         return ieducacionRepository.findAll();
     }
     
     public Optional<Educacion> getOne(int id){
         return ieducacionRepository.findById(id);
     }
     
     public Optional<Educacion> getByTitulo(String titulo){
         return ieducacionRepository.findByTitulo(titulo);
     }
     
     public void save(Educacion edu){
         ieducacionRepository.save(edu);
     }
     
     public void delete(int id){
         ieducacionRepository.deleteById(id);
     }
     
     public boolean existsById(int id){
         return ieducacionRepository.existsById(id);
     }
     
     public boolean existsByTitulo(String titulo){
         return ieducacionRepository.existsByTitulo(titulo);
     }

    public void saveEducacion(Educacion educacion) {
        ieducacionRepository.save(educacion);
    }
    
    public Educacion findEducacion(int id) {
        Educacion educacion = ieducacionRepository.findById(id).orElse(null);
        return educacion;
    }
}
