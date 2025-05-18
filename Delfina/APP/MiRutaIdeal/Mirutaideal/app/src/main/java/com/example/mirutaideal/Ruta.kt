package com.example.mirutaideal


data class Ruta(
    val id: Int,
    val nombre: String,
    val descripcion: String?, // nullable
    val distancia_km: Double,
    val altitud_maxima: Int?, // nullable
    val altitud_minima: Int?, // nullable
    val nivel_dificultad: String,
    val tipo: String
)
