package example.pacewear.com.appname;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by p_billylu on 2018/4/10.
 */

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    private  RefWatcher refWatcher;
    private static final int TOAST_APPEAR_TIME = 3000;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = setupLeakCanary();

        Log.d(TAG,"MyApplication");
        Toast.makeText(this,"hello world",Toast.LENGTH_LONG).show();
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication leakApplication = (MyApplication) context.getApplicationContext();
        return leakApplication.refWatcher;
    }
}
