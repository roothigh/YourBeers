package cl.roothigh.yourbeers.views.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cl.roothigh.yourbeers.R;

/**
 * Created by SebastianVP on 27-11-2016.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private Context context;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public PagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return BeerListFragment.newInstance();
            case 1 :
                return FavoriteFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2 ;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //This is why we need the context, so we can get the string from the strings.xml file
        switch (position) {
            case 0:
                return context.getString(R.string.Beers_List);
            case 1:
                return context.getString(R.string.Favorites);

        }
        return null;
    }
}
