package org.ingservicios.ISS_P2;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
		@Autowired
		private matriculaDAOInterface dao;
		
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "testparking";
	}
	
	@RequestMapping(value="/registroMatricula", method = RequestMethod.POST)
	public ResponseEntity<matriculaDTO> crearmatricula(@RequestBody matriculaDTO matricula){
		boolean resul=false;
		if(dao.buscaMatricula(matricula.getMatricula())==null && matricula.getParkingId()==0) {
		dao.addMatricula(matricula);
		
		}else if(dao.buscaMatricula(matricula.getMatricula())!=null && dao.buscaParkingIDMatricula(matricula.getParkingId(),matricula.getMatricula())==null
				&& matricula.getParkingId()==1) {
			dao.addMatricula(matricula);
			
		}else if(dao.buscaMatricula(matricula.getMatricula())!=null && dao.buscaParkingIDMatricula(matricula.getParkingId(), matricula.getMatricula())!=null 
				&& matricula.getParkingId()==0) {
			int registro=dao.buscaRegistroMatricula(matricula.getMatricula(), matricula.getParkingId()).getRegistro();
			dao.actualizaCoche(matricula, registro);
		}else{
			int registro=dao.buscaRegistroMatricula(matricula.getMatricula(), matricula.getParkingId()).getRegistro();
			dao.actualizaCoche(matricula, registro);
		}
		
		ResponseEntity<matriculaDTO> respuestaHTTP = new ResponseEntity<matriculaDTO>(matricula, HttpStatus.CREATED);
		return respuestaHTTP;
		
		
	}
	 
	
}