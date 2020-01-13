package `in`.creativelizard.contacts.adapters

import `in`.creativelizard.contacts.beans.ContactItem
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_list_cell.view.*

class ContactListAdapter (private val list: List<ContactItem>,
                          private val context: Context,
                          private val layout: Int) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContactListAdapter.ViewHolder, position: Int) {
        holder.bindItems(list[position])

    }




    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: ContactItem) {
            itemView.tvName.text = item.name
            itemView.tvNumber.text = item.number

        }
    }
}