package com.example.medicare.ui

import com.example.dispensary.ui.composables.ChooseTabState
import com.example.medicare.R

object Validator {

    private fun String.isValidEmail(): Boolean {
        val emailRegex = "^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?\$"
        return matches(emailRegex.toRegex())
    }

    fun checkEmail(email:String):Int?{
        if(email.equals("")) return checkRequiredTextField(email)
        else if (!email.isValidEmail()) return R.string.invalid_email
        return null
    }
    fun checkRequiredTextField(text:String):Int?{
        if(text.equals("")) return R.string.required_field
        return null
    }
    fun checkPassword(password:String):Int?{
        if(password.equals("")) return checkRequiredTextField(password)
        if(password.length<8) return R.string.password_at_least_8_letters
        return null
    }

    fun checkGender(gender: ChooseTabState?):Int?{
        if(gender==null) return R.string.required_field
        return null
    }


}