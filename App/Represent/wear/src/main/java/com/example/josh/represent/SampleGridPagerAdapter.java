package com.example.josh.represent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridPagerAdapter;
import android.view.Gravity;
import android.view.View;

import java.util.List;

/**
 * Created by Josh on 3/2/2016.
 */
public class SampleGridPagerAdapter extends FragmentGridPagerAdapter {

//    private final Context mContext;
//    private List mRows;

//    public SampleGridPagerAdapter(Context ctx, FragmentManager fm) {
//        super(fm);
//        mContext = ctx;
//    }
//
//    static final int[] BG_IMAGES = new int[] {R.drawable.party, R.drawable.president};
//
//    // A simple container for static data in each page
//    private static class Page {
//        // static resources
//        int titleRes;
//        int textRes;
//        int iconRes;
//
//        public Page(int titleRes, int textRes, int iconRes) {
//            this.titleRes = titleRes;
//            this.textRes = textRes;
//            this.iconRes = iconRes;
//        }
//    }

    // Create a static set of pages in a 2D array
//    private final Page[][] PAGES = {{new Page(R.string.app_name, R.string.hello_round, R.drawable.president), new Page(R.string.app_name, R.string.hello_square, R.drawable.party)}};

    // Obtain the UI fragment at the specified position
//    @Override
//    public Fragment getFragment(int row, int col) {
//        Page page = PAGES[row][col];
//        String title =
//                page.titleRes != 0 ? mContext.getString(page.titleRes) : null;
//        String text =
//                page.textRes != 0 ? mContext.getString(page.textRes) : null;
//        CardFragment fragment = CardFragment.create(title, text, page.iconRes);

        // Advanced settings (card gravity, card expansion/scrolling)
//        fragment.setCardGravity(page.cardGravity);
//        fragment.setExpansionEnabled(page.expansionEnabled);
//        fragment.setExpansionDirection(page.expansionDirection);
//        fragment.setExpansionFactor(page.expansionFactor);
//        return fragment;
//    }

    // Obtain the background image for the specific page
//    @Override
//    public Drawable getBackgroundForPage(int row, int column) {
//        if( row == 2 && column == 0) {
//            // Place image at specified position
//            return mContext.getResources().getDrawable(R.drawable.party, null);
//        } else {
//            // Default to background image for row
//            return GridPagerAdapter.BACKGROUND_NONE;
//        }
//    }

    // Obtain the background image for the row
//    @Override
//    public Drawable getBackgroundForRow(int row) {
//        return mContext.getResources().getDrawable(
//                (BG_IMAGES[row % BG_IMAGES.length]), null);
//    }

    // Obtain the number of pages (vertical)
//    @Override
//    public int getRowCount() {
//        return PAGES.length;
//    }
//
//    // Obtain the number of pages (horizontal)
//    @Override
//    public int getColumnCount(int rowNum) {
//        return PAGES[rowNum].length;
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object o) {
//        return view.equals(o);
//    }


    MainActivity.Page[][] mData;

    public SampleGridPagerAdapter(FragmentManager fm, MainActivity.Page[][] data) {
        super(fm);
        mData = data;
    }

    @Override
    public Fragment getFragment(int row, int column) {
        CardFragSub card = new CardFragSub();
        return (card.create(mData[row][column].title, mData[row][column].obama, mData[row][column].romney));
    }

    @Override
    public int getRowCount() {
        return mData.length;
    }

    @Override
    public int getColumnCount(int row) {
        return mData[row].length;
    }
}