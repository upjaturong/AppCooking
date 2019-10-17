package com.example.cooking

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase


class AdapterBill(val mCtx: Context, val layoutId:Int, val employeeList:List<Employees>) : ArrayAdapter<Employees>(mCtx,layoutId,employeeList){

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutId,null)
        val firstname = view.findViewById<TextView>(R.id.name_order)
        val lastname = view.findViewById<TextView>(R.id.price)
        val employee = employeeList[position]
        firstname.text = employee.namefood
        lastname.text = employee.price

        return view

    }
}