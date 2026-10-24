package com.example.laboratorio4.controller;

import com.example.laboratorio4.model.Alumno;
import com.example.laboratorio4.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
@CrossOrigin(origins = "*") // Permite el consumo desde el frontend Angular
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<Alumno>> listarTodos() {
        return new ResponseEntity<>(alumnoService.listarAlumnos(), HttpStatus.OK); // Lista de alumnos
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtenerPorId(@PathVariable Long id) {
        Optional<Alumno> alumno = alumnoService.obtenerAlumnoPorId(id);
        return alumno.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Alumno o 404
    }

    @PostMapping
    public ResponseEntity<Alumno> guardar(@RequestBody Alumno alumno) {
        Alumno nuevoAlumno = alumnoService.guardarAlumno(alumno);
        return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizar(@PathVariable Long id, @RequestBody Alumno alumnoDetalles) {
        Optional<Alumno> alumnoExistente = alumnoService.obtenerAlumnoPorId(id);
        if (alumnoExistente.isPresent()) {
            Alumno alumno = alumnoExistente.get();
            alumno.setNombres(alumnoDetalles.getNombres());
            alumno.setCarrera(alumnoDetalles.getCarrera());
            alumno.setCiclo(alumnoDetalles.getCiclo());
            alumno.setPromedio(alumnoDetalles.getPromedio());
            return new ResponseEntity<>(alumnoService.guardarAlumno(alumno), HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 No encontrado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Optional<Alumno> alumno = alumnoService.obtenerAlumnoPorId(id);
        if (alumno.isPresent()) {
            alumnoService.eliminarAlumno(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 No encontrado
        }
    }
}