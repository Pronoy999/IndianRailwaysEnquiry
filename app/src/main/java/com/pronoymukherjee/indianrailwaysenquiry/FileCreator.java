package com.pronoymukherjee.indianrailwaysenquiry;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is the file to create the text file for saved JSON.
 */

public class FileCreator {
    String TAG=FileCreator.class.getSimpleName();
    String fileName;
    Context context;
    public FileCreator(String fileName,Context context){
        this.fileName=fileName;
        this.context=context;
    }
    private boolean checkState(){
        String state= Environment.getExternalStorageState();
        if(!state.equals(Environment.MEDIA_MOUNTED)){
            Messages.toastMessge(context,"External Storage is not found.","");
            return false;
        }
        return true;
    }
    public void createFile(String data){
        String appName=context.getResources().getString(R.string.app_name);
        File root=Environment.getExternalStorageDirectory();
        File dir=new File(root.getAbsolutePath()+"/"+appName);
        dir.mkdirs();
        FileOutputStream fileOutputStream=null;
        PrintWriter printWriter=null;
        try {
            File file = new File(dir, fileName);
            fileOutputStream = new FileOutputStream(file);
            printWriter = new PrintWriter(fileOutputStream);
            printWriter.println(data);
            printWriter.flush();
        }
        catch (IOException e){
            Messages.toastMessge(context,"Oops, an Error occured.","");
        }
        finally {
            if(printWriter!=null){
                printWriter.close();
            }
            if (fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException ignored) {}
            }
        }
    }
}
