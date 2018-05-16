package com.example.peter.playapp.util;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadUtil {
    public static void downClassFile(){
        new downloadClassTask().execute();
    }

    private static class downloadClassTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            HttpURLConnection conn = null;
            InputStream is = null;
            BufferedInputStream bis= null;
            BufferedOutputStream bos = null;
            String filePath = "";
            try {
                URL url = new URL("http://47.100.210.98:8080/patch/classes2.dex");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setUseCaches(false);
                conn.setDefaultUseCaches(false);
                conn.addRequestProperty("Cache-Control", "no-cache");
                conn.addRequestProperty("Cache-Control", "max-age=0");

                String storageState = Environment.getExternalStorageState();
                if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                    filePath = Environment.getExternalStorageDirectory()
                            .getAbsolutePath() + "/";
                    File savedir = new File(filePath);
                    if (!savedir.exists()) {
                        savedir.mkdirs();
                    }
                    String fileName1 = "classes2.dex";
                    File file1 = new File(filePath, fileName1);
                    if (file1.exists()){
                        return null;
                    }
                }

                String fileName = "classes2.dex";
                File file = new File(filePath, fileName);
                conn.connect();
                is = conn.getInputStream();
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(new FileOutputStream(file));
                byte[] by = new byte[8 * 1024];
                int temp = 0;
                while((temp=bis.read(by))!=-1){
                    bos.write(by, 0, temp);
                }

                bos.flush();
                bos.close();
                bis.close();
                is.close();
                conn.disconnect();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

    }
}
