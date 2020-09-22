package com.example.ageinminutes

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnDatePicker.setOnClickListener {view->
                clickDatePicker(view)
            Toast.makeText(this,"select Date",Toast.LENGTH_LONG).show() }


       btnCamera.setOnClickListener {
           var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
           startActivityForResult(intent,123)
       }

    }



   //creating function for datePicker
    fun clickDatePicker(view:View){

       val myCalender= Calendar.getInstance()
       val year= myCalender.get(Calendar.YEAR)
       val month= myCalender.get(Calendar.MONTH)
       val day= myCalender.get(Calendar.DAY_OF_MONTH)
       DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
           var selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

           tvDateOfBirth.setText(selectedDate)

           var sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

           val theDate=sdf.parse(selectedDate)

           var selectedDateInMinutes=theDate!!.time/60000

           val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

           val currentDateInMinutes= currentDate!!.time/60000

           val diffenceInMinutes = currentDateInMinutes - selectedDateInMinutes

           tvInMinutes.setText((diffenceInMinutes.toString()))
           
       },year,month,day).show()


   }
    // function to capture and set image in ImageView
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==123){
            var bitmp = data?.extras?.get("Data") as Bitmap
            imgUser.setImageBitmap(bitmp)
        }
    }


}

