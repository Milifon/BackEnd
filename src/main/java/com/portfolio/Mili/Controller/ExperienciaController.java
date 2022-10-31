package com.portfolio.Mili.Controller;

import com.google.gson.Gson;
import com.portfolio.Mili.Entity.Experiencia;
import com.portfolio.Mili.Security.Controller.Mensaje;
import com.portfolio.Mili.Service.ImpExperienciaService;
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
public class ExperienciaController {
    @Autowired
    ImpExperienciaService iexperienciaService;
    
    @GetMapping("/experiencia/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = iexperienciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/experiencia/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!iexperienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = iexperienciaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @DeleteMapping("/experiencia/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!iexperienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        iexperienciaService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }

    
    @PostMapping("/experiencia/create")
    public ResponseEntity<?> create(@RequestParam("expNueva") String strExperiencia, @RequestParam("fichero") MultipartFile multipartFile) throws IOException{      
        
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        //Establecemos el directorio donde se subiran nuestros ficheros  
        String uploadDir = "photos";
         
        Gson gson = new Gson();
        Experiencia exp = gson.fromJson(strExperiencia, Experiencia.class);
        
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        Experiencia experiencia = new Experiencia(exp.getEmpleado(), exp.getEmpresa(), exp.getPeriodo(), exp.getDescripcion(), fileName);
        iexperienciaService.save(experiencia);
        
        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }
    
    
    @PutMapping("/experiencia/update/{id}")
    public void update(@PathVariable("id") int id,@RequestParam("expEditar") String strExperiencia, @RequestParam("fichero") MultipartFile multipartFile) throws IOException{
        
        Experiencia exp = iexperienciaService.getOne(id).get();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        //Establecemos el directorio donde se subiran nuestros ficheros  
        String uploadDir = "photos";
         
        System.out.println(id + strExperiencia);
        Gson gson = new Gson();
        Experiencia experiencia = gson.fromJson(strExperiencia, Experiencia.class);
        //Obtenemos la propiedades del usuario
         
        //Establacecemos la imagen
        exp.setImg(fileName);
        System.out.println(experiencia.getImg());
         
        //Guardamos la imagen
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    
        exp.setEmpleado(experiencia.getEmpleado());
        exp.setEmpresa(experiencia.getEmpresa());
        exp.setPeriodo(experiencia.getPeriodo());
        exp.setDescripcion(experiencia.getDescripcion());
        
        System.out.println(exp.getId());
        System.out.println(exp.getEmpleado());
        System.out.println(exp.getEmpresa());
        System.out.println(exp.getDescripcion());
        System.out.println(exp.getPeriodo());
        iexperienciaService.saveExperiencia(exp);
    }
    
    @GetMapping("/experiencia/traer/{id}")
    public Experiencia findExperiencia(@PathVariable("id") int id){
        return iexperienciaService.findExperiencia(id);
    }
}
