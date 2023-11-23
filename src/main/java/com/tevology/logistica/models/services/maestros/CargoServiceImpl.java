package com.tevology.logistica.models.services.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tevology.logistica.VariablesGlobales;
import com.tevology.logistica.models.dao.maestros.ICargoDao;
import com.tevology.logistica.models.entity.maestros.Cargo;

@Service
public class CargoServiceImpl implements ICargoService {
	
	@Autowired
	private ICargoDao cargoDao;

	@Override
	@Transactional(readOnly = true)
	public Cargo findById(Integer id) {
		return cargoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cargo> findAll() {
		return cargoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cargo> findAllPageAndSort(String nombre, Integer estadoId, String columnSort, int order, int page) {
		Pageable sorted = null;
		if (order == 0) {
			sorted = PageRequest.of(page, VariablesGlobales.ITEMS_PER_PAGE_MAESTROS, Sort.by(columnSort).ascending());
		} else {
			sorted = PageRequest.of(page, VariablesGlobales.ITEMS_PER_PAGE_MAESTROS, Sort.by(columnSort).descending());
		}
		return cargoDao.findAllPageAndSort(nombre, estadoId, sorted);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cargo> autocompleteList(String term) {
		return cargoDao.autocompleteList(term);
	}

	@Override
	@Transactional(readOnly = false)
	public Cargo save(Cargo cargo) {
		return cargoDao.save(cargo);
	}
}
