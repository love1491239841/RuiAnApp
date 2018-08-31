package com.example.ruianapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.activity.ContactActivity;
import com.example.ruianapp.bean.Gcfk;
import com.example.ruianapp.bean.Gclx;
import com.example.ruianapp.bean.Plan;
import com.example.ruianapp.bean.Task;
import com.example.ruianapp.bean.Zgtz;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;
import com.liulishuo.filedownloader.FileDownloader;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhanghx on 2018/5/16.
 */

public class FineLxAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Gclx> GclxList;
    private Context context;
    private PopupWindow popupWindow;

    public FineLxAdapter(List<Gclx> GclxList, Context context) {
        this.GclxList = GclxList;
        this.context = context;
    }
    class RwHolder extends RecyclerView.ViewHolder{
        private TextView Gclx_item_name;
        private TextView Gclx_item_time;
        private TextView Gclx_item_saved;
        private View view;
        public RwHolder(View itemView) {
            super(itemView);
            Gclx_item_name=(TextView)itemView.findViewById(R.id.fine_item_name);
            Gclx_item_time=(TextView)itemView.findViewById(R.id.fine_item_time);
            Gclx_item_saved=(TextView)itemView.findViewById(R.id.fine_item_saved);
            view=itemView;
        }
    }
    @Override
    public int getItemCount() {
        return GclxList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RwHolder holder = new RwHolder(LayoutInflater.from(context).inflate(R.layout.fine_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final Gclx Gclx = GclxList.get(position);
        if (Gclx.isSubmited()){
            ((RwHolder)holder).Gclx_item_saved.setText("已提交");
            ((RwHolder)holder).Gclx_item_saved.setTextColor(Color.parseColor("#354B5E"));
        }else {
            ((RwHolder)holder).Gclx_item_saved.setText("未提交");
            ((RwHolder)holder).Gclx_item_saved.setTextColor(Color.RED);
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (Gclx.getAddTime() != null){
                Date date1 = sdf1.parse(Gclx.getAddTime());
                String date2 = sdf2.format(date1);
                ((RwHolder)holder).Gclx_item_time.setText(date2);
            }else {

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((RwHolder)holder).Gclx_item_name.setText(Gclx.getGcmc());
        ((RwHolder)holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ContactActivity.class);
                intent.putExtra("lx",Gclx);
                context.startActivity(intent);
            }
        });
        ((RwHolder)holder).view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View view = LayoutInflater.from(context).inflate(R.layout.fine_window,null,false);
                TextView textView1 = (TextView) view.findViewById(R.id.fine_window_doc);
                textView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        new AlertDialog.Builder(context)
                                .setTitle("提示")
                                .setMessage("是否下载.doc附件" )
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (Gclx.getWord() == null){
                                            Toast.makeText(context, "此罚款单没有附件", Toast.LENGTH_SHORT).show();
                                        }else {
                                            String url = Constants.IMGURL+Gclx.getWord();
                                            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator+"RuiAn/"+Gclx.getGcmc()+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+".doc";
                                            downloadFile(url,path);
                                        }

                                    }
                                })
                                .setNegativeButton("取消", null)
                                .show();
                    }
                });
                TextView textView2 = (TextView) view.findViewById(R.id.fine_window_pdf);
                textView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        new AlertDialog.Builder(context)
                                .setTitle("提示")
                                .setMessage("是否下载.pdf附件" )
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (Gclx.getPdf() == null){
                                            Toast.makeText(context, "此罚款单没有附件", Toast.LENGTH_SHORT).show();
                                        }else {
                                            String url = Constants.IMGURL+Gclx.getPdf();
                                            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator+"RuiAn/"+Gclx.getGcmc()+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+".pdf";
                                            downloadFile(url,path);
                                        }

                                    }
                                })
                                .setNegativeButton("取消", null)
                                .show();
                    }
                });
                popupWindow = new PopupWindow(view,400, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(((RwHolder)holder).Gclx_item_saved, Gravity.RIGHT,Gravity.RIGHT);
                view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                        return false;
                    }
                });
                return true;
            }
        });

    }
    private void downloadFile(String url, final String path){
        FileDownloader.getImpl().create(url)
                .setPath(path)
                .setForceReDownload(true)
                .setListener(new FileDownloadLargeFileListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {
//                                下载开始
                        Toast.makeText(context, "开始下载", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
//                                进度回调
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {
//                                暂停
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        Toast.makeText(context, "文件保存到:"+path, Toast.LENGTH_SHORT).show();
//                                结束

                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
//                                出错
                        Toast.makeText(context, "下载出错", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
//                                存在相同下载
                        Toast.makeText(context, "已下载", Toast.LENGTH_SHORT).show();
                    }
                }).start();
    }
}
