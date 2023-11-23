package com.tevology.logistica.models.services.auxiliares;

import java.util.List;

import com.tevology.logistica.models.entity.auxiliares.TablaAuxiliar;
import com.tevology.logistica.models.entity.auxiliares.TablaAuxiliarDetalle;

public interface IConfiguracionService {
	//Tabla Auxiliar Detalle
	public TablaAuxiliarDetalle findTablaAuxiliarDetalleById(Integer id, String codTablaAuxiliar);

	public TablaAuxiliarDetalle findTablaAuxiliarDetalleByNombre(String nombre, String codTablaAuxiliar);
	
	public List<String> listTablaAuxiliarDetalleByCodigo(String codTablaAuxiliar);
	
	public List<TablaAuxiliarDetalle> autocompleteList(String codTablaAuxiliar, String nombre);
	
	public List<TablaAuxiliarDetalle> listTablaAuxiliarDetalleDropdownByCodigo(String codTablaAuxiliar);
	
	public List<TablaAuxiliarDetalle> listTablaAuxiliarDetalleDropdownByCodigoMerma(String codTablaAuxiliar, Integer id);
	
	
	public List<TablaAuxiliar> findAll();
	public TablaAuxiliar findByCodTablaAux(String cod);
	public List<TablaAuxiliar> listFiltro(Integer modulo, Integer submodulo);
	
	public List<TablaAuxiliarDetalle> listTablaAuxiliarDetalleByCodigoAuxiliar(String codTablaAuxiliar);
	
	public TablaAuxiliarDetalle save(TablaAuxiliarDetalle tablaAuxiliarDetalle);
	
}
