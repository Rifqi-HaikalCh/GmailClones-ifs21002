package com.ifs21002.gmailclone

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21002.gmailclone.databinding.ActivityMainBinding
import com.ifs21002.gmailclone.ProfileActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDinos.setHasFixedSize(true)
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListEmail(): ArrayList<Email> {
        val dataTitle = resources.getStringArray(R.array.email_name)
        val dataSub = resources.getStringArray(R.array.email_sub)
        val dataName = resources.getStringArray(R.array.email_desc)
        val dataIcon = resources.obtainTypedArray(R.array.email_icon)

        val listEmail = ArrayList<Email>()
        for (i in dataTitle.indices) {
            val email = Email(dataIcon.getResourceId(i, -1), dataTitle[i], dataSub[i], dataName[i])
            listEmail.add(email)
        }

        return listEmail
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDinos.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvDinos.layoutManager = LinearLayoutManager(this)
        }

        val dataEmail = getListEmail() // Mengambil data email dari fungsi getListEmail
        val listEmailAdapter = ListEmailAdapter(dataEmail)
        binding.rvDinos.adapter = listEmailAdapter


        // Menyesuaikan penamaan adapter dengan yang seharusnya
//        listEmailAdapter.setOnItemClickCallback(object : ListEmailAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: Email) {
//                showSelectedEmail(data)
//            }
//        })
    }

    private fun openProfile(){
        val intent = Intent(this@MainActivity, ProfileActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                // Tambahkan kode aksi yang diinginkan di sini
                openProfile()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }



}