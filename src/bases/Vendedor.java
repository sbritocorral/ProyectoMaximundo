package bases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Vendedor implements Queryable
{
	
	private String tabla = "vendedores";
	
	private String Codigo;
	private String Nombre;
	private String Recorrido;
	private String Password;
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	public String getCodigo() {
		return Codigo;
	}
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getRecorrido() {
		return Recorrido;
	}
	public void setRecorrido(String recorrido) {
		Recorrido = recorrido;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public Object toQuery(String dbType, Connection con) {
		
		String query = null;
		
		query = "INSERT INTO vendedores (Codigo, Nombre, Recorrido, Password) values (?,?,?,?)";
		
		PreparedStatement statement= null;
		try {
			
			statement = con.prepareStatement(query);
			
			statement.setString(1, this.Codigo);
			statement.setString(2, this.Nombre);
			statement.setString(3, this.Recorrido);
			statement.setString(4, this.Password);
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
					
		return statement;
	}
	
	@Override
	public Object beforeInsert(String dbType, Connection con) {

		PreparedStatement stmt = null;
		try {
			String sql = "TRUNCATE TABLE vendedores;";
			stmt = con.prepareStatement(sql);			
		  
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		return stmt;
	}
	@Override
	public String toString() {
		return "Vendedor [tabla=" + tabla + ", Codigo=" + Codigo + ", Nombre=" + Nombre + ", Recorrido=" + Recorrido
				+ ", Password=" + Password + "]";
	}
	
	
	
	

}
