package com.example.cub01.samplechatapp.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cub01.samplechatapp.ObjectGraphService;
import com.example.cub01.samplechatapp.Screen.TimelineScreen;
import com.example.cub01.samplechatapp.model.FriendStatus;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by cub01 on 9/1/2017.
 */

public class TimelineView extends ListView {
        @Inject
        TimelineScreen.Presenter presenter;

        public TimelineView(Context context, AttributeSet attrs) {
            super(context, attrs);
            ObjectGraphService.inject(context, this);
        }

        @Override protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            presenter.takeView(this);
        }

        @Override protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            presenter.dropView(this);
        }

        public void showConversations(List<FriendStatus> posts) {
            Adapter adapter = new Adapter(getContext(), posts);

            setAdapter(adapter);
            setOnItemClickListener(new OnItemClickListener() {
                @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    presenter.onConversationSelected(position);
                }
            });
        }

        private static class Adapter extends ArrayAdapter<FriendStatus> {
            public Adapter(Context context, List<FriendStatus> objects) {
                super(context, android.R.layout.simple_list_item_1, objects);
            }
        }
    }



