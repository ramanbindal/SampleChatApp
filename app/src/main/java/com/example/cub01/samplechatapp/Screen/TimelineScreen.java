package com.example.cub01.samplechatapp.Screen;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.example.cub01.samplechatapp.R;
import com.example.cub01.samplechatapp.View.TimelineView;
import com.example.cub01.samplechatapp.core.RootModule;
import com.example.cub01.samplechatapp.model.FriendStatus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import flow.Flow;
import flow.path.Path;
import mortar.ViewPresenter;

import static android.R.attr.path;

/**
 * Created by cub01 on 9/1/2017.
 */

@Layout(R.layout.timeline_view)
@WithModule(TimelineScreen.Module.class)
public class TimelineScreen extends Path {

    @dagger.Module(injects = TimelineView.class, addsTo = RootModule.class)
    public static class Module {
        @Provides
        List<FriendStatus> provideConversations() {
            ArrayList<FriendStatus> posts = new ArrayList<>();
            posts = new ArrayList<>();
            posts.add(new FriendStatus(1, "Hello"));
            posts.add(new FriendStatus(2, " hey"));
            posts.add(new FriendStatus(3, "hi"));

            return posts;
        }
    }

    @Singleton
    public static class Presenter extends ViewPresenter<TimelineView> {
        public List<FriendStatus> posts;

        @Inject
        Presenter(List<FriendStatus> posts) {
            this.posts = posts;
        }

        @Override
        public void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            if (!hasView()) return;
            getView().showConversations(posts);
        }

        public void onConversationSelected(int position) {
//              Flow.get(getView()).set(new ChatScreen(position));
        }
    }

}
