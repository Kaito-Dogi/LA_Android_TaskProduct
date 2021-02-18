package app.doggy.la_taskproduct

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Book(
    @PrimaryKey open var id: String = UUID.randomUUID().toString(),
    open var bookImageId: Int = R.drawable.book,
    open var title: String = "",
    open var author: String = "",
    open var price: Int = 0,
    open var content: String = "",
    open var createdAt: Date = Date(System.currentTimeMillis())
) : RealmObject()