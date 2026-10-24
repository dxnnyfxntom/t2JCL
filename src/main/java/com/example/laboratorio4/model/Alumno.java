package com.example.laboratorio4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombres;

    @Column(nullable = false, length = 120)
    private String carrera;

    @Column(nullable = false)
    private int ciclo;

    @Column(nullable = false)
    private double promedio;

    public Alumno() {
    }

    public Alumno(Long id, String nombres, String carrera, int ciclo, double promedio) {
        this.id = id;
        this.nombres = nombres;
        this.carrera = carrera;
        this.ciclo = ciclo;
        this.promedio = promedio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @Transient
    public String getEstadoAcademico() {
        return promedio >= 13 ? "REGULAR" : "EN RIESGO";
    }
}