package upload.cgs.cn.sczp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;

public class MainActivity extends AppCompatActivity {
    private GridView gridView1;
    private Button buttonPublish;
    private final int IMAGE_OPEN = 4;
    private final int GET_DATA = 2;
    private final int TAKE_PHOTO = 3;
    private final int MY_PERMISSIONS_REQUEST_CALL_PHONE2=5;
    private final int MY_PERMISSIONS_REQUEST_CALL_PHONE=6;
    private String pathImage;
    private Bitmap bmp;
    private Uri imageUri;
    private String pathTakePhoto;
    private List<String> mphotopath=new ArrayList<>();
    private String[] urlPicture;
    private ArrayList<HashMap<String, Object>> imageItem;
    private SimpleAdapter simpleAdapter;
    private Button btn_query;
    ProcessBean process=new ProcessBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this,"f7c0b060aadf4a5a7a3875aa3f79cd6d");
        initViews();
        initDatas2();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }



    private void initViews(){
        gridView1=(GridView)findViewById(R.id.gridView1);
        buttonPublish= (Button) findViewById(R.id.button1);
        btn_query= (Button) findViewById(R.id.btn_query);
    }
    //上传一条信息一个图片文件
  /*  private void initDatas() {
        buttonPublish.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("unused")
            @Override
            public void onClick(View v) {
                if(imageItem.size()==1){
                    Toast.makeText(MainActivity.this,"沒有圖片需要上傳",Toast.LENGTH_LONG).show();
                    return ;
                }
                String photo1=null;
                for(String photo2 : mphotopath){
                    photo1=photo2;
                }
                File file= new File(photo1);
                if(file!=null){
                    final BmobFile bmobFile=new BmobFile(file);
                    final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("正在上传...");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();
                    bmobFile.uploadblock(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                process.setName("aaa");
                                process.setPassword("222");
                                process.setFile(bmobFile);
                                process.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        progressDialog.dismiss();
                                        Toast.makeText(MainActivity.this, "上传文件成功" + bmobFile.getFileUrl(), Toast.LENGTH_SHORT). show();
                                    }
                                });

                            } else {
                                Toast.makeText(MainActivity.this, "上传文件失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                Toast.makeText(MainActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
            }
        });
    }*/
    //上传一条信息多个图片文件
    private void initDatas2() {
        buttonPublish.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("unused")
            @Override
            public void onClick(View v) {
                if (imageItem.size() == 1) {
                    Toast.makeText(MainActivity.this, "沒有圖片需要上傳", Toast.LENGTH_LONG).show();
                    return;
                }
                final String[] photo1 = new String[mphotopath.size()];
                int i = 0;
                for (String photo2 : mphotopath) {
                    photo1[i++] = photo2;
                }
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("正在上传...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                Bmob.uploadBatch(photo1, new UploadBatchListener() {
                    @Override
                    public void onSuccess(List<BmobFile> list, List<String> list1) {
                        if (list1.size() == photo1.length) {
                        switch (photo1.length){
                            case 1: ProcessBean process1 = new ProcessBean(list.get(0));
                                process1.setName("cc");
                                process1.setPassword("0000");
                                process1.setFileCount(6);
                                insertObject(process1);
                                break;
                            case 2: ProcessBean process2 = new ProcessBean(list.get(0), list.get(1));
                                process2.setName("cc");
                                process2.setPassword("0000");
                                process2.setFileCount(5);
                                insertObject(process2);;
                                break;
                            case 3: ProcessBean process3 = new ProcessBean(list.get(0), list.get(1),list.get(2));
                                process3.setName("cc");
                                process3.setPassword("0000");
                                process3.setFileCount(4);
                                insertObject(process3);
                                break;
                            case 4: ProcessBean process4 = new ProcessBean(list.get(0), list.get(1),list.get(2),list.get(3));
                                process4.setName("cc");
                                process4.setPassword("0000");
                                process4.setFileCount(3);
                                insertObject(process4);
                                break;
                            case 5: ProcessBean process5 = new ProcessBean(list.get(0), list.get(1),list.get(2),list.get(3),list.get(4));
                                process5.setName("cc");
                                process5.setPassword("0000");
                                process5.setFileCount(2);
                                insertObject(process5);
                                break;
                            case 6: ProcessBean process6 = new ProcessBean(list.get(0), list.get(1),list.get(2),list.get(3),list.get(4),list.get(5));
                                process6.setName("cc");
                                process6.setPassword("0000");
                                process6.setFileCount(1);
                                insertObject(process6);
                                break;
                            }
                            progressDialog.dismiss();
                        }
                    }
                    @Override
                    public void onProgress(int i, int i1, int i2, int i3) {
                        Log.i("life", "insertBatchDatasWithOne -onProgress :" + i + "---" + i1 + "---" + i2 + "----" + i3);

                    }

                    @Override
                    public void onError(int i, String s) {
                        Toast.makeText(MainActivity.this, "错误码" + i + ",错误描述：" + s, Toast.LENGTH_LONG).show();
                    }


                });
                Toast.makeText(MainActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
            }

            private void insertObject(ProcessBean processn) {
                processn.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            Toast.makeText(MainActivity.this,"process上傳成功",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        /**
         * 载入图片添加图片加号
         * 通过适配器实现
         * SimpleAdapter参数imageItem为数据源R.layout.gridItem_addpic为布局
         */
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.gridview_addpic); //加号
        imageItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("itemImage", bmp);
        map.put("pathImage", "add_pic");
        imageItem.add(map);
        simpleAdapter = new SimpleAdapter(this,
                imageItem, R.layout.griditem_addpic,
                new String[] { "itemImage"}, new int[] { R.id.imageView1});
        /*
         * HashMap载入bmp图片在GridView中不显示,但是如果载入资源ID能显示 如
         * map.put("itemImage", R.drawable.img);
         * 解决方法:
         *              1.自定义继承BaseAdapter实现
         *              2.ViewBinder()接口实现
         *  参考 http://blog.csdn.net/admin_/article/details/7257901
         */
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {

            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                // TODO Auto-generated method stub
                if(view instanceof ImageView && data instanceof Bitmap){
                    ImageView i = (ImageView)view;
                    i.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });
        gridView1.setAdapter(simpleAdapter);
       // gridView1.setAdapter(simpleAdapter);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(imageItem.size()==10&&position==0){
                    Toast.makeText(MainActivity.this,"图片数为9张已满",Toast.LENGTH_LONG).show();
                }
                else if(position==0){
                    AddImageDialog();
                }else{
                    DeleteDialog(position);
                }
            }
        });
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,QueryActivity.class);
                startActivity(i);
            }
        });
    }

    private void DeleteDialog(final int position) {
        AlertDialog.Builder buidler=new AlertDialog.Builder(MainActivity.this);
        buidler.setMessage("確定删除图片吗？");
        buidler.setTitle("提示");
        buidler.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                imageItem.remove(position);
                simpleAdapter.notifyDataSetChanged();
            }
        });
        buidler.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        buidler.create().show();
    }

    private void AddImageDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("添加图片");
        builder.setTitle(R.mipmap.ic_launcher);
        builder.setCancelable(false);
        builder.setItems(new String[]{"本地相册选择", "手机相机添加", "取消选择图片"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0:
                        dialog.dismiss();
                        chooseImage();
                        break;
                    case 1:
                        dialog.dismiss();
                        permission();
                        break;
                    case 2:
                        dialog.dismiss();break;
                    default:break;
                }
            }
        });
        builder.create().show();
    }
    private void permission(){
    if (ContextCompat.checkSelfPermission(this,
    Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED)
    {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSIONS_REQUEST_CALL_PHONE2);

    }else{
        takePhoto();
    }
}
    public void chooseImage(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE2);

        }else {
            choosePhoto();
        }
    }

    private void choosePhoto() {
        Intent intent=new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,IMAGE_OPEN);
    }

    private void takePhoto() {
        File outputImage=new File(Environment.getExternalStorageDirectory(),"cgs_image.jpg");
        pathTakePhoto=outputImage.toString();
        try{
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
        }
        imageUri=Uri.fromFile(outputImage);
        Intent intentPhoto=new Intent("android.media.action.IMAGE_CAPTURE");
        intentPhoto.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intentPhoto,TAKE_PHOTO);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                takePhoto();
            } else
            {
                // Permission Denied
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }


        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE2)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                choosePhoto();
            } else
            {
                // Permission Denied
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
    @Override
    protected void onResume() {
        super.onResume();
        if(!TextUtils.isEmpty(pathImage)){
            Bitmap addbmp=BitmapFactory.decodeFile(pathImage);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", addbmp);
            map.put("pathImage", pathImage);
            imageItem.add(map);
            simpleAdapter = new SimpleAdapter(this,
                    imageItem, R.layout.griditem_addpic,
                    new String[] { "itemImage"}, new int[] { R.id.imageView1});
            //接口载入图片
            simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Object data,
                                            String textRepresentation) {
                    // TODO Auto-generated method stub
                    if(view instanceof ImageView && data instanceof Bitmap){
                        ImageView i = (ImageView)view;
                        i.setImageBitmap((Bitmap) data);
                        return true;
                    }
                    return false;
                }
            });
            gridView1.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
            //刷新后释放防止手机休眠后自动添加
            mphotopath.add(pathImage);
            pathImage = null;
            for (String m:mphotopath){
               System.out.println(m);
                Toast.makeText(MainActivity.this,m,Toast.LENGTH_LONG).show();
            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //打开图片
        if(resultCode==RESULT_OK && requestCode==IMAGE_OPEN) {
            Uri uri = data.getData();
            if (!TextUtils.isEmpty(uri.getAuthority())) {
                //查询选择图片
                Cursor cursor = getContentResolver().query(
                        uri,
                        new String[] { MediaStore.Images.Media.DATA },
                        null,
                        null,
                        null);
                //返回 没找到选择图片
                if (null == cursor) {
                    return;
                }
                //光标移动至开头 获取图片路径
                cursor.moveToFirst();
                String path = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
                //向处理活动传递数据
                //Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ProcessActivity.class); //主活动->处理活动
                intent.putExtra("path", path);
                //startActivity(intent);
                startActivityForResult(intent, GET_DATA);
            } else {
                Intent intent = new Intent(this, ProcessActivity.class); //主活动->处理活动
                intent.putExtra("path", uri.getPath());
                //startActivity(intent);
                startActivityForResult(intent, GET_DATA);
            }
        }
        if(resultCode==RESULT_OK&&requestCode==GET_DATA){
            pathImage=data.getStringExtra("pathProcess");
        }
        if(resultCode==RESULT_OK&&requestCode==TAKE_PHOTO){
            Intent intentBc=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intentBc.setData(imageUri);
            this.sendBroadcast(intentBc);
            Intent intentput=new Intent(this,ProcessActivity.class);
            intentput.putExtra("path",pathTakePhoto);
            startActivityForResult(intentput,GET_DATA);
        }
    }


}
