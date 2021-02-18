package app.doggy.la_taskproduct

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import io.realm.Realm

class DeleteDialogFragment(val bookId: String): DialogFragment() {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("本当に消しますか？")
                .setPositiveButton("はい",
                    DialogInterface.OnClickListener { dialog, id ->
                        // FIRE ZE MISSILES!
                        delete(bookId)
                        requireActivity().finish()
                    })
                .setNegativeButton("いいえ",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun delete(id: String) {
        realm.executeTransaction {
            val book = realm.where(Book::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            book.deleteFromRealm()
        }
    }
}