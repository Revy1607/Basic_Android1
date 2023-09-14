package com.datnt.test3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView

class StudentAdd : AppCompatActivity() {
    private var edtName: EditText? = null
    private var edtAge: EditText? = null
    private var btnSave: ImageView? = null
    private var rollBackAdd: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_add)

        edtName = findViewById(R.id.edtName)
        edtAge = findViewById(R.id.edtAge)
        btnSave = findViewById(R.id.btnSave)
        rollBackAdd = findViewById(R.id.rollBackAdd)
//
//        val name: String = edtName?.text.toString()
//        val age: String = edtAge?.text.toString()

        btnSave?.setOnClickListener {
            val student = Student(
                id = System.currentTimeMillis().toInt(),
                name = edtName?.text.toString(),
                age = edtAge?.text.toString()
            )
            val intent = Intent()
            intent.putExtra("student", student)
            intent.putExtra(MainActivity.KEY, MainActivity.TYPE_ADD)
            setResult(RESULT_OK, intent)
            finish()
        }

        rollBackAdd?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}