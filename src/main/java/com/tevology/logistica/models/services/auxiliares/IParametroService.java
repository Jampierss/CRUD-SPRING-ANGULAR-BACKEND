package com.tevology.logistica.models.services.auxiliares;

import java.util.List;

import com.tevology.logistica.models.entity.auxiliares.Parametro;

public interface IParametroService {
	
	public Parametro getParametroById(Integer id);
	
	public Parametro getValorIntervalo();
	
	public List<Parametro> getAllParametros();
	
	public Parametro save(Parametro parametro);
}
