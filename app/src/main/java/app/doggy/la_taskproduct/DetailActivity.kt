package app.doggy.la_taskproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_post.container

class DetailActivity : AppCompatActivity() {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()

        val id: String? = intent.getStringExtra("id")
        val book = realm.where(Book::class.java).equalTo("id", id).findFirst()
        titleTextView.text = book?.title
        authorTextView.text = book?.author
        priceTextView.text = book?.price.toString()
        contentTextView.text = book?.content.toString()

        topAppBarDetail.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.back -> {
                    finish()
                    true
                }

                R.id.edit -> {
                    val postIntent = Intent(applicationContext, PostActivity::class.java)
                    postIntent.putExtra("id", id)
                    startActivity(postIntent)
                    true
                }

                R.id.delete -> {
                    delete(id as String)
                    finish()
                    true
                }

                else -> false

            }
        }

    }

    fun delete(id: String) {
        realm.executeTransaction {
            val book = realm.where(Book::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            book.deleteFromRealm()
        }
    }

}