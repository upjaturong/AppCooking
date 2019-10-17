package com.example.cooking

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle

import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.order.*




class Order :AppCompatActivity(){

    var mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference
    lateinit var employeeList:MutableList<Employees>
    lateinit var listview: ListView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order)
        mDatabase = FirebaseDatabase.getInstance().getReference("Foods")

        val user = mAuth.currentUser
        val uid = user!!.uid


        employeeList = mutableListOf()
        listview = findViewById(R.id.bill_Lv)
        mDatabase = FirebaseDatabase.getInstance().getReference("Foods")

        var total_price = 0

        mDatabase.child(uid).addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    employeeList.clear()
                    for (e in p0.children){
                        val employee = e.getValue(Employees::class.java)
                        employeeList.add(employee!!)
                        total_price += employee.price.toInt()
                    }
                    val adapter = AdapterBill(this@Order,R.layout.list_bill,employeeList)
                    listview.adapter = adapter
                    val total_count = bill_Lv.count
                    count.text = ""+total_count
                    total.text = ""+total_price
                }
            }
        })
        paynow.setOnClickListener {
            mDatabase.child(uid).addValueEventListener(object :
                ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                        for (e in p0.children){
                            val employee = e.getValue(Employees::class.java)
                            mDatabase.child(uid).child(employee!!.id).removeValue()
                            val employeeId = mDatabase.push().key
                            mDatabase.child(uid).child(employeeId!!).removeValue()
                        }
                }
            })

            Toast.makeText(this,"Thank You ",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity::class.java))
        }

    }

    override fun onRestart() {
        Toast.makeText(getApplicationContext(), "onStop called", Toast.LENGTH_LONG).show();
        super.onRestart()
    }

}


