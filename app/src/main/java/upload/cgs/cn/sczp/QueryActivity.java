package upload.cgs.cn.sczp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by CGS on 2016/12/23.
 */
public class QueryActivity extends Activity{
    private ListView scQueryLv;
    private List<ProcessBean> mpbList=new ArrayList<ProcessBean>();
    private MyBaseAdapter myadapter;
    private BmobQuery<ProcessBean> queryAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylist_layout);
        Bmob.initialize(this,"f7c0b060aadf4a5a7a3875aa3f79cd6d");
        initView();
        initDatas();
    }
    private void initView() {
        scQueryLv= (ListView) findViewById(R.id.mylv);
        myadapter=new MyBaseAdapter(mpbList,QueryActivity.this);
    }
    private void initDatas() {
        queryAll=new BmobQuery<ProcessBean>();
                    queryAll.order("-createdAt");
                    queryAll.findObjects(new FindListener<ProcessBean>() {
                        @Override
                        public void done(List<ProcessBean> list, BmobException e) {
                            if(e==null){
                    mpbList=list;
                    myadapter=new MyBaseAdapter(mpbList,QueryActivity.this);
                    scQueryLv.setAdapter(myadapter);
                }else{
                    Toast.makeText(QueryActivity.this,"查询不到数据",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
