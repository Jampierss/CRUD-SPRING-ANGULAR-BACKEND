package com.tevology.logistica.models.dao.maestros;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tevology.logistica.models.entity.maestros.Cargo;

public interface ICargoDao extends JpaRepository<Cargo, Integer> {
	
    @Query("   select c from Cargo c "
    		+ "where UPPER(c.nombre) like '%' + UPPER(:nombre) + '%' "
    		+ "and (-1=:estadoId or c.estado.tablaAuxiliarDetalleId.id= :estadoId) ")
    public Page<Cargo> findAllPageAndSort(String nombre, Integer estadoId, Pageable pageable);
    
    @Query(value="select top(3) id from mae_cargo "
    		+ "   where UPPER(nombre) like '%' + UPPER(?1) + '%' "
    		+ "   order by nombre asc ", nativeQuery = true)
	public List<Cargo> autocompleteList(String term);

}
