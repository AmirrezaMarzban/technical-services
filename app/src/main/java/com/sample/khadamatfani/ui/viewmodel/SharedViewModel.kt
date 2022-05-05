package com.sample.khadamatfani.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sample.khadamatfani.model.Category
import com.sample.khadamatfani.model.Post
import com.sample.khadamatfani.model.Same
import kotlinx.coroutines.Dispatchers.Main

class SharedViewModel: BaseViewModel(Main) {
    val categoryData: MutableLiveData<Category> = MutableLiveData()
    val cooperation: MutableLiveData<Same> = MutableLiveData()
    val locationData: MutableLiveData<String> = MutableLiveData()
    val pMethodData: MutableLiveData<Same> = MutableLiveData()
    val workinghoursData: MutableLiveData<Same> = MutableLiveData()
    val workingeExperiencesData: MutableLiveData<Same> = MutableLiveData()

    val fbmVisibility: MutableLiveData<Int> = MutableLiveData()
    val fbmIsAdd: MutableLiveData<Boolean> = MutableLiveData()

    val currentPost: MutableLiveData<Post> = MutableLiveData()
}