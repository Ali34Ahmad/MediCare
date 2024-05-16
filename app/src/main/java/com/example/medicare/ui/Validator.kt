package com.example.medicare.ui

class Validator {

    fun emailIsValid(email:String): Boolean {
        val emailRegex = "^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?\$"
        if (!email.matches(emailRegex.toRegex())) {
            return false
        }
        return true
    }

    fun checkEmail(email:String):String?{
        if(email.equals("")) return "required_field"
        else if (!emailIsValid(email)) return "invalid_email"
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
}