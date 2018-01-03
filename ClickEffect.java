package wesely.tw.sandbox.click_effect;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by wesel on 2018/1/3.
 */

public class ClickEffect {

    private static int getRandomColor() {
        Random rnd = new Random();
        int transparent = 200;
        return Color.argb(transparent, 56 + rnd.nextInt(170), 56 + rnd.nextInt(170), 56 + rnd.nextInt(170));
    }

    private static int setRandomColor(Drawable d) {
        Random rnd = new Random();
        int transparent = 200;
        int color = Color.argb(transparent, 56 + rnd.nextInt(170), 56 + rnd.nextInt(170), 56 + rnd.nextInt(170));
        d.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        return color;
    }

    public static void animNova(Context context, ImageButton v) {
        int effectSize = v.getWidth() * 2 / 3;
        int color = getRandomColor(); // get a random color
        AxisPoint p = AxisPoint.getCenter(v);
        ImageView iv = new ImageView(context);
        ((ViewGroup) v.getParent()).addView(iv); // add View to parent
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setShape(GradientDrawable.OVAL);
        gd.setStroke(8, color * 7 / 10);
        iv.setBackground(gd);
        iv.getLayoutParams().height = effectSize;
        iv.getLayoutParams().width = effectSize;
        iv.setX(p.x - effectSize / 2);
        iv.setY(p.y - effectSize / 2);
        v.bringToFront(); // bring original view to front
        Interpolator ip = new DecelerateInterpolator();
        iv.animate().scaleX(6).scaleY(6).setDuration(400).alpha(0).setInterpolator(ip);
    }

    public static void animRipple(Context context, View v) {
        animRing(context, v, 300);
        animRing(context, v, 700);
    }

