package ifsp.pdm.mycontacts.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import ifsp.pdm.mycontacts.R
import ifsp.pdm.mycontacts.databinding.ActivityMainBinding
import ifsp.pdm.mycontacts.model.Constant.EXTRA_CONTACT
import ifsp.pdm.mycontacts.model.Contact

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //data source
    private val contactList: MutableList<Contact> = mutableListOf()
    private lateinit var carl: ActivityResultLauncher<Intent> //criação do objeto que trata os retorno de telas secundarias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        fillContactList()
        amb.contactsLv.adapter = contactAdapter;

        carl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            result ->
            if (result.resultCode == RESULT_OK){
                val contact = result.data?.getParcelableExtra<Contact>(EXTRA_CONTACT)
                contact?.let {_contact ->  //se o contato nao for nulo
                    contactList.add(_contact)
                    contactAdapter.add(_contact.name)
                    contactAdapter.notifyDataSetChanged() //adapter verifica uma modificação da lista
                }
            }
        }
    }

    //adapter
    private val contactAdapter: ArrayAdapter<String> by lazy { //by lazy: inicializar
        ArrayAdapter(this, android.R.layout.simple_list_item_1, contactList.map { it.name })//ou it.toString() //criou uma copia do contactList onde cada posição é o objeto convertido pra string
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    //trata dos clicks em itens de menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addContactMi -> {
                carl.launch(Intent(this, ContactActivity::class.java))
                true
            }
            else -> { false }
        }
    }

    private fun fillContactList(){
        for(i in 1..10){
            contactList.add(Contact(id = i, "name = $i", address = "endereco $i", phone = "telefone $i", email = "email $i"))

        }
    }


}