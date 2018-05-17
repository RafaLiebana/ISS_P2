package org.ingservicios.ISS_P2;


import java.sql.Timestamp;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	//Al marcarlo con @Autowired, se inyectará, como una instancia de dao, 
		//un bean de una clase que implemente el interfaz DAOVehiculosInterfaz QUE PACAHAO
		@Autowired
		private matriculaDAOInterface dao;
		

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "testparking";
	}
	
	
	@RequestMapping(value="/registroMatricula/enviar", method = RequestMethod.POST)
	public ResponseEntity<matriculaDTO> crearVehiculo(@RequestBody matriculaDTO vehiculo){
		boolean resul=false;
		if(dao.buscaMatricula(vehiculo.getMatricula())==null && vehiculo.getParkingId()==0) {
		dao.addMatricula(vehiculo);
		
		}else if(dao.buscaMatricula(vehiculo.getMatricula())!=null && dao.buscaParkingIDVehiculoMatricula(vehiculo.getParkingId(),vehiculo.getMatricula())==null
				&& vehiculo.getParkingId()==1) {
			dao.addMatricula(vehiculo);
			
		}else if(dao.buscaMatricula(vehiculo.getMatricula())!=null && dao.buscaParkingIDVehiculoMatricula(vehiculo.getParkingId(), vehiculo.getMatricula())!=null 
				&& vehiculo.getParkingId()==0) {
			int registro=dao.buscaRegistroVehiculo(vehiculo.getMatricula(), vehiculo.getParkingId()).getRegistro();
			dao.actualizaCoche(vehiculo, registro);
		}else{
			int registro=dao.buscaRegistroVehiculo(vehiculo.getMatricula(), vehiculo.getParkingId()).getRegistro();
			dao.actualizaCoche(vehiculo, registro);
		}
		
		ResponseEntity<matriculaDTO> respuestaHTTP = new ResponseEntity<matriculaDTO>(vehiculo, HttpStatus.CREATED);
		return respuestaHTTP;
		
		
	}
	
	
	 @RequestMapping(value = "/pago", method = RequestMethod.GET)
		public String mostrarPago (Model model) {
			
				return "testcoste";
			
		} 
	 
	
	 //En el caso de solicitar los datos de un único elemento, es común identificarlo mediante un id 
	 //en la URL del recurso, para ello, Spring facilita la etiqueta @PathVariable
	 @RequestMapping(value="/coste/{matricula}",method= RequestMethod.GET)
	 public @ResponseBody String coste(@PathVariable (value="matricula")String matricula,Model model) {
	 	String precio=""; 	
	 	
	 	/* No
	 	DTOVehiculos vehiculos = new DTOVehiculos();
	 	vehiculos = dao.buscaMatricula(matricula);
	 	*/

	 	
	 	//Buscamos la matricula en BDDD
	 	if(dao.buscaMatricula(matricula)!=null) {
	 		
	 		/* No es así.
	 		int registroEntr=dao.buscaRegistroVehiculo(matricula, dao.buscaParkingIDVehiculo(0).getParkingId()).getRegistro();
	 		int registroSali=dao.buscaRegistroVehiculo(matricula, dao.buscaParkingIDVehiculo(1).getParkingId()).getRegistro();
	 		*/
	 		
	 		//obtenemos el tiempo de entrada y salida 
	 		Timestamp tentrada = dao.tentrada(matricula, dao.buscaParkingIDVehiculo(0).getParkingId());
	 		Timestamp tsalida = dao.tsalida(matricula, dao.buscaParkingIDVehiculo(1).getParkingId());
	 			
	 		double tiempo = (((tsalida.getTime()-tentrada.getTime())/1000)/60);
	 		System.out.println("Minutos de estancia: "+ tiempo);
	 		
	 		double Tarifa= 0.01;
	 		//Coste en Euros por minuto
	 		double coste= tiempo * Tarifa;
	 	
	     // No necesario model
	 	   
	 		precio = Double.toString(coste);

	 		}else {
	 			double coste=0;
  
	 	 		precio = Double.toString(coste);
	 		}
	 
	 	

	return precio;
}
}