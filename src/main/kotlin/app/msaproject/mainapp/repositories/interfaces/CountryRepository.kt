package app.msaproject.mainapp.repositories.interfaces

import app.msaproject.mainapp.dtos.CountryDTO

interface CountryRepository {
    suspend fun getAll(): List<CountryDTO>
    suspend fun getById(id: String): CountryDTO?
}