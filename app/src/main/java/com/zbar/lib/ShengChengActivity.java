package com.zbar.lib;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.global.globalonline.R;
import com.global.globalonline.base.MyApplication;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.tools.GetSelectBouncedUtil;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;
import java.util.Map;

public class ShengChengActivity extends Activity {


    ImageView  erweima;

   String symbol = "";
    int QR_WIDTH = 400;
    int QR_HEIGHT = 400;

    public static void  toActivity(Activity activity,String symbol){

        Intent intent = new Intent(activity, ShengChengActivity.class);
        intent.putExtra("symbol",symbol);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheng_cheng);


        WindowManager wm = this.getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

       // QR_WIDTH =Integer.parseInt(new DecimalFormat("#").format(width * 0.6));

        symbol = getIntent().getStringExtra("symbol");
        erweima = (ImageView)this.findViewById(R.id.erweima);

        Map<String,String>  map = new Hashtable<>();
        map.put("sysid",symbol);
        String name =  GetSelectBouncedUtil.getBankName(ShengChengActivity.this, StaticBase.VIRTUALOIN,symbol);
        map.put("sysname",name);
        map.put("userid", MyApplication.userBean.getUserid());
        String str = new  Gson().toJson(map);
        createQRImage(str);
    }




    public void createQRImage(String url)
    {
        try
        {
            //判断URL合法性
            if (url == null || "".equals(url) || url.length() < 1)
            {
                return;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");


            //com.google.zxing.client.j2se.MatrixToImageWriter.toBufferedImage(new com.google.zxing.qrcode.QRCodeWriter().encode($F{xxx},com.google.zxing.BarcodeFormat.QR_CODE,200,200))

            //图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (int y = 0; y < QR_HEIGHT; y++)
            {
                for (int x = 0; x < QR_WIDTH; x++)
                {
                    if (bitMatrix.get(x, y))
                    {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    }
                    else
                    {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            //显示到一个ImageView上面
            erweima.setImageBitmap(bitmap);
        }
        catch (WriterException e)
        {
            e.printStackTrace();
        }
    }

}
