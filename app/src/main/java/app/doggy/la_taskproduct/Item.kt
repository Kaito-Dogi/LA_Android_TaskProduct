package app.doggy.la_taskproduct

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo
)
