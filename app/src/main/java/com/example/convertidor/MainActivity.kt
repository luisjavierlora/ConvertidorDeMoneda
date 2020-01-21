package com.example.convertidor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Array

class MainActivity : AppCompatActivity() {


    var  Monedas:ArrayList<Moneda>?=null

    var layoutManager:RecyclerView.LayoutManager?=null
    var adaptador: RecyclerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Monedas= ArrayList()
        create_monedas()

        layoutManager=LinearLayoutManager(this)
        adaptador= RecyclerAdapter(this,Monedas!!)
        rv_lista.layoutManager=layoutManager
        rv_lista.adapter=adaptador





        val options_spiner = arrayOf("Peso colombiano","Euro","Dolar","YEN","Libra esterlina")
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options_spiner )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter= adapter

        var moneda:String=""

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view
                moneda = "${parent.getItemAtPosition(position).toString()}"
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }


        bt_Convertir.setOnClickListener {
            if (et_valor.text.toString().isEmpty())
                Toast.makeText(this, "Ingrese un valor a convertir", Toast.LENGTH_SHORT).show()

            else {
                var valor= et_valor.text.toString().toDouble()

                val lista_new: ArrayList<Moneda> = convertir(moneda,valor)
                adaptador= RecyclerAdapter(this,lista_new)
                rv_lista.layoutManager=layoutManager
                rv_lista.adapter=adaptador
            }
        }




    }

    fun create_monedas(){

        Monedas?.add(Moneda(R.drawable.colombia,"COP","Peso colombiano","0"))
        Monedas?.add(Moneda(R.drawable.union_europea,"EU","Euro","0"))
        Monedas?.add(Moneda(R.drawable.usa,"USD","Dolar","0"))
        Monedas?.add(Moneda(R.drawable.japon,"JPY","YEN","0"))
        Monedas?.add(Moneda(R.drawable.reino_unido,"GBP","Libra esterlina","0"))

    }

    fun convertir(moneda:String,valor:Double):ArrayList<Moneda>{

        var monedas:ArrayList<Moneda> =ArrayList()

        var cop:Double=0.0
        var eu:Double=0.0
        var us:Double=0.0
        var jp:Double=0.0
        var libra:Double=0.0


        if (moneda=="Peso colombiano"){
            eu= valor*0.00027
            us=valor*0.00031
            jp = valor*0.034
            libra=valor*0.00024
        }
        else if(moneda=="Euro"){
            cop = valor*3642.08
            us = valor*1.11
            jp= valor*122.42
            libra=valor*0.086
        }

        else if(moneda=="Dolar"){
            cop = valor*3270.75
            eu = valor*0.9
            jp = valor*109.94
            libra=valor*0.77
        }

        else if(moneda=="YEN"){
            cop = valor*29.75
            eu = valor*0.0082
            us = valor*0.0091
            libra=valor*0.0070
        }

        else if(moneda=="Libra esterlina"){
            cop = valor*4248.92
            eu = valor*1.17
            us = valor*1.3
            jp=valor*142.8
        }


        for(i in 0..4){
            if(Monedas?.get(i)!!.nombre!=moneda){

                if (Monedas?.get(i)!!.nombre=="Peso colombiano")
                    Monedas?.get(i)!!.valor=String.format("%.2f", cop)
                else if (Monedas?.get(i)!!.nombre=="Dolar")
                    Monedas?.get(i)!!.valor=String.format("%.2f", us)
                else if (Monedas?.get(i)!!.nombre=="Euro")
                    Monedas?.get(i)!!.valor=String.format("%.2f", eu)
                else if (Monedas?.get(i)!!.nombre=="YEN")
                    Monedas?.get(i)!!.valor=String.format("%.2f", jp)

                else if (Monedas?.get(i)!!.nombre=="Libra esterlina")
                    Monedas?.get(i)!!.valor=String.format("%.2f", libra)

                monedas.add(Monedas?.get(i)!!)
            }
        }

        return monedas



    }

}
