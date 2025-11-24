package app.msaproject.mainapp.dtos_mocks.countrygroup

import app.msaproject.mainapp.dtos.countrygroup.CountryGroupFullDTO
import app.msaproject.mainapp.entities.GroupType

object MockedCountryGroupData {

    val groupList = listOf(
        CountryGroupFullDTO(
            groupID = 1,
            groupName = "European Union",
            groupDescription = "Political and economic union in Europe",
            groupType = GroupType.ROADMAP,
            dateStarted = "1993-11-01",
            stillExists = true
        ),
        CountryGroupFullDTO(
            groupID = 2,
            groupName = "NATO",
            groupDescription = "Military alliance",
            groupType = GroupType.ALLIANCE,
            dateStarted = "1949-04-04",
            stillExists = true
        ),
        CountryGroupFullDTO(
            groupID = 3,
            groupName = "Holy Roman Empire",
            groupDescription = "Historical empire in Europe",
            groupType = GroupType.AREA,
            dateStarted = "0800-01-01",
            dateEnded = "1806-08-06",
            stillExists = false
        )
    )
}
