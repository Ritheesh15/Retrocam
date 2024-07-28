package com.example.retrocam

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 22

    private lateinit var btnPicture: Button
    private lateinit var imageView: ImageView
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPicture = findViewById(R.id.btn1)
        imageView = findViewById(R.id.imageView)

        btnPicture.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            activityResultLauncher.launch(cameraIntent)
        }

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                val extras = result.data?.extras
            val imageBitmap = extras?.get("data") as Bitmap?
            imageBitmap?.let {
                val result1 = WeakReference(
                        Bitmap.createScaledBitmap(it, it.width, it.height, false)
                                .copy(Bitmap.Config.RGB_565, true)
                )
                val bm = result1.get()
                val imageUri = bm?.let { saveImage(it, this@MainActivity) }
                imageView.setImageURI(imageUri)
            }
        }
    }

    private fun saveImage(image: Bitmap, context: MainActivity): Uri? {
            val imageFolder = File(context.cacheDir, "images")
            var uri: Uri? = null
    try {
        imageFolder.mkdirs()
        val file = File(imageFolder, "captured_image.jpg")
        val stream = FileOutputStream(file)
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        stream.flush()
        stream.close()
        uri = FileProvider.getUriForFile(context.applicationContext, "com.allcodingtutorial.camerafull1.provider", file)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return uri
    }
}
