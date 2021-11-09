package ejercicios1_3;

import java.sql.*;

/**
 * Clase que gestiona la base de datos
 * @author windows10
 *
 */
public class GestionBBDD {

	Connection cnnctn;
	
	/**
	 * Metodo para abrir la conexion
	 */
	public void abrirConexion() {
		try {
			cnnctn=DriverManager.getConnection("jdbc:mysql://localhost/manempsa","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para ver los trabajadores
	 * @return un string con la informacion del resultset
	 */
	public String verTrabajadores() {
		try {
			Statement st=cnnctn.createStatement();
			ResultSet rs=st.executeQuery("Select * From trabajadores Order By nombre");
			
			String info="";
			String fecha;
			String dia;
			String mes;
			String anyo;
			while(rs.next()) {
				fecha=rs.getString("fecha");
				dia=fecha.substring(8, 10);
				mes=fecha.substring(5, 7);
				anyo=fecha.substring(0, 4);
				fecha=dia+"/"+mes+"/"+anyo;
				info+=rs.getString("nombre")+" "+rs.getString("apellido")+" --- "+rs.getString("Sueldo")+" --- "+fecha+"\n";
			}
			return info;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo para ver trabajadores a partir de un sueldo
	 * @param signo. Segun el boton que se pulse sera =,>,<
	 * @param sueldo. Sueldo a comparar
	 * @return un string con la informacion del resultset
	 */
	public String verTrabajadoresSueldo(String signo, String sueldo) {
		
		try {
			PreparedStatement ps;
			if(signo.equals("="))
				ps=cnnctn.prepareStatement("Select * From trabajadores Where sueldo = ?");
			else if(signo.equals(">"))
				ps=cnnctn.prepareStatement("Select * From trabajadores Where sueldo > ?");
			else
				ps=cnnctn.prepareStatement("Select * From trabajadores Where sueldo < ?");
			
			ps.setString(1,sueldo);
			ResultSet rs=ps.executeQuery();
			
			String info="";
			String fecha;
			String dia;
			String mes;
			String anyo;
			while(rs.next()) {
				fecha=rs.getString("fecha");
				dia=fecha.substring(8, 10);
				mes=fecha.substring(5, 7);
				anyo=fecha.substring(0, 4);
				fecha=dia+"/"+mes+"/"+anyo;
				info+=rs.getString("nombre")+" "+rs.getString("apellido")+" --- "+rs.getString("sueldo")+" --- "+fecha+"\n";
			}
			return info;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo para ver a los trabajadores segun el nombre
	 * @param nombre. Nombre a comparar
	 * @return un string con la informacion del resultset
	 */
	public String verTrabajadoresNombre(String nombre) {
		try {
			Statement st=cnnctn.createStatement();
			ResultSet rs=st.executeQuery("Select * From trabajadores Where nombre = '"+nombre+"'");
			
			String info="";
			String fecha;
			String dia;
			String mes;
			String anyo;
			while(rs.next()) {
				fecha=rs.getString("fecha");
				dia=fecha.substring(8, 10);
				mes=fecha.substring(5, 7);
				anyo=fecha.substring(0, 4);
				fecha=dia+"/"+mes+"/"+anyo;
				info+=rs.getString("nombre")+" "+rs.getString("apellido")+" --- "+rs.getString("sueldo")+" --- "+fecha+"\n";
			}
			return info;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo para ver trabajadores que contienen el nombre
	 * @param nombre. Letras que contiene el trabajador
	 * @return un string con la informacion del resultset
	 */
	public String verTrabajadoresContieneNombre(String nombre) {
		try {
			Statement st=cnnctn.createStatement();
			ResultSet rs=st.executeQuery("Select * From trabajadores Where nombre like '%"+nombre+"%'");
			
			String info="";
			String fecha;
			String dia;
			String mes;
			String anyo;
			while(rs.next()) {
				fecha=rs.getString("fecha");
				dia=fecha.substring(8, 10);
				mes=fecha.substring(5, 7);
				anyo=fecha.substring(0, 4);
				fecha=dia+"/"+mes+"/"+anyo;
				info+=rs.getString("nombre")+" "+rs.getString("apellido")+" --- "+rs.getString("sueldo")+" --- "+fecha+"\n";
			}
			return info;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo para mostrar los trabajadores segun la fecha
	 * @param signo. Segun el boton que se pulse sera < o >
	 * @param fechaT. Fecha a comparar
	 * @return un string con la informacion del resultset
	 */
	public String verTrabajadoresFechas(String signo,String fechaT) {
		
		try {
			PreparedStatement ps;
			 if(signo.equals(">"))
				ps=cnnctn.prepareStatement("Select * From trabajadores Where fecha >= ?");
			else
				ps=cnnctn.prepareStatement("Select * From trabajadores Where fecha <= ?");
			
			ps.setString(1,fechaT);
			ResultSet rs=ps.executeQuery();
			
			String info="";
			String fecha;
			String dia;
			String mes;
			String anyo;
			while(rs.next()) {
				fecha=rs.getString("fecha");
				dia=fecha.substring(8, 10);
				mes=fecha.substring(5, 7);
				anyo=fecha.substring(0, 4);
				fecha=dia+"/"+mes+"/"+anyo;
				info+=rs.getString("nombre")+" "+rs.getString("apellido")+" --- "+rs.getString("sueldo")+" --- "+fecha+"\n";
			}
			return info;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void cerrarConexion() {
		try {
			cnnctn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
