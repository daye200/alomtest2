package com.example.alomtest.food.mainpage

class DataClass ( var dataTitle:String, var foodSelect: String="", // 새로 추가된 필드
                  var foodTime: String=""){
    override fun toString(): String {
        return "DataClass(dataTitle=$dataTitle)"
    }
}
