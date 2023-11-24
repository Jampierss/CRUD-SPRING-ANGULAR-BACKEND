package com.tevology.logistica.models.services.maestros;

import com.tevology.logistica.VariablesGlobales;
import com.tevology.logistica.models.dao.maestros.ISubareaDAO;
import com.tevology.logistica.models.entity.maestros.Subarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubareaServiceImpl implements ISubareaService{

    @Autowired
    private ISubareaDAO subareaDAO;

    @Override
    @Transactional(readOnly = true)
    public Subarea findById(Long id) {
        return subareaDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subarea> findAll() {
        return subareaDAO.findAll();
    }

    @Override
    public Page<Subarea> findAllPageAndSort(String nombre, String areaNombre, Integer estadoId, String columnSort, int order, int page) {
        Pageable sorted = null;

        if (order == 0) {
            sorted = PageRequest.of(page, VariablesGlobales.ITEMS_PER_PAGE_MAESTROS, Sort.by(columnSort).ascending());
        } else {
            sorted = PageRequest.of(page, VariablesGlobales.ITEMS_PER_PAGE_MAESTROS, Sort.by(columnSort).descending());
        }
        return subareaDAO.findAllPageAndSort(nombre, areaNombre, estadoId, sorted);
    }

    @Override
    @Transactional(readOnly = false)
    public Subarea save(Subarea subarea) {
        return subareaDAO.save(subarea);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subarea> autocompleteList(String term) {
        return subareaDAO.autocompleteList(term);
    }
}
