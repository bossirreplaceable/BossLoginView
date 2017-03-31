package com.yobo.bossloginview;


import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdapterVPFragment adapterVP=new AdapterVPFragment(getSupportFragmentManager());
        vp = (ViewPager) findViewById(R.id.vp_main);
        vp.setAdapter(adapterVP);
        tab= (TabLayout) findViewById(R.id.tab_main);
        tab.setupWithViewPager(vp);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
    }


    public static class FragmentVP extends Fragment {
        public static final String POSITION = "position";
        public static FragmentVP getIntance(int position) {

            FragmentVP fragmentVP = new FragmentVP();
            Bundle args = new Bundle();
            args.putInt(POSITION, position);
            fragmentVP.setArguments(args);
            return fragmentVP;
        }
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            int position = getArguments().getInt(POSITION);
            View rootView = inflater.inflate(R.layout.activity_main, container, false);

            switch (position) {
                case 1:
                    rootView = inflater.inflate(R.layout.view1, container, false);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.view1, container, false);
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.view1, container, false);
                    break;
                case 4:
                    rootView = inflater.inflate(R.layout.view1, container, false);
                    break;
                case 5:
                    rootView = inflater.inflate(R.layout.view1, container, false);
                    break;
            }

            return rootView;
        }
    }

    public static class AdapterVPFragment extends FragmentPagerAdapter {

        public AdapterVPFragment(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentVP.getIntance(position + 1);
        }

        @Override
        public int getCount() {
            return 5;
        }
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {

                case 0:
                    return "1";
                case 1:
                    return "2";
                case 2:
                    return "3";
                case 3:
                    return "4";
                case 4:
                    return "5";

            }

            return null;
        }
    }


}
