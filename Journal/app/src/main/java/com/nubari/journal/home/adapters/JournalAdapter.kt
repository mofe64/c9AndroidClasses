package com.nubari.journal.home.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nubari.journal.data.model.Journal
import com.nubari.journal.databinding.JournalBinding

class JournalAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<JournalAdapter.ViewHolder>() {
    private var journals = listOf<Journal>()

    class ViewHolder(view: JournalBinding) : RecyclerView.ViewHolder(view.root) {
        val journalName = view.journalName
        var card = view.journalCard

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = JournalBinding.inflate(
            LayoutInflater.from(fragment.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val journal = journals[position]
        holder.journalName.text = journal.title

        holder.card.setOnClickListener {
            Log.i("card view clicked", "card view")
        }
    }

    override fun getItemCount(): Int {
        return journals.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<Journal>) {
        journals = data
        notifyDataSetChanged()
    }

}