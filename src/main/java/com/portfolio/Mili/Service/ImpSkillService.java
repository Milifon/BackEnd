package com.portfolio.Mili.Service;

import com.portfolio.Mili.Entity.Skill;
import com.portfolio.Mili.Repository.ISkillRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpSkillService {
     @Autowired
     ISkillRepository iskillRepository;
     
     public List<Skill> list(){
         return iskillRepository.findAll();
     }
     
     public Optional<Skill> getOne(int id){
         return iskillRepository.findById(id);
     }
     
     public Optional<Skill> getByTitulo(String titulo){
         return iskillRepository.findByNombre(titulo);
     }
     
     public void save(Skill skill){
         iskillRepository.save(skill);
     }
     
     public void delete(int id){
         iskillRepository.deleteById(id);
     }
     
     public boolean existsById(int id){
         return iskillRepository.existsById(id);
     }
     
     public boolean existsByNombre(String nombre){
         return iskillRepository.existsByNombre(nombre);
     }

    public void saveSkill(Skill skill) {
        iskillRepository.save(skill);
    }
    
    public Skill findSkill(int id) {
        Skill skill = iskillRepository.findById(id).orElse(null);
        return skill;
    }
}