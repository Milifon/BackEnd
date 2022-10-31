package com.portfolio.Mili.Controller;

import com.google.gson.Gson;
import com.portfolio.Mili.Entity.Proyecto;
import com.portfolio.Mili.Security.Controller.Mensaje;
import com.portfolio.Mili.Service.ImpProyectoService;
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
public class ProyectoController {
    @Autowired
    ImpProyectoService iproyectoService;
    
    @GetMapping("/proyecto/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = iproyectoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/proyecto/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        if(!iproyectoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyecto proyecto = iproyectoService.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    @DeleteMapping("/proyecto/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!iproyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        iproyectoService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }

    
    @PostMapping("/proyecto/create")
    public ResponseEntity<?> create(@RequestParam("proyNueva") String strProy) throws IOException{      
        
        
        
        Gson gson = new Gson();
        Proyecto proy = gson.fromJson(strProy, Proyecto.class);
        
        Proyecto proyecto = new Proyecto(proy.getNombre(), proy.getDescripcion(), proy.getFecha(), proy.getLink());
        iproyectoService.save(proyecto);
        
        return new ResponseEntity(new Mensaje("Proyecto agregado"), HttpStatus.OK);
    }
    
    
    @PutMapping("/proyecto/update/{id}")
    public void update(@PathVariable("id") int id,@RequestParam("proyEditar") String strProy) throws IOException{
        
        Proyecto proy = iproyectoService.getOne(id).get();
        Gson gson = new Gson();
        Proyecto proyecto = gson.fromJson(strProy, Proyecto.class);
        
        proy.setNombre(proyecto.getNombre());
        proy.setDescripcion(proyecto.getDescripcion());
        proy.setFecha(proyecto.getFecha());
        proy.setLink(proyecto.getLink());
        iproyectoService.saveProyecto(proy);
    }
    
    @GetMapping("/proyecto/traer/{id}")
    public Proyecto findProyecto(@PathVariable("id") int id){
        return iproyectoService.findProyecto(id);
    }
}