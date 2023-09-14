package com.datnt.test3

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    companion object {
        const val KEY = "KEY"
        const val TYPE_EDIT = "TYPE_EDIT"
        const val TYPE_ADD = "TYPE_ADD"
    }
    private var btnAdd: ImageView? = null

    private var rcvStudent: RecyclerView? = null
    private var studentAdapter: StudentAdapter? = null
    private val array: ArrayList<Student> = ArrayList()

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val type = result.data?.extras?.getString(KEY)
            if(type == TYPE_ADD){
                val student = result.data?.extras?.get("student") as? Student
                if(student != null){
                    array.add(0, student)
                }
                studentAdapter?.notifyDataSetChanged()
            }else if (type == TYPE_EDIT){
                val student = result.data?.extras?.get("student") as? Student
                //Cập nhật lại dữ liệu cho array
                if(student != null){
                    for(item in array){
                        if(item.id == student.id){
                            item.name = student.name
                            item.age = student.age
                            break
                        }
                    }
                    studentAdapter?.notifyDataSetChanged()
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btnAdd)
        rcvStudent = findViewById(R.id.rcvData)


        array.add(Student(1, "đạt1", "22"))
        array.add(Student(2, "đạt2", "23"))

        studentAdapter = StudentAdapter(array)
        rcvStudent?.adapter = studentAdapter
        studentAdapter?.onClickItem = { student, position ->
            val intent = Intent(this, StudentEdit::class.java)
            intent.putExtra("student", student)
            startForResult.launch(intent)
        }

        studentAdapter?.onClickItemRemove = {
            array.removeAt(it)
            studentAdapter?.notifyDataSetChanged()
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show()
        }

//        btnAdd?.setOnClickListener {
//            val i1 = Intent(this, StudentAdd::class.java)
//            startForResult.launch(i1)
//        }

        btnAdd?.setOnClickListener {
            showAddStudentDialog()
        }
    }

    private fun deleteStudent(){
        val swipeToDelete = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                array.removeAt(position)
                rcvStudent?.adapter?.notifyItemRemoved(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(rcvStudent)
    }

    private fun showAddStudentDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_add_user)

        val nameEdt: EditText = dialog.findViewById(R.id.nameEdt)
        val ageEdit: EditText = dialog.findViewById(R.id.ageEdt)
        val addButtonDialog: Button = dialog.findViewById(R.id.addButtonDialog)

        addButtonDialog.setOnClickListener {
            val nameDialog = nameEdt.text.toString()
            val ageDialog = ageEdit.text.toString()
            if(nameDialog.isNotEmpty() && ageDialog.isNotEmpty()){
                val student = Student(id = System.currentTimeMillis().toInt(), name = nameDialog, age = ageDialog)
                array.add(student)
                studentAdapter?.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_LONG).show()
            }
        }
        dialog.show()
    }
}


