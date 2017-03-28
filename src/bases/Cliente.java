package bases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Cliente implements Queryable {

	private String tabla = "clientes";
	
	private String codigo;
	private String nombre;
	private String direccion;
	private String telefono1;
	private String ciudad;
	private double monto;
	private double saldo;
	private String tipo_precio;
	private String tipo_credito;
	private String email;
	private int dias_credito;
	private String ruc;
	private String vendedor1;
	private String vendedor2;
	private String representante;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getTipo_precio() {
		return tipo_precio;
	}
	public void setTipo_precio(String tipo_precio) {
		this.tipo_precio = tipo_precio;
	}
	public String getTipo_credito() {
		return tipo_credito;
	}
	public void setTipo_credito(String tipo_credito) {
		this.tipo_credito = tipo_credito;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDias_credito() {
		return dias_credito;
	}
	public void setDias_credito(int dias_credito) {
		this.dias_credito = dias_credito;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getVendedor1() {
		return vendedor1;
	}
	public void setVendedor1(String vendedor1) {
		this.vendedor1 = vendedor1;
	}
	public String getVendedor2() {
		return vendedor2;
	}
	public void setVendedor2(String vendedor2) {
		this.vendedor2 = vendedor2;
	}
	public String getRepresentante() {
		return representante;
	}
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	

	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono1="
				+ telefono1 + ", ciudad=" + ciudad + ", monto=" + monto + ", saldo=" + saldo + ", tipo_precio="
				+ tipo_precio + ", tipo_credito=" + tipo_credito + ", email=" + email + ", dias_credito=" + dias_credito
				+ ", ruc=" + ruc + ", vendedor1=" + vendedor1 + ", vendedor2=" + vendedor2 + ", representante="
				+ representante + "]";
	}
	
	
	
	@Override
	public Object toQuery(String dbType, Connection con) {	
		
		String query = null;
		
		query = "INSERT INTO clientes (codigo, nombre, direccion, telefono1, ciudad, monto, saldo, tipo_precio, tipo_credito, email, dias_credito, ruc, vendedor1, vendedor2, representante) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement statement= null;
		try {
			
			statement = con.prepareStatement(query);
			
			statement.setString(1, this.codigo);
			statement.setString(2, this.nombre);
			statement.setString(3, this.direccion);
			statement.setString(4, this.telefono1);
			statement.setString(5, this.ciudad);
			statement.setDouble(6, this.monto);
			statement.setDouble(7, this.saldo);
			statement.setString(8, this.tipo_precio);
			statement.setString(9, this.tipo_credito);
			statement.setString(10, this.email);
			statement.setInt(11, this.dias_credito);
			statement.setString(12, this.ruc);
			statement.setString(13, this.vendedor1);
			statement.setString(14, this.vendedor2);
			statement.setString(15, this.representante);
			
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
					
		return statement;
	}
	@Override
	public Object beforeInsert(String dbType, Connection con) {
		
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE from clientes ;";
			stmt = con.prepareStatement(sql);			
		  
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		return stmt;
	}
	
}
