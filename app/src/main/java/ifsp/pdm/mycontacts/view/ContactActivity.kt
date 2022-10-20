package ifsp.pdm.mycontacts.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ifsp.pdm.mycontacts.databinding.ActivityContactBinding
import ifsp.pdm.mycontacts.databinding.ActivityMainBinding
import ifsp.pdm.mycontacts.model.Constant.EXTRA_CONTACT
import ifsp.pdm.mycontacts.model.Contact
import kotlin.random.Random

class ContactActivity : AppCompatActivity() {
    private val acb: ActivityContactBinding by lazy {
        ActivityContactBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)

        acb.saveBt.setOnClickListener{
            val contact = Contact(
                id = Random(System.currentTimeMillis()).nextInt(),
                name = acb.nameEt.text.toString(),
                address = acb.addressEt.text.toString(),
                phone = acb.phoneEt.text.toString(),
                email = acb.emailEt.text.toString()
            )
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_CONTACT, contact)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}