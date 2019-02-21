package example.pacewear.com.appname;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * autour: bobbylu
 * date: 2019/1/12 on 17:49
 */
public class MyTestService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
