package com.example.justin_chen.drawer;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int GRID_COLUMN_SPAN_COUNT = 2;

    private RecyclerView mRecyclerView;
    private HomeListAdapter mHomeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        initRecycleView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.loadPhoto();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
    }

    private void initRecycleView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mHomeListAdapter = new HomeListAdapter(this);
        mRecyclerView.setAdapter(mHomeListAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, GRID_COLUMN_SPAN_COUNT));
    }

    private void loadPhoto() {
        ArrayList<String> photoList = new ArrayList<>();
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211543_730.jpg/TOP07719239.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211529_719.jpg/TOP07719228.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211531_720.jpg/TOP07719229.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211519_711.jpg/TOP07719220.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211510_704.jpg/TOP07719213.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211533_722.jpg/TOP07719231.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211538_726.jpg/TOP07719235.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211523_714.jpg/TOP07719223.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211512_705.jpg/TOP07719214.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211504_699.jpg/TOP07719208.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211543_730.jpg/TOP07719239.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211529_719.jpg/TOP07719228.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211531_720.jpg/TOP07719229.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211519_711.jpg/TOP07719220.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211510_704.jpg/TOP07719213.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211533_722.jpg/TOP07719231.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211538_726.jpg/TOP07719235.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211523_714.jpg/TOP07719223.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211512_705.jpg/TOP07719214.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211504_699.jpg/TOP07719208.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211543_730.jpg/TOP07719239.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211529_719.jpg/TOP07719228.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211531_720.jpg/TOP07719229.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211519_711.jpg/TOP07719220.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211510_704.jpg/TOP07719213.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211533_722.jpg/TOP07719231.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211538_726.jpg/TOP07719235.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211523_714.jpg/TOP07719223.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211512_705.jpg/TOP07719214.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211504_699.jpg/TOP07719208.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211543_730.jpg/TOP07719239.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211529_719.jpg/TOP07719228.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211531_720.jpg/TOP07719229.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211519_711.jpg/TOP07719220.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211510_704.jpg/TOP07719213.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211533_722.jpg/TOP07719231.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211538_726.jpg/TOP07719235.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211523_714.jpg/TOP07719223.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211512_705.jpg/TOP07719214.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211504_699.jpg/TOP07719208.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211543_730.jpg/TOP07719239.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211529_719.jpg/TOP07719228.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211531_720.jpg/TOP07719229.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211519_711.jpg/TOP07719220.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211510_704.jpg/TOP07719213.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211533_722.jpg/TOP07719231.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211538_726.jpg/TOP07719235.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211523_714.jpg/TOP07719223.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211512_705.jpg/TOP07719214.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211504_699.jpg/TOP07719208.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211543_730.jpg/TOP07719239.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211529_719.jpg/TOP07719228.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211531_720.jpg/TOP07719229.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211519_711.jpg/TOP07719220.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211510_704.jpg/TOP07719213.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211533_722.jpg/TOP07719231.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211538_726.jpg/TOP07719235.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211523_714.jpg/TOP07719223.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211512_705.jpg/TOP07719214.jpg");
        photoList.add("http://58.86.56.5/mid2_com_img/c3RvY2tfZm9vZA==/20110523_rm5/2011052321/top_20110523211504_699.jpg/TOP07719208.jpg");

        mHomeListAdapter.updateList(photoList);
    }
}
