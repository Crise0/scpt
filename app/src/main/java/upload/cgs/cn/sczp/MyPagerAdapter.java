package upload.cgs.cn.sczp;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by CGS on 2017/1/5.
 */

public class MyPagerAdapter extends PagerAdapter {
    private List<View>pagerList;
    public MyPagerAdapter(List<View>pagerList){
        this.pagerList=pagerList;
    }
    @Override
    public int getCount() {
        return pagerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(pagerList.get(position));
        return pagerList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
         container.removeView(pagerList.get(position));
    }
}
