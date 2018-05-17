package org.ingservicios.ISS_P2;

import java.util.List;

public interface matriculaDAOInterface {
	public List<matriculaDTO> listarMatriculas();
	public void addMatricula(matriculaDTO Matricula);
	public matriculaDTO buscaRegistroMatricula(String Matricula, int ParkingID);
	public matriculaDTO buscaMatricula(String matricula);	
	public matriculaDTO buscaParkingIDMatricula(int ID, String Matricula);
	public void actualizaCoche(matriculaDTO Vehiculo, int Registro);
}
