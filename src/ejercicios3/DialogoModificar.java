package ejercicios3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DialogoModificar extends JDialog{
	private JTextField tfDNI, tfNombre, tfApellido, tfSueldo;
	private JTextField tfDia, tfMes, tfAnyo, tfMatricula;
	private GestionBBDD gest;
	private DefaultTableModel dtm;
	private String[] datosTrabajador;
	
	public DialogoModificar(GestionBBDD gest,DefaultTableModel dtm, String dni) {
		iniciarComponentes();
		this.gest=gest;
		this.dtm=dtm;
		tfDNI.setText(dni);
		datosTrabajador=gest.datosTrabajador(dni, dtm);
		rellenarCampos();
	}
	
	private void iniciarComponentes() {
		setTitle("Modificar Trabajadores");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(260,400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDNI.setBounds(10, 33, 46, 14);
		getContentPane().add(lblDNI);
		
		tfDNI = new JTextField();
		tfDNI.setEditable(false);
		tfDNI.setBounds(77, 30, 117, 20);
		getContentPane().add(tfDNI);
		tfDNI.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 77, 55, 14);
		getContentPane().add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(77, 74, 117, 20);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellidos:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellido.setBounds(10, 121, 55, 14);
		getContentPane().add(lblApellido);
		
		tfApellido = new JTextField();
		tfApellido.setBounds(77, 118, 117, 20);
		getContentPane().add(tfApellido);
		tfApellido.setColumns(10);
		
		JLabel lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSueldo.setBounds(10, 161, 46, 14);
		getContentPane().add(lblSueldo);
		
		tfSueldo = new JTextField();
		tfSueldo.setBounds(77, 158, 117, 20);
		getContentPane().add(tfSueldo);
		tfSueldo.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setBounds(10, 214, 46, 14);
		getContentPane().add(lblFecha);
		
		tfDia = new JTextField();
		tfDia.setBounds(67, 211, 28, 20);
		getContentPane().add(tfDia);
		tfDia.setColumns(10);
		
		tfMes = new JTextField();
		tfMes.setBounds(108, 211, 28, 20);
		getContentPane().add(tfMes);
		tfMes.setColumns(10);
		
		tfAnyo = new JTextField();
		tfAnyo.setBounds(148, 211, 46, 20);
		getContentPane().add(tfAnyo);
		tfAnyo.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMatricula.setBounds(10, 256, 63, 14);
		getContentPane().add(lblMatricula);
		
		tfMatricula = new JTextField();
		tfMatricula.setBounds(77, 253, 117, 20);
		getContentPane().add(tfMatricula);
		tfMatricula.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(10, 302, 89, 23);
		getContentPane().add(btnAceptar);
		
		btnAceptar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String fecha="";
				fecha+=tfAnyo.getText();
				fecha+="-"+tfMes.getText();
				fecha+="-"+tfDia.getText();
				
				gest.modificarTrabajador(tfDNI.getText(),tfNombre.getText(),tfApellido.getText(),tfSueldo.getText(),fecha,tfMatricula.getText());
				JOptionPane.showMessageDialog(null, "Trabajador modificado");
				gest.limpiarTabla(dtm);
				try {
					gest.mostrarTrabajadores(dtm);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				vaciarCampos();
			}
			
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(126, 302, 89, 23);
		getContentPane().add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Modificacion cancelada");
				vaciarCampos();
			}
			
		});
		setVisible(true);
	}
	
	private void vaciarCampos() {
		tfDNI.setText("");
		tfNombre.setText("");
		tfApellido.setText("");
		tfSueldo.setText("");
		tfDia.setText("");
		tfMes.setText("");
		tfAnyo.setText("");
		tfMatricula.setText("");
	}
	
	private void rellenarCampos() {
		tfNombre.setText(datosTrabajador[0]);
		tfApellido.setText(datosTrabajador[1]);
		tfSueldo.setText(datosTrabajador[2]);
		tfDia.setText(datosTrabajador[3].substring(8, 10));
		tfMes.setText(datosTrabajador[3].substring(5,7));
		tfAnyo.setText(datosTrabajador[3].substring(0,4));
		tfMatricula.setText(datosTrabajador[4]);
	}
}
