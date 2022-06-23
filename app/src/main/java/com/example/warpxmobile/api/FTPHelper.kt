package com.example.warpxmobile.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.warpxmobile.utils.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.commons.net.ftp.FTPClient
import java.io.InputStream

object FTPHelper {
    suspend fun getImage(path: String): Bitmap = withContext(Dispatchers.IO) {
        val ftpClient = FTPClient()
        ftpClient.connect(Settings.SERVER_ADDRESS, Settings.FTP_PORT)
        ftpClient.login("test", "test") //TODO: Save ftp username and password somewhere
        ftpClient.enterLocalPassiveMode()
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE)
        val inputStream: InputStream = ftpClient.retrieveFileStream(path)
        return@withContext BitmapFactory.decodeStream(inputStream)
    }
}