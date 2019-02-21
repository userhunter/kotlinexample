package example.pacewear.com.appname;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classloader.test.ISay;
import com.example.kt.test.KtActivity;
import com.example.webview.test.WebViewTestActivity;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dalvik.system.DexClassLoader;

public class MainActivity extends Activity {

    private  static final String TAG = "MainActivity";
    public final static int DO_WORK = 1;

    private Button mClick;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == DO_WORK) {
                //do something
            }
            return false;
        }
    });

    public void ClassLoader(View view){
        testClassLoader();
    }

    private void testClassLoader(){
        // 获取到包含 class.dex 的 jar 包文件
        final File jarFile =
                new File("/sdcard/" + "test_dex.jar");

        // 如果没有读权限,确定你在 AndroidManifest 中是否声明了读写权限
        Log.d(TAG, jarFile.canRead() + "");

        if (!jarFile.exists()) {
            Log.e(TAG, "sayhello_dex.jar not exists");
            return;
        }

        // getCodeCacheDir() 方法在 API 21 才能使用,实际测试替换成 getExternalCacheDir() 等也是可以的
        // 只要有读写权限的路径均可
        DexClassLoader dexClassLoader =
                new DexClassLoader(jarFile.getAbsolutePath(), getExternalCacheDir().getAbsolutePath(), null, getClassLoader());
        try {
            // 加载 HelloAndroid 类
            Class clazz = dexClassLoader.loadClass("com.example.classloader.test.HelloAndroid");
            //            // 强转成 ISayHello, 注意 ISayHello 的包名需要和 jar 包中的一致
            ISay iSayHello = (ISay) clazz.newInstance();
            mText.setText(""+iSayHello.say());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testActivieService();

        mClick = (Button)findViewById(R.id.button_test);
        mClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
                mHandler.sendEmptyMessage(DO_WORK);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "btnHandler", Toast.LENGTH_SHORT).show();
                    }
                }, 15 * 1000);
            }
        });

        mText = (TextView) findViewById(R.id.text_classloader);

        Log.d(TAG,"get the main Activity");
        testSharePrefence();


    }

    @Override
    protected void onResume() {
        super.onResume();
        String version = System.getProperty("java.vm.version");
        Log.d(TAG,"JVM version:"+version);
        isVMMultidexCapable(version);
    }

    static boolean isVMMultidexCapable(String versionString) {
        boolean isMultidexCapable = false;
        if (versionString != null) {
            Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(versionString);
            if (matcher.matches()) {
                try {
                    int major = Integer.parseInt(matcher.group(1));
                    int minor = Integer.parseInt(matcher.group(2));
//                    isMultidexCapable = (major > VM_WITH_MULTIDEX_VERSION_MAJOR)
//                            || ((major == VM_WITH_MULTIDEX_VERSION_MAJOR)
//                            && (minor >= VM_WITH_MULTIDEX_VERSION_MINOR));
                } catch (NumberFormatException e) {
                    // let isMultidexCapable be false
                }
            }
        }
        Log.i(TAG, "VM with version " + versionString +
                (isMultidexCapable ?
                        " has multidex support" :
                        " does not have multidex support"));
        return isMultidexCapable;
    }

    private void testSharePrefence(){
        SharedPreferenceHelper.setPlayStatus(this,true);

        boolean status = SharedPreferenceHelper.getPlayStatus(this);
        Log.d(TAG,"status:"+status);
    }

    private void testActivieService(){
        Intent intent = new Intent(MainActivity.this,MyTestService.class);
        startService(intent);
    }

    public void onAnimation(View view){
        Intent intent = new Intent(MainActivity.this,AnimationActivity.class);
        startActivity(intent);
    }

    public void onWebView(View view){
        Intent intent = new Intent(MainActivity.this,WebViewTestActivity.class);
        startActivity(intent);
    }

    public void onKotlin(View view){
        Intent intent = new Intent(MainActivity.this,KtActivity.class);
        startActivity(intent);
    }
}
