package com.tevology.logistica.models.services.seguridad;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tevology.logistica.models.dao.seguridad.IModuloDao;
import com.tevology.logistica.models.entity.seguridad.Menu;
import com.tevology.logistica.models.entity.seguridad.Modulo;
import com.tevology.logistica.models.entity.seguridad.Role;
import com.tevology.logistica.models.entity.seguridad.RoleDetalle;
import com.tevology.logistica.models.entity.seguridad.SubModulo;
import com.tevology.logistica.models.entity.seguridad.Usuario;

@Service
public class ModuloServiceImpl implements IModuloService{

	@Autowired
	private IModuloDao moduloDao;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public List<Modulo> findAll() {
		return moduloDao.findAll();
	}
	
	
	@Override
	public List<Modulo> findModulosByUsername(String username){
		List<Integer> rolesId = new ArrayList<>();
		List<Modulo> modulosAll =  new ArrayList<>();
		List<Modulo> modulos = new ArrayList<>();
		
		
		Usuario usuario = usuarioService.findByUsername(username);
		for (Role rol : usuario.getRoles()) {
			rolesId.add(rol.getId());
		}
		
		modulosAll = moduloDao.findAll();
		
		for (Modulo mod : modulosAll) {
			List<SubModulo> subModulos =  new ArrayList<>();
			for(SubModulo sub: mod.getSubModulos()) {
				List<Menu> menus = new ArrayList<>();
				for(Menu men: sub.getMenus()) {					
					List<Integer> rolAux = new ArrayList<>();
					if(men.getRolesDetallados().size() > 0) {
						rolAux = men.getRolesDetallados().stream()
								 .map(RoleDetalle::getRol)
								 .map(Role::getId)
								 .collect(Collectors.toList());	
					}
			        for(Integer r: rolAux) {
			            if(rolesId.contains(r)) {
			            	Menu menuCopy = new Menu();
			            	menuCopy.setId(men.getId());
			            	menuCopy.setNombre(men.getNombre());
			            	menuCopy.setIcon(men.getIcon());
			            	menuCopy.setRuta(men.getRuta());
			            	menuCopy.setRolesDetallados(new ArrayList<>());
			            	menus.add(menuCopy);
				            break;
			            }
			        }					
				}
				if(menus.size() > 0) {
					SubModulo subModuloCopy = new SubModulo();
					subModuloCopy.setId(sub.getId());
					subModuloCopy.setNombre(sub.getNombre());
					subModuloCopy.setIcon(sub.getIcon());
					subModuloCopy.setMenus(menus);
					subModulos.add(subModuloCopy);
				}
			}
			if(subModulos.size() > 0) {
				Modulo moduloCopy = new Modulo();
				moduloCopy.setId(mod.getId());
				moduloCopy.setNombre(mod.getNombre());
				moduloCopy.setIcon(mod.getIcon());
				moduloCopy.setSubModulos(subModulos);
				modulos.add(moduloCopy);
			}
		}
		return modulos;
	}
}
