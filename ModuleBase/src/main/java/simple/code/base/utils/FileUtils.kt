package simple.code.base.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File


class FileUtils{

    /**
     * Used to get file detail from uri.
     *
     *
     * 1. Used to get file detail (name & size) from uri.
     * 2. Getting file details from uri is different for different uri scheme,
     * 2.a. For "File Uri Scheme" - We will get file from uri & then get its details.
     * 2.b. For "Content Uri Scheme" - We will get the file details by querying content resolver.
     *
     * @param uri Uri.
     * @return file detail.
     */
    fun getFileDetailFromUri(context: Context, uri: Uri): FileDetail? {
        var fileDetail: FileDetail? = null
            fileDetail = FileDetail()
            // File Scheme.
            if (ContentResolver.SCHEME_FILE == uri.scheme) {
                val file = File(uri.path)
                fileDetail.fileName = file.name
                fileDetail.fileSize = file.length()
            } else if (ContentResolver.SCHEME_CONTENT == uri.scheme) {
                val returnCursor = context.contentResolver.query(uri, null, null, null, null)
                if (returnCursor != null && returnCursor.moveToFirst()) {
                    val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    val sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE)
                    fileDetail.fileName = returnCursor.getString(nameIndex)
                    fileDetail.fileSize = returnCursor.getLong(sizeIndex)
                    returnCursor.close()
                }
            }// Content Scheme.
        return fileDetail
    }


    /**
     * File Detail.
     *
     *
     * 1. Model used to hold file details.
     */
    /**
     * Constructor.
     */
    class FileDetail {

        // fileSize.
        var fileName: String? = null

        // fileSize in bytes.
        var fileSize: Long = 0
    }
}