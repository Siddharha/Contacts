package `in`.creativelizard.contacts.utils

import `in`.creativelizard.contacts.R
import `in`.creativelizard.contacts.beans.ContactItem
import `in`.creativelizard.contacts.database.AppDatabase
import `in`.creativelizard.contacts.interfaces.AddContactCompleteCallback
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.dialog_add_contact_actions.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AddContactActionsDialog(context: Context,val db:AppDatabase) : BottomSheetDialog(context){
     lateinit var addContactCompleteCallback: AddContactCompleteCallback
   init {
       if (context is Activity) {
           setOwnerActivity(context)
       }
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutInflater.inflate(R.layout.dialog_add_contact_actions, null))

        btnAdd.setOnClickListener {
            if(etName.text.isNotEmpty()){
                if(etMail.text.isNotEmpty()){
                    if(etNumber.text.isNotEmpty()){
                        doAsync {
                            val itm = ContactItem(etName.text.toString(),
                                etMail.text.toString(),
                                etNumber.text.toString())
                            db.contactDao().insertContact(itm)
                            addContactCompleteCallback.onContactAdded(true)
                        }
                    }else{
                        etNumber.error = "Enter number of contact"
                    }
                }else{ etMail.error = "Enter email of contact"}
            }else{
                etName.error = "Enter Name of contact"
            }




        }


    }
}