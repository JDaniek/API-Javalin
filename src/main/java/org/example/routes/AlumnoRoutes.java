package org.example.routes;

import io.javalin.Javalin;
import org.example.controllers.AlumnoController;

public class AlumnoRoutes {

    private final AlumnoController alumnoController;

    public AlumnoRoutes(AlumnoController alumnoController) {
        this.alumnoController = alumnoController;
    }

    public void register(Javalin app) {
        app.get("/alumnos", alumnoController::getAll);
        app.post("/alumnos", alumnoController::create);
        // Aquí puedes agregar más rutas (PUT, DELETE) en el futuro
    }
}
