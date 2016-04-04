package etong.bottomnavigation.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import etong.bottomnavigation.lib.BottomBarTab;
import etong.bottomnavigation.lib.BottomNavigationBar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationBar bottomLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mAdapter;

    private Fragment mMovieFragment;
    private Fragment mMusicFragment;
    private Fragment mBooksFragment;
    private Fragment mNewsstandFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Instant run");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        setUpBottomNavigationBar();




        initViewPager();



    }

    private void initViewPager(){
        mViewPager = (ViewPager)findViewById(R.id.container);

        mMovieFragment = MoviesFragment.newInstance();
        mMusicFragment = MusicFragment.newInstance();
        mBooksFragment = BooksFragment.newInstance();
        mNewsstandFragment = NewsstandFragment.newInstance();

        mFragmentList.add(mMovieFragment);
        mFragmentList.add(mMusicFragment);
        mFragmentList.add(mBooksFragment);
        mFragmentList.add(mNewsstandFragment);

        mAdapter = new FragmentAdapter(this.getSupportFragmentManager(),mFragmentList);

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomLayout.setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void setUpBottomNavigationBar() {

        bottomLayout = (BottomNavigationBar) findViewById(R.id.bottomLayout);
        bottomLayout.setTabWidthSelectedScale(1.5f);
        bottomLayout.setTextDefaultVisible(true);
//        bottomLayout.setTextColorResId(R.color.color_tab_text);
        bottomLayout.addTab(R.drawable.selector_movie, "Movies & Tv", 0xff4a5965);
        bottomLayout.addTab(R.drawable.selector_music, "Music", 0xff4a5965);
        bottomLayout.addTab(R.drawable.selector_books, "Books", 0xff4a5965);
        bottomLayout.addTab(R.drawable.selector_news, "Newsstand", 0xff4a5965);
        bottomLayout.setOnTabListener(new BottomNavigationBar.TabListener() {
            @Override
            public void onSelected(BottomBarTab tab, int position) {

                mViewPager.setCurrentItem(position);

            }
        });
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private class FragmentAdapter extends FragmentPagerAdapter{

        private List<Fragment> list = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm,List<Fragment> list){
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }


    }
}
