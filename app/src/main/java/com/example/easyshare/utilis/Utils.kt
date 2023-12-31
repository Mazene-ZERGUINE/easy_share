import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.easyshare.R
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

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

    fun showCustomDialogBox(
        message: String?,
        dialog: Dialog,
        callback: (Boolean) -> Unit
    ) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.confirmation_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvMessage: TextView = dialog.findViewById(R.id.tvMessage)
        val btnYes: Button = dialog.findViewById(R.id.btnYes)
        val btnNo: Button = dialog.findViewById(R.id.btnNo)

        tvMessage.text = message

        btnYes.setOnClickListener {
            dialog.dismiss()
            callback(true)
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
            callback(false)
        }

        dialog.show()
    }



}
