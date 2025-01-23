package com.example.foodordering.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.example.foodordering.Model.CategoryModel
import com.example.foodordering.Model.FoodModel

import com.example.foodordering.Repository.MainRepository;

class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }

    fun loadPopular(): LiveData<MutableList<FoodModel>> {
        return repository.loadPopular()
    }
}
