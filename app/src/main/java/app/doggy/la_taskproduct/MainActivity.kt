package app.doggy.la_taskproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        // タスクリストが空だったときにダミーデータを生成する
        if (bookList.isEmpty()) {
            createDummyData()
        }

        val adapter = BookAdapter(this, bookList, true)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(baseContext, 3)
        recyclerView.adapter = adapter

        postButton.setOnClickListener {
            val postIntent = Intent(applicationContext, PostActivity::class.java)
            startActivity(postIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun createDummyData() {
        for (i in 0..20) {
            create("本$i", "作者$i")
        }
    }

    fun create(title: String, author: String) {
        realm.executeTransaction {
            val book = it.createObject(Book::class.java, UUID.randomUUID().toString())
            book.title = title
            book.author = author
        }
    }

    fun readAll(): RealmResults<Book> {
        return realm.where(Book::class.java).findAll().sort("createdAt", Sort.ASCENDING)
    }
}