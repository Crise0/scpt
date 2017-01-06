package upload.cgs.cn.sczp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CGS on 2017/1/5.
 */

public class SelectShowImageView extends Activity implements View.OnClickListener{
    private ViewPager pager;
    private List<View> pagerList;
    private MyPagerAdapter adapter;
    private ImageView backImg;
    private TextView imgeCount;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_imageview);
        initView();
        initDatas();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        pager = (ViewPager) findViewById(R.id.pager);
        pagerList = new ArrayList<>();

    }

    private void initDatas() {
        Intent i = getIntent();
        Bundle b = i.getExtras();
        ProcessBean p = (ProcessBean) b.getSerializable("myperson");
        final int a = p.getFileCount();
        switch (a) {
            case 1:
                View mview6 = View.inflate(this, R.layout.select_imageview_item, null);
                ImageView img6 = (ImageView) mview6.findViewById(R.id.select_image_item);
                Glide.with(this).load(p.getFile5().getFileUrl()).fitCenter().into(img6);
                backImg= (ImageView) mview6.findViewById(R.id.backImg);
                backImg.setOnClickListener(this);
                imgeCount= (TextView)mview6. findViewById(R.id.imageCount);
                imgeCount.setText((2-a)+"/"+(6-a+1)+"");
                pagerList.add(mview6);
            case 2:
                View mview5 = View.inflate(this, R.layout.select_imageview_item, null);
                ImageView img5 = (ImageView) mview5.findViewById(R.id.select_image_item);
                Glide.with(this).load(p.getFile4().getFileUrl()).fitCenter().into(img5);
                backImg= (ImageView) mview5.findViewById(R.id.backImg);
                backImg.setOnClickListener(this);
                imgeCount= (TextView)mview5. findViewById(R.id.imageCount);
                imgeCount.setText((3-a)+"/"+(6-a+1)+"");
                pagerList.add(mview5);

            case 3:
                View mview4 = View.inflate(this, R.layout.select_imageview_item, null);
                ImageView img4 = (ImageView) mview4.findViewById(R.id.select_image_item);
                Glide.with(this).load(p.getFile3().getFileUrl()).fitCenter().into(img4);
                backImg= (ImageView) mview4.findViewById(R.id.backImg);
                backImg.setOnClickListener(this);
                imgeCount= (TextView)mview4. findViewById(R.id.imageCount);
                imgeCount.setText((4-a)+"/"+(6-a+1)+"");
                pagerList.add(mview4);
            case 4:
                View mview3 = View.inflate(this, R.layout.select_imageview_item, null);
                ImageView img3 = (ImageView) mview3.findViewById(R.id.select_image_item);
                Glide.with(this).load(p.getFile2().getFileUrl()).fitCenter().into(img3);
                backImg= (ImageView) mview3.findViewById(R.id.backImg);
                backImg.setOnClickListener(this);
                imgeCount= (TextView)mview3. findViewById(R.id.imageCount);
                imgeCount.setText((5-a)+"/"+(6-a+1)+"");
                pagerList.add(mview3);
            case 5:
                View mview2 = View.inflate(this, R.layout.select_imageview_item, null);
                ImageView img2 = (ImageView) mview2.findViewById(R.id.select_image_item);
                Glide.with(this).load(p.getFile1().getFileUrl()).fitCenter().into(img2);
                backImg= (ImageView) mview2.findViewById(R.id.backImg);
                backImg.setOnClickListener(this);
                imgeCount= (TextView)mview2. findViewById(R.id.imageCount);
                imgeCount.setText((6-a)+"/"+(6-a+1)+"");
                pagerList.add(mview2);
            case 6:
                View mview1 = View.inflate(this, R.layout.select_imageview_item, null);
                ImageView img1 = (ImageView) mview1.findViewById(R.id.select_image_item);
                Glide.with(this).load(p.getFile().getFileUrl()).fitCenter().into(img1);
                backImg= (ImageView) mview1.findViewById(R.id.backImg);
                backImg.setOnClickListener(this);
                imgeCount= (TextView)mview1. findViewById(R.id.imageCount);
                imgeCount.setText(7-a+"/"+(6-a+1)+"");
                pagerList.add(mview1);
                break;
            default:
                break;
        }
        pager.setPageTransformer(true,new DepthPageTransformer());
        adapter = new MyPagerAdapter(pagerList);
        pager.setAdapter(adapter);
  }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SelectShowImageView Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
