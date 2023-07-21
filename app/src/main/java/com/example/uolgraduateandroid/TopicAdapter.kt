package com.example.uolgraduateandroid

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TopicAdapter(private val sharedPreferences: SharedPreferences) : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {

    private val topics: MutableList<Topic> = mutableListOf()

    fun setItems(topics: List<Topic>) {
        this.topics.clear()
        this.topics.addAll(topics)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.topic_item, parent, false)
        return TopicViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.bind(topics[position])
    }

    override fun getItemCount(): Int = topics.size

    inner class TopicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val topicName: TextView = view.findViewById(R.id.topicName)
        private val topicCheckbox: CheckBox = view.findViewById(R.id.topicCheckbox)

        fun bind(topic: Topic) {
            topicName.text = topic.topicName
            topicCheckbox.isChecked = sharedPreferences.getBoolean(topic.topicID.toString(), false)
            topicCheckbox.setOnCheckedChangeListener { _, isChecked ->
                sharedPreferences.edit().putBoolean(topic.topicID.toString(), isChecked).apply()
            }
        }
    }
}
