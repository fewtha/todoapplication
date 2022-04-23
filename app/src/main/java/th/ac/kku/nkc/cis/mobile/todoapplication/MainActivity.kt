package th.ac.kku.nkc.cis.mobile.todoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var db: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = FirebaseDatabase.getInstance().reference

        var btn_new:FloatingActionButton = findViewById(R.id.btn_new)
        btn_new.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
            var itemEditText = EditText(this)

            alertDialog.setTitle("Create new todo")
            alertDialog.setMessage("Create new todo")
            alertDialog.setView(itemEditText)

            alertDialog.setPositiveButton("SAVE"){ dialog, positiveButton ->
                var newTodo = Todo.create()
                newTodo.todoText = itemEditText.text.toString()

                var newDbItem = db.child("todo_item").push()
                newTodo.objectId = newDbItem.key

                newDbItem.setValue(newTodo)

                dialog.dismiss()

            }
            alertDialog.show()


        }
    }
}