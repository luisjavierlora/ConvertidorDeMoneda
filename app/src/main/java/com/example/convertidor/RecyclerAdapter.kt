package com.example.convertidor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.elemento_lista.view.*

class RecyclerAdapter(var context: Context,items: ArrayList<Moneda>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var items:ArrayList<Moneda>?=null

    var viewHolder:ViewHolder?=null
    init {
        this.items=items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.elemento_lista,parent,false)

        viewHolder= ViewHolder(vista)
        return  viewHolder!!
    }

    override fun getItemCount(): Int {
        return this.items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=items?.get(position)
        holder.foto?.setImageResource(item?.image!!)
        holder.tv_iso?.text=item?.ISO
        holder.tv_nombre?.text=item?.nombre
        holder.tv_valor?.text=item?.valor

    }



    class ViewHolder(vista:View):RecyclerView.ViewHolder(vista){
        var vista = vista


        var foto:ImageView?=null
        var tv_iso:TextView?=null
        var tv_nombre:TextView?=null
        var tv_valor:TextView?=null

        init {
            foto=vista.ivFoto
            tv_iso=vista.tv_iso
            tv_nombre=vista.tv_nombre
            tv_valor=vista.tv_valor
        }

    }

}