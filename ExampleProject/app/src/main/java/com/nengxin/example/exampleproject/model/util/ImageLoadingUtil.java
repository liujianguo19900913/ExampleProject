package com.nengxin.example.exampleproject.model.util;

import android.graphics.Bitmap;

import com.nengxin.example.exampleproject.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by alan on 2016/12/1.
 */

public class ImageLoadingUtil {
    /**
     * 获得DisplayImageOptions实例
     * @return
     */
    public static DisplayImageOptions getImageLoaderOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()

                .showImageOnLoading(R.mipmap.loading)// 加载过程中显示的图片

                .showImageForEmptyUri(null)// 加载内容为空显示的图片

                .showImageOnFail(null)// 加载失败显示的图片

                .cacheInMemory(true).cacheOnDisk(false).considerExifParams(true)

                .bitmapConfig(Bitmap.Config.RGB_565).displayer(new FadeInBitmapDisplayer(388)).build();
        return options;
    }
}
