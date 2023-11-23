package com.tevology.logistica.controllers.auxiliares;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tevology.logistica.models.entity.auxiliares.TablaAuxiliar;
import com.tevology.logistica.models.services.auxiliares.IConfiguracionService;

@RestController
@RequestMapping("/api")
public class TablaAuxiliarRestController {

	@Autowired
	private IConfiguracionService configuracionService;
	
	@GetMapping("/tablaAuxiliar/all")
	public List<TablaAuxiliar> getAllTablaAuxiliar() {
		return configuracionService.findAll();
	}
	
	@GetMapping("/tablaAuxiliar/filtro")
	public List<TablaAuxiliar> index (
			@RequestParam(value = "modulo"			,required = false,defaultValue = "0") Integer modulo,
			@RequestParam(value = "submodulo"		,required = false,defaultValue = "0") Integer submodulo) {
		return configuracionService.listFiltro(modulo, submodulo);
	}
	
	@GetMapping("/tablaAuxiliar/ByCod/{codTablaAuxiliar}")
	public TablaAuxiliar getTablaAuxiliarByCod(@PathVariable String codTablaAuxiliar) {
		return configuracionService.findByCodTablaAux(codTablaAuxiliar);
	}
	
}
