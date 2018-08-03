package com.example.ruianapp.Utlis;

import android.os.Environment;

import java.io.File;

/**
 * Created by zhanghx on 2018/6/1.
 */

public class Constants {
    /*
    * 以下是用户信息的接口
    * */
//    public final static String URL = "http://123.206.198.102:8081/RuiAnDataCollectWeb/api/user/";
    public final static String URL = "http://192.168.43.144:8080/RuiAnDataCollectWeb/api/user/";
    public final static String LOGIN_URL = URL+"login";
    public final static String PassWord_URL = URL+"changeUserPassword";
    public final static String Register_URL = URL+"register";
    public final static String User_info_URL = URL+"getUserInfo";
    public final static String UserXg_URL = URL+"saveUserInfo";
    public final static String GCCJ_URL = URL+"task/tasksList";
    /*
     * 以下是数据采集的接口
     * */

    public final static String CJGS_URL = URL+"enterprises/enterprisesList";//采集公司
    public final static String GCFK_URL = URL+"gcfktzd/saveGcfktzd";
//    public final static String GCFK_URL = "http://192.168.43.144:8080/demo";
    public final static String GCFKList_URL = URL+"gcfktzd/gcfktzdList";
    public final static String GCLX_URL = URL+"gclxd/saveGclxd";
    public final static String GCLXList_URL = URL+"gclxd/gclxdList";
    public final static String GCTG_URL = URL+"gctgtzd/saveGctgtzd";
    public final static String GCTGList_URL = URL+"gctgtzd/gctgtzdList";
    public final static String ZGTZ_URL = URL+"zgtzd/saveZgtzd";
    public final static String ZGTZList_URL = URL+"zgtzd/zgtzdList";
//    public final static String GCFKIMAGE_URL = URL+"gcfktzd/uploadFkdImages";
    public final static String GCFKIMAGE_URL = "http://192.168.43.144:8000/img";
    public final static String GCLXIMAGE_URL = "http://192.168.43.144:8000/img";
    public final static String GCTGIMAGE_URL = "http://192.168.43.144:8000/img";
    public final static String GCZGIMAGE_URL = "http://192.168.43.144:8000/img";
    public final static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator ;




    public final static String CGJC_URL = "http://192.168.43.144:8000/index";
    public final static String CGJCIMG_URL = "http://192.168.43.144:8000/img";
    public final static String CGRQ_URL = "http://192.168.43.144:8000/index";
    public final static String CGRQIMG_URL = "http://192.168.43.144:8000/img";

    /*
    * 以下是企业操作的接口
    * */
    public final static String News_URL = URL+"news/newsList";
    public final static String MyPlan_URL = URL+"commonData/workPlansListBySelf";
    public final static String MyLeave_URL = URL+"askLeave/askLeaveListBySelf";
    public final static String MyTask_URL = URL+"task/tasksList";
    public final static String MyInform_URL = URL+"innerMessage/innerMessageListSelf";
    public final static String MySignin_URL = URL+"attendance/saveAttendance";
    public final static String QYNAME_URL = URL+"enterprises/enterprisesList";
}
