package com.tevology.logistica.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.tevology.logistica.VariablesGlobales;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//Login
		.antMatchers(HttpMethod.GET,"/api/user/comprobar/{token}","/api/user/validarUsuario/{token}").permitAll()
		.antMatchers(HttpMethod.POST,"/api/user/resetPassword","/api/user/validateUser").permitAll()
		.antMatchers(HttpMethod.PUT,"/api/user/changedPassword/{token}/{password}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/modulo/modulosByUsername/{username}").permitAll()
		//Administracion usuarios
		.antMatchers(HttpMethod.GET,"/api/usuarios-lolfar/autocomplete/{term}").hasAnyRole("TI")
		.antMatchers(HttpMethod.PUT,"/api/usuarios/{id}").hasAnyRole("TI")
		.antMatchers(HttpMethod.PUT,"/api/user/password/{id}").authenticated()
		.antMatchers(HttpMethod.PUT,"/api/user/datos/{id}").authenticated()
				.anyRequest().permitAll()
		/*
		//Parametros, Modulos y Tabla Auxiliar
		.antMatchers(HttpMethod.GET,"/api/parametro/{id}","/api/parametro/parametros").authenticated()
		.antMatchers(HttpMethod.GET,"/api/tablaAuxiliar/all","/api/modulo/all","/api/subModulo/all",
									"/api/subModuloById/{id}","/api/tablaAuxiliar/filtro").permitAll()
		.antMatchers(HttpMethod.GET,"/api/tabla_auxiliar_detalle/{codTablaAuxiliar}/{nombre}",
									"/api/tabla_auxiliar_detalle/id/{codTablaAuxiliar}/{id}",
									"/api/tabla_auxiliar_detalle/**").authenticated()
		.antMatchers(HttpMethod.POST,"/api/tabla_auxiliar_detalle").hasAnyRole("TI")
		.antMatchers(HttpMethod.PUT,"/api/parametro/update/{id}").hasAnyRole("TI")	
		.antMatchers(HttpMethod.PUT,"/api/tabla_auxiliar_detalle/update/{nombre}/{cod_tabla_auxiliar}").hasAnyRole("TI")
		//Local y listado de usuarios
		.antMatchers(HttpMethod.GET,"/api/usuario/{id}").hasAnyRole("TI")
		.antMatchers(HttpMethod.GET,"/api/usuarios","/api/usuarios/page/{page}", "/api/usuarios/page2/{page}",
									"/api/roles/autocompleteRoles/{term}","/api/role/all","/api/role/{id}").hasAnyRole("TI")
		.antMatchers(HttpMethod.PUT,"/api/usuarios/{id}").hasAnyRole("TI")
		.antMatchers(HttpMethod.PUT,"/api/user/password/{id}").authenticated()
		
		//Rutas
		.antMatchers(HttpMethod.GET, "/api/cargo/busqueda/{id}", "/api/cargo/listadoSelect",
									 "/api/cargo/page", "/api/cargo/autocomplete").hasAnyRole("TI")
		.antMatchers(HttpMethod.POST, "/api/cargo").hasAnyRole("TI")
		.antMatchers(HttpMethod.POST, "/api/cargo/update/{id}").hasAnyRole("TI")
		
		.antMatchers(HttpMethod.GET, "/api/cliente/busqueda/{id}","/api/cliente/listadoSelect",
									 "/api/cliente/page").hasAnyRole("TI")
		.antMatchers(HttpMethod.GET, "/api/cliente/img/{nombreFoto:.+}").permitAll()
		.antMatchers(HttpMethod.POST,"/api/cliente","/api/cliente/upload").hasAnyRole("TI")
		.antMatchers(HttpMethod.PUT, "/api/cliente/update/{id}").hasAnyRole("TI")
		
		//Configuracion
		.anyRequest().permitAll()*/
		.and()
		.cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOriginPatterns(Arrays.asList(VariablesGlobales.ORIGENES_PERMITIDOS));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;		
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		return bean;
	}
}
