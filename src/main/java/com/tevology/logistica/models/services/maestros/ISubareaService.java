package com.tevology.logistica.models.services.maestros;

import com.tevology.logistica.models.entity.maestros.Subarea;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISubareaService {

    public Subarea findById(Long id);
    public List<Subarea> findAll();

    public Page<Subarea> findAllPageAndSort(String nombre, Integer estadoId, String columnSort, int order, int page);
    public Subarea save(Subarea subarea);

    public List<Subarea> autocompleteList(String term);

}
