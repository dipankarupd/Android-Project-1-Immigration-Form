package com.example.immigrationform

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var name : EditText
    lateinit var address: EditText
    lateinit var dob: EditText
    lateinit var maleCB: CheckBox
    lateinit var femaleCB: CheckBox
    lateinit var yesRB: RadioButton
    lateinit var noRB: RadioButton
    lateinit var countries: Spinner
    lateinit var submitBtn: Button
    lateinit var layout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.personName)
        address = findViewById(R.id.personAddress)
        dob = findViewById(R.id.personDON)
        maleCB = findViewById(R.id.cbMale)
        femaleCB = findViewById(R.id.cbFemale)
        yesRB = findViewById(R.id.rbYes)
        noRB = findViewById(R.id.rbNo)
        countries = findViewById(R.id.country)
        submitBtn = findViewById(R.id.btnSubmit)
        layout = findViewById(R.id.myLayout)

        var arrayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.Countries,
            android.R.layout.simple_spinner_item
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countries.adapter = arrayAdapter

        countries.onItemSelectedListener = this

        maleCB.setOnClickListener {
            if(maleCB.isChecked) {
                femaleCB.isChecked = false
                Toast.makeText(applicationContext, "Selected Male", Toast.LENGTH_SHORT).show()

            }
        }

        femaleCB.setOnClickListener {
            if(femaleCB.isChecked) {
                maleCB.isChecked = false
                Toast.makeText(applicationContext, "Selected female", Toast.LENGTH_SHORT).show()

            }
        }

        yesRB.setOnClickListener {
            if(yesRB.isChecked) {
                noRB.isChecked = false
                Toast.makeText(applicationContext, "Fully Vaccinated", Toast.LENGTH_SHORT).show()
            }
        }

        noRB.setOnClickListener {
            if(noRB.isChecked) {
                yesRB.isChecked = false
                Toast.makeText(
                    applicationContext, "Not vaccinated.Need to stay in Quarantine", 
                    Toast.LENGTH_SHORT).show()
            }
        }



        submitBtn.setOnClickListener {
            showDialogue()
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?,view : View?, position: Int, id: Long) {

        var select : String = parent!!.getItemAtPosition(position).toString()
        Toast.makeText(applicationContext, "Selected " + select , Toast.LENGTH_SHORT).show()

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun showDialogue() {

        var alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("Are You Sure")
            .setMessage("Are You sure you want to submit the form? ")
            .setCancelable(false)
            .setIcon(R.drawable.ic_baseline_warning_24)
            .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, which->
                dialogInterface.cancel()
            })
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                Snackbar.make(layout,"Form Submitted Successfully",Snackbar.LENGTH_LONG).show()
            })
        alert.show()
    }
}