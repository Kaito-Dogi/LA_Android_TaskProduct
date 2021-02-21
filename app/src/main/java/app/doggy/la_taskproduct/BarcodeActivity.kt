package app.doggy.la_taskproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_barcode.*

class BarcodeActivity : AppCompatActivity() {

    private var qrScanIntegrator: IntentIntegrator? = null

    lateinit var isbnEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)

        supportActionBar?.hide()

        isbnEditText = findViewById(R.id.isbnEditText)

        qrScanIntegrator = IntentIntegrator(this)

        // 画面の回転をさせない (今回は縦画面に固定)
        qrScanIntegrator?.setOrientationLocked(false)

        // QR 読み取り後にビープ音がなるのを止める
        qrScanIntegrator?.setBeepEnabled(false)

        // スキャン開始 (QR アクティビティ生成)
        qrScanIntegrator?.initiateScan()

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

    // 読み取り後に呼ばれるメソッド
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // 結果の取得
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null) {
            // result.contents で取得した値を参照できる
            isbnEditText.setText(result.contents)
        }

        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}