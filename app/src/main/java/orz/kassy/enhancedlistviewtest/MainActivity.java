package orz.kassy.enhancedlistviewtest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.LinkedList;


public class MainActivity extends ActionBarActivity {

    // 最初のリスト
    private static final String[] INITIAL_LIST = {
            "最初に", "表示される", "リストの", "項目で", "あります",
    };

    // リストビュー
    private ListView mListView;

    // リストビューに設定するリストとアダプター
    private LinkedList<String> mItemList;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // リストビュー
        mListView = (ListView) findViewById(R.id.listview1);

        // リストビューにアイテム追加
        mItemList = new LinkedList<String>();
        mItemList.addAll(Arrays.asList(INITIAL_LIST));
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mItemList);
        mListView.setAdapter(mAdapter);

    }
}
