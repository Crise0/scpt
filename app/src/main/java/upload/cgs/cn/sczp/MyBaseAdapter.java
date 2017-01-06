package upload.cgs.cn.sczp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CGS on 2016/12/23.
 */
public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<ProcessBean> mList=new ArrayList<>();
    private LayoutInflater mInflater;
    public MyBaseAdapter(){

    }
    public MyBaseAdapter(List<ProcessBean> mList,Context context){
        this.mList=mList;
        this.context=context;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.mylist_item,null);
            holder=new ViewHolder();
            //holder.hv= (HorizontalScrollView) convertView.findViewById(R.id.hzsv);
            holder.tv1= (TextView) convertView.findViewById(R.id.tv1);
            holder.tv2= (TextView) convertView.findViewById(R.id.tv2);
            mInflater = LayoutInflater.from(context);
            holder.mgallery= (LinearLayout) convertView.findViewById(R.id.id_gallery);
            holder.hv= (HorizontalScrollView) convertView.findViewById(R.id.hzsv);
//            for(int i:guideImage){
//                View mview=mInflater.inflate(R.layout.activity_index_gallery,holder.mgallery,false);
//                ImageView img= (ImageView) mview.findViewById(R.id.id_index_gallery_item_image);
//                img.setTag(i);
//                holder.mgallery.addView(mview);
//            }
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        final ProcessBean p=mList.get(position);
        holder.tv1.setText("姓名："+p.getName());
        holder.tv2.setText("密码："+p.getPassword());
        holder.mgallery.removeAllViews();
        final int a=p.getFileCount();
        switch (a){
            case 1:View mview6 = mInflater.inflate(R.layout.activity_index_gallery, holder.mgallery, false);
                ImageView img6 = (ImageView) mview6.findViewById(R.id.id_index_gallery_item_image);
                Glide.with(context).load(p.getFile5().getFileUrl()).fitCenter().into(img6);
                holder.mgallery.addView(mview6);

            case 2:View mview5 = mInflater.inflate(R.layout.activity_index_gallery, holder.mgallery, false);
                ImageView img5 = (ImageView) mview5.findViewById(R.id.id_index_gallery_item_image);
                Glide.with(context).load(p.getFile4().getFileUrl()).fitCenter().into(img5);
                holder.mgallery.addView(mview5);

            case 3:View mview4 = mInflater.inflate(R.layout.activity_index_gallery, holder.mgallery, false);
                ImageView img4 = (ImageView) mview4.findViewById(R.id.id_index_gallery_item_image);
                Glide.with(context).load(p.getFile3().getFileUrl()).fitCenter().into(img4);
                holder.mgallery.addView(mview4);
            case 4:View mview3 = mInflater.inflate(R.layout.activity_index_gallery, holder.mgallery, false);
                ImageView img3 = (ImageView) mview3.findViewById(R.id.id_index_gallery_item_image);
                Glide.with(context).load(p.getFile2().getFileUrl()).fitCenter().into(img3);
                holder.mgallery.addView(mview3);
            case 5: View mview2 = mInflater.inflate(R.layout.activity_index_gallery, holder.mgallery, false);
                ImageView img2 = (ImageView) mview2.findViewById(R.id.id_index_gallery_item_image);
                Glide.with(context).load(p.getFile1().getFileUrl()).fitCenter().into(img2);
                holder.mgallery.addView(mview2);
            case 6:  View mview = mInflater.inflate(R.layout.activity_index_gallery, holder.mgallery, false);
                ImageView img = (ImageView) mview.findViewById(R.id.id_index_gallery_item_image);
                Glide.with(context).load(p.getFile().getFileUrl()).fitCenter().into(img);
                holder.mgallery.addView(mview);break;
            default:break;
        }
        holder.mgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,SelectShowImageView.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("myperson",p);
                intent.putExtras(bundle);
                ((Context)context).startActivity(intent);
                Toast.makeText(context,"hv被点击",Toast.LENGTH_SHORT).show();
            }
        });
//       if(p.getFile().getFileUrl()!=null) {
//            View mview = mInflater.inflate(R.layout.activity_index_gallery, holder.mgallery, false);
//            ImageView img = (ImageView) mview.findViewById(R.id.id_index_gallery_item_image);
//            Glide.with(context).load(p.getFile().getFileUrl()).fitCenter().into(img);
//        holder.mgallery.addView(mview);
//       }
//        if(p.getFile1().getFileUrl()!=null) {
//            View mview = mInflater.inflate(R.layout.activity_index_gallery, holder.mgallery, false);
//            ImageView img = (ImageView) mview.findViewById(R.id.id_index_gallery_item_image);
//            Glide.with(context).load(p.getFile1().getFileUrl()).fitCenter().into(img);
//            holder.mgallery.addView(mview);
//        }
        return convertView;

    }
    class ViewHolder{
        TextView tv1,tv2;
        HorizontalScrollView hv;
        LinearLayout mgallery;
    }
}
