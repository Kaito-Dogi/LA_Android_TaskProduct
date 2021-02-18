package app.doggy.la_taskproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_post.container

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.hide()

        topAppBarDetail.setOnMenuItemClickListener { menuItem ->
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