package com.ahmed.validationlibraryproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.ahmed.field_validator.IValidator
import com.ahmed.field_validator.validateField
import com.appsnado.myapplication.ValidationEnum
import com.appsnado.myapplication.ValidationModel

class MainActivity : AppCompatActivity(), IValidator {
    private var email = ""
    private var password = ""
    private var date = ""

    val dummyDataList: ArrayList<ValidationModel>
         = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun submit(view: View) {
        date = findViewById<EditText>(R.id.editTextDate).text.toString()
        email = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
        password = findViewById<EditText>(R.id.editTextTextPassword).text.toString()

        findViewById<EditText>(R.id.editTextDate).setText("")
        findViewById<EditText>(R.id.editTextTextEmailAddress).setText("")
        findViewById<EditText>(R.id.editTextTextPassword).setText("")

        populateDataList()
        validateFields()
    }

    override fun populateDataList() {
        dummyDataList.clear()
        dummyDataList.add(ValidationModel(ValidationEnum.EMAILFIELD, email, "Email"))
        dummyDataList.add(ValidationModel(ValidationEnum.PASSWORDFIELD, password, "Password"))
        dummyDataList.add(ValidationModel(ValidationEnum.NONE, date, "Date"))
    }

    override fun validateFields() {
        if (this@MainActivity.validateField(dummyDataList)) {
            // Perform action
            Toast.makeText(this, "Validation Successful", Toast.LENGTH_SHORT).show()
        }
    }

}