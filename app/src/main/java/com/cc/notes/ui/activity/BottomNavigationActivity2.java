package com.cc.notes.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.cc.notes.adapter.BottomnavigationViewPagerAdapter;
import com.cc.notes.ui.fragment.Fragment1;
import com.cc.notes.ui.fragment.Fragment2;
import com.cc.notes.ui.fragment.Fragment3;
import com.cc.notes.utils.BottomNavigationViewHelper;
import com.notes.cc.notes.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BottomNavigationActivity2 extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,ViewPager.OnPageChangeListener,ViewPager.OnTouchListener{

//    @BindView(R.id.fl_fragment)
//    FrameLayout flFragment;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    Unbinder unbinder;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
//    private Fragment fragment_now = null;
    BottomnavigationViewPagerAdapter pagerAdapter;
    List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation2);
        unbinder = ButterKnife.bind(this);
        inint();
    }

    @SuppressLint("NewApi")
    private void inint() {

        fragments = new ArrayList<>();
        fragment1 = Fragment1.newInstance();
        fragment2 = Fragment2.newInstance();
        fragment3 = Fragment3.newInstance();
        if(!fragments.contains(fragment1)){
            fragments.add(fragment1);
        }
        if(!fragments.contains(fragment2)){
            fragments.add(fragment2);
        }
        if(!fragments.contains(fragment3)){
            fragments.add(fragment3);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(this);//设置 NavigationItemSelected 事件监听
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);//改变 BottomNavigationView 默认的效果
        //选中第一个item,对应第一个fragment
        bottomNavigationView.setSelectedItemId(R.id.item_1);

        pagerAdapter = new BottomnavigationViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);

        // 如果想禁止滑动，可以把下面的代码取消注释
//        viewPager.setOnTouchListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    //NavigationItemSelected 事件监听
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        changePageFragment(item.getItemId());
        switch (item.getItemId()){
            case  R.id.item_1:
                viewPager.setCurrentItem(0);
                break;
            case  R.id.item_2:
                viewPager.setCurrentItem(1);
                break;
            case  R.id.item_3:
                viewPager.setCurrentItem(3);
                break;
        }
        return true;
    }

//    /**
//     * 当点击导航栏时改变fragment
//     *
//     * @param id
//     */
//    public void changePageFragment(int id) {
//        switch (id) {
//            case R.id.item_1:
//                if (fragment1 == null) { //减少new fragmnet,避免不必要的内存消耗
//                    fragment1 = Fragment1.newInstance();
//                }
//                    switchFragment(fragment_now, fragment1);
//                break;
//            case R.id.item_2:
//                if (fragment2 == null) {
//                    fragment2 = Fragment2.newInstance();
//                }
//                    switchFragment(fragment_now, fragment2);
//                break;
//            case R.id.item_3:
//                if (fragment3 == null) {
//                    fragment3 = Fragment3.newInstance();
//                }
//                    switchFragment(fragment_now, fragment3);
//                break;
//        }
//    }

//    /**
//     * 隐藏显示fragment
//     *
//     * @param from 需要隐藏的fragment
//     * @param to   需要显示的fragment
//     */
//    public void switchFragment(Fragment from, Fragment to) {
//        if (to == null)
//            return;
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        if (!to.isAdded()) {
//            if (from == null) {
//                transaction.add(R.id.fl_fragment, to).show(to).commit();
//            } else {
//                // 隐藏当前的fragment，add下一个fragment到Activity中
//                transaction.hide(from).add(R.id.fl_fragment, to).commitAllowingStateLoss();
//            }
//        } else {
//            // 隐藏当前的fragment，显示下一个
//            transaction.hide(from).show(to).commit();
//        }
//        fragment_now = to;
//    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

}
