package com.portfolio.Mili.Interface;

import com.portfolio.Mili.Entity.Experiencia;
import java.util.List;

public interface IExperienciaService {
    //traer lista de persona
    public List<Experiencia> getExperiencia();
    
    //guardar objeto tipo persona
    public void saveExperiencia(Experiencia experiencia);
    
    //eliminar objeto tipo persona
    public void deleteExperiencia(Long id);
    
    //buscar persona
    public Experiencia findExperiencia(Long id);

    public Object getOne(int id);
}