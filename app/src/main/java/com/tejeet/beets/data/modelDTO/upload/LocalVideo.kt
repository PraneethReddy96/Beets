
package com.tejeet.beets.data.modelDTO.upload

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalVideo(
    var filePath: String?,
    val duration: Long?,
    val dateCreated: String?
): Parcelable{

}