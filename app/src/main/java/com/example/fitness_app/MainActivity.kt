package com.example.fitness_app

import android.content.ClipData
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity() : AppCompatActivity(), onItemClickListner{

    private lateinit var btnImc : LinearLayout

    //!criando uma recycler view!
    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mainItems = mutableListOf<MainItem>()
        mainItems.add(
            MainItem(
                id = 1,
                drawbleId = R.drawable.ic_baseline_wb_sunny_24,
                textStringId = R.string.first_bottom,
                colorId = Color.GREEN
            )
        )
        mainItems.add(
            MainItem(
                id = 2,
                drawbleId = R.drawable.ic_baseline_timeline_24,
                textStringId = R.string.ideal,
                colorId = Color.YELLOW 
            )
        )

//        btnImc = findViewById(R.id.btn_imc)
//        btnImc.setOnClickListener{
//            val intent = Intent(this, IMC_Activity::class.java)
//            startActivity(intent)
        val adapterinho = MainAdapter(mainItems, this)
        rvMain = findViewById(R.id.rcr_view)
        rvMain.adapter = adapterinho
        rvMain.layoutManager = GridLayoutManager(this, 2)

        }

    override fun onClick(id : Int) {
        when(id) {
            1 -> {
                val intent = Intent(this, IMC_Activity::class.java)
                startActivity(intent)
            }
        }
    }


    private inner class MainAdapter(
        private var mainItems: List<MainItem>,
        private val onItemClickListner: onItemClickListner
        ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val viewIn = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(viewIn)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val itemCurrent = mainItems[position]
            holder.bind(itemCurrent)
        }

        override fun getItemCount(): Int {
            return mainItems.size
        }
        // VIEW HOLDER
        private inner class MainViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
            fun bind(item: MainItem){
                val image : ImageView = itemView.findViewById(R.id.item_icon)
                val nameIcon : TextView = itemView.findViewById(R.id.item_name)
                val container : LinearLayout = itemView.findViewById(R.id.container_imc)

                image.setImageResource(item.drawbleId)
                nameIcon.setText(item.textStringId)
                container.setBackgroundColor(item.colorId)
                container.setOnClickListener(){
                    onItemClickListner.onClick(item.id)
                }

            }
        }

    }






}
