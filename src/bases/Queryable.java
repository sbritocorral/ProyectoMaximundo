package bases;

import java.sql.Connection;

public interface Queryable {

	//Interfaz para cada base de datos.
	
	public Object toQuery(String dbType, Connection con);
	
	public Object beforeInsert(String dbType, Connection con);
	
	public String getTabla();
	
}
