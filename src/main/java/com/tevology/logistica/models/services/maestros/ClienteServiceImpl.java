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
import com.tevology.logistica.models.dao.maestros.IClienteDao;
import com.tevology.logistica.models.entity.maestros.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private IClienteDao clienteDao;

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Integer id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAllPageAndSort(String columnSort, int order, int page) {
		Pageable sorted = null;
		if (order == 0) {
			sorted = PageRequest.of(page, VariablesGlobales.ITEMS_PER_PAGE_MAESTROS, Sort.by(columnSort).ascending());
		} else {
			sorted = PageRequest.of(page, VariablesGlobales.ITEMS_PER_PAGE_MAESTROS, Sort.by(columnSort).descending());
		}
		return clienteDao.findAllPageAndSort(sorted);
	}

	@Override
	@Transactional(readOnly = false)
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}
}