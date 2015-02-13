package orz.kassy.enhancedlistviewtest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.timroes.android.listview.EnhancedListView;


public class MainActivity extends ActionBarActivity {

    // 最初のリスト
    private static final String[] INITIAL_LIST = {
            "最初に", "表示される", "リストの", "項目で", "あります",
    };

    // リストビュー
    private EnhancedListView mListView;

    // リストビューに設定するリストとアダプター
    private LinkedList<String> mItemList;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // リストビュー
        mListView = (EnhancedListView) findViewById(R.id.listview1);

        // リストビューにアイテム追加
        mItemList = new LinkedList<String>();
        mItemList.addAll(Arrays.asList(INITIAL_LIST));
        // mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mItemList);
        mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.text, mItemList);
        mListView.setAdapter(mAdapter);

        // スワイプで消す設定
        mListView.setDismissCallback(new de.timroes.android.listview.EnhancedListView.OnDismissCallback() {
            /**
             * This method will be called when the user swiped a way or deleted it via
             * {@link de.timroes.android.listview.EnhancedListView#delete(int)}.
             *
             * @param listView The {@link EnhancedListView} the item has been deleted from.
             * @param position The position of the item to delete from your adapter.
             * @return An {@link de.timroes.android.listview.EnhancedListView.Undoable}, if you want
             *      to give the user the possibility to undo the deletion.
             */
            @Override
            public EnhancedListView.Undoable onDismiss(EnhancedListView listView, final int position) {

                final String item = (String) mAdapter.getItem(position);
                /// 消す処理
                mItemList.remove(position);
                mAdapter.notifyDataSetChanged();
                return new EnhancedListView.Undoable() {
                    @Override
                    public void undo() {
                        mItemList.add(position, item);
                        mAdapter.notifyDataSetChanged();
                    }
                };
            }
        });
        mListView.enableSwipeToDismiss();
    }
}
