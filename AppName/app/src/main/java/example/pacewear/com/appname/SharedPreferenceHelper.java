package example.pacewear.com.appname;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {

    private static final String SHARED_PREFERENCE_NAME = "default_file";

    private static final String PLAY_STATUS = "play_status";  // 定义播放状态
    private static final String NEW_INSTALL_STATUS = "new_install_status";  //  第一次安装的设置状态
    private static final String IS_NEW_INSTALL_HAS_REPORTED = "is_has_report";           // 判断是第一次安装否已经上报
    private static final String IS_NOT_NEW_INSTALL_HAS_REPORTED = "is_not_first_install_report"; // 非第一次安装上报
    private static final String SAVE_VERSION_CODE = "version_code"; // 保存versionCode

    public  static void  setVersionCode(Context context, int versionCode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SAVE_VERSION_CODE,versionCode);
        editor.commit();
    }

    public static int getVersionCode(Context context){
        SharedPreferences sharedPreferences =  context.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(SAVE_VERSION_CODE,0);
    }

    public  static void  setPlayStatus(Context context, boolean isPlay){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PLAY_STATUS,isPlay);
        editor.commit();
    }

    public static boolean getPlayStatus(Context context){
        SharedPreferences sharedPreferences =  context.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PLAY_STATUS,false);
    }

    public  static void  setNewInstallStatus(Context context, boolean hasSetting){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(NEW_INSTALL_STATUS,hasSetting);
        editor.commit();
    }

    public static boolean getNewInstallStatus(Context context){
        SharedPreferences sharedPreferences =  context.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(NEW_INSTALL_STATUS,false);
    }


    public  static void  setHasReportAppNewInstall(Context context, boolean isSetting){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_NEW_INSTALL_HAS_REPORTED,isSetting);
        editor.commit();
    }

    public static boolean getHasReportAppNewInstall(Context context){
        SharedPreferences sharedPreferences =  context.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_NEW_INSTALL_HAS_REPORTED,false);
    }

    public  static void  setHasReportAppNotNewInstall(Context context, boolean isSetting){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_NOT_NEW_INSTALL_HAS_REPORTED,isSetting);
        editor.commit();
    }

    public static boolean getHasReportAppNotNewInstall(Context context){
        SharedPreferences sharedPreferences =  context.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_NOT_NEW_INSTALL_HAS_REPORTED,false);
    }

}
