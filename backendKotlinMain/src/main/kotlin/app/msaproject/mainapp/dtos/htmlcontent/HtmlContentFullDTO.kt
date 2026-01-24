package app.msaproject.mainapp.dtos.htmlcontent

import app.msaproject.mainapp.entities.HtmlContentType
import kotlinx.serialization.Serializable

@Serializable
data class HtmlContentFullDTO (
    val htmlContentID: Int,
    val countryID: Int,
    val contentHtml: String,
    val contentType: HtmlContentType? = null,
    val version: Int? = null,
    val pageIndex: Int? = null,
    val pageSource: String? = null,
    val dateAdded: String,
)
