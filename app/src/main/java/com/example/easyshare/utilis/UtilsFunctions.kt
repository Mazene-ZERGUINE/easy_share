import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.easyshare.R

object Utils {
    fun displayToast(
        context: Context,
        layoutResId: Int,
        message: String,
        duration: Int
    ) {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val layout: View = inflater.inflate(layoutResId, null)
        val messageTextView: TextView = layout.findViewById(R.id.messageTextView)

        messageTextView.text = message
        val toast = Toast(context)

        toast.duration = duration
        toast.view = layout
        toast.show()
    }
}
