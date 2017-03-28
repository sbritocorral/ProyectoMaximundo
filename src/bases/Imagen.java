package bases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Imagen implements Queryable{

	private String tabla = "imagenes";
	
	private String PROD_MODELO;
	private String IMAGEN_GRANDE;
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	public String getPROD_MODELO() {
		return PROD_MODELO;
	}
	public void setPROD_MODELO(String pROD_MODELO) {
		PROD_MODELO = pROD_MODELO;
	}
	public String getIMAGEN_GRANDE() {
		return IMAGEN_GRANDE;
	}
	public void setIMAGEN_GRANDE(String iMAGEN_GRANDE) {
		IMAGEN_GRANDE = iMAGEN_GRANDE;
	}
	@Override
	public String toString() {
		return "Imagen [tabla=" + tabla + ", PROD_MODELO=" + PROD_MODELO + ", IMAGEN_GRANDE=" + IMAGEN_GRANDE + "]";
	}
	@Override
	public Object toQuery(String dbType, Connection con) {
		
String query = null;
		
		query = "INSERT INTO producto_imagen (PROD_MODELO, IMAGEN_GRANDE) values (?,?)";
		
		PreparedStatement statement= null;
		try {
			
			statement = con.prepareStatement(query);
			
			statement.setString(1, this.PROD_MODELO);
			statement.setString(2, this.IMAGEN_GRANDE);
	
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
					
		return statement;
	}
	@Override
	public Object beforeInsert(String dbType, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
