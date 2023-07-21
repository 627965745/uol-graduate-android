package com.example.uolgraduateandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class LectureFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LectureAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lecture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = LectureAdapter { lecture ->
            val fragment = TopicFragment.newInstance(lecture)
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        loadLectures()
    }

    private fun loadLectures() {
        val jsonString = context?.assets?.open("lectures.json")?.bufferedReader().use { it?.readText() }
        val gson = Gson()

        val responseType = object : TypeToken<Map<String, List<Lecture>>>() {}.type
        val response: Map<String, List<Lecture>> = gson.fromJson(jsonString, responseType)

        val lectures = response["lectures"] ?: listOf()

        adapter.setItems(lectures)
    }
}