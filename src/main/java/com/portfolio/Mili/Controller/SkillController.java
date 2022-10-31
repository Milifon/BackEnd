package com.portfolio.Mili.Controller;

import com.google.gson.Gson;
import com.portfolio.Mili.Entity.Skill;
import com.portfolio.Mili.Security.Controller.Mensaje;
import com.portfolio.Mili.Service.ImpSkillService;
import java.io.IOException;
import java.util.List;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "https://frontendmili.web.app")
public class SkillController {
    @Autowired
    ImpSkillService iskillService;
    
    @GetMapping("/skill/lista")
    public ResponseEntity<List<Skill>> list(){
        List<Skill> list = iskillService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/skill/detail/{id}")
    public ResponseEntity<Skill> getById(@PathVariable("id") int id){
        if(!iskillService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Skill skill = iskillService.getOne(id).get();
        return new ResponseEntity(skill, HttpStatus.OK);
    }
    
    @DeleteMapping("/skill/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!iskillService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        iskillService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }

    
    @PostMapping("/skill/create")
    public ResponseEntity<?> create(@RequestParam("skillNueva") String strSkill) throws IOException{      
        
        
        
        Gson gson = new Gson();
        Skill sk = gson.fromJson(strSkill, Skill.class);
        
        System.out.println(sk.getNombre());
        
        Skill skill = new Skill(sk.getNombre(), sk.getPorcentaje());
        iskillService.save(skill);
        
        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }
    
    
    @PutMapping("/skill/update/{id}")
    public void update(@PathVariable("id") int id,@RequestParam("skillEditar") String strSkill) throws IOException{
        
        Skill sk = iskillService.getOne(id).get();
        Gson gson = new Gson();
        Skill skill = gson.fromJson(strSkill, Skill.class);
        
        sk.setNombre(skill.getNombre());
        sk.setPorcentaje(skill.getPorcentaje());
        iskillService.saveSkill(sk);
    }
    
    @GetMapping("/skill/traer/{id}")
    public Skill findSkill(@PathVariable("id") int id){
        return iskillService.findSkill(id);
    }
}