    public static void animRing(Context context, View v, int duration) {
        int effectSize = v.getWidth() * 2 / 3;
        int color = getRandomColor(); // get a random color
        AxisPoint p = AxisPoint.getCenter(v);
        ImageView iv = new ImageView(context);
        ((ViewGroup) v.getParent()).addView(iv); // add View to parent
        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.OVAL);
        gd.setStroke(6, color);
        iv.setBackground(gd);
        iv.getLayoutParams().height = effectSize;
        iv.getLayoutParams().width = effectSize;
        iv.setX(p.x - effectSize / 2);
        iv.setY(p.y - effectSize / 2);
        v.bringToFront(); // bring original view to front
        Interpolator ip = new DecelerateInterpolator();
        iv.animate().scaleX(4).scaleY(4).setDuration(duration).alpha(0).setInterpolator(ip);
    }

    public static void animParticalNova(Context context, View v, int particles) {
        int radious = v.getWidth() * 6 / 5;
        int ballSize = v.getWidth() / 8;
        int color = getRandomColor(); // get a random color
        AxisPoint p = AxisPoint.getCenter(v);
        ArrayList<AxisPoint> points = new ArrayList();
        for (int i = 0; i < particles; i++) {
            AxisPoint point = new AxisPoint();
            point.x = (int) (Math.cos(Math.PI * 2 * i / particles) * radious);
            point.y = (int) (Math.sin(Math.PI * 2 * i / particles) * radious);
            points.add(point);
            ImageView iv = new ImageView(context);
            ((ViewGroup) v.getParent()).addView(iv); // add View to parent
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(color);
            gd.setShape(GradientDrawable.OVAL);
            iv.setBackground(gd);
            iv.getLayoutParams().height = ballSize;
            iv.getLayoutParams().width = ballSize;
            iv.setX(p.x - ballSize / 2);
            iv.setY(p.y - ballSize / 2);
            v.bringToFront(); // bring original view to front
            Interpolator ip = new LinearOutSlowInInterpolator();
            iv.animate().translationXBy(point.x).translationYBy(point.y).scaleX(2).scaleY(2).setDuration(400).alpha(0).setInterpolator(ip);
        }
    }

    public static void animExplosion(Context context, View v) {
        animParticalNova(context, v, 18);
        int particles = 9;
        int radious = v.getWidth() * 8 / 5;
        int ballSize = v.getWidth() / 6;
        int color = getRandomColor(); // get a random color
        AxisPoint p = AxisPoint.getCenter(v);
        ArrayList<AxisPoint> points = new ArrayList();
        for (int i = 0; i < particles; i++) {
            AxisPoint point = new AxisPoint();
            point.x = (int) (Math.cos(Math.PI * 2 * i / particles) * radious);
            point.y = (int) (Math.sin(Math.PI * 2 * i / particles) * radious);
            points.add(point);
            ImageView iv = new ImageView(context);
            ((ViewGroup) v.getParent()).addView(iv); // add View to parent
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(color);
            gd.setShape(GradientDrawable.OVAL);
            iv.setBackground(gd);
            iv.getLayoutParams().height = ballSize;
            iv.getLayoutParams().width = ballSize;
            iv.setX(p.x - ballSize / 2);
            iv.setY(p.y - ballSize / 2);
            v.bringToFront(); // bring original view to front
            Interpolator ip = new DecelerateInterpolator();
            iv.animate().translationXBy(point.x).translationYBy(point.y).scaleX(2).scaleY(2).setDuration(400).alpha(0).setInterpolator(ip);
        }
    }

    /**
     * Create another ImageView and then change its scale.
     * Can ONLY apply to ImageView or ImageButton.
     *
     * @param context
     * @param v
     */
    public static void animHeartBeat(Context context, ImageButton v) {
        AxisPoint p = AxisPoint.getCenter(v);
        ImageView iv = new ImageView(context);
        ((ViewGroup) v.getParent()).addView(iv); // add View to parent
        iv.setImageDrawable(v.getDrawable());
        iv.getLayoutParams().height = v.getHeight();
        iv.getLayoutParams().width = v.getWidth();
        iv.setX(p.x - v.getWidth() / 2);
        iv.setY(p.y - v.getHeight() / 2);
        setRandomColor(iv.getDrawable());
        Interpolator ip = new BounceInterpolator();
        iv.animate().scaleX(3).scaleY(3).setInterpolator(ip).setDuration(500).alpha(0);
    }

    static Handler handle = new Handler();

    public static void animHelixNova(Context context, final ImageButton v, final int particles) {
        final int radious = (int) (v.getWidth() * 3);
        final double angle = Math.PI * 2 / 3; // total spinning angle (rad)
        final int duration = 600;
        int ballSize = v.getWidth() / 6;
        final ArrayList<ImageView> points = new ArrayList();
        for (int i = 0; i < particles; i++) {
            ImageView iv = new ImageView(context);
            ((ViewGroup) v.getParent()).addView(iv); // add View to parent
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(getRandomColor());
            gd.setShape(GradientDrawable.OVAL);
            iv.setBackground(gd);
            iv.getLayoutParams().height = ballSize;
            iv.getLayoutParams().width = ballSize;
            points.add(iv);
        }
        v.bringToFront(); // bring original view to front
        final long start = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int t = (int) (System.currentTimeMillis() - start);
                double progress = (t * 1.0) / duration;
                for (int i = 0; i < points.size(); i++) {
                    ImageView iv = points.get(i);
                    double x = AxisPoint.getCenter(v).x - iv.getWidth() / 2
                            + Math.cos(angle * progress + Math.PI * 2 * i / points.size())
                            * radious * progress / 2;
                    double y = AxisPoint.getCenter(v).y - iv.getHeight() / 2
                            + Math.sin(angle * progress + Math.PI * 2 * i / points.size())
                            * radious * progress / 2;
                    iv.setX((float) x);
                    iv.setY((float) y);
                    iv.setScaleX((float) 2);
                    iv.setScaleY((float) 2);
                    iv.setAlpha((float) (1 - progress));
                }
                if (progress < 1)
                    handle.postDelayed(this, 10);
                else
                    for (int i = 0; i < points.size(); i++) {
                        points.get(i).setVisibility(View.GONE);
                    }
            }
        }).start();
    }


    public static void animJumping(final View v) {
        final int duration = 800;
        final long start = System.currentTimeMillis();
        final float origin = v.getY();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int t = (int) (System.currentTimeMillis() - start);
                double progress = (t * 1.0) / duration;
                float y = (float) Math.abs(
                        v.getHeight()
                                * Math.sin(3 * Math.PI * progress)
                                * (1 - progress));
                v.setY(origin - y);
                Log.d("anim jumping", y + "/" + progress);
                if (progress < 1)
                    handle.postDelayed(this, 20);
                else
                    v.setY(origin);
            }
        }).start();
    }

    public static void animShaking(final View v) {
        final int duration = 1000;
        final long start = System.currentTimeMillis();
        final float origin = v.getY();
        final float rotateAngle = (float) (Math.PI) * 8;
        final float waveTimes = 5;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int t = (int) (System.currentTimeMillis() - start);
                double progress = (t * 1.0) / duration;
                float y = (float) Math.abs(
                        v.getHeight()
                                * Math.sin(Math.PI * progress)
                                * (1 - progress));
                v.setY(origin - y);
//                v.setRotation((float) ((rotateAngle * waveTimes * progress) % rotateAngle - 0.5 * rotateAngle));
                v.setRotation((float) (45 * Math.sin(progress * Math.PI * 6)));
                Log.d("anim jumping", y + "/" + progress);
                if (progress < 1)
                    handle.postDelayed(this, 10);
                else {
                    v.setY(origin);
                    v.setRotation(0);
                }
            }
        }).start();
    }
}
