package com.sample.khadamatfani.api

object HttpRequestsUrl {
    const val BASE_URL = "http://192.168.1.101:8000/api/v1/"

    const val CATEGORIES = "categories"
    const val PROVINCES = "provinces"
    const val COOPERATIONS = "cooperations"
    const val PMTHODS = "pmethods"
    const val WORKING_HOURS = "workinghours"
    const val WORKING_EXPERIENCES = "workingExperiences"
    const val CITIES = "provinces/{province}"
    const val USER = "user"
    const val CATEGORIES_ITEM = "categories/{category}"
    const val VERIFY_OTP = "verify/{code}"
    const val SEARCH = "search?"
    const val LOGIN = "login"
    const val RECENT = "recent"
    const val POST = "posts"
    const val UPDATE_POST = "updatepost"
    const val DELETE_POST = "deletepost"
    const val SINGLE_POST = "posts/{post}"
}