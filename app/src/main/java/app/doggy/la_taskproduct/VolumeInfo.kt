package app.doggy.la_taskproduct

import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    @SerializedName("description") val content: String?
)
