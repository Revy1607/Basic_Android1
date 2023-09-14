package com.datnt.test3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private var studentList: List<Student>): RecyclerView.Adapter<StudentAdapter.StudentVH>() {
    class StudentVH(view: View): RecyclerView.ViewHolder(view){
        private var tvName: TextView = view.findViewById(R.id.tvName)
        private var tvAge: TextView = view.findViewById(R.id.tvAge)
        var imgRemove: ImageView = view.findViewById(R.id.imgRemove)

        fun setData(student: Student){
            tvName.text = student.name
            tvAge.text = student.age
        }
    }

    var onClickItem: ((Student, Int) -> Unit)? = null
    var onClickItemRemove: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentVH(view)
    }

    override fun getItemCount(): Int = if(studentList.isNotEmpty()) studentList.size else 0

    override fun onBindViewHolder(holder: StudentVH, position: Int) {
        holder.setData(studentList[position])
        holder.itemView.setOnClickListener {
            onClickItem?.invoke(studentList[position], position)
        }
        holder.imgRemove.setOnClickListener {
            onClickItemRemove?.invoke(position)
        }
    }
}