package `in`.creativelizard.contacts.activitties

import `in`.creativelizard.contacts.R
import `in`.creativelizard.contacts.adapters.ContactListAdapter
import `in`.creativelizard.contacts.beans.ContactItem
import `in`.creativelizard.contacts.database.AppDatabase
import `in`.creativelizard.contacts.interfaces.AddContactCompleteCallback
import `in`.creativelizard.contacts.utils.AddContactActionsDialog
import `in`.creativelizard.contacts.utils.AppUtil
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AddContactCompleteCallback{


    lateinit var db:AppDatabase
    lateinit var contactListAdapter:ContactListAdapter
    lateinit var addContactDialog:AddContactActionsDialog
    lateinit var layoutManager:RecyclerView.LayoutManager
    lateinit var contactList:ArrayList<ContactItem>
    private val p = Paint()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        loadData()
        onActionPerform()
    }

    private fun loadData() {
        contactList.addAll(db.contactDao().getAllContactDataData())
        contactListAdapter.notifyDataSetChanged()
    }

    private fun initialize() {
        layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "Contact")
            .allowMainThreadQueries()
            .build()

        contactList = ArrayList()
        contactListAdapter = ContactListAdapter(contactList,this,R.layout.contact_list_cell)

        addContactDialog = AddContactActionsDialog(this, db)
        addContactDialog.addContactCompleteCallback = this

        rlContacts.layoutManager = layoutManager
        rlContacts.adapter = contactListAdapter

        AppUtil.initSwipe(rlContacts,Paint(),this)
    }

    private fun onActionPerform() {
        fabAdd.setOnClickListener {
            addContactDialog.show()
        }

        btnCheck.setOnClickListener {
            val itm = db.contactDao().getAllContactDataData()
            if(itm.isNotEmpty()){
                Toast.makeText(this@MainActivity,itm[0].name,Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onContactAdded(isAdded:Boolean) {
        //Toast.makeText(this@MainActivity,"Done Adding",Toast.LENGTH_SHORT).show()
        addContactDialog.dismiss()
        contactList.clear()
        loadData()
        contactListAdapter.notifyDataSetChanged()

        rlContacts.smoothScrollToPosition(contactList.size-1)
    }



}
