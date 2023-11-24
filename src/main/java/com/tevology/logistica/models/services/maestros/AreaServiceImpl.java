package com.tevology.logistica.models.services.maestros;

import com.tevology.logistica.models.dao.maestros.IAreaDAO;
import com.tevology.logistica.models.entity.maestros.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService{

    @Autowired
    private IAreaDAO areaDAO;


    @Override
    public List<Area> findAll() {
        return areaDAO.findAll();
    }

    @Override
    public Area save(Area area) {
        return areaDAO.save(area);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Area> findByNombre(String term) {
        return areaDAO.findByNombre(term);
    }
}
