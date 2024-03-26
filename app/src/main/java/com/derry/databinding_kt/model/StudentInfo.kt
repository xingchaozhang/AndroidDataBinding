package com.derry.databinding_kt.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField

// DataBinding BindingAdapter 涉及到判空 后面看

class StudentInfo /*: BaseObservable()*/ {

    // 第一种方式  未修复  KT放弃第一种方式
    var name: String? = null
    // @Bindable
    get() {
        return field
    }
    set(value) {
        field = value
        // notifyPropertyChanged(BR.name)
    }

    var pwd : String? = null
        get() {
            return field
        }
        set(value) {
            field = value
        }






    // 第二种方式  已修复  懒加载
    val nameF : ObservableField<String> by lazy { ObservableField<String>() }
    val pwdF : ObservableField<String> by lazy { ObservableField<String>() }
}