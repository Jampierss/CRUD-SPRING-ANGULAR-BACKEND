package com.tevology.logistica.models.services.auxiliares;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tevology.logistica.models.dao.auxiliares.IParametroDao;
import com.tevology.logistica.models.entity.auxiliares.Parametro;

@Service
public class IParametroServiceImpl implements IParametroService {

	@Autowired
	private IParametroDao parametroDao;
	
	@Override
	public Parametro getParametroById(Integer id) {
		return parametroDao.findById(id).orElse(null);
	}

	@Override
	public Parametro getValorIntervalo() {
		return parametroDao.findIntervaloHorario();
	}

	@Override
	public List<Parametro> getAllParametros() {
		return parametroDao.findAll();
	}

	@Override
	public Parametro save(Parametro parametro) {
		return parametroDao.save(parametro);
	}
	
}
