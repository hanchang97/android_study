package com.hanchang97.imageload

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.hanchang97.imageload.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val imgUrl1 =
        "https://media.istockphoto.com/photos/global-connection-picture-id1335295270?s=612x612"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setBtn1()
    }

    private fun setBtn1() {
        binding.btnLoad1.setOnClickListener {

            /* // 방법 1 - work happens on main thread
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitNetwork().build())
            val bitmap = downloadBitmap(imgUrl1)
            binding.iv1.setImageBitmap(bitmap)
            */

            /* // 방법2 - Handler, create a thread to do image download, then send bitmap to mainthread
            val uiHandler = Handler(Looper.getMainLooper())
             thread(start = true) {
                 Log.d("AppTest", "Current thread name1 : ${Thread.currentThread().name}")
                 val bitmap = downloadBitmap(imgUrl1)
                 uiHandler.post {
                     Log.d("AppTest", "Current thread name2 : ${Thread.currentThread().name}")
                     binding.iv1.setImageBitmap(bitmap)
                 }
             }
 */

            // 방법 3 - Coroutine
           /* CoroutineScope(Dispatchers.IO).launch {
                Log.d("AppTest", "Current thread name3 : ${Thread.currentThread().name}")
                val bitmap = downloadBitmap(imgUrl1)

                withContext(Dispatchers.Main) {
                    Log.d("AppTest", "Current thread name4 : ${Thread.currentThread().name}")
                    binding.iv1.setImageBitmap(bitmap)
                }
            }*/

            // 방법 4 - Coroutine
            val urlConnection = URL(imgUrl1).openConnection() as HttpURLConnection
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("AppTest", "Current thread name5 : ${Thread.currentThread().name}")
                try {
                    if (urlConnection.responseCode == 200) {
                        val stream = BufferedInputStream(urlConnection.inputStream)
                        val bitmap = BitmapFactory.decodeStream(stream)

                        withContext(Dispatchers.Main){
                            Log.d("AppTest", "Current thread name6 : ${Thread.currentThread().name}")
                            binding.iv1.isVisible = true
                            binding.clImageLoadFail.isVisible = false
                            binding.iv1.setImageBitmap(bitmap)
                        }

                    } else {
                        withContext(Dispatchers.Main){
                            Log.d("AppTest", "Current thread name7 : ${Thread.currentThread().name}")
                            binding.iv1.setImageBitmap(null)
                            binding.iv1.isVisible = false
                            binding.clImageLoadFail.isVisible = true
                        }
                    }
                } catch (e: Exception) {
                    Log.e("AppTest", "Exception : $e")
                    withContext(Dispatchers.Main){
                        Log.d("AppTest", "Current thread name7 : ${Thread.currentThread().name}")
                        binding.iv1.setImageBitmap(null)
                    }
                } finally {
                    urlConnection.disconnect()
                }
            }

        }
    }

    private fun downloadBitmap(imageUrl: String): Bitmap? {
        return try {
            val connection = URL(imageUrl).openConnection()
            connection.connect()

            val inputStream = connection.getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()

            bitmap
        } catch (e: Exception) {
            Log.e("AppTest", "Exception : $e")
            null
        }
    }


}