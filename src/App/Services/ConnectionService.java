package App.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import App.Models.Pet;

public class ConnectionService {
	private static String user = "root"; // Cambiar de ser necesario;
	private static String password = ""; // Cambiar de ser necesario;
	private static String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false", "localhost", 3306, "veterinaria");
	private static Connection connection = null;

	static { // Se invoca automaticamente llamando a cualquier metodo estático.
		try {
			if (connection == null) // Singleton de conexión
				connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}
	}

	public static final List<Pet> findAll(String table) {
		List<Pet> list = new ArrayList<Pet>();
		
		try {
			String query = String.format("SELECT * FROM veterinaria.%s", table);
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
				list.add(new Pet(rs.getInt("id"), rs.getString("Nombre"), rs.getInt("Edad"), rs.getString("Sexo")));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}
		
		return list;
	}

	public static final void insert(String table, List<String> columns, List<String> values) {
		try {
			if (columns.size() != values.size())
				throw new Exception("No coinciden las columnas con los valores");

			String query = String.format("INSERT INTO veterinaria.%s (", table);

			Integer i = 0;
			for (String column : columns)
				query += column + (++i == columns.size() ? ") VALUES (" : ", ");

			i = 0;
			for (String value : values)
				query += value + (++i == values.size() ? ");" : ", ");

			Statement st = connection.createStatement();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}
	}

	public static final void delete(String table, int petId) {
		try {
			String query = String.format("DELETE FROM veterinaria.%s WHERE id = %d", table, petId);
			Statement st = connection.createStatement();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}
	}
}
