package com.datnt.test3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView

class StudentEdit : AppCompatActivity() {
    private var editName: EditText? = null
    private var editAge: EditText? = null
    private var btnEdit: ImageView? = null
    private var rollBackEdit: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_edit)

        editName = findViewById(R.id.editName)
        editAge = findViewById(R.id.editAge)
        btnEdit = findViewById(R.id.btnEdit)
        rollBackEdit = findViewById(R.id.rollBackEdit)

        val student = intent.extras?.get("student") as? Student
        if(student != null){
            editName?.setText(student.name)
            editAge?.setText(student.age)
        }

        btnEdit?.setOnClickListener {
            //set lại các giá trị name, age cho biến student để truyền về main home
            // ?:"" nghĩa là khi editName?.text?.toString() bị null thì ta để name là rỗng
            student?.name = editName?.text?.toString() ?:""
            student?.age = editAge?.text?.toString() ?:""
            val intent = Intent()
            intent.putExtra("student", student)
            intent.putExtra(MainActivity.KEY, MainActivity.TYPE_EDIT)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        rollBackEdit?.setOnClickListener {
            val i1 = Intent(this, MainActivity::class.java)
            startActivity(i1)
        }
    }
}