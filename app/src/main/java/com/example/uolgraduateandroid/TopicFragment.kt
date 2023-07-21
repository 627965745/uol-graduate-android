package com.example.uolgraduateandroid

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TopicFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TopicAdapter

    companion object {
        fun newInstance(lecture: Lecture): TopicFragment {
            val fragment = TopicFragment()
            val args = Bundle()
            args.putSerializable("lecture", lecture)
            args.putString("lectureName", lecture.lectureName)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_topic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton: ImageButton = view.findViewById(R.id.topicBack)
        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        recyclerView = view.findViewById(R.id.topicRecyclerView)
        val sharedPreferences = requireActivity().getSharedPreferences("TopicPreferences", Context.MODE_PRIVATE)
        adapter = TopicAdapter(sharedPreferences)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        val lecture = arguments?.getSerializable("lecture") as Lecture
        val topics = getTopicsForLecture(lecture)
        adapter.setItems(topics)
        val lectureName = arguments?.getString("lectureName")
        val lectureNameTextView: TextView = view.findViewById(R.id.lectureNameTextView)
        lectureNameTextView.text = lectureName
    }

    private fun getTopicsForLecture(lecture: Lecture): List<Topic>{
        val jsonString = context?.assets?.open("lectures.json")?.bufferedReader().use { it?.readText() }
        val gson = Gson()

        val responseType = object : TypeToken<Map<String, List<Topic>>>() {}.type
        val response: Map<String, List<Topic>> = gson.fromJson(jsonString, responseType)

        val topics = response["topics"] ?: listOf()

        return lecture.topics.mapNotNull { topicId ->
            topics.find { it.topicID == topicId }
        }
    }
}