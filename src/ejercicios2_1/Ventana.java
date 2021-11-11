package ejercicios2_1;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.border.LineBorder;
import java.awt.Color;

public class Ventana extends JFrame{

	private JTextPane tp;
	private JTextField tfDNI,tfNombre,tfApellido,tfSueldo;
	private JTextField tfDia,tfMes,tfAnyo,tfMatricula;
	private GestionBBDD gest=new GestionBBDD();
	private JTextField tfDNIDelete;
	private JTextField tfDNIM;
	private JTextField tfNombreM;
	private JTextField tfApellidoM;
	private JTextField tfSueldoM;
	private JTextField tfDiaM;
	private JTextField tfMesM;
	private JTextField tfAnyoM;
	private JTextField tfMatriculaM;
	
	public Ventana() {
		iniciarComponentes();
		gest.abrirConexion();
	}
	
	private void iniciarComponentes() {
		setTitle("Insertar Trabajadores");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(620, 720);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblAltaTrabajador = new JLabel("Alta Trabajador");
		lblAltaTrabajador.setForeground(Color.BLACK);
		lblAltaTrabajador.setBounds(39, 252, 89, 14);
		getContentPane().add(lblAltaTrabajador);
		lblAltaTrabajador.setBackground(Color.WHITE);
		lblAltaTrabajador.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		tp = new JTextPane();
		tp.setEditable(false);
		getContentPane().add(tp);
		
		JScrollPane scrollPane = new JScrollPane(tp);
		scrollPane.setBounds(25, 11, 554, 187);
		getContentPane().add(scrollPane);
		
		JButton btnVerTrabajadores = new JButton("Ver Trabajadores");
		btnVerTrabajadores.setBounds(206, 209, 136, 23);
		getContentPane().add(btnVerTrabajadores);
		
		btnVerTrabajadores.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					tp.setText(gest.verTrabajadores());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDNI.setBounds(25, 301, 46, 14);
		getContentPane().add(lblDNI);
		
		tfDNI = new JTextField();
		tfDNI.setBounds(344, 298, 136, 20);
		getContentPane().add(tfDNI);
		tfDNI.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:\r\n");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(25, 345, 57, 14);
		getContentPane().add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(123, 342, 136, 20);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellidos:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellido.setBounds(25, 385, 57, 14);
		getContentPane().add(lblApellido);
		
		tfApellido = new JTextField();
		tfApellido.setBounds(123, 382, 136, 20);
		getContentPane().add(tfApellido);
		tfApellido.setColumns(10);
		
		JLabel lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSueldo.setBounds(25, 424, 46, 14);
		getContentPane().add(lblSueldo);
		
		tfSueldo = new JTextField();
		tfSueldo.setBounds(123, 421, 136, 20);
		getContentPane().add(tfSueldo);
		tfSueldo.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setBounds(25, 463, 46, 14);
		getContentPane().add(lblFecha);
		
		tfDia = new JTextField();
		tfDia.setBounds(81, 460, 24, 20);
		getContentPane().add(tfDia);
		tfDia.setColumns(10);
		
		tfMes = new JTextField();
		tfMes.setBounds(123, 460, 24, 20);
		getContentPane().add(tfMes);
		tfMes.setColumns(10);
		
		tfAnyo = new JTextField();
		tfAnyo.setBounds(167, 460, 86, 20);
		getContentPane().add(tfAnyo);
		tfAnyo.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMatricula.setBounds(25, 500, 57, 14);
		getContentPane().add(lblMatricula);
		
		tfMatricula = new JTextField();
		tfMatricula.setBounds(123, 497, 136, 20);
		getContentPane().add(tfMatricula);
		tfMatricula.setColumns(10);
		
		JButton btnDarAlta = new JButton("Dar Alta");
		btnDarAlta.setBounds(123, 543, 89, 23);
		getContentPane().add(btnDarAlta);
		
		btnDarAlta.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				String dia=tfDia.getText();
				String mes=tfMes.getText();
				String anyo=tfAnyo.getText();
				String fecha=anyo+"-"+mes+"-"+dia;
				try {
					gest.insertarTrabajadores(tfDNI.getText(), tfNombre.getText(), tfApellido.getText(), tfSueldo.getText(), fecha, tfMatricula.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					tp.setText(gest.verTrabajadores());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				vaciarCampos();
			}
			
		});
		
