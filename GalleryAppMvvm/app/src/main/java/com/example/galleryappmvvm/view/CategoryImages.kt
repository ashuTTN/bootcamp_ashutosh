package com.example.galleryappmvvm.view

import java.io.Serializable

data class CategoryImages(val categoryId:Any,val categoryImageId:Any,val categoryImageUrl:Any):Serializable{
    constructor():this("","","")
}