package com.sample.khadamatfani.repository

import com.sample.khadamatfani.api.ApiService

class MainRepository(private val apiService: ApiService) {
    suspend fun getCategories() = apiService.categories()
    suspend fun getProvinces() = apiService.provinces()
    suspend fun getCities(province: Int) = apiService.cities(province)
    suspend fun getUserProfile() = apiService.getUserProfile()
    suspend fun getPostsByCategory(category: Int) = apiService.getCategoriesPosts(category)
    suspend fun getCooperations() = apiService.cooperations()
    suspend fun search(input: String) = apiService.search(input)
    suspend fun getWorkingHours() = apiService.workingHours()
    suspend fun getPMethods() = apiService.pmethods()
    suspend fun getSinglePost(post: Int) = apiService.getSinglePost(post)
    suspend fun updateUserProfile(name: String, phone: String, location: String) = apiService.updateUserProfile(name, phone, location)
    suspend fun sendRecent(post_id: Int) = apiService.sendRecent(post_id)
    suspend fun deletePost(post_id: Int) = apiService.deletePost(post_id)
    suspend fun getRecent() = apiService.getRecent()
    suspend fun getWorkingExperiences() = apiService.workingExperiences()
    suspend fun getPostsByCategory() = apiService.posts()
    suspend fun createPost(categoryId: Int, title: String, description: String, p_c: String, working_experiences_id: Int, cooperation_id: Int,
                           pmethod_id: Int, workinghours_id: Int, insurance: Boolean, remote: Boolean, military_service: Boolean) = apiService.createPost(categoryId, title, description, p_c, working_experiences_id, cooperation_id,
        pmethod_id, workinghours_id, insurance, remote, military_service)

    suspend fun updatePost(postId: Int, categoryName: String, title: String, description: String, p_c: String, working_experiences_name: String, cooperation_name: String,
                           pmethod_name: String, workinghours_name: String, insurance: Boolean, remote: Boolean, military_service: Boolean) = apiService.updatePost(postId, categoryName, title, description, p_c, working_experiences_name, cooperation_name,
        pmethod_name, workinghours_name, insurance, remote, military_service)
}