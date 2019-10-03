package App.Elements;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;

import App.Controllers.VeterinaryController;

@SuppressWarnings("serial")
public class Alert extends JFrame {
	public Alert(JScrollPane scrollPane, JTable table) {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Color mainColor = new Color(243, 86, 94);
		setForeground(new Color(51, 51, 51));
		getContentPane().setFont(new Font("Khmer OS System", Font.PLAIN, 11));
		getContentPane().setBackground(mainColor);
		setTitle("Eliminar registro");
		setBackground(mainColor);
		setSize(310, 110);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		getContentPane().setLayout(null);
		
		JTextPane txtpnrealmenteDeseaEliminar = new JTextPane();
		txtpnrealmenteDeseaEliminar.setForeground(new Color(51, 51, 51));
		txtpnrealmenteDeseaEliminar.setBackground(mainColor);
		txtpnrealmenteDeseaEliminar.setEditable(false);
		txtpnrealmenteDeseaEliminar.setText("¿Realmente desea eliminar éste registro?");
		txtpnrealmenteDeseaEliminar.setBounds(26, 12, 254, 25);
		getContentPane().add(txtpnrealmenteDeseaEliminar);
		
		JButton btnSi = new JButton("Si");
		btnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VeterinaryController.remove(table.getSelectedRow());
				Table.populateTable();
				table.setModel(Table.table);
				scrollPane.setViewportView(table);
				setVisible(false);
			}
		});
		btnSi.setBackground(new Color(250, 80, 90));
		btnSi.setBounds(233, 41, 47, 25);
		getContentPane().add(btnSi);
		
		JLabel lblCancelar = new JLabel("Cancelar");
		lblCancelar.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		lblCancelar.setBounds(26, 49, 66, 15);
		getContentPane().add(lblCancelar);
		
		setVisible(true);
	}
}
