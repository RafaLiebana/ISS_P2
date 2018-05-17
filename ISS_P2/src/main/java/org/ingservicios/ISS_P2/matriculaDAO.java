package org.ingservicios.ISS_P2;

import java.util.List;
import java.sql.Timestamp;
import java.util.Calendar;
import org.ingservicios.ISS_P2.matriculaMapper;
import org.ingservicios.ISS_P2.matriculaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class matriculaDAO implements matriculaDAOInterface {
	private JdbcTemplate jdbcTemplate;
	//private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	public List<matriculaDTO> listarMatriculas(){
		String sql="select * from matriculas";
		matriculaMapper mapper=new matriculaMapper();
		List<matriculaDTO> matriculas=this.jdbcTemplate.query(sql, mapper);
		return matriculas;
	}
	
	public void addMatricula(matriculaDTO matricula) {			
		String sql = "insert into matriculas(ParkingID,Matricula) values(?,?)";
		Object[ ] parametros = {matricula.getParkingId(), matricula.getMatricula()}; //Array de objetos
		//Para operaciones INSERT, UPDATE o DELETE se usa el método jdbcTemplate.update
		this.jdbcTemplate.update(sql,parametros);
		}
	
	//Devuelve la matricula buscado o null si no existe segun matricula
		public matriculaDTO buscaMatricula(String matricula){ 
			String sql = "select * from matriculas where Matricula = ?";
			Object[ ] parametros = {matricula}; //Array de objetos
			matriculaMapper mapper = new matriculaMapper();
			List<matriculaDTO> vehiculos = this.jdbcTemplate.query(sql, parametros, mapper);
			if (vehiculos.isEmpty()) return null;
			else return vehiculos.get(0);
			}
		
		public matriculaDTO buscaRegistroMatricula(String matricula, int parkingID) {
			String sql = "select * from matriculas where ParkingID = ? AND Matricula = ?";
			Object[] parametros = {parkingID, matricula};
			matriculaMapper mapper = new matriculaMapper();
			List<matriculaDTO> vehiculos = this.jdbcTemplate.query(sql, parametros, mapper);
			if (vehiculos.isEmpty()) return null;
			else return vehiculos.get(0);
		}
		
		public matriculaDTO buscaParkingIDMatricula(int id, String matricula){ 
			String sql = "select * from matriculas where ParkingID= ? AND Matricula= ?";
			Object[ ] parametros = {id, matricula}; //Array de objetos
			matriculaMapper mapper = new matriculaMapper();
			List<matriculaDTO> vehiculos = this.jdbcTemplate.query(sql, parametros, mapper);
			if (vehiculos.isEmpty()) return null;
			else return vehiculos.get(0);
			}
		
		public void actualizaCoche(matriculaDTO matricula, int registro){
			String sql = "update matriculas SET ParkingID = ?, Matricula = ? ,TimeStamp =? where Registro= ?";
			//Obtenemos fecha actual para actualizarla
			Calendar calendar = Calendar.getInstance();
			Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
			Object[] parametros = {matricula.getParkingId(),matricula.getMatricula(),currentTimestamp,registro}; 
			//Para operaciones INSERT, UPDATE o DELETE se usa el método jdbcTemplate.update
			this.jdbcTemplate.update(sql,parametros);
		}	
}
