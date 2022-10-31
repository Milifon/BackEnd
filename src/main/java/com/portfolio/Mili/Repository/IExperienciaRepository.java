package com.portfolio.Mili.Repository;

import com.portfolio.Mili.Entity.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExperienciaRepository extends JpaRepository<Experiencia, Integer>{
    public Optional<Experiencia> findByEmpleado(String empleado);
    public boolean existsByEmpleado(String empleado);
}