		JLabel lblDNIDelete = new JLabel("DNI:");
		lblDNIDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDNIDelete.setBounds(25, 609, 46, 14);
		getContentPane().add(lblDNIDelete);
		
		tfDNIDelete = new JTextField();
		tfDNIDelete.setBounds(123, 606, 136, 20);
		getContentPane().add(tfDNIDelete);
		tfDNIDelete.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(265, 647, 89, 23);
		getContentPane().add(btnEliminar);
		
		JLabel lblModificar = new JLabel("Modificar Trabajador");
		lblModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModificar.setBounds(327, 252, 136, 14);
		getContentPane().add(lblModificar);
		
		JLabel lblDNIM = new JLabel("DNI:");
		lblDNIM.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDNIM.setBounds(308, 301, 46, 14);
		getContentPane().add(lblDNIM);
		
		tfDNIM = new JTextField();
		tfDNIM.setBounds(123, 297, 136, 20);
		getContentPane().add(tfDNIM);
		tfDNIM.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(490, 297, 89, 23);
		getContentPane().add(btnBuscar);
		
		JLabel lblNombreM = new JLabel("Nombre:");
		lblNombreM.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombreM.setBounds(308, 345, 57, 14);
		getContentPane().add(lblNombreM);
		
		tfNombreM = new JTextField();
		tfNombreM.setBounds(375, 342, 146, 20);
		getContentPane().add(tfNombreM);
		tfNombreM.setColumns(10);
		
		JLabel lblApellidoM = new JLabel("Apellidos:");
		lblApellidoM.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellidoM.setBounds(308, 385, 57, 14);
		getContentPane().add(lblApellidoM);
		
		tfApellidoM = new JTextField();
		tfApellidoM.setBounds(375, 382, 146, 20);
		getContentPane().add(tfApellidoM);
		tfApellidoM.setColumns(10);
		
		JLabel lblSueldoM = new JLabel("Sueldo:");
		lblSueldoM.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSueldoM.setBounds(308, 424, 46, 14);
		getContentPane().add(lblSueldoM);
		
		tfSueldoM = new JTextField();
		tfSueldoM.setBounds(375, 421, 146, 20);
		getContentPane().add(tfSueldoM);
		tfSueldoM.setColumns(10);
		
		JLabel lblFechaM = new JLabel("Fecha:");
		lblFechaM.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaM.setBounds(308, 463, 46, 14);
		getContentPane().add(lblFechaM);
		
		tfDiaM = new JTextField();
		tfDiaM.setBounds(375, 460, 24, 20);
		getContentPane().add(tfDiaM);
		tfDiaM.setColumns(10);
		
		tfMesM = new JTextField();
		tfMesM.setBounds(410, 460, 24, 20);
		getContentPane().add(tfMesM);
		tfMesM.setColumns(10);
		
		tfAnyoM = new JTextField();
		tfAnyoM.setBounds(444, 460, 86, 20);
		getContentPane().add(tfAnyoM);
		tfAnyoM.setColumns(10);
		
		JLabel lblMatriculaM = new JLabel("Matricula:");
		lblMatriculaM.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMatriculaM.setBounds(308, 500, 57, 14);
		getContentPane().add(lblMatriculaM);
		
		tfMatriculaM = new JTextField();
		tfMatriculaM.setBounds(375, 497, 146, 20);
		getContentPane().add(tfMatriculaM);
		tfMatriculaM.setColumns(10);
		
		JButton btnModificar = new JButton("Efectuar Cambios");
		btnModificar.setBounds(375, 543, 146, 23);
		getContentPane().add(btnModificar);
		
		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean eliminacion;
				try {
					eliminacion = gest.eliminarTrabajadores(tfDNIDelete.getText());
					if(eliminacion) {
							tp.setText(gest.verTrabajadores());
						tfDNIDelete.setText("");
					}else
						tfDNIDelete.setText("");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
			}
			
		});
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				gest.cerrarConexion();
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
}
