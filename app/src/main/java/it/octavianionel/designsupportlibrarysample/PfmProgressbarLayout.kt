package it.octavianionel.designsupportlibrarysample

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_pfm_progressbar.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat


/**
 * Created by Reply on 14/01/2019.
 */
class PfmProgressbarLayout(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    private var mAttrs: AttributeSet?

    private lateinit var mProgressbar: ProgressBar
    private lateinit var mThresholdBar: View
    private lateinit var mThresholdLeftTextTv: TextView
    private lateinit var mThresholdRightTextTv: TextView

    private lateinit var mThreshold: BigDecimal
    private lateinit var mTotalAmount: BigDecimal
    private lateinit var mSpentAmount: BigDecimal

    private var mWidthMeasureSpec: Int = 0
    private var mHeightMeasureSpec: Int = 0


    private var numberFormat: DecimalFormat = DecimalFormat("###,##0.00")

    init {
        mAttrs = attrs
    }

    fun setValues(amount: BigDecimal, spentAmount: BigDecimal, threshold: BigDecimal) {
        mTotalAmount = amount
        mSpentAmount = spentAmount
        mThreshold = threshold
        init(context, mAttrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {

        val view = LayoutInflater.from(context).inflate(R.layout.layout_pfm_progressbar, this)
        mProgressbar = pfm_progressbar as ProgressBar
        mThresholdBar = pfm_threshdold_bar_view as View
        mThresholdLeftTextTv = pfm_threshold_left_tv as TextView
        mThresholdRightTextTv = pfm_threshold_right_tv as TextView



        val observer: ViewTreeObserver = view.viewTreeObserver
        observer.addOnGlobalLayoutListener {
            val displayMetrics = this.resources.displayMetrics
            val dpHeight = displayMetrics.heightPixels / displayMetrics.density
            val dpWidth = displayMetrics.widthPixels / displayMetrics.density
            val dpWidthProgress = mProgressbar.width / this.resources.displayMetrics.density

            val params = RelativeLayout.LayoutParams(
                    mThresholdBar.width,
                    mThresholdBar.height
            )

            if (mSpentAmount > mThreshold) { //in this case we have 2 progressbars
                mProgressbar.progress = calculateProgress(mSpentAmount).toInt()
                mProgressbar.secondaryProgress =  calculateProgress(mThreshold).toInt()
            } else if (mSpentAmount <= mThreshold) {
                mProgressbar.secondaryProgress = calculateProgress(mSpentAmount).toInt()
            }


            when {
                mThreshold == 0.toBigDecimal() -> {
                    params.setMargins(0, 0, 0, 0)

                    mThresholdBar.setLayoutParams(params)
                    mThresholdLeftTextTv.visibility = View.GONE
                    mThresholdBar.visibility = View.GONE
                    mThresholdRightTextTv.visibility = View.VISIBLE
                    mThresholdRightTextTv.text = "Soglia " + numberFormat.format(mThreshold) + " €"
                }
                (mThreshold > 0.toBigDecimal() && mThreshold <= mTotalAmount) -> {
                    var leftMargin = ((mThreshold.toDouble() - 1) * dpWidthProgress) / mTotalAmount.toDouble()

                    var px = (leftMargin) * (displayMetrics.densityDpi / 160f)

                    params.setMargins(px.toInt(), 0, 0, 0)
                    mThresholdBar.visibility = View.VISIBLE
                    mThresholdBar.setLayoutParams(params)

                    if (leftMargin <= dpWidthProgress / 2) {
                        mThresholdLeftTextTv.visibility = View.GONE
                        mThresholdRightTextTv.visibility = View.VISIBLE
                        mThresholdRightTextTv.text = "Soglia " + numberFormat.format(mThreshold) + " €"
                    } else {
                        mThresholdLeftTextTv.visibility = View.VISIBLE
                        mThresholdRightTextTv.visibility = View.GONE
                        mThresholdLeftTextTv.text = "Soglia " + numberFormat.format(mThreshold) + " €"
                    }
                }
                mThreshold > mTotalAmount -> {
                    var px = (dpWidthProgress - 1) * (displayMetrics.densityDpi / 160f)
                    params.setMargins(px.toInt(), 0, 0, 0)
                    mThresholdLeftTextTv.visibility = View.VISIBLE
                    mThresholdRightTextTv.visibility = View.GONE
                    mThresholdBar.visibility = View.VISIBLE
                    mThresholdLeftTextTv.text = "Soglia " + numberFormat.format(mThreshold) + " €"
                    mThresholdBar.setLayoutParams(params)
                }
            }
        }

    }

    fun calculateProgress(spentAmount: BigDecimal): BigDecimal {
        return (spentAmount.multiply(100.toBigDecimal())).divide(mTotalAmount, 2, RoundingMode.HALF_UP)
    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        if(mWidthMeasureSpec == 0 && mHeightMeasureSpec == 0) {
//            mWidthMeasureSpec = widthMeasureSpec
//            mHeightMeasureSpec = heightMeasureSpec
//
//            if (mSpentAmount > mThreshold) { //in this case we have 2 progressbars
//                mProgressbar.progress = calculateProgress(mSpentAmount).toInt()
//                mProgressbar.secondaryProgress =  calculateProgress(mThreshold).toInt()
//            } else if (mSpentAmount <= mThreshold) {
//                mProgressbar.secondaryProgress = calculateProgress(mSpentAmount).toInt()
//            }
//
//            val displayMetrics = this.resources.displayMetrics
//            val dpHeight = displayMetrics.heightPixels / displayMetrics.density
//            val dpWidth = displayMetrics.widthPixels / displayMetrics.density
//            val dpWidthProgress = mProgressbar.width / this.resources.displayMetrics.density
//
//            val params = RelativeLayout.LayoutParams(
//                    mThresholdBar.width,
//                    mThresholdBar.height
//            )
//
//
//            when {
//                mThreshold == 0.toBigDecimal() -> {
//                    params.setMargins(0, 0, 0, 0)
//
//                    mThresholdBar.setLayoutParams(params)
//                    mThresholdLeftTextTv.visibility = View.GONE
//                    mThresholdBar.visibility = View.GONE
//                    mThresholdRightTextTv.visibility = View.VISIBLE
//                    mThresholdRightTextTv.text = "Soglia " + numberFormat.format(mThreshold) + " €"
//                }
//                (mThreshold > 0.toBigDecimal() && mThreshold <= mTotalAmount) -> {
//                    var leftMargin = ((mThreshold.toDouble() - 1) * dpWidthProgress) / mTotalAmount.toDouble()
//
//                    var px = (leftMargin) * (displayMetrics.densityDpi / 160f)
//
//                    params.setMargins(px.toInt(), 0, 0, 0)
//                    mThresholdBar.visibility = View.VISIBLE
//                    mThresholdBar.setLayoutParams(params)
//
//                    if (leftMargin <= dpWidthProgress / 2) {
//                        mThresholdLeftTextTv.visibility = View.GONE
//                        mThresholdRightTextTv.visibility = View.VISIBLE
//                        mThresholdRightTextTv.text = "Soglia " + numberFormat.format(mThreshold) + " €"
//                    } else {
//                        mThresholdLeftTextTv.visibility = View.VISIBLE
//                        mThresholdRightTextTv.visibility = View.GONE
//                        mThresholdLeftTextTv.text = "Soglia " + numberFormat.format(mThreshold) + " €"
//                    }
//                }
//                mThreshold > mTotalAmount -> {
//                    var px = (dpWidthProgress - 1) * (displayMetrics.densityDpi / 160f)
//                    params.setMargins(px.toInt(), 0, 0, 0)
//                    mThresholdLeftTextTv.visibility = View.VISIBLE
//                    mThresholdRightTextTv.visibility = View.GONE
//                    mThresholdBar.visibility = View.VISIBLE
//                    mThresholdLeftTextTv.text = "Soglia " + numberFormat.format(mThreshold) + " €"
//                    mThresholdBar.setLayoutParams(params)
//                }
//            }
//        }
//    }



}