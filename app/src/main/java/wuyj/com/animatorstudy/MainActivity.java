package wuyj.com.animatorstudy;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.startAnimator);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动动画
                //ofIntAnimator();
                //ofObjectAnimatorForAlpha();
                //ofObjectAnimatorForRotation();
                //ofObjectAnimatorForTranslationX();
                //ofObjectAnimatorForScaleX();
                //ofObjectAnimatorForSet();
               // ofViewPropertyAnimator();
                ofAnimateObject();
            }
        });
    }

    private void ofIntAnimator ()  {
        final Button mButton = (Button) findViewById(R.id.Button);
        // 创建动画作用对象：此处以Button为例
        // 步骤1：设置属性数值的初始值 & 结束值
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(mButton.getLayoutParams().width, 800);
        // 初始值 = 当前按钮的宽度，此处在xml文件中设置为150
        // 结束值 = 500
        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置
        // 即默认设置了如何从初始值150 过渡到 结束值500
        // 步骤2：设置动画的播放各种属性
        valueAnimator.setDuration(3000);
        valueAnimator.setRepeatCount(3);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        // 设置动画运行时长:1s
        // 步骤3：将属性数值手动赋值给对象的属性:此处是将 值 赋给 按钮的宽度
        // 设置更新监听器：即数值每次变化更新都会调用该方法
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {

                int currentValue = (Integer) animator.getAnimatedValue();
                // 获得每次变化后的属性值
                // System.out.println(currentValue);
                // 输出每次变化后的属性值进行查看

                mButton.getLayoutParams().width = currentValue;
                // 每次值变化时，将值手动赋值给对象的属性
                // 即将每次变化后的值 赋 给按钮的宽度，这样就实现了按钮宽度属性的动态变化
                // 步骤4：刷新视图，即重新绘制，从而实现动画效果
                mButton.requestLayout();
            }
        });
        valueAnimator.start();
    }

    private void ofObjectAnimatorForAlpha() {
        final Button mButton = (Button) findViewById(R.id.Button);
        // 创建动画作用对象：此处以Button为例

        ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "alpha", 1f, 0f, 1f);
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是透明度alpha
        // 动画效果是:常规 - 全透明 - 常规
        animator.setDuration(5000);
        animator.start();
    }

    private void ofObjectAnimatorForRotation() {
        final Button mButton = (Button) findViewById(R.id.Button);
        // 创建动画作用对象：此处以Button为例

        ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "rotation", 0f, 360f);

        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是旋转alpha
        // 动画效果是:0 - 360
        animator.setDuration(5000);
        animator.start();
    }

    private void ofObjectAnimatorForTranslationX() {
        final Button mButton = (Button) findViewById(R.id.Button);
        // 创建动画作用对象：此处以Button为例

        float curTranslationX = mButton.getTranslationX();
        // 获得当前按钮的位置
        ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "translationX", curTranslationX, 300,curTranslationX);


        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴平移（在Y轴上平移同理，采用属性"translationY"
        // 动画效果是:从当前位置平移到 x=1500 再平移到初始位置
        animator.setDuration(5000);
        animator.start();
    }

    private void ofObjectAnimatorForScaleX() {
        final Button mButton = (Button) findViewById(R.id.Button);
        // 创建动画作用对象：此处以Button为例

        ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "scaleX", 1f, 3f, 1f);
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴缩放
        // 动画效果是:放大到3倍,再缩小到初始大小
        animator.setDuration(5000);
        animator.start();
    }

    private void ofObjectAnimatorForSet() {
        final Button mButton = (Button) findViewById(R.id.Button);
        // 步骤1：设置需要组合的动画效果
        float curTranslationX = mButton.getTranslationX();
        ObjectAnimator translation = ObjectAnimator.ofFloat(mButton, "translationX", curTranslationX, 300,curTranslationX);
        // 平移动画
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mButton, "rotation", 0f, 360f);
        // 旋转动画
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mButton, "alpha", 1f, 0f, 1f);
        // 透明度动画

        // 步骤2：创建组合动画的对象
        AnimatorSet animSet = new AnimatorSet();

        // 步骤3：根据需求组合动画
        animSet.play(translation).with(rotate).before(alpha);
        animSet.setDuration(5000);

        // 步骤4：启动动画
        animSet.start();
    }

    private void ofViewPropertyAnimator() {
        final Button mButton = (Button) findViewById(R.id.Button);
        // 创建动画作用对象：此处以Button为例

        mButton.animate().alpha(0f);
        // 单个动画设置:将按钮变成透明状态
        mButton.animate().alpha(0f).setDuration(5000).setInterpolator(new BounceInterpolator());
        // 单个动画效果设置 & 参数设置
        mButton.animate().alpha(0f).x(500).y(500);
    }

    private void ofAnimateObject() {
        //渐变动画    从显示（1.0）到隐藏（0.0）
        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.0f);
        //执行三秒
        alphaAnim.setDuration(1000);
        alphaAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        final Button mButton = (Button) findViewById(R.id.Button);
        mButton.startAnimation(alphaAnim);
    }
 }
