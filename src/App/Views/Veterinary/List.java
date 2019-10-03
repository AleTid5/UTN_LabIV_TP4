package App.Views.Veterinary;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import App.Controllers.VeterinaryController;
import App.Elements.Alert;
import App.Elements.Table;
import App.Elements.Window;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class List extends Window {
	private JTextField txtName;
	private JTextField txtAge;
	private JRadioButton rdbtnHembra = new JRadioButton("Hembra");
	private JRadioButton rdbtnMacho = new JRadioButton("Macho");
	private final Button button_1 = new Button("Agregar");
	private JTable table;
	private JScrollPane scrollPane;

	public List() {
		setHeader("Lista de mascotas");

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(24, 12, 54, 15);
		getContentPane().add(lblNombre);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(24, 39, 54, 15);
		getContentPane().add(lblEdad);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(24, 66, 54, 15);
		getContentPane().add(lblSexo);

		txtName = new JTextField();
		txtName.addKeyListener(this.onKeyPressed("name"));
		txtName.setBounds(96, 10, 179, 19);
		getContentPane().add(txtName);
		txtName.setColumns(10);

		txtAge = new JTextField();
		txtAge.setBounds(96, 37, 179, 19);
		txtAge.addKeyListener(this.onKeyPressed("age"));
		getContentPane().add(txtAge);
		txtAge.setColumns(10);
		rdbtnHembra.setSelected(true);

		rdbtnHembra.setBackground(new Color(255, 240, 88));
		rdbtnHembra.setBounds(92, 62, 79, 23);
		getContentPane().add(rdbtnHembra);

		rdbtnMacho.setBackground(new Color(255, 240, 88));
		rdbtnMacho.setBounds(206, 62, 69, 23);
		getContentPane().add(rdbtnMacho);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMacho);
		group.add(rdbtnHembra);

		Button button = new Button("Eliminar");
		button.addActionListener(this.onRemove());
		button.setBounds(189, 337, 86, 23);
		getContentPane().add(button);
		button_1.addActionListener(this.onAdd());
		button_1.setBounds(189, 91, 86, 23);

		getContentPane().add(button_1);

		Table.populateTable();
		table = new JTable(Table.table);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(24, 319, 251, 185);
		table.setBackground(new Color(255, 240, 190));
		scrollPane = new JScrollPane(table);
		scrollPane.setLocation(24, 120);
		scrollPane.setSize(251, 211);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);

		setVisible(true);
	}

	public ActionListener onAdd() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtAge.getText().length() == 0)
					txtAge.setText("0");

				VeterinaryController.add(txtName.getText(), Integer.parseInt(txtAge.getText()),
						rdbtnMacho.isSelected());
				
				Table.populateTable();
				table.setModel(Table.table);
				scrollPane.setViewportView(table);
			}
		};
	}

	public ActionListener onRemove() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1)
					return;

				new Alert(scrollPane, table);
			}
		};
	}

	public KeyAdapter onKeyPressed(String type) {
		return new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (type) {
				case "name":
					txtName.setEditable(true);

					if (!(e.getKeyCode() == 8 || e.getKeyCode() == 127) // Permito borrado
							&& ((e.getKeyCode() >= 48 && e.getKeyCode() <= 57) || txtName.getText().length() >= 45))
						txtName.setEditable(false);

					break;

				case "age":
					txtAge.setEditable(true);

					if (e.getKeyCode() < 48 || e.getKeyCode() > 57)
						txtAge.setEditable(false);

					break;

				default:
					break;
				}
			}
		};
	}
}
