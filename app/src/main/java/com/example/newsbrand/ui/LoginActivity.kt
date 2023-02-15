package com.example.newsbrand.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newsbrand.R
import com.example.newsbrand.databinding.ActivityLoginBinding
import com.example.newsbrand.utils.Session
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
//    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: ActivityLoginBinding
@Inject
lateinit var session: Session
    override fun onStart() {
        super.onStart()
        //check the login session
        val value = session.getTheValue()
        if (value == "logged"){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }else return

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        sharedPreferences = this.getSharedPreferences("loginId", MODE_PRIVATE)
        val username = binding.editText1
        val password = binding.editText2

        binding.loginButton.setOnClickListener {
//            val editer = sharedPreferences.edit()

            if (username.text.isEmpty() && password.text.isEmpty()) {
                Toast.makeText(this, "content blank...", Toast.LENGTH_SHORT).show()
            } else if (username.text.toString() != "aa" && password.text.toString() !="aa") {
                Toast.makeText(this, "username and password not match", Toast.LENGTH_SHORT).show()
            } else {
               session.setTheValue("logged")
//                sessio.setTheValue("logged")
                val intent = Intent(this,MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
        }

    }
}