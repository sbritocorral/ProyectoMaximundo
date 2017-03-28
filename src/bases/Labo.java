package bases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Labo implements Queryable {

	private String tabla = "labo";
	
	private String LABO;
	private String DSCR;
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	public String getLABO() {
		return LABO;
	}
	public void setLABO(String lABO) {
		LABO = lABO;
	}
	public String getDSCR() {
		return DSCR;
	}
	public void setDSCR(String dSCR) {
		DSCR = dSCR;
	}
	@Override
	public String toString() {
		return "Labo [tabla=" + tabla + ", LABO=" + LABO + ", DSCR=" + DSCR + "]";
	}
	@Override
	public Object toQuery(String dbType, Connection con) {
		
		String query = null;
		
		query = "INSERT INTO labo (LABO, DSCR) values (?,?)";
		
		PreparedStatement statement= null;
		try {
			
			statement = con.prepareStatement(query);
			
			statement.setString(1, this.LABO);
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
			String sql = "TRUNCATE TABLE labo ;";
			stmt = con.prepareStatement(sql);			
		  
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		return stmt;
	}
	
	
}
