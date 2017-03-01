package myapp.bwie.com.newstoplihui.activity.channelManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import myapp.bwie.com.newstoplihui.R;
import myapp.bwie.com.newstoplihui.activity.channelManager.helper.ChannelEntity;
import myapp.bwie.com.newstoplihui.activity.channelManager.helper.ItemDragHelperCallback;
import myapp.bwie.com.newstoplihui.utils.Title;

public class ChannelManager extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecy;
    private ImageView cancelCM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_manager);

        initOnclicklistener();
        init();
    }

    private void initOnclicklistener() {
        mRecy = (RecyclerView) findViewById(R.id.recy);
        mRecy.setOnClickListener(this);
        cancelCM = (ImageView) findViewById(R.id.cancelChannerManager);
        cancelCM.setOnClickListener(this);
    }

    private void init() {

        final List<ChannelEntity> items = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            ChannelEntity entity = new ChannelEntity();
            entity.setName(new Title().getTitle()[i]);
            items.add(entity);
        }
        final List<ChannelEntity> otherItems = new ArrayList<>();
        for (int i = 18; i < new Title().getTitle().length; i++) {
            ChannelEntity entity = new ChannelEntity();
            entity.setName(new Title().getTitle()[i]);
            otherItems.add(entity);
        }

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecy.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecy);

        final ChannelAdapter adapter = new ChannelAdapter(this, helper, items, otherItems);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == ChannelAdapter.TYPE_MY || viewType == ChannelAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        mRecy.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recy:

                break;
            case R.id.cancelChannerManager:
                finish();
                break;
        }
    }
}
