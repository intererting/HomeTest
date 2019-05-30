package com.yuliyang.hometest

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.provider.OpenableColumns
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_saf.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import android.R.attr.data
import java.io.FileOutputStream


class SAFActivity : AppCompatActivity() {

    companion object {
        const val READ_REQUEST_CODE = 100
        const val WRITE_REQUEST_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saf)
        fileSearch.setOnClickListener {
            //            performFileSearch()
            createFile("image/*", "safCreateFile.jpg")
        }
    }

    fun performFileSearch() {
        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.type = "image/*"

        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int,
                                         resultData: Intent?) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            var uri: Uri? = null
            if (resultData != null) {
                uri = resultData.data
//                dumpImageMetaData(uri)
//                println("uri   $uri")
                display.setImageBitmap(getBitmapFromUri(uri))
            }
        } else if (requestCode == WRITE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
//                contentResolver.openOutputStream(resultData.data)

//                contentResolver.openFileDescriptor(resultData.data, "w")?.use {
//                    FileOutputStream(it.fileDescriptor)
//                }

//                val pipes = ParcelFileDescriptor.createReliablePipe()
//                val readPipe = pipes[0]
//                val writePipe = pipes[1]
//
//                val stream = ParcelFileDescriptor.AutoCloseOutputStream(writePipe)
//                stream.write(data)
                println("新创建文件  ${resultData.data}")
            }
        }
    }

    fun dumpImageMetaData(uri: Uri) {

        // The query, since it only applies to a single document, will only return
        // one row. There's no need to filter, sort, or select fields, since we want
        // all fields for one document.
        val cursor = contentResolver.query(uri, null, null, null, null, null)

        try {
            // moveToFirst() returns false if the cursor has 0 rows.  Very handy for
            // "if there's anything to look at, look at it" conditionals.
            if (cursor != null && cursor.moveToFirst()) {

                // Note it's called "Display Name".  This is
                // provider-specific, and might not necessarily be the file name.
                val displayName = cursor.getString(
                        cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))

                println("displayName   $displayName")

                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                // If the size is unknown, the value stored is null.  But since an
                // int can't be null in Java, the behavior is implementation-specific,
                // which is just a fancy term for "unpredictable".  So as
                // a rule, check if it's null before assigning to an int.  This will
                // happen often:  The storage API allows for remote files, whose
                // size might not be locally known.
                var size: String? = null
                if (!cursor.isNull(sizeIndex)) {
                    // Technically the column stores an int, but cursor.getString()
                    // will do the conversion automatically.
                    size = cursor.getString(sizeIndex)
                } else {
                    size = "Unknown"
                }
                println("size   $size")
            }
        } finally {
            cursor!!.close()
        }
    }

    @Throws(IOException::class)
    private fun getBitmapFromUri(uri: Uri): Bitmap {
        val parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor = parcelFileDescriptor!!.fileDescriptor
        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor.close()
        return image
    }

    @Throws(IOException::class)
    private fun readTextFromUri(uri: Uri): String {
        val inputStream = contentResolver.openInputStream(uri)
        val reader = BufferedReader(InputStreamReader(
                inputStream))
        val stringBuilder = StringBuilder()
        var line: String
        while (reader.readLine().also {
                    line = it
                } != null) {
            stringBuilder.append(line)
        }
        reader.close()
        return stringBuilder.toString()
    }

    private fun createFile(mimeType: String, fileName: String) {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)

        // Filter to only show results that can be "opened", such as
        // a file (as opposed to a list of contacts or timezones).
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        // Create a file with the requested MIME type.
        intent.type = mimeType
        intent.putExtra(Intent.EXTRA_TITLE, fileName)
        startActivityForResult(intent, WRITE_REQUEST_CODE)
    }
}