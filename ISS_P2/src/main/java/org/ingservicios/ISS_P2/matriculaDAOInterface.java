package org.ingservicios.ISS_P2;

import java.util.List;


import java.sql.Timestamp;


public interface matriculaDAOInterface {
	public List<matriculaDTO> listarMatriculas();
	
	public void addMatricula(matriculaDTO Matricula);
	public matriculaDTO buscaRegistroVehiculo(String Matricula, int ParkingID);
	
	public matriculaDTO buscaMatricula(String matricula);
	
	
	public matriculaDTO buscaParkingIDVehiculo(int ID);
	
	public matriculaDTO buscaParkingIDVehiculoMatricula(int ID, String Matricula);
	
	
	
	public void actualizaCoche(matriculaDTO Vehiculo, int Registro);
	
	public Timestamp tsalida(String Matricula, int ParkingID);

	public Timestamp tentrada(String Matricula, int ParkingID);
}
