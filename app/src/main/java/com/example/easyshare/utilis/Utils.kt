import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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

    fun loadRandomUserAvatar(view: View) {
        Glide.with(view.context)
            .load("https://api.minimalavatars.com/avatar/random/png")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .error(R.drawable.ic_user_profile)
            .listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.e("GlideError", "Image load failed", e)
                        return false // `false`: the error placeholder can be placed
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                }
            )
            .into(view.findViewById(R.id.businessImage))
    }
}
