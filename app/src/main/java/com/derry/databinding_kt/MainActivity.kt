package com.derry.databinding_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.derry.databinding_kt.databinding.ActivityMainBinding
import com.derry.databinding_kt.model.StudentInfo

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private val studentInfo = StudentInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // TODO 单向绑定第一种方式：<Model ---> View>
        studentInfo.name = "zhangsan"
        studentInfo.pwd = "123456"
        binding.studentInfo = studentInfo

        // 数据的刷新 发现界面没有效果，目前：界面不会根据Model的变化 而 变化
        // 未修复：
        Log.d(TAG, "name:${studentInfo.name}, pwd:${studentInfo.pwd}")
        Handler().postDelayed({
            studentInfo.name = "Lisi"
            studentInfo.pwd = "999999"
        }, 3000)

        // 修复后：  Model ---> View  一向
        Log.d(TAG, "name:${studentInfo.nameF.get()}, pwd:${studentInfo.pwdF.get()}")
        Handler().postDelayed({
            studentInfo.nameF.set("Lisi")
            studentInfo.pwdF.set("999999")
        }, 3000)


        // TODO 双向绑定（Model ---> View 一向        and          View ---> Model 一向 ）  == 双向

        // Model ---> View 一向
        studentInfo.nameF.set("zhangsan")
        studentInfo.pwdF.set("123456") // 修改Model --> View
        binding.studentInfo = studentInfo
        Log.d(TAG, "name:${studentInfo.nameF.get()}, pwd:${studentInfo.pwdF.get()}")


        Handler().postDelayed({
             // 10秒后，测试 View ---> Model    Model是否被修改了
            Log.d(TAG, "name:${studentInfo.nameF.get()}, pwd:${studentInfo.pwdF.get()}")
        }, 10000)

        // 我们开发用的多的是， Model(修改888) ---> View(888) 一向
    }
}