package com.example.medicare.ui

import com.example.dispensary.ui.composables.ChooseTabState

class Validator {

    private fun String.isValidEmail(): Boolean {
        val emailRegex = "^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?\$"
        return matches(emailRegex.toRegex())
    }

    fun checkEmail(email:String):String?{
        if(email.equals("")) return "required_field"
        else if (!email.isValidEmail()) return "invalid_email"
        return null
    }
    fun checkFirstName(firstName:String):String?{
        if(firstName.equals("")) return "required_field"
        return null
    }
    fun checkSecondName(secondName:String):String?{
        if(secondName.equals("")) return "required_field"
        return null
    }
    fun checkPassword(password:String):String?{
        if(password.equals("")) return "required_field"
        if(password.length<8) return "password_at_least_8_letters"
        return null
    }

    fun checkGender(gender: ChooseTabState?):String?{
        if(gender==null) return "required_field"
        return null
    }


}