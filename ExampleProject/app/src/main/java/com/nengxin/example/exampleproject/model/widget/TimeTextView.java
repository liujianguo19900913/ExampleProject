package com.nengxin.example.exampleproject.model.widget;

import android.content.Context;
import android.graphics.Paint;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.TextView;


public class TimeTextView extends TextView implements Runnable {
    Paint mPaint; //画笔,包含了画几何图形、文本等的样式和颜色信息
    private boolean run = false; //是否启动了
    private TimeEntity timeBeginEntity;
    private TimeEntity timeEndEntity;

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint();
    }

    public TimeTextView(Context context) {
        super(context);
    }

    public void setEndTimes(long startTime, long endTime) {
        timeEndEntity = TimeEntity.build(startTime, endTime);
    }

    public void setBeginTimes(long startTime, long endTime) {
        timeBeginEntity = TimeEntity.build(startTime, endTime);
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    @Override
    public void run() {
        //标示已经启动
        run = true;
        if (timeBeginEntity != null)
            timeBeginEntity.ComputeTime();
        if (timeEndEntity != null)
            timeEndEntity.ComputeTime();

        if (timeBeginEntity != null && timeEndEntity == null) {
            this.setText(timeBeginEntity.formatText());
        } else if (timeBeginEntity != null && timeEndEntity != null) {
            if (timeBeginEntity.isEnd()) this.setText(timeEndEntity.formatText());
            else this.setText(timeBeginEntity.formatText());
        } else if (timeEndEntity != null) {
            this.setText(timeEndEntity.formatText());
        } else {
            this.setText("");
        }
        postDelayed(this, 1000);
    }

    public static class TimeEntity {
        public long day, hour, min, second;//天，小时，分钟，秒

        public static TimeEntity build(long startTime, long endTime) {
            TimeEntity entity = new TimeEntity();
            entity.initTime(startTime, endTime);
            return entity;
        }

        public boolean isEnd() {
            return day <= 0l && hour <= 0l && min <= 0l && second <= 0l;
        }

        public Spanned formatText() {
            if (day < 0l || hour < 0l || min < 0l || second < 0l) return Html.fromHtml("");
            String strTime = " <font color='blue' > " + day
                    + " </font> <pre> " + " 天</pre > <font color='red'> " + hour
                    + " </font > <pre > 小时 </pre > <font color='red' > " + min
                    + "</font> <pre > 分钟 </pre > <font color='red' > " + second
                    + " </font > <pre > 秒 ";
            StringBuilder sb = new StringBuilder();
            if (day > 0)
                sb.append(" <font color='#fb053f' > " + day + "</font> <pre> 天</pre >");
            if (hour > 0)
                sb.append("  <font color='#fb053f'> " + hour + "</font > <pre > 小时 </pre >");
            if (min > 0)
                sb.append("  <font color='#fb053f' > " + min + "</font> <pre > 分钟 </pre > ");
            sb.append("<font color='#fb053f' > " + second
                    + " </font > <pre > 秒 ");
            return Html.fromHtml(sb.toString());
        }

        public void initTime(long startTime, long endTime) {
            //按照传入的格式生成一个simpledateformate对象
            long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
            long nh = 1000 * 60 * 60;//一小时的毫秒数
            long nm = 1000 * 60;//一分钟的毫秒数
            long ns = 1000;//一秒钟的毫秒数long diff;try {
            //获得两个时间的毫秒时间差异
            long diff = endTime - startTime;
            day = diff / nd;//计算差多少天
            if (day < 0) {
                day = 0;
                diff = diff + nd;
            }
            hour = diff % nd / nh;//计算差多少小时
            if (hour < 0) {
                hour = 0;
                diff = diff + nh;
            }
            min = diff % nd % nh / nm;//计算差多少分钟
            if (min < 0) { //1456472047135  //1456471759000
                min = 0;
                diff = diff + nm;
            }
            second = diff % nd % nh % nm / ns;//计算差多少秒//输出结果
            if (second < 0) {
                second = 0;
            }
        }

        /**
         * 倒计时计算
         */
        private void ComputeTime() {
            second++;
            if (second == 60) {
                min++;
                second = 0;
                if (min == 60) {
                    min = 0;
                    hour++;
                    if (hour == 24) {
                        // 倒计时结束
                        hour = 0;
                        day++;
                    }
                }
            }
        }
    }
}