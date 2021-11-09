package ejercicios1_3;

import java.sql.*;

public class GestionBBDD {

	Connection cnnctn;
	
	public void abrirConexion() {
		try {
			cnnctn=DriverManager.getConnection("jdbc:mysql://localhost/manempsa","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
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
	
	
	public void cerrarConexion() {
		try {
			cnnctn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
