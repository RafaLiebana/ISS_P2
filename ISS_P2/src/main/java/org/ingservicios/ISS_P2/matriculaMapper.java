package org.ingservicios.ISS_P2;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class matriculaMapper implements RowMapper<matriculaDTO> {
public matriculaDTO mapRow(ResultSet rs,int rowNum) throws SQLException{
		
		matriculaDTO matricula=new matriculaDTO();
		matricula.setMatricula(rs.getString("Matricula"));
		matricula.setRegistro(rs.getInt("Registro"));
		matricula.setParkingID(rs.getInt("ParkingID"));
		matricula.setTimeStamp(rs.getTimestamp("TimeStamp"));
		
		return matricula;
	}

}
