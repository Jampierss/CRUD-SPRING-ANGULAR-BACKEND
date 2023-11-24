package com.tevology.logistica.models.services.maestros;

import com.tevology.logistica.models.entity.maestros.Area;

import java.util.List;

public interface IAreaService {

    public List<Area> findAll();

    public Area save(Area area);

    public List<Area> findByNombre(String term);
}
