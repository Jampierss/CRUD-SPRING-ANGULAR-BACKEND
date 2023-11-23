package com.tevology.logistica.models.dao.auxiliares;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tevology.logistica.models.entity.auxiliares.TablaAuxiliar;

public interface ITablaAuxiliarDao extends CrudRepository<TablaAuxiliar, String>{

	@Query(value = "select t from TablaAuxiliar t"
				 + " where ((0=?1) or (t.subModulo.modulo.id=?1)) "
				 + "and ((0=?2) or (t.subModulo.id=?2)) ")
	public List<TablaAuxiliar> listFiltro(Integer modulo, Integer submodulo);
	
	public TablaAuxiliar findByCodTablaAuxiliar(String codTablaAuxiliar);
	
}
