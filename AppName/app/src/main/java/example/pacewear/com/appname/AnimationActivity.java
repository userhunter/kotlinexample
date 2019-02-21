package example.pacewear.com.appname;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * autour: bobbylu
 * date: 2019/2/12 on 15:20
 */
public class AnimationActivity extends Activity {

    private static final String TAG ="AnimationActivity";

    private boolean isValueAnimator = false;   //

    private Button mButton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_layout);
        imageView = (ImageView)findViewById(R.id.image_item);
        statAnimation(imageView);

        testArrayMap();

    }


    private void testArrayMap(){
        ArrayMap<String,String> arrayMap = new ArrayMap<String, String>();
        arrayMap.put("a","str");
        arrayMap.put("b","yes");
        arrayMap.put("c","name");
    }

    public void onClose(View view){
        finish();
    }

    public void invokeAnimation(View view){
        if(isValueAnimator){
            invokeValueAnimator(imageView);
        }else{
            invokeObjectAnimator(imageView,"alpha");
        }

    }

    private void invokeValueAnimator(final View view){
            ValueAnimator valueAnimator = ValueAnimator.ofInt(0,400);
            valueAnimator.setDuration(2000);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int currentValue = (int)animation.getAnimatedValue();
                    view.layout(currentValue,currentValue,currentValue+view.getWidth(),currentValue+view.getHeight());
                }
            });
            valueAnimator.start();
    }

    private void statAnimation(View view){
        ScaleAnimation animation = new ScaleAnimation(1.0f,0.5f,1.0f,0.5f);
        animation.setDuration(3000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG,"value:"+animation.getDuration());
                Log.d(TAG,"data:"+animation.getStartTime());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    private void invokeObjectAnimator(View view,String type){

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,type,1,0,1,0);
        objectAnimator.setDuration(2000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d(TAG,"data:"+animation.getAnimatedValue());
            }
        });
        objectAnimator.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
