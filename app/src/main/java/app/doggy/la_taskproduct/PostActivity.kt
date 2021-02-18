package app.doggy.la_taskproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_post.container
import java.util.*

class PostActivity : AppCompatActivity() {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        supportActionBar?.hide()

        val id: String? = intent.getStringExtra("id")

        if (id != null) {
            val book = realm.where(Book::class.java).equalTo("id", id).findFirst()
            titleEditTextView.setText(book?.title)
            authorEditTextView.setText(book?.author)
            priceEditTextView.setText(book?.price.toString())
            contentEditTextView.setText(book?.content)

            addButton.text = "更新"
        }

        addButton.setOnClickListener {
            if (titleEditTextView.text.toString() == "" ||
                authorEditTextView.text.toString() == "" ||
                priceEditTextView.text.toString() == "" ||
                contentEditTextView.text.toString() == "") {
                Toast.makeText(applicationContext, "データを入力して下さい", Toast.LENGTH_SHORT).show()

            } else if (id == null) {
                create(
                    titleEditTextView.text.toString(),
                    authorEditTextView.text.toString(),
                    priceEditTextView.text.toString().toInt(),
                    contentEditTextView.text.toString()
                )
                finish()

            } else if (id != null) {
                update(
                    id,
                    titleEditTextView.text.toString(),
                    authorEditTextView.text.toString(),
                    priceEditTextView.text.toString().toInt(),
                    contentEditTextView.text.toString()
                )
                finish()

            }

        }

        topAppBarPost.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.back -> {
                    //Snackbar.make(container, "戻る", Snackbar.LENGTH_SHORT).show()
                    finish()
                    true
                }

                else -> false

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun create(title: String, author: String, price: Int, content: String) {
        realm.executeTransaction {
            val book = it.createObject(Book::class.java, UUID.randomUUID().toString())
            book.title = title
            book.author = author
            book.price = price
            book.content = content
        }
    }

    fun update(id: String, title: String, author: String, price: Int, content: String) {
        realm.executeTransaction {
            val book = realm.where(Book::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            book.title = title
            book.author = author
            book.price = price
            book.content = content
        }
    }

}