package com.portfolio.Mili.Interface;

import com.portfolio.Mili.Entity.Persona;
import java.util.List;

public interface IPersonaService {
    //traer lista de persona
    public List<Persona> getPersona();
    
    //guardar objeto tipo persona
    public void savePersona(Persona persona);
    
    //eliminar objeto tipo persona
    public void deletePersona(Long id);
    
    //buscar persona
    public Persona findPersona(Long id);

    public Object getOne(int id);
}
