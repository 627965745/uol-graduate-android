package com.example.uolgraduateandroid.ui.theme

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TopicViewModel : ViewModel() {
    val topicStates = MutableLiveData<MutableMap<Int, Boolean>>()

    init {
        topicStates.value = mutableMapOf()
    }

    fun setTopicChecked(topicID: Int, isChecked: Boolean) {
        topicStates.value?.put(topicID, isChecked)
        topicStates.postValue(topicStates.value)
    }

    fun isTopicChecked(topicID: Int): Boolean {
        return topicStates.value?.get(topicID) ?: false
    }
}