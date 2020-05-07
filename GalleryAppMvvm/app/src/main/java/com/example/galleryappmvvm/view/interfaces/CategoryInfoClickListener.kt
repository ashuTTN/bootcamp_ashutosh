package com.example.galleryappmvvm.view.interfaces

interface CategoryInfoClickListener {
    fun onClick(imageUrl: String, categoryId: String, currentImageId: String) :Any
}