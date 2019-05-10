package vn.linh.androidloadingbutton

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.layout_loading_button.view.*
import android.graphics.drawable.GradientDrawable
import android.view.View

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    var title: String? = null

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.layout_loading_button, this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingButton)
        try {
            title = typedArray.getString(R.styleable.LoadingButton_lb_title)
            val isLoading = typedArray.getBoolean(R.styleable.LoadingButton_lb_loading, false)

            val style = typedArray.getInt(R.styleable.LoadingButton_lb_style, 0)
            setBackground(style)

            text_title.text = title
            if (isLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        } finally {
            typedArray.recycle()
        }
    }

    private fun setBackground(style: Int) {
        val gd = GradientDrawable()
        if (style == 0) {
            gd.setColor(Color.BLUE)
            text_title.setTextColor(Color.WHITE)
            progress_loading.indeterminateTintList = ColorStateList.valueOf(Color.WHITE)
        }
        if (style == 1) {
            gd.setColor(Color.WHITE)
            gd.setStroke(1, Color.BLUE)
            text_title.setTextColor(Color.BLUE)
            progress_loading.indeterminateTintList = ColorStateList.valueOf(Color.BLUE)
        }
        gd.cornerRadius = 15f
        text_title.background = gd
    }

    fun showLoading() {
        progress_loading.visibility = View.VISIBLE
        text_title.text = ""
    }

    fun hideLoading() {
        progress_loading.visibility = View.GONE
        text_title.text = title
    }
}