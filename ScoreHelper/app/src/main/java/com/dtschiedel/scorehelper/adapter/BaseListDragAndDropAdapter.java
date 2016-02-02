package com.dtschiedel.scorehelper.adapter;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.dtschiedel.scorehelper.util.ItemDragShadowBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by daniel.sousa on 02/02/2016.
 * <p/>
 * Description:
 */
public abstract class BaseListDragAndDropAdapter<T> extends BaseListAdapter<T> {

    public interface DropListener<T> {

        /**
         *
         * @param dropedItem item that was dragged and dropped
         * @param destinationItem item where the dragged item was dropped
         */
        void onDrop(T dropedItem, T destinationItem);
    }

    private static final String DRAG_ITEM = "DRAG_ITEM";

    private static final String ENTITY_MIME_TYPE = "object/com.dtschiedel.entity";

    private static final int DRAG_PADDING = 10;

    private float xLastTouchPosition = 0;
    private float yLastTouchPosition = 0;

    private DropListener<T> dropListener = null;

    public BaseListDragAndDropAdapter(Context context, List<T> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                doDrag(v);

                return true;
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    saveLastTouchPosition((int)event.getX(), (int)event.getY());
                }

                return false;
            }
        });

        view.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return doOnDrag(v, event);
            }
        });


        return view;
    }

    private void saveLastTouchPosition(int x, int y) {

        xLastTouchPosition = x;
        yLastTouchPosition = y;
    }

    private void doDrag(View v) {

        Intent intent = new Intent();

        intent.putExtra(DRAG_ITEM, (Serializable) v.getTag());

        ClipData.Item item = new ClipData.Item(intent);

        ClipData dragData = new ClipData(DRAG_ITEM,
                new String[]{ENTITY_MIME_TYPE}, item);

        ItemDragShadowBuilder shadow = new ItemDragShadowBuilder(v);

        shadow.setTouchPoint((int) xLastTouchPosition, (int) yLastTouchPosition);

        Log.d(this.getClass().getName(), "starting drag");

        v.startDrag(dragData, shadow, null, 0);
    }

    private boolean doOnDrag(View v, DragEvent event) {

        switch(event.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:

                if (event.getClipDescription().hasMimeType(ENTITY_MIME_TYPE)) {

                    return true;
                }
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:

                setViewDragLeftPadding(v, DRAG_PADDING);

                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                return true;
            case DragEvent.ACTION_DRAG_EXITED:

                setViewDragLeftPadding(v, -DRAG_PADDING);

                return true;
            case DragEvent.ACTION_DRAG_ENDED:


                return true;
            case DragEvent.ACTION_DROP:

                DoDrop(v, event);

                setViewDragLeftPadding(v, -DRAG_PADDING);

                return true;
        }

        return false;
    }

    private void DoDrop(View v, DragEvent event) {

        T destinationItem = (T) v.getTag();

        T draggedItem = (T) event.getClipData().getItemAt(0).getIntent()
                .getSerializableExtra(DRAG_ITEM);

        if (dropListener != null) {

            dropListener.onDrop(draggedItem, destinationItem);
        }

    }

    private void setViewDragLeftPadding(View view, int padding) {

        view.setPadding(view.getPaddingLeft() + padding,
                view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }

    public DropListener<T> getDropListener() {
        return dropListener;
    }

    public void setDropListener(DropListener<T> dropListener) {
        this.dropListener = dropListener;
    }
}
