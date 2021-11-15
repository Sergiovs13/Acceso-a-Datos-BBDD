package ejercicios3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class Ventana extends JFrame{
	private JTable table;
	private DefaultTableModel dtm= new DefaultTableModel();
	private GestionBBDD gest=new GestionBBDD();
	
	public Ventana() {
		iniciarComponentes();
		gest.abrirConexion();
	}
	
	private void iniciarComponentes() {
		setTitle("Ver trabajadores");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		dtm.setColumnIdentifiers(new String [] {"DNI","Nombre","Apellidos","Sueldo","Fecha","Matricula"});
		table = new JTable(dtm);
		getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 464, 192);
		getContentPane().add(scrollPane);
		
		JButton btnVerTrabajadores = new JButton("Ver Trabajadores");
		btnVerTrabajadores.setBounds(141, 214, 140, 23);
		getContentPane().add(btnVerTrabajadores);
		
		
		btnVerTrabajadores.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					gest.limpiarTabla(dtm);
					gest.mostrarTrabajadores(dtm);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		JLabel lblAcciones = new JLabel("Panel Acciones");
		lblAcciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAcciones.setBounds(24, 339, 98, 14);
		getContentPane().add(lblAcciones);
		
		JButton btnEliminar = new JButton("Eliminar Trabajador");
		btnEliminar.setBounds(24, 374, 152, 23);
		getContentPane().add(btnEliminar);
		
		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()>0) {
					String trabajador="";
					for(int i=0;i<dtm.getColumnCount();i++) {
			
						trabajador+=(String) table.getValueAt(table.getSelectedRow(), i)+" ";
					}
					int opcion=JOptionPane.showConfirmDialog(null, "Se ha encontrado el siguiente trabajador: \n"+trabajador, "Eliminacion Trabajador", JOptionPane.YES_NO_OPTION);
					
					if(opcion==JOptionPane.YES_OPTION) {
						gest.eliminarTrabajador((String)table.getValueAt(table.getSelectedRow(), 0));
						dtm.removeRow(table.getSelectedRow());
						try {
							gest.limpiarTabla(dtm);
							gest.mostrarTrabajadores(dtm);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}else
						JOptionPane.showMessageDialog(null, "Eliminacion cancelada");
				}else
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun trabajador");
			}
			
		});
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(186, 374, 89, 23);
		getContentPane().add(btnNuevo);
		
		btnNuevo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new DialogoNuevo(gest,dtm);
			}
			
		});
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(285, 374, 89, 23);
		getContentPane().add(btnModificar);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(384, 374, 89, 23);
		getContentPane().add(btnFiltrar);
		
		btnModificar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()==-1)
					JOptionPane.showMessageDialog(null, "Debes seleccionar un trabajador");
				else
					new DialogoModificar(gest,dtm,(String)dtm.getValueAt(table.getSelectedRow(), 0));
			}
			
		});
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				gest.cerrarConexion();
			}
		});
	}
}
