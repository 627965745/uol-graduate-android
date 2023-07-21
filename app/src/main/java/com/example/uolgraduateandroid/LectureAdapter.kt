package com.example.uolgraduateandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LectureAdapter(private val onClick: (Lecture) -> Unit) :
    RecyclerView.Adapter<LectureAdapter.LectureViewHolder>() {

    private val lectures: MutableList<Lecture> = mutableListOf()

    fun setItems(lectures: List<Lecture>) {
        this.lectures.clear()
        this.lectures.addAll(lectures)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lecture_item, parent, false)
        return LectureViewHolder(view)
    }

    override fun onBindViewHolder(holder: LectureViewHolder, position: Int) {
        holder.bind(lectures[position])
    }

    override fun getItemCount(): Int = lectures.size

    inner class LectureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val lectureIcon: ImageView = view.findViewById(R.id.lectureIcon)
        private val lectureName: TextView = view.findViewById(R.id.lectureName)
        private val lectureDuration: TextView = view.findViewById(R.id.lectureDuration)

        fun bind(lecture: Lecture) {
            Glide.with(lectureIcon.context).load(lecture.lectureIconURL).into(lectureIcon)
            lectureName.text = lecture.lectureName
            lectureDuration.text = "%dh:%02dm".format(lecture.lectureDurationMinutes / 60, lecture.lectureDurationMinutes % 60)
            itemView.setOnClickListener { onClick(lecture) }
        }
    }
}