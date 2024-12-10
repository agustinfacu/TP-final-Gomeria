package main.modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    public static void setupDatabase() {
        String databaseName = "Gomeria"; 
        String sqlFilePath = "/baseSQL.sql"; // Ruta relativa del archivo SQL

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement()) {

            // Crear la base de datos si no existe
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
            statement.executeUpdate("USE " + databaseName);

            // Leer y ejecutar el archivo SQL
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(DatabaseSetup.class.getResourceAsStream(sqlFilePath)))) {

                StringBuilder sql = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    sql.append(line).append("\n");
                }

                // Ejecutar consultas separadas por ";"
                String[] queries = sql.toString().split(";");
                for (String query : queries) {
                    if (!query.trim().isEmpty()) {
                        statement.execute(query.trim());
                    }
                }
                System.out.println("Base de datos y tablas configuradas exitosamente.");
            }
        } catch (Exception e) {
            System.err.println("Error al configurar la base de datos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        setupDatabase();
    }
}
