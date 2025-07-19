package org.example.service;

import org.example.config.Database;
import org.example.models.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlumnoService {

    public List<Alumno> getAll() throws Exception {
        List<Alumno> alumnos = new ArrayList<>();
        try (Connection conn = Database.getDataSource().getConnection()) {
            String sql = "SELECT * FROM alumno";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Alumno a = new Alumno();
                    a.setId(rs.getInt("id_alumno"));
                    a.setNombre(rs.getString("nombre"));
                    a.setAp_paterno(rs.getString("ap_paterno"));
                    a.setAp_materno(rs.getString("ap_materno"));
                    a.setEdad(rs.getInt("edad"));
                    alumnos.add(a);
                }
            }
        }
        return alumnos;
    }

    public void create(Alumno alumno) throws Exception {
        try (Connection conn = Database.getDataSource().getConnection()) {
            String sql = "INSERT INTO alumnos(nombre, ap_paterno, ap_materno, edad) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, alumno.getNombre());
                ps.setString(2, alumno.getAp_paterno());
                ps.setString(3, alumno.getAp_materno());
                ps.setInt(4, alumno.getEdad());
                ps.executeUpdate();
            }
        }
    }

}
