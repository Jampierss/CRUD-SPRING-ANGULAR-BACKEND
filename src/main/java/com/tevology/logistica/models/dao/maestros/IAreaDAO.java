package com.tevology.logistica.models.dao.maestros;

import com.tevology.logistica.models.entity.maestros.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAreaDAO extends JpaRepository<Area, Long> {

    @Query("SELECT a FROM Area a WHERE a.nombre LIKE %?1%")
    public List<Area> findByNombre(String term);

}
