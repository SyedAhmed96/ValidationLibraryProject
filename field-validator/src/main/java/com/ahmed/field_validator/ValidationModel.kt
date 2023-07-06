package com.appsnado.myapplication

/**
 * A validation model class, represents type, input text for validation field, field title to be printed
 * @param validationType
 * @param textInput
 * @param fieldTitle
 * */
data class ValidationModel(
    val validationType: ValidationEnum,
    val textInput: String,
    val fieldTitle: String
)