package it.octavianionel.designsupportlibrarysample;

import android.widget.TextView;

import java.math.BigDecimal;

/**
 * Created by Reply on 16/01/2019.
 */
public class ProgressbarModel {

    private String mTitle;
    private BigDecimal mAmount;
    private BigDecimal mSpentAmount;
    private BigDecimal mThreshold;

    public ProgressbarModel(String title, BigDecimal amount, BigDecimal spentAmount, BigDecimal threshold) {
        mTitle = title;
        mAmount = amount;
        mSpentAmount = spentAmount;
        mThreshold = threshold;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public BigDecimal getmAmount() {
        return mAmount;
    }

    public void setmAmount(BigDecimal mAmount) {
        this.mAmount = mAmount;
    }

    public BigDecimal getmSpentAmount() {
        return mSpentAmount;
    }

    public void setmSpentAmount(BigDecimal mSpentAmount) {
        this.mSpentAmount = mSpentAmount;
    }

    public BigDecimal getmThreshold() {
        return mThreshold;
    }

    public void setmThreshold(BigDecimal mThreshold) {
        this.mThreshold = mThreshold;
    }

}
