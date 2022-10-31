package com.portfolio.Mili.Interface;

import com.portfolio.Mili.Entity.Proyecto;
import java.util.List;

public interface IProyectoService {
    //traer lista de persona
    public List<Proyecto> getProyecto();
    
    //guardar objeto tipo persona
    public void saveProyecto(Proyecto proyecto);
    
    //eliminar objeto tipo persona
    public void deleteProyecto(Long id);
    
    //buscar persona
    public Proyecto findProyecto(Long id);

    public Object getOne(int id);
}