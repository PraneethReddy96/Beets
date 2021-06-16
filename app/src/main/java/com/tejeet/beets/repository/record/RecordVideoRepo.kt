
package com.tejeet.beets.repository.record

import android.content.Context
import com.tejeet.beets.data.modelDTO.upload.LocalRecordLocation


interface RecordVideoRepo {

    suspend fun initVideo(context: Context, timeCreated: Long): LocalRecordLocation?

    suspend fun stopVideo(context: Context)
}