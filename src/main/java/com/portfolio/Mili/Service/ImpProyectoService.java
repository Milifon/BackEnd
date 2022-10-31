package com.portfolio.Mili.Service;

import com.portfolio.Mili.Entity.Proyecto;
import com.portfolio.Mili.Repository.IProyectoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpProyectoService {
     @Autowired
     IProyectoRepository iproyectoRepository;
     
     public List<Proyecto> list(){
         return iproyectoRepository.findAll();
     }
     
     public Optional<Proyecto> getOne(int id){
         return iproyectoRepository.findById(id);
     }
     
     public Optional<Proyecto> getByTitulo(String titulo){
         return iproyectoRepository.findByNombre(titulo);
     }
     
     public void save(Proyecto proyecto){
         iproyectoRepository.save(proyecto);
     }
     
     public void delete(int id){
         iproyectoRepository.deleteById(id);
     }
     
     public boolean existsById(int id){
         return iproyectoRepository.existsById(id);
     }
     
     public boolean existsByNombre(String nombre){
         return iproyectoRepository.existsByNombre(nombre);
     }

    public void saveProyecto(Proyecto proyecto) {
        iproyectoRepository.save(proyecto);
    }
    
    public Proyecto findProyecto(int id) {
        Proyecto proyecto = iproyectoRepository.findById(id).orElse(null);
        return proyecto;
    }
}