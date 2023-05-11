package asia.fourtitude.recipe.utils.intent

import android.content.Intent
import android.net.Uri


object IntentUtils {

    fun sendIntent(extraText : String) : Intent {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, extraText)
            type = "text/plain"
        }
        return Intent.createChooser(sendIntent, null)
    }

    fun composeEmail(addresses: Array<String>, subject: String) : Intent {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        return Intent.createChooser(intent,"Choose an Email client : ")

    }

    fun dialPhoneNumber(phoneNumber: String) : Intent {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        return Intent.createChooser(intent, null)
    }

}