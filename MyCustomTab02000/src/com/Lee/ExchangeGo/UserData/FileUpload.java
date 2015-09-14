package com.Lee.ExchangeGo.UserData;

import android.content.Context;
import android.widget.ImageView;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by Administrator on 2015/7/30.
 */
public class FileUpload extends BmobFile {

    @Override
    public void loadImage(Context context, ImageView imageView) {

        super.loadImage(context, imageView);
    }
}
