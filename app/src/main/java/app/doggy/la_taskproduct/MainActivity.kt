package app.doggy.la_taskproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bookList = readAll()

        val adapter =
            BookAdapter(this, bookList, object: BookAdapter.OnItemClickListener {
                override fun onItemClick(item: Book) {
                    // クリック時の処理
                    toDetail(item.id)
                }
            }, true)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(baseContext, 2)
        recyclerView.adapter = adapter

        postButton.setOnClickListener {
            val postIntent = Intent(applicationContext, PostActivity::class.java)
            startActivity(postIntent)
        }

        barcodeButton.setOnClickListener {
            val barcodeIntent = Intent(applicationContext, BarcodeActivity::class.java)
            startActivity(barcodeIntent)
        }

        searchButton.setOnClickListener {

            val searchWord: String = searchEditText.text.toString()

            val searchedBookList = search(searchEditText.text.toString())

            val searchedAdapter =
                    BookAdapter(this, searchedBookList, object: BookAdapter.OnItemClickListener {
                        override fun onItemClick(item: Book) {
                            // クリック時の処理
                            toDetail(item.id)
                        }
                    }, true)

            recyclerView.adapter = searchedAdapter

            if (searchedBookList.isEmpty()) {
                explainTextView.text = "「${searchWord}」を含む本はありません"
                explainTextView.isVisible = true

            } else {
                explainTextView.text = "読んだ本を登録しよう！"
                explainTextView.isVisible = false
            }

        }
    }

    override fun onResume() {
        super.onResume()

        explainTextView.isVisible = readAll().isEmpty()

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun search(title: String): RealmResults<Book> {
        return realm.where(Book::class.java).contains("title", title).findAll()
    }

    fun readAll(): RealmResults<Book> {
        return realm.where(Book::class.java).findAll().sort("createdAt", Sort.ASCENDING)
    }

    fun toDetail(id: String) {
        val detailIntent = Intent(applicationContext, DetailActivity::class.java)
        detailIntent.putExtra("id", id)
        startActivity(detailIntent)
    }

}