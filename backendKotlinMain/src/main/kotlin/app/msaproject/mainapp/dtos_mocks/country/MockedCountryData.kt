package app.msaproject.mainapp.dtos_mocks.country

import app.msaproject.mainapp.dtos.country.CountryFullDTO

object MockedCountryData {

    val countries = listOf(
        CountryFullDTO(
            countryID = 1,
            groupID = 1,
            countryName = "United States",
            stillExists = true,
            dateStarted = "1776-07-04",
            hexColor = "#3C3B6E"
        ),
        CountryFullDTO(
            countryID = 2,
            groupID = 1,
            countryName = "Germany",
            stillExists = true,
            dateStarted = "1871-01-18",
            hexColor = "#000000"
        ),
        CountryFullDTO(
            countryID = 3,
            groupID = 1,
            countryName = "Roman Empire",
            stillExists = false,
            dateStarted = "0027-01-01",
            dateEnded = "0476-09-04",
            hexColor = "#CC0000"
        ),
        CountryFullDTO(
            countryID = 4,
            groupID = 2,
            countryName = "Romania",
            stillExists = true,
            dateStarted = "1859-01-24",
            hexColor = "#002B7F"
        ),
        CountryFullDTO(
            countryID = 5,
            groupID = 2,
            countryName = "Yugoslavia",
            stillExists = false,
            dateStarted = "1918-12-01",
            dateEnded = "2003-02-04",
            hexColor = "#0047AB"
        )
    )
}