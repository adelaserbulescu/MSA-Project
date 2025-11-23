package app.msaproject.mainapp.repositories.interfaces

import app.msaproject.mainapp.dtos.country.CountryFullDTO

interface CountryRepository {
    suspend fun getAll(): List<CountryFullDTO>
    suspend fun getById(id: Int): CountryFullDTO?
}