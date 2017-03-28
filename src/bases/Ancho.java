package bases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ancho implements Queryable {
	
	private String tabla = "anchos";
	private String ancho;

	public String getAncho() {
		return ancho;
	}

	public void setAncho(String ancho) {
		this.ancho = ancho;
	}

	@Override
	public String toString() {
		return "Ancho [ancho=" + ancho + "]";
	}

	@Override
	public Object toQuery(String dbType, Connection con) {
		
		String query = null;
		
		query = "INSERT INTO anchos (ancho) values (?)";
		PreparedStatement statement = null;
		try {
			
			
			statement = con.prepareStatement(query);
			statement.setString(1, this.ancho);
			
		
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
					
		return statement;
	}

	@Override
	public Object beforeInsert(String dbType, Connection con) {
		
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE from anchos ;";
			stmt = con.prepareStatement(sql);			
		  
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		return stmt;
		
		
	}

	@Override
	public String getTabla() {
		
		return this.tabla;
	}
	
	
	
	

}
