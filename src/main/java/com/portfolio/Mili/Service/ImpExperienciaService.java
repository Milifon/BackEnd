package com.portfolio.Mili.Service;

import com.portfolio.Mili.Entity.Experiencia;
import com.portfolio.Mili.Repository.IExperienciaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpExperienciaService {
     @Autowired
     IExperienciaRepository iexperienciaRepository;
     
     public List<Experiencia> list(){
         return iexperienciaRepository.findAll();
     }
     
     public Optional<Experiencia> getOne(int id){
         return iexperienciaRepository.findById(id);
     }
     
     public Optional<Experiencia> getByEmpleado(String empleado){
         return iexperienciaRepository.findByEmpleado(empleado);
     }
     
     public void save(Experiencia expe){
         iexperienciaRepository.save(expe);
     }
     
     public void delete(int id){
         iexperienciaRepository.deleteById(id);
     }
     
     public boolean existsById(int id){
         return iexperienciaRepository.existsById(id);
     }
     
     public boolean existsByEmpleado(String empleado){
         return iexperienciaRepository.existsByEmpleado(empleado);
     }

    public void saveExperiencia(Experiencia experiencia) {
        iexperienciaRepository.save(experiencia);
    }
    
    public Experiencia findExperiencia(int id) {
        Experiencia experiencia = iexperienciaRepository.findById(id).orElse(null);
        return experiencia;
    }
}
