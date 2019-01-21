package it.octavianionel.designsupportlibrarysample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.MotionEvent;
import android.view.View;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_SWIPE;
import static android.support.v7.widget.helper.ItemTouchHelper.LEFT;
import static android.support.v7.widget.helper.ItemTouchHelper.RIGHT;

/**
 * Created by Reply on 19/01/2019.
 */
public class SwipeController extends Callback {

    public enum ButtonsState {
        GONE,
        LEFT_VISIBLE,
        RIGHT_VISIBLE,
        ACTION_ADD,
        ACTION_MODIFY
    }

    private boolean swipeBack = false;

    private ButtonsState buttonShowedState = ButtonsState.GONE;

    private RectF buttonInstance = null;

    private RecyclerView.ViewHolder currentItemViewHolder = null;

    private SwipeControllerActions buttonsActions = null;

    private static final float buttonWidth = 300;

    public SwipeController(SwipeControllerActions buttonsActions) {
        this.buttonsActions = buttonsActions;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, LEFT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        if (swipeBack) {
            swipeBack = buttonShowedState != ButtonsState.GONE;
            return 0;
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ACTION_STATE_SWIPE) {
            if (buttonShowedState != ButtonsState.GONE) {
//                if (buttonShowedState == ButtonsState.LEFT_VISIBLE) dX = Math.max(dX, buttonWidth);
                if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) dX = Math.min(dX, -buttonWidth);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
            else {
                setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }

        if (buttonShowedState == ButtonsState.GONE) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
        currentItemViewHolder = viewHolder;
    }

    private void setTouchListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
                if (swipeBack) {
                    if (dX < -buttonWidth) buttonShowedState = ButtonsState.RIGHT_VISIBLE;
//                    else if (dX > buttonWidth) buttonShowedState  = ButtonsState.LEFT_VISIBLE;

                    if (buttonShowedState != ButtonsState.GONE) {
                        setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                        setItemsClickable(recyclerView, false);
                    }
                }
                return false;
            }
        });
    }

    private void setTouchDownListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setTouchUpListener(c, recyclerView, viewHolder, dX / 4, dY, actionState, isCurrentlyActive);
                }
                return false;
            }
        });
    }

    private void setTouchUpListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    SwipeController.super.onChildDraw(c, recyclerView, viewHolder, 0F, dY, actionState, isCurrentlyActive);
                    recyclerView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return false;
                        }
                    });
                    setItemsClickable(recyclerView, true);
                    swipeBack = false;

                    if (buttonsActions != null && buttonInstance != null && buttonInstance.contains(event.getX(), event.getY())) {
//                        if (buttonShowedState == ButtonsState.LEFT_VISIBLE) {
//                            buttonsActions.onLeftClicked(viewHolder.getAdapterPosition());
//                        }
//                        else
                            if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) {
                            buttonsActions.onRightClicked(viewHolder.getAdapterPosition());
                        }
                    }
                    buttonShowedState = ButtonsState.GONE;
                    currentItemViewHolder = null;
                }
                return false;
            }
        });
    }

    private void setItemsClickable(RecyclerView recyclerView, boolean isClickable) {
        for (int i = 0; i < recyclerView.getChildCount(); ++i) {
            recyclerView.getChildAt(i).setClickable(isClickable);
        }
    }

    private void drawButtons(Context context, Canvas c, RecyclerView.ViewHolder viewHolder, RVAdapterProgressbar adapter, int swipeButtonBackgroundColor, int addIconDrawable, int modifyIconDrawable) {
        float buttonWidthWithoutPadding = buttonWidth - 30;
        float corners = 0;

        View itemView = viewHolder.itemView;
        Paint p = new Paint();
//        RectF leftButton = new RectF(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + buttonWidthWithoutPadding, itemView.getBottom());
//        p.setColor(Color.BLUE);
//        c.drawRoundRect(leftButton, corners, corners, p);
//        drawText("EDIT", c, leftButton, p);
        RectF rightButton = new RectF(itemView.getRight() - buttonWidthWithoutPadding, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        p.setColor(swipeButtonBackgroundColor);
        c.drawRoundRect(rightButton, corners, corners, p);
//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_plus_green);
//        c.drawBitmap(bitmap, (bitmap.getWidth()) / 4, 250, p);
        if (viewHolder.getAdapterPosition() % 2 == 0) {
            drawText(context, viewHolder, adapter,"ADD", c, rightButton, p, addIconDrawable, modifyIconDrawable);
        } else {
            drawText(context, viewHolder, adapter,"MODIFY", c, rightButton, p, addIconDrawable, modifyIconDrawable);
        }
//        if (action.equals(ButtonsState.ACTION_ADD)) {
//            drawText("ADD", c, rightButton, p);
//        } else if (action.equals(ButtonsState.ACTION_MODIFY)) {
//            drawText("MODIFY", c, rightButton, p);
//        }

        buttonInstance = null;
//        if (buttonShowedState == ButtonsState.LEFT_VISIBLE) {
//            buttonInstance = leftButton;
//        }
//        else
            if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) {
            buttonInstance = rightButton;
        }
    }

    private void drawText(Context context, RecyclerView.ViewHolder viewHolder, RVAdapterProgressbar adapter, String text, Canvas c, RectF button, Paint p, int addIconDrawable, int modifyIconDrawable) {
        float textSize = 30;
        p.setColor(Color.WHITE);
        p.setAntiAlias(true);
        p.setTextSize(textSize);

        float textWidth = p.measureText(text);
        float spaceHeight = 10; // change this to whatever looks good to you
        Rect bounds = new Rect();
        p.getTextBounds(text, 0, text.length(), bounds);
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inMutable = true;
        Bitmap bitmapAdd = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), addIconDrawable, opt), 70, 70, false);
        Bitmap bitmapModify = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), modifyIconDrawable, opt), 70, 70, false);
        if (viewHolder.getAdapterPosition() % 2 == 0) {
            float combinedHeight = bitmapAdd.getHeight() + spaceHeight + bounds.height();
            c.drawBitmap(bitmapAdd, button.centerX() - (bitmapAdd.getWidth() / 2), button.top + spaceHeight, p);
            c.drawText(text, button.centerX() - (textWidth / 2), button.bottom - spaceHeight, p);
        } else {
            float combinedHeight = bitmapModify.getHeight() + spaceHeight + bounds.height();
            c.drawBitmap(bitmapModify, button.centerX() - (bitmapModify.getWidth() / 2), button.top + spaceHeight, p);
            c.drawText(text, button.centerX() - (textWidth / 2), button.bottom - spaceHeight, p);
        }


    }

    public void onDraw(Context context, Canvas c, RVAdapterProgressbar adapter, int swipeButtonBackgroundColor, int addIconDrawable, int modifyIconDrawable) {
        if (currentItemViewHolder != null) {
            drawButtons(context, c, currentItemViewHolder, adapter, swipeButtonBackgroundColor, addIconDrawable, modifyIconDrawable);
        }
    }
}
