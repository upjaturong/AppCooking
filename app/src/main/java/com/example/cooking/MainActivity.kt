package com.example.cooking


import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private var promotion: RecyclerView? = null
    private var Popdrink: RecyclerView? = null
    private var Popsweet: RecyclerView? = null
    private var Menu: RecyclerView? = null
    private var foods = arrayOf(
        "ชาเขียวน้ำผึ้งมะนาว",
        "ชาดำเย็น",
        "ชาเขียวลาเต้",
        "ชาเขียวนมสด ",
        "ปังเย็น"


    )
    private var arrImg = arrayOf(
        R.drawable.ic_lemontea,
        R.drawable.ic_blacktea,
        R.drawable.img_greentea,
        R.drawable.img_milkgreentea,
        R.drawable.pangyen

    )
    private var arrTextfood = arrayOf(
        R.string.pro_lamonteas,
        R.string.pro_blacktea,
        R.string.pro_greentea,
        R.string.pro_milkgreentea,
        R.string.pro_pangyen
    )

    var foods2 = arrayOf(
        "ชาเขียวลาเต้",
        "ชาเขียวนมสด ",
        "COMING SOON"
    )
    private var arrImg2 = arrayOf(
        R.drawable.img_greentea,
        R.drawable.img_milkgreentea,
        R.drawable.img_comingsoon
    )
    var arrTextfood2 = arrayOf(
        R.string.greentea,
        R.string.milkgreentea,
        R.string.coming
    )

    var foods3 = arrayOf(
        "ปังเย็น",
        "ปังหน้ากลัวย",
        "COMING SOON"
    )
    var arrImg3 = arrayOf(
        R.drawable.pangyen,
        R.drawable.pangbanana,
        R.drawable.img_comingsoon
    )
    private var arrTextfood3 = arrayOf(
        R.string.pangyen,
        R.string.pangbanana,
        R.string.coming

    )
    private var foods4 = arrayOf(
        "ชาเขียวน้ำผึ้งมะนาว",
        "โอเลี้ยง",
        "ชาดำเย็น",
        "ชาเขียวลาเต้",
        "ชาเขียวนมสด ",
        "ปังเย็น",
        "ปังหน้ากลัวย",
        "COMING SOON"

    )
    private var arrImg4 = arrayOf(
        R.drawable.ic_lemontea,
        R.drawable.ic_ohliang,
        R.drawable.ic_blacktea,
        R.drawable.img_greentea,
        R.drawable.img_milkgreentea,
        R.drawable.pangyen,
        R.drawable.pangbanana,
        R.drawable.img_comingsoon
    )
    private var arrTextfood4 = arrayOf(
        R.string.lamonteas,
        R.string.ohliang,
        R.string.blacktea,
        R.string.greentea,
        R.string.milkgreentea,
        R.string.pangyen,
        R.string.pangbanana,
        R.string.coming
    )

    lateinit var mDatabase: DatabaseReference
    var mAuth = FirebaseAuth.getInstance()
    var user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        promotion = findViewById(R.id.promotion)
        promotion!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val myAdapter = Adapter(foods, arrImg, arrTextfood, this)
        promotion!!.adapter = myAdapter

        Popdrink = findViewById(R.id.Popdrink)
        Popdrink!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val myAdapter2 = Adapter(foods2, arrImg2, arrTextfood2, this)
        Popdrink!!.adapter = myAdapter2

        Popsweet = findViewById(R.id.Popsweet)
        Popsweet!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val myAdapter3 = Adapter(foods3, arrImg3, arrTextfood3, this)
        Popsweet!!.adapter = myAdapter3

        Menu = findViewById(R.id.Menu)
        Menu!!.layoutManager = LinearLayoutManager(this)
        Menu!!.layoutManager = GridLayoutManager(this, 3)
        val myAdapter4 = Adapter(foods4, arrImg4, arrTextfood4, this)
        Menu!!.adapter = myAdapter4

        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        if (user == null){
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            val uid = user!!.uid

            mDatabase.child(uid).child("Restaurant").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                @SuppressLint("SetTextI18n")
                override fun onDataChange(snapshot: DataSnapshot) {
                    supportActionBar!!.title = " " + snapshot.value.toString()
                }
            })
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        //searchView
        val sv: SearchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView

        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_order -> {
                startActivity(Intent(this, Order::class.java))
            }
            R.id.action_settings -> {
                mAuth.signOut()
                val uid = user!!.uid
                mDatabase.child(uid).child("Status").setValue(0)
                Toast.makeText(this, "Signed Out :(", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }
            R.id.tracking ->{
                startActivity(Intent(this, TrackingOrder::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
