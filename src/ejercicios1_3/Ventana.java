package ejercicios1_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.Font;

/**
 * Clase que gestiona la ventana
 * @author windows10
 *
 */
public class Ventana extends JFrame{

	private GestionBBDD gest=new GestionBBDD();
	private JTextPane tp;
	private JTextField tfSueldo;
	private JTextField tfNombre;
	private JTextField tfDia;
	private JTextField tfMes;
	private JTextField tfAnyo;
	/**
	 * Constructor de la clase
	 */
	public Ventana() {
		iniciarComponentes();
		gest.abrirConexion();
	}
	/**
	 * Metodo que inicia y da utilidad a los componentes
	 */
	private void iniciarComponentes() {
		setTitle("Ver informacion");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		tp = new JTextPane();
		getContentPane().add(tp);
		
		JScrollPane scrollPane = new JScrollPane(tp);
		scrollPane.setBounds(41, 21, 403, 255);
		getContentPane().add(scrollPane);
		
		JButton btnVerTrabajadores = new JButton("Ver Trabajadores");
		btnVerTrabajadores.setBounds(151, 287, 146, 23);
		getContentPane().add(btnVerTrabajadores);
		
		btnVerTrabajadores.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tp.setText(gest.verTrabajadores());
			}
			
		});
		
		JLabel lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSueldo.setBounds(31, 348, 46, 14);
		getContentPane().add(lblSueldo);
		
		tfSueldo = new JTextField();
		tfSueldo.setBounds(87, 345, 86, 20);
		getContentPane().add(tfSueldo);
		tfSueldo.setColumns(10);
		
		JButton btnIgualSueldo = new JButton("Igual");
		btnIgualSueldo.setBounds(187, 344, 89, 23);
		getContentPane().add(btnIgualSueldo);
		
		btnIgualSueldo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tp.setText(gest.verTrabajadoresSueldo("=", tfSueldo.getText()));
				if(tp.getText().equals(""))
					tp.setText("No hay trabajadores que tengan ese sueldo");
				tfSueldo.setText("");
				
			}
			
		});
		
		JButton btnMenorSueldo = new JButton("Menor");
		btnMenorSueldo.setBounds(286, 344, 89, 23);
		getContentPane().add(btnMenorSueldo);
		
		btnMenorSueldo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tp.setText(gest.verTrabajadoresSueldo("<", tfSueldo.getText()));
				if(tp.getText().equals(""))
					tp.setText("No hay trabajadores con sueldo menor a "+tfSueldo.getText()+"€");
				tfSueldo.setText("");
			}
			
		});
		JButton btnMayorSueldo = new JButton("Mayor");
		btnMayorSueldo.setBounds(385, 344, 89, 23);
		getContentPane().add(btnMayorSueldo);
		
		btnMayorSueldo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tp.setText(gest.verTrabajadoresSueldo(">", tfSueldo.getText()));
				if(tp.getText().equals(""))
					tp.setText("No hay trabajadores con sueldo mayor a "+tfSueldo.getText()+"€");
				tfSueldo.setText("");
			}
			
		});
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(31, 392, 58, 14);
		getContentPane().add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(87, 389, 86, 20);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JButton btnIgualNombre = new JButton("Igual a");
		btnIgualNombre.setBounds(187, 388, 89, 23);
		getContentPane().add(btnIgualNombre);
		
		btnIgualNombre.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tp.setText(gest.verTrabajadoresNombre(tfNombre.getText()));
				if(tp.getText().equals(""))
					tp.setText("No hay trabajadores con ese nombre");
				tfNombre.setText("");
			}
			
		});
		
		JButton btnContieneNombre = new JButton("Contiene a");
		btnContieneNombre.setBounds(286, 388, 107, 23);
		getContentPane().add(btnContieneNombre);
		
		btnContieneNombre.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tp.setText(gest.verTrabajadoresContieneNombre(tfNombre.getText()));
				if(tp.getText().equals(""))
					tp.setText("No hay trabajadores que contengan "+tfNombre.getText());
				tfNombre.setText("");
			}
			
		});
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setBounds(31, 424, 46, 14);
		getContentPane().add(lblFecha);
		
		tfDia = new JTextField();
		tfDia.setBounds(87, 421, 24, 20);
		getContentPane().add(tfDia);
		tfDia.setColumns(10);
		
		JLabel lblGuion = new JLabel("-");
		lblGuion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGuion.setBounds(114, 424, 10, 14);
		getContentPane().add(lblGuion);
		
		tfMes = new JTextField();
		tfMes.setBounds(127, 421, 24, 20);
		getContentPane().add(tfMes);
		tfMes.setColumns(10);
		
		JLabel lblGuion2 = new JLabel("-");
		lblGuion2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGuion2.setBounds(154, 424, 10, 14);
		getContentPane().add(lblGuion2);
		
		tfAnyo = new JTextField();
		tfAnyo.setBounds(169, 421, 58, 20);
		getContentPane().add(tfAnyo);
		tfAnyo.setColumns(10);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(237, 422, 89, 23);
		getContentPane().add(btnAnterior);
		
		btnAnterior.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tp.setText(gest.verTrabajadoresFechas("<", tfAnyo.getText()+"-"+tfMes.getText()+"-"+tfDia.getText()));
				tfDia.setText("");
				tfMes.setText("");
				tfAnyo.setText("");
				if(tp.getText().equals(""))
					tp.setText("No hay trabajadores anteriores a esa fecha");
			}
			
		});
		
		JButton btnDespues = new JButton("Despues");
		btnDespues.setBounds(336, 420, 89, 23);
		getContentPane().add(btnDespues);
		
		btnDespues.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tp.setText(gest.verTrabajadoresFechas(">", tfAnyo.getText()+"-"+tfMes.getText()+"-"+tfDia.getText()));
				tfDia.setText("");
				tfMes.setText("");
				tfAnyo.setText("");
				if(tp.getText().equals(""))
					tp.setText("No hay trabajadores posteriores a esa fecha");
				
			}
			
		});
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				gest.cerrarConexion();
			}
		});
		setVisible(true);
	}
}
