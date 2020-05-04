package com.example.galleryappmvvm.viewmodel

import androidx.lifecycle.*
import com.example.galleryappmvvm.model.Repository
import com.example.galleryappmvvm.view.Category
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

private val TAG = CategoryViewModel::class.java.simpleName
class CategoryViewModel(private val repository : Repository):ViewModel() {
    private var savedCategories: MutableLiveData<List<Category>> = MutableLiveData()

    fun getSavedCategories(): LiveData<List<Category>> {
        repository.getSavedCategories1()
            .addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
                if (e != null) {
                    savedCategories.value = null
                    return@EventListener
                }

                var savedCategoryList: MutableList<Category> = mutableListOf()
                for (doc in value!!) {
                    //var categoryItem = doc.toObject(Category::class.java)
                    val categoryItem = Category(
                        "${doc.get("name")}",
                        "${doc.get("categoryProfileImage")}",
                        "${doc.id}"
                    )
                    savedCategoryList.add(categoryItem)
                }
                savedCategories.value = savedCategoryList
            })
        return savedCategories
    }

    enum class CategoryState{
        HIDE_PROGRESS,
        SHOW_PROGRESS,
        SUCCESS
    }
}