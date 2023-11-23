package com.tevology.logistica.models.services.auxiliares;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tevology.logistica.models.dao.auxiliares.ITablaAuxiliarDao;
import com.tevology.logistica.models.dao.auxiliares.ITablaAuxiliarDetalleDao;
import com.tevology.logistica.models.dao.seguridad.ISubModuloDao;
import com.tevology.logistica.models.entity.auxiliares.TablaAuxiliar;
import com.tevology.logistica.models.entity.auxiliares.TablaAuxiliarDetalle;

@Service
public class ConfiguracionServiceImpl implements IConfiguracionService {
	
	@Autowired
	public ITablaAuxiliarDetalleDao tablaAuxiliarDetalleDao;
	
	@Autowired
	public ITablaAuxiliarDao tablaAuxiliarDao;
	
	@Autowired
	public ISubModuloDao subModuloDao;	

	@Override
	@Transactional(readOnly=true)
	public TablaAuxiliarDetalle findTablaAuxiliarDetalleById(Integer id, String codTablaAuxiliar) {
		return tablaAuxiliarDetalleDao.findById(id, codTablaAuxiliar);
	}

	@Override
	@Transactional(readOnly=true)
	public TablaAuxiliarDetalle findTablaAuxiliarDetalleByNombre(String nombre, String codTablaAuxiliar) {
		return tablaAuxiliarDetalleDao.findByNombre(nombre, codTablaAuxiliar);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TablaAuxiliar> findAll() {
		// TODO Auto-generated method stub
		return (List<TablaAuxiliar>) tablaAuxiliarDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<TablaAuxiliar> listFiltro(Integer modulo, Integer submodulo) {
		// TODO Auto-generated method stub
		return tablaAuxiliarDao.listFiltro(modulo, submodulo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public TablaAuxiliar findByCodTablaAux(String codTablaAuxiliar) {
		// TODO Auto-generated method stub
		return tablaAuxiliarDao.findByCodTablaAuxiliar(codTablaAuxiliar);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<String> listTablaAuxiliarDetalleByCodigo(String codTablaAuxiliar) {
		return tablaAuxiliarDetalleDao.listByCodigo(codTablaAuxiliar);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TablaAuxiliarDetalle> autocompleteList(String codTablaAuxiliar, String nombre) {
		return tablaAuxiliarDetalleDao.autocompleteList(codTablaAuxiliar, nombre);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TablaAuxiliarDetalle> listTablaAuxiliarDetalleDropdownByCodigo(String codTablaAuxiliar) {
		return tablaAuxiliarDetalleDao.listDropdownByCodigo(codTablaAuxiliar);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TablaAuxiliarDetalle> listTablaAuxiliarDetalleDropdownByCodigoMerma(String codTablaAuxiliar, Integer id) {
		return tablaAuxiliarDetalleDao.listDropdownByCodigoMerma(codTablaAuxiliar, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TablaAuxiliarDetalle> listTablaAuxiliarDetalleByCodigoAuxiliar(String codTablaAuxiliar) {
		// TODO Auto-generated method stub
		return tablaAuxiliarDetalleDao.listByCodigoTablaAux(codTablaAuxiliar);
	}

	@Override
	public TablaAuxiliarDetalle save(TablaAuxiliarDetalle tablaAuxiliarDetalle) {
		// TODO Auto-generated method stub
		return tablaAuxiliarDetalleDao.save(tablaAuxiliarDetalle);
	}

}
