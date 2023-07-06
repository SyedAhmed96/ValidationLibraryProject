package com.ahmed.field_validator

import android.app.Activity
import android.widget.Toast
import com.appsnado.myapplication.ValidationEnum
import com.appsnado.myapplication.ValidationModel
import java.util.ArrayList
import java.util.regex.Pattern

/**
 * Show validaiton to fields accordingly..
 **/
fun Activity.validateField(
    validationModelList: ArrayList<ValidationModel>
): Boolean {
    for (i in 0 until validationModelList.size) {
        if (validationModelList.get(i).textInput.isEmpty() || validationModelList.get(i).textInput.isBlank()) {
            Toast.makeText(
                this,
                "${validationModelList.get(i).fieldTitle} " + getString(R.string.field_cant_be_empty),
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else {
            when (validationModelList.get(i).validationType) {
                ValidationEnum.EMAILFIELD -> {
                    if (!isValidEmail(validationModelList.get(i).textInput)) {
                        Toast.makeText(
                            this,
                            getString(R.string.invalid_email_format),
                            Toast.LENGTH_LONG
                        ).show()
                        return false
                    }

                }
                ValidationEnum.PASSWORDFIELD -> {
                    if (!validationModelList.get(i).textInput.isValidPassword()) {
                        Toast.makeText(
                            this,
                            getString(R.string.password_must_be),
                            Toast.LENGTH_LONG
                        ).show()
                        return false
                    }
                }
                ValidationEnum.CONFIRMPASSWORDFIELD -> {
                    if (!validationModelList.get(i).textInput.isValidPassword()) {
                        Toast.makeText(
                            this,
                            getString(R.string.password_must_be),
                            Toast.LENGTH_LONG
                        ).show()
                        return false
                    }

                    if (!validationModelList.get(i - 1).textInput.equals(
                            validationModelList.get(i).textInput,
                            true
                        )
                    ) {
                        Toast.makeText(
                            this,
                            "${validationModelList.get(i - 1).fieldTitle} & " + "${
                                validationModelList.get(
                                    i
                                ).fieldTitle
                            } " + getString(R.string.should_be_same_same),
                            Toast.LENGTH_LONG
                        ).show()

                        return false
                    }

                }
                ValidationEnum.REPEATPASSWORDFIELD -> {
                    // Supposition new password will be above repeat password always..
                    if (!validationModelList.get(i).textInput.isValidPassword()) {
                        Toast.makeText(
                            this,
                            getString(R.string.password_must_be),
                            Toast.LENGTH_LONG
                        ).show()
                        return false
                    }

                    // Password repeat password comparision case
                    if (validationModelList.get(i - 1).textInput.equals(
                            validationModelList.get(i).textInput,
                            true
                        )
                    ) {
                        Toast.makeText(
                            this,
                            "${validationModelList.get(i - 1).fieldTitle} & " + "${
                                validationModelList.get(
                                    i
                                ).fieldTitle
                            } " + getString(R.string.should_be_same),
                            Toast.LENGTH_LONG
                        ).show()

                        return false
                    }

                }

                else -> {
                    return true
                }

            }
        }
    }
    return true
}

/**
 * A pattern checker method for email format checking
 * @param str
 * @return Boolean
 */
fun isValidEmail(str: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()
}

/**
 * A method which is responsible to check password is valid or not and
 * also check its length tobe greater or equals to eight
 * @return Boolean
 */
fun CharSequence.isValidPassword(): Boolean {
    val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
    val pattern = Pattern.compile(passwordPattern)
    val matcher = pattern.matcher(this)
    return matcher.matches() && this.length >= 8
}

