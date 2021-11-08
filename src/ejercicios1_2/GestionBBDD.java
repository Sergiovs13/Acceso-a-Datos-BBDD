package ejercicios1_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class GestionBBDD {

	Connection cnnctn;
	
	public void crearConexion() {
		try {
			cnnctn=DriverManager.getConnection("jdbc:mysql://localhost/manempsa","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void verServicios() {
		try {
			Statement st=cnnctn.createStatement();
			ResultSet rs=st.executeQuery("Select * From servicios Order By cantidad");
			
			String info="";
			while(rs.next()) {
				info+=rs.getString("fecha")+" "+rs.getString("tipo")+" "+rs.getString("cantidad")+"\n";
			}
			JOptionPane.showMessageDialog(null, info);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void cerrarConexion() {
		try {
			cnnctn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
