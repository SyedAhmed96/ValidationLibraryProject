package com.ahmed.field_validator

import com.appsnado.myapplication.ValidationModel

/**
 * Interface will be implemented by fragments/activities which has to validate
 * field data
 **/
interface IValidator {
    /**
     * Function is responsible to populate datalist of type ValidaitonModel
     * */
    fun populateDataList()

    /**
     * This method is responsible to validate fields, will be called afterwards
     */
    fun validateFields()
}