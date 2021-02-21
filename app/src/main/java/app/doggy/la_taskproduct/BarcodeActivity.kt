package app.doggy.la_taskproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_barcode.*

class BarcodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)

        supportActionBar?.hide()

        topAppBarBarcode.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.back -> {
                    //Snackbar.make(container, "戻る", Snackbar.LENGTH_SHORT).show()
                    finish()
                    true
                }

                else -> false

            }
        }

        requestButton.setOnClickListener {
            if (isbnEditText.text.toString() == "") {
                Toast.makeText(applicationContext, "ISBNを入力して下さい", Toast.LENGTH_SHORT).show()

            } else {
                val postIntent = Intent(applicationContext, PostActivity::class.java)
                postIntent.putExtra("isbn", isbnEditText.text.toString())
                startActivity(postIntent)
                finish()
            }

        }

    }
}