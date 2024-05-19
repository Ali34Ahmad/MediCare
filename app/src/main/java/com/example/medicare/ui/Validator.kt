package com.example.medicare.ui

import com.example.dispensary.ui.composables.ChooseTabState

object Validator {

    private fun String.isValidEmail(): Boolean {
        val emailRegex = "^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?\$"
        return matches(emailRegex.toRegex())
    }

    fun checkEmail(email:String):String?{
        if(email.equals("")) return checkRequiredTextField(email)
        else if (!email.isValidEmail()) return "invalid_email"
        return null
    }
    fun checkRequiredTextField(text:String):String?{
        if(text.equals("")) return "required_field"
        return null
    }
    fun checkPassword(password:String):String?{
        if(password.equals("")) return checkRequiredTextField(password)
        if(password.length<8) return "password_at_least_8_letters"
        return null
    }

    fun checkGender(gender: ChooseTabState?):String?{
        if(gender==null) return "required_field"
        return null
    }


}