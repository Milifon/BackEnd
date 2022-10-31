package com.portfolio.Mili.Interface;

import com.portfolio.Mili.Entity.Skill;
import java.util.List;

public interface ISkillService {
    //traer lista de persona
    public List<Skill> getSkill();
    
    //guardar objeto tipo persona
    public void saveSkill(Skill skill);
    
    //eliminar objeto tipo persona
    public void deleteSkill(Long id);
    
    //buscar persona
    public Skill findSkill(Long id);

    public Object getOne(int id);
}