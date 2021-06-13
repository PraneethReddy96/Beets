

package com.tejeet.beets.data.model.upload

import android.net.Uri
import java.io.FileDescriptor


data class LocalRecordLocation(val contentUri: Uri, val filePath: String, val fileDescriptor: FileDescriptor)