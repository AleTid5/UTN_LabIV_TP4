package App.Elements;

import javax.swing.table.DefaultTableModel;

import App.Controllers.VeterinaryController;
import App.Models.Pet;

public class Table {
	private static Object[] columnNames = {"Id", "Nombre", "Edad", "Sexo"};
	public static DefaultTableModel table = null;

	public static final void populateTable() {
		table = new DefaultTableModel(new Object[0][0], columnNames);
		for (Pet pet : VeterinaryController.getList()) {
			Object[] object = new Object[4];
			object[0] = pet.getId();
			object[1] = pet.getName();
			object[2] = pet.getAge();
			object[3] = pet.getGender();
			table.addRow(object);
		}
	}
}
