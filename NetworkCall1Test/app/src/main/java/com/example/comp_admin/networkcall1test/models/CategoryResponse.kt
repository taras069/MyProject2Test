package com.example.comp_admin.networkcall1test.models

data class CategoryResponse(
    var error: Boolean,
    var count: Int,
    var data: ArrayList<Category>
)