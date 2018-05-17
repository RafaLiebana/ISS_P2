package org.ingservicios.ISS_P2;
import java.sql.Timestamp;

public class matriculaDTO {
	private int registro;
	private int parkingId;
	private String matricula;
	private Timestamp TimeStamp;


public matriculaDTO(){	
	this.registro=0;
	this.parkingId=0;
	this.matricula="";
	this.TimeStamp=null;
	}

public matriculaDTO(int Registro, int ParkingID, String Matricula, Timestamp TimeStamp) {
	this.registro=Registro;
	this.parkingId=ParkingID;
	this.matricula=Matricula;
	this.TimeStamp=TimeStamp;
	
}

public int getRegistro() {
	return registro;
}

public void setRegistro(int registro) {
	this.registro = registro;
}

public int getParkingId() {
	return parkingId;
}

public void setParkingId(int parkingId) {
	this.parkingId = parkingId;
}

public String getMatricula() {
	return matricula;
}

public void setMatricula(String matricula) {
	this.matricula = matricula;
}

public Timestamp getTimeStamp() {
	return TimeStamp;
}

public void setTimeStamp(Timestamp timeStamp) {
	TimeStamp = timeStamp;
}


}
