package bases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Recod implements Queryable{
	
	private String tabla = "recod";
	
	private String RECOD;
	private String DSCR;
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	public String getRECOD() {
		return RECOD;
	}
	public void setRECOD(String rECOD) {
		RECOD = rECOD;
	}
	public String getDSCR() {
		return DSCR;
	}
	public void setDSCR(String dSCR) {
		DSCR = dSCR;
	}
	@Override
	public String toString() {
		return "Recod [tabla=" + tabla + ", RECOD=" + RECOD + ", DSCR=" + DSCR + "]";
	}
	@Override
	public Object toQuery(String dbType, Connection con) {
		
		String query = null;
		
		query = "INSERT INTO recod (RECOD, DSCR) values (?,?)";
		PreparedStatement statement = null;
		try {
			
			
			statement = con.prepareStatement(query);
			statement.setString(1, this.RECOD);
			statement.setString(2, this.DSCR);
				
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
					
		return statement;
		
		
	}
	@Override
	public Object beforeInsert(String dbType, Connection con) {

		PreparedStatement stmt = null;
		try {
			String sql = "TRUNCATE TABLE recod ;";
			stmt = con.prepareStatement(sql);			
		  
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		return stmt;
	}
	
	

}
