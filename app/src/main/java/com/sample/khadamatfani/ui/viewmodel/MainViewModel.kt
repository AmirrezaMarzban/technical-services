package com.sample.khadamatfani.ui.viewmodel

import androidx.lifecycle.liveData
import com.sample.khadamatfani.api.Resources
import com.sample.khadamatfani.repository.MainRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainViewModel(private val rep: MainRepository): BaseViewModel(Main) {

    fun getCategories() = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.getCategories()))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun getProvinces() = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.getProvinces()))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun getCities(province: Int) = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.getCities(province)))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun getUserProfile() = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.getUserProfile()))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun updateUserProfile(name: String, phone: String, location: String) = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.updateUserProfile(name, phone, location)))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun getPostsByCategory(category: Int) = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.getPostsByCategory(category)))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun getSinglePost(post: Int) = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.getSinglePost(post)))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun getCooperations() = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.getCooperations()))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun getWorkingHours() = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.getWorkingHours()))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun getPMethods() = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.getPMethods()))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun getWorkingExperiences() = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.getWorkingExperiences()))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun sendRecent(postId: Int) = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.sendRecent(postId)))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun getRecents() = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.getRecent()))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun getPostsByCategory() = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.getPostsByCategory()))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun search(input: String) = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.search(input)))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun deletePost(post_id: Int) = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.deletePost(post_id)))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun createPost(categoryId: Int, title: String, description: String, p_c: String, working_experiences_id: Int, cooperation_id: Int,
                   pmethod_id: Int, workinghours_id: Int, insurance: Boolean, remote: Boolean, military_service: Boolean) = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.createPost(categoryId, title, description, p_c, working_experiences_id, cooperation_id,
                pmethod_id, workinghours_id, insurance, remote, military_service)))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun updatePost(postId: Int, categoryName: String, title: String, description: String, p_c: String, working_experiences_name: String, cooperation_name: String,
                   pmethod_name: String, workinghours_name: String, insurance: Boolean, remote: Boolean, military_service: Boolean) = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.updatePost(postId, categoryName, title, description, p_c, working_experiences_name, cooperation_name,
                pmethod_name, workinghours_name, insurance, remote, military_service)))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }
}