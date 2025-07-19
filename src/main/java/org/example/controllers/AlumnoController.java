package org.example.controllers;

import io.javalin.http.Context;
import org.example.models.Alumno;
import org.example.service.AlumnoService;

import java.util.List;

public class AlumnoController {
    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    // Método para obtener todos los alumnos (GET /alumnos)
    public void getAll(Context ctx) {
        try {
            List<Alumno> alumnos = alumnoService.getAll();
            ctx.json(alumnos);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al obtener alumnos");
        }
    }

    // Método para crear un nuevo alumno (POST /alumnos)
    public void create(Context ctx) {
        try {
            Alumno alumno = ctx.bodyAsClass(Alumno.class);
            alumnoService.create(alumno);
            ctx.status(201).result("Alumno creado");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al crear alumno");
        }
    }
}
