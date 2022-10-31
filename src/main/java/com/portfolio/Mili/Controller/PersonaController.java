package com.portfolio.Mili.Controller;

import com.google.gson.Gson;
import java.io.File;
 
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import com.portfolio.Mili.Entity.Persona;
import com.portfolio.Mili.Security.Controller.Mensaje;
import com.portfolio.Mili.Service.ImpPersonaService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "https://frontendmili.web.app")
public class PersonaController {
    @Autowired ImpPersonaService ipersonaService;
    
    @GetMapping("/personas/traer")
    public List<Persona> getPersona(){
        return ipersonaService.getPersona();
    }
    
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "La persona fue creada correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable int  id){
        ipersonaService.deletePersona(id);
        return "La persona fue eliminada correctamente";
    }
    
    @GetMapping("/photos/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
        byte[] image = new byte[0];
        try {
            image = FileUtils.readFileToByteArray(new File("photos/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
    
    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/personas/editar/{id}")
    public void editPersona(@PathVariable("id") int id,@RequestParam("persona") String strPersona, @RequestParam("fichero") MultipartFile multipartFile) throws IOException{
        Persona per = ipersonaService.getOne(id).get();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        //Establecemos el directorio donde se subiran nuestros ficheros  
        String uploadDir = "photos";
         
        System.out.println(strPersona);
        Gson gson = new Gson();
        Persona persona = gson.fromJson(strPersona, Persona.class);
        //Obtenemos la propiedades del usuario
        System.out.println(persona.getId());
        System.out.println(persona.getNombre());
        System.out.println(persona.getDescripcion());
        System.out.println(persona.getPuesto());
         
        //Establacecemos la imagen
        per.setImg(fileName);
        System.out.println(persona.getImg());
         
        //Guardamos la imagen
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    
        per.setNombre(persona.getNombre());
        per.setPuesto(persona.getPuesto());
        per.setDescripcion(persona.getDescripcion());
        
        
        System.out.println(per.getNombre());
        System.out.println(per.getDescripcion());
        System.out.println(per.getPuesto());
        System.out.println(per.getImg());
        
        
        
        
        
        
        ipersonaService.savePersona(per);
        //return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }
    
    @GetMapping("/personas/traer/perfil")
    public Persona findPersona(){
        return ipersonaService.findPersona((int)1);
    }
}
