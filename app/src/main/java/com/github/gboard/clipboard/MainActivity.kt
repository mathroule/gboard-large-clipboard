package com.github.gboard.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            copyTextToClipboard()
        }
    }

    private fun copyTextToClipboard() {
        Thread().run {
            assets.open("large-text.txt").bufferedReader().use {
                val text = it.readText()
                val clipData = ClipData.newPlainText("large-text", text)
                val clipboardManager: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                clipboardManager.setPrimaryClip(clipData)

                Toast.makeText(this@MainActivity, R.string.large_text_copied_to_clipboard, Toast.LENGTH_LONG).show()
            }
        }
    }
}
