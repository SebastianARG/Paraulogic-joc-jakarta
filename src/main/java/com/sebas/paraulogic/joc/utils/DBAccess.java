package com.sebas.paraulogic.joc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


import java.io.InputStream;
import java.io.FileNotFoundException;

public class DBAccess {

    private static final String CONFIG_FILE = "properties/config.properties";

    public static Connection getConnection() throws SQLException {
        try (InputStream input = DBAccess.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new FileNotFoundException("No se encontró el archivo de configuración: " + CONFIG_FILE);
                
            }

            Properties props = new Properties();
            props.load(input);

            String dbName = props.getProperty("db.name");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            String url = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

            // Registrar el driver explícitamente
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            System.err.println("Error cargando configuración de la base de datos: " + e.getMessage());
            throw new SQLException("No se pudo establecer la conexión a la base de datos.");
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}