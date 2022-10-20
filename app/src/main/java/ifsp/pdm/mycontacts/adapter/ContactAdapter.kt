package ifsp.pdm.mycontacts.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ifsp.pdm.mycontacts.R
import ifsp.pdm.mycontacts.databinding.TileContactBinding
import ifsp.pdm.mycontacts.model.Contact

class ContactAdapter(
    context: Context,
    private val contactList: MutableList<Contact>
    ): ArrayAdapter<Contact>(context, R.layout.tile_contact, contactList) {
    //é um jeito de escrever um construtor

    private data class TileContactHolder(val name:TextView, val email: TextView)

    private lateinit var tcb: TileContactBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contact = contactList[position]
        var contactTileView = convertView

        if (contactTileView == null) {
            tcb = TileContactBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )
            contactTileView = tcb.root;
            val tileContactHolder = TileContactHolder(tcb.nameTv, tcb.emailTv)
            contactTileView.tag = tileContactHolder //associar o holder a celula
        }

        with (contactTileView.tag as TileContactHolder){
            tcb.emailTv.text = contact.email
            tcb.nameTv.text = contact.name
        }

        return contactTileView;
    }
    }