package com.tevology.logistica.models.dao.maestros;

import com.tevology.logistica.models.entity.maestros.Subarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISubareaDAO extends JpaRepository<Subarea, Long> {

    @Query("SELECT s FROM Subarea s WHERE UPPER(s.nombre) LIKE '%' + UPPER(:nombre) + '%' AND  (-1=:estadoId OR s.estado.tablaAuxiliarDetalleId.id = :estadoId) AND s.area.nombre LIKE '%' +  UPPER(:areaNombre) + '%'")
    public Page<Subarea> findAllPageAndSort(String nombre,String areaNombre, Integer estadoId, Pageable pageable);


    @Query(value = "SELECT TOP(3) id FROM mae_subarea WHERE UPPER(nombre) LIKE '%' + UPPER(?1) + '%' ORDER BY nombre ASC ", nativeQuery = true)
    public List<Subarea> autocompleteList(String term);

}
