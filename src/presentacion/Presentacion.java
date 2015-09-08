package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.DataConnection;

import javax.swing.JTextArea;

import negocio.ControladorPartida;

public class Presentacion extends JFrame {

	private JPanel contentPane;
	private JTextField txtDni;
	private JTextField txtDni2;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Presentacion frame = new Presentacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Presentacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 0, 424, 251);
		contentPane.add(panel_2);
		panel_2.setVisible(false);
		

		JPanel panel_inicial = new JPanel();
		panel_inicial.setBounds(5, 5, 424, 251);
		contentPane.add(panel_inicial);
		panel_inicial.setLayout(null);
		
	
		
		JLabel label = new JLabel("Fichas Blancas");
		label.setFont(new Font("Verdana", Font.BOLD, 16));
		label.setBounds(38, 22, 135, 44);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("Fichas Negras");
		label_1.setFont(new Font("Verdana", Font.BOLD, 16));
		label_1.setBounds(257, 22, 167, 44);
		panel_2.add(label_1);
		
		JList list_1 = new JList();
		list_1.setBounds(38, 61, 135, 120);
		panel_2.add(list_1);
		
		JList list_2 = new JList();
		list_2.setBounds(257, 61, 135, 120);
		panel_2.add(list_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(139, 192, 53, 20);
		panel_2.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(202, 192, 53, 20);
		panel_2.add(textField_1);
		
		JLabel label_2 = new JLabel("Posicion Final: ");
		label_2.setBounds(38, 192, 104, 20);
		panel_2.add(label_2);
		
		JButton button = new JButton("Mover");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_inicial.setVisible(true);
				panel_2.setVisible(false);
			}
		});
		button.setBounds(292, 192, 89, 23);
		panel_2.add(button);
		
		
		txtDni = new JTextField();
		txtDni.setBounds(124, 32, 98, 20);
		panel_inicial.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblIngreseSuDni = new JLabel("Ingrese DNI: ");
		lblIngreseSuDni.setBounds(36, 35, 79, 14);
		panel_inicial.add(lblIngreseSuDni);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(36, 60, 340, 87);
		panel_inicial.add(textArea);
		
		
		txtDni2 = new JTextField();
		txtDni2.setBounds(36, 152, 262, 20);
		panel_inicial.add(txtDni2);
		txtDni2.setColumns(10);
		
		JLabel lblDniOponente = new JLabel(": DNI Oponente");
		lblDniOponente.setBounds(308, 153, 87, 14);
		panel_inicial.add(lblDniOponente);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addMouseListener(new MouseAdapter() {
			@Override
		
			public void mouseClicked(MouseEvent arg0) {
				int j1 =Integer.parseInt( txtDni.getText());
				int j2 =Integer.parseInt( txtDni2.getText());
				ControladorPartida cp = new ControladorPartida();
				try {
					if (!cp.buscarPartida(j1,j2))
					cp.crearPartida(j1, j2);
					else System.out.println("La Partida YA ESISTE; MAS TARDE PROGRAMO EL METODO");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				panel_inicial.setVisible(false);
				panel_2.setVisible(true);
			}
		});
		btnJugar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnJugar.setBounds(36, 178, 359, 34);
		panel_inicial.add(btnJugar);
		
		JButton btnBuscarOponentes = new JButton("Buscar Oponentes");
		btnBuscarOponentes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				textArea.setText("");
				String dni= txtDni.getText();
				String query="select  p.blanco, p.negro, jn.nombre, jn.apellido, jb.nombre, jb.apellido from partida p inner join jugadores jb on p.blanco=jb.dni inner join jugadores jn on p.negro=jn.dni where blanco="+dni+" or negro="+dni;
				DataConnection con = new DataConnection();
				ResultSet resultado= con.getQuery(query);
				try{
					while(resultado.next())
						{
						if(Integer.parseInt(resultado.getString("p.blanco")) != Integer.parseInt(dni)){
							textArea.append(resultado.getString("p.blanco")+" "+resultado.getString("jb.nombre")+" "+resultado.getString("jb.apellido")+"\n");
						}
						else{
							textArea.append(resultado.getString("p.negro")+" "+resultado.getString("jn.nombre")+" "+resultado.getString("jn.apellido")+"\n");
						}
						};
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				
			}
		});
		btnBuscarOponentes.setBounds(243, 31, 133, 23);
		panel_inicial.add(btnBuscarOponentes);
	}
}
