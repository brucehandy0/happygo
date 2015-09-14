package com.Lee.ExchangeGo.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import com.Lee.ExchangeGo.R;
import com.Lee.ExchangeGo.UserData.Myuser;
import com.Lee.ExchangeGo.UserData.UserGoods;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class FragmentPageDeliver extends Fragment implements View.OnClickListener{
    private static final int PHOTO_REQUEST_CAREMA = 1;// ����
    private static final int PHOTO_REQUEST_GALLERY = 2;// �������ѡ��
    private static final int PHOTO_REQUEST_CUT = 3;// ���


    /* ͷ����� */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;


    private View rootView;
    private ImageView iv_image;
    private TextView btn_pic_upload,btn_deliver;
    private Button btn_add_view_cancle,btn_addby_camera,btn_addby_gallery;
    private EditText et_tittle,et_content,et_price;

    private LinearLayout add_view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_deliver, null);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }



        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       /*事件监听
               ......*/
       //btn_pic_upload= (TextView) getActivity().findViewById(R.id.page_deli_btn_upload);
        add_view= (LinearLayout) getActivity().findViewById(R.id.page_deli_add_view);
        btn_add_view_cancle= (Button) getActivity().findViewById(R.id.page_deli_add_view_cancle);
        iv_image= (ImageView) getActivity().findViewById(R.id.page_deli_add_imageview);
        btn_addby_camera= (Button) getActivity().findViewById(R.id.page_deli_addby_camera);
        btn_addby_gallery= (Button) getActivity().findViewById(R.id.page_deli_addby_gallery);
        btn_deliver= (TextView) getActivity().findViewById(R.id.page_deli_btn_deliver);
        et_tittle= (EditText) getActivity().findViewById(R.id.page_deli_add_tittle);
        et_content= (EditText) getActivity().findViewById(R.id.page_deli_add_content);
        et_price= (EditText) getActivity().findViewById(R.id.page_deli_et_price);


       // btn_pic_upload.setOnClickListener(this);
        btn_add_view_cancle.setOnClickListener(this);
        btn_addby_gallery.setOnClickListener(this);
        btn_addby_camera.setOnClickListener(this);
        btn_deliver.setOnClickListener(this);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // ����᷵�ص����
            if (data != null) {
                // �õ�ͼƬ��ȫ·��
                Uri uri = data.getData();
                crop(uri);

            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // �����ص����
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
               // Toast.makeText(FragmentPageDeliver.this, "dsdsds", Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // �Ӽ���ͼƬ���ص����
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");

                bitmap=compressImage(bitmap);
                this.iv_image.setImageBitmap(bitmap);

            }
            try {
                // ����ʱ�ļ�ɾ��
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        if(v==btn_pic_upload){
            add_view.setVisibility(View.VISIBLE);
        }
        else if (v==btn_add_view_cancle){
            add_view.setVisibility(View.GONE);
        }
        else if (v==btn_addby_camera){
            camera(null);
        }
        else if (v==btn_addby_gallery){
            gallery(null);
        }
        else if(v==btn_deliver){
           Myuser user=Myuser.getCurrentUser(getActivity(),Myuser.class);
            UserGoods good=new UserGoods();
            good.setUser(user);
            good.setNickname(user.getNickname());
            good.setGood_tittle(et_tittle.getText().toString());
            good.setGood_content(et_content.getText().toString());
            good.setGood_price(Integer.valueOf(et_price.getText().toString()));
            good.setHead_image(null);
            good.save(getActivity(),new SaveListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(getActivity(),"发布成功！",Toast.LENGTH_SHORT).show();
                    init();

                }

                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(getActivity(),"发布失败！"+s,Toast.LENGTH_SHORT).show();
                }
            });

        }
    }





    public void gallery(View view) {
        // ����ϵͳͼ�⣬ѡ��һ��ͼƬ
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // ����һ�����з���ֵ��Activity��������ΪPHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /*
     * ������ȡ
     */
    public void camera(View view) {
        // �������
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (hasSdcard()) {
            tempFile = new File(Environment.getExternalStorageDirectory(),
                    PHOTO_FILE_NAME);
            // ���ļ��д���uri
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        // ����һ�����з���ֵ��Activity��������ΪPHOTO_REQUEST_CAREMA
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    /*
     * ����ͼƬ
     */
    private void crop(Uri uri) {
        // �ü�ͼƬ��ͼ
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // �ü���ı���1��1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // �ü������ͼƬ�ĳߴ��С
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// ͼƬ��ʽ
        intent.putExtra("noFaceDetection", true);// ȡ������ʶ��
        intent.putExtra("return-data", true);
        // ����һ�����з���ֵ��Activity��������ΪPHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /*
     * �ж�sdcard�Ƿ񱻹���
     */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    //图片压缩
    private Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    private void init(){
        iv_image.setImageResource(R.drawable.add_pic);
        et_tittle.setText("");
        et_content.setText("");
        et_price.setText("");
    }
}