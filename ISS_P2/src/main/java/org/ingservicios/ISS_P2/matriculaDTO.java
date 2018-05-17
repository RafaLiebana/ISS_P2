package org.ingservicios.ISS_P2;
import java.sql.Timestamp;

public class matriculaDTO {
	private int Registro;
	private int ParkingID;
	private String Matricula;
	private Timestamp TimeStamp;


public matriculaDTO(){	
	this.Registro=0;
	this.ParkingID=0;
	this.Matricula="";
	this.TimeStamp=null;
	}

public matriculaDTO(int Registro, int ParkingID, String Matricula, Timestamp TimeStamp) {
	this.Registro=Registro;
	this.ParkingID=ParkingID;
	this.Matricula=Matricula;
	this.TimeStamp=TimeStamp;
	
}

public int getRegistro() {
	return Registro;
}

public void setRegistro(int registro) {
	Registro = registro;
}

public int getParkingID() {
	return ParkingID;
}

public void setParkingID(int parkingID) {
	ParkingID = parkingID;
}

public String getMatricula() {
	return Matricula;
}

public void setMatricula(String matricula) {
	Matricula = matricula;
}

public Timestamp getTimeStamp() {
	return TimeStamp;
}

public void setTimeStamp(Timestamp timeStamp) {
	TimeStamp = timeStamp;
}


}