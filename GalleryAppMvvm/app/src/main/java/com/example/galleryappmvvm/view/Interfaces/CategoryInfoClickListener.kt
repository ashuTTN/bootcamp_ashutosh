package com.example.galleryappmvvm.view.Interfaces

interface CategoryInfoClickListener {
    fun onClick(imageUrl: String, categoryId: String, currentImageId: String) :Any
}