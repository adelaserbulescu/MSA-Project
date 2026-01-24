package app.msaproject.mainapp.dtos_mocks.htmlcontent

import app.msaproject.mainapp.dtos.htmlcontent.HtmlContentFullDTO
import app.msaproject.mainapp.entities.HtmlContentType

object MockedHtmlContentData {

    val htmlContents = listOf(
        HtmlContentFullDTO(
            htmlContentID = 1,
            countryID = 1,
            contentHtml = "<p>USA Info v1</p>",
            contentType = HtmlContentType.GENERAL,
            version = 1,
            pageIndex = 0,
            pageSource = "home",
            dateAdded = "2023-01-10"
        ),
        HtmlContentFullDTO(
            htmlContentID = 2,
            countryID = 1,
            contentHtml = "<p>USA Info v2</p>",
            contentType = HtmlContentType.GENERAL,
            version = 2,
            pageIndex = 0,
            pageSource = "home",
            dateAdded = "2023-05-01"
        ), // Newer version of ID 1

        HtmlContentFullDTO(
            htmlContentID = 3,
            countryID = 2,
            contentHtml = "<p>France History</p>",
            contentType = HtmlContentType.HISTORY,
            version = 1,
            pageIndex = 1,
            pageSource = "history",
            dateAdded = "2023-03-15"
        ),
        HtmlContentFullDTO(
            htmlContentID = 4,
            countryID = 3,
            contentHtml = "<p>Italy Culture</p>",
            contentType = HtmlContentType.CULTURE,
            version = 1,
            pageIndex = 1,
            pageSource = "culture",
            dateAdded = "2023-04-01"
        ),
        HtmlContentFullDTO(
            htmlContentID = 5,
            countryID = 1,
            contentHtml = "<p>USA Extended</p>",
            contentType = HtmlContentType.GENERAL,
            version = 1,
            pageIndex = 2,
            pageSource = "extended",
            dateAdded = "2023-06-01"
        ),
        HtmlContentFullDTO(
            htmlContentID = 6,
            countryID = 2,
            contentHtml = "<p>France Culture</p>",
            contentType = HtmlContentType.CULTURE,
            version = 1,
            pageIndex = 2,
            pageSource = "culture",
            dateAdded = "2023-02-20"
        ),
        HtmlContentFullDTO(
            htmlContentID = 7,
            countryID = 2,
            contentHtml = "<p>France Culture v2</p>",
            contentType = HtmlContentType.CULTURE,
            version = 2,
            pageIndex = 2,
            pageSource = "culture",
            dateAdded = "2023-06-20"
        ) // newer version of ID 6
    )
}
