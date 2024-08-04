package maven.com.jp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import maven.com.jp.connection.MariaDbManager;
import maven.com.jp.entity.Estudiantes;

public class TecnicosDao {
    public static void leerTecnicos() {
    	
    	Connection conexion = MariaDbManager.getConnection();
    	
    	List<Estudiantes> listaEstudiantes = new ArrayList<>();
    	
        if (conexion != null) {
            try {
            	String consulta = "SELECT * FROM estudiantes";
            	
            	PreparedStatement statement = conexion.prepareStatement(consulta);
            	
            	ResultSet result = statement.executeQuery();
            	
            	while(result.next()) {
            		Estudiantes estudiante = new Estudiantes();
            		estudiante.setId(result.getInt("id"));
            		estudiante.setNombre(result.getString("nombre"));
            		estudiante.setApellido(result.getString("apellido"));
            		estudiante.setFecha_nacimiento(result.getString("fecha_nacimiento"));
            		estudiante.setGenero(result.getString("genero"));
            		estudiante.setDireccion(result.getString("direccion"));
            		estudiante.setTelefono(result.getString("telefono"));
            		estudiante.setCorreo_electronico(result.getString("correo_electronico"));
            		estudiante.setGrado(result.getString("grado"));
            		estudiante.setFecha_inscripcion(result.getString("fecha_inscripcion"));
            		listaEstudiantes.add(estudiante);
            	}
            	
            	Gson gson = new Gson();
            	String json = gson.toJson(listaEstudiantes);
            	
            	System.out.println(json);
            	result.close();
            	statement.close();
            	conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al leer clientes: " + e.getMessage());
            }
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
}
