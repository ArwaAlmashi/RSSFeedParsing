package com.example.rssfeedparsing.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedparsing.R
import com.example.rssfeedparsing.databinding.ActivityMainBinding
import com.example.rssfeedparsing.model.Question
import com.example.rssfeedparsing.model.XmlParser
import com.example.rssfeedparsing.recyclerview.MyAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var questions: ArrayList<Question>
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        questions = arrayListOf()
        recyclerView = binding.recyclerview
        myAdapter = MyAdapter(questions)
        recyclerView.adapter = myAdapter

        parseRSS()

    }

    private fun parseRSS() {
        CoroutineScope(IO).launch {
            val data = async {
                val parser = XmlParser()
                parser.parse()
            }.await()
            try {
                runOnUiThread{
                    myAdapter.update(data)
                }

            } catch (e: IOException){
                e.printStackTrace()
            }
        }
    }
}