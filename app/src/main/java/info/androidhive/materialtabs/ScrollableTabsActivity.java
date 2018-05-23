package info.androidhive.materialtabs;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.fragments.CODBCalculator;
import info.androidhive.materialtabs.fragments.DoFCalculator;
import info.androidhive.materialtabs.fragments.FiveFragment;
import info.androidhive.materialtabs.fragments.FlashRangeCalculator;
import info.androidhive.materialtabs.fragments.FourFragment;
import info.androidhive.materialtabs.fragments.OneFragment;
import info.androidhive.materialtabs.fragments.RegularCalculator;
import info.androidhive.materialtabs.fragments.SixFragment;
import info.androidhive.materialtabs.fragments.ThreeFragment;
import info.androidhive.materialtabs.fragments.TimeLapseCalculator;
import info.androidhive.materialtabs.fragments.TwoFragment;

public class ScrollableTabsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable_tabs);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "FoV");
        adapter.addFrag(new TwoFragment(), "Aspect Ratio");
        adapter.addFrag(new ThreeFragment(), "Star Trails");
        adapter.addFrag(new FourFragment(), "DoF");
        adapter.addFrag(new FiveFragment(), "Exposure");
        adapter.addFrag(new SixFragment(), "Print Quality");

        adapter.addFrag(new CODBCalculator(), "CODB");
        adapter.addFrag(new DoFCalculator(), "Hyperfocal");
        adapter.addFrag(new TimeLapseCalculator(), "Time Lapse");
        adapter.addFrag(new RegularCalculator(), "Regular");
        adapter.addFrag(new FlashRangeCalculator(), "Flash Range");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
