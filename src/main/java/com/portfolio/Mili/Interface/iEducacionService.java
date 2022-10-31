package com.portfolio.Mili.Interface;

import com.portfolio.Mili.Entity.Educacion;
import java.util.List;

public interface iEducacionService {
    //traer lista de persona
    public List<Educacion> getEducacion();
    
    //guardar objeto tipo persona
    public void saveEducacion(Educacion experiencia);
    
    //eliminar objeto tipo persona
    public void deleteEducacion(Long id);
    
    //buscar persona
    public Educacion findEducacion(Long id);

    public Object getOne(int id);
}