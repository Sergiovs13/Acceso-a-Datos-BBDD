package ejercicios2_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class GestionBBDD {

	Connection cnnctn;
	
	public void abrirConexion() {
		try {
			cnnctn=DriverManager.getConnection("jdbc:mysql://localhost/manempsa","root","");
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
	
	public String verTrabajadores() throws SQLException {
		Statement st=cnnctn.createStatement();
		ResultSet rs=st.executeQuery("Select * From trabajadores");
		
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
			
			info+=rs.getString("dni")+" -- "+rs.getString("nombre")+" "+rs.getString("apellido")+" -- "+rs.getString("sueldo")+" -- "+fecha+" -- "+rs.getString("matricula")+"\n";
		}
		return info;
	}
	
	public void insertarTrabajadores(String dni, String nombre, String apellido, String sueldo, String fecha,String matricula) throws SQLException {
		Statement st=cnnctn.createStatement();
		st.execute("Insert Into trabajadores (dni, nombre, apellido, sueldo, fecha, matricula) Values ('"+dni+"','"+nombre+"','"+apellido+"','"+sueldo+"','"+fecha+"','"+matricula+"')");
		st.close();
	}
	
	public boolean eliminarTrabajadores(String dni) throws SQLException {
		Statement st=cnnctn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs=st.executeQuery("Select * From trabajadores Where dni='"+dni+"'");
		
		if(!rs.first()) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado ningun trabajador con ese DNI");
			return false;
		}else {
			String info="";
			String fecha;
			String dia;
			String mes;
			String anyo;
			rs.first();
			fecha=rs.getString("fecha");
			dia=fecha.substring(8, 10);
			mes=fecha.substring(5, 7);
			anyo=fecha.substring(0, 4);
			fecha=dia+"/"+mes+"/"+anyo;		
			info+=rs.getString("dni")+" -- "+rs.getString("nombre")+" "+rs.getString("apellido")+" -- "+rs.getString("sueldo")+" -- "+fecha+" -- "+rs.getString("matricula");
			
			int opcion=JOptionPane.showConfirmDialog(null, "Se ha encontrado el siguiente trabajador: \n"+info, "Borrar trabajador", JOptionPane.YES_NO_OPTION);
			
			if(opcion==JOptionPane.YES_OPTION) {
				st.execute("Delete From trabajadores Where dni ='"+dni+"'");
			}else
				JOptionPane.showMessageDialog(null, "Eliminacion cancelada");
			return true;
		}
		
		
	}
}
