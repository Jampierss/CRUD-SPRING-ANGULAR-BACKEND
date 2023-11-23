package com.tevology.logistica.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tevology.logistica.VariablesGlobales;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final static Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);


	@Override
	public Path getPath(String nombreFoto, String rutaBase) {
		return Paths.get(rutaBase).resolve(nombreFoto).toAbsolutePath();
	}
	
	@Override
	public Resource cargar(String nombreFoto,String rutaBase) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreFoto, rutaBase);
		Resource recurso = null;

		recurso = new UrlResource(rutaArchivo.toUri());

		if (!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get(VariablesGlobales.RUTA_IMAGEN_DEFAULT).resolve(VariablesGlobales.IMAGEN_DEFAULT).toAbsolutePath();
			recurso = new UrlResource(rutaArchivo.toUri());

			log.error("No se pudo cargar la imagen: " + nombreFoto);
		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo, String rutaBase) throws IOException {
		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
		Path rutaArchivo = getPath(nombreArchivo, rutaBase);

		try {
			Files.copy(archivo.getInputStream(), rutaArchivo);
		} catch (Exception e) {
			log.info(e.getCause().toString());
		}

		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto, String rutaBase) {
		if (nombreFoto != null && nombreFoto.length() > 0) {
			Path rutaFotoAnterior = getPath(nombreFoto, rutaBase);
			File archivoFotoAnterior = rutaFotoAnterior.toFile();

			if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
				return true;
			}
		}
		return false;
	}
}
