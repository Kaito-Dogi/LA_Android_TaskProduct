package app.doggy.la_taskproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        supportActionBar?.hide()

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.back -> {
                    //Snackbar.make(container, "戻る", Snackbar.LENGTH_LONG).show()
                    finish()
                    true
                }

                R.id.edit -> {
                    Snackbar.make(container, "編集する", Snackbar.LENGTH_LONG).show()
                    true
                }

                R.id.delete -> {
                    Snackbar.make(container, "削除する", Snackbar.LENGTH_LONG).show()
                    true
                }

                else -> false

            }
        }

    }
}