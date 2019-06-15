package com.example.myhrd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class My_game extends AppCompatActivity {
    Button chess[] = new Button[10];
    TextView step_text;
    Button refresh;
    int BG[][] = new int[5][4];
    float SW;
    float x1, x2, y1, y2;
    int Step = 0;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_game);
        intent=getIntent();
        chess[0] = findViewById(R.id.chess1);
        chess[1] = findViewById(R.id.chess2);
        chess[2] = findViewById(R.id.chess3);
        chess[3] = findViewById(R.id.chess4);
        chess[4] = findViewById(R.id.chess5);
        chess[5] = findViewById(R.id.chess6);
        chess[6] = findViewById(R.id.chess7);
        chess[7] = findViewById(R.id.chess8);
        chess[8] = findViewById(R.id.chess9);
        chess[9] = findViewById(R.id.chess10);
        step_text = findViewById(R.id.test_flag);
        refresh = findViewById(R.id.refresh_game);
        //注册监听器
        for (int i = 0; i < 10; i++)
            chess[i].setOnTouchListener(new mTouch());
        //背景数组对应填充

        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        SW = wm.getDefaultDisplay().getWidth();
        if(intent.getStringExtra("type").equals("0"))
            init();
        else if(intent.getStringExtra("type").equals("1"))
            init_2();
        else if(intent.getStringExtra("type").equals("2"))
            init_3();
        else if(intent.getStringExtra("type").equals("3"))
            init_4();
    }

    public void refresh(View view) {
        Step = 0;
        step_text.setText("你已经走了：" + Step + "步！");
        if(intent.getStringExtra("type").equals("0"))
            init();
        else if(intent.getStringExtra("type").equals("1"))
            init_2();
        else if(intent.getStringExtra("type").equals("2"))
            init_3();
        else if(intent.getStringExtra("type").equals("3"))
            init_4();
    }

    public class mTouch implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int type; // 1 兵  2 张飞  3 关羽  4 曹操
            int r, c;
            if (v.getWidth() == v.getHeight()) {
                if (v.getHeight() > 300)
                    type = 4;
                else
                    type = 1;

            } else {
                if (v.getHeight() > v.getWidth())
                    type = 2;
                else
                    type = 3;
            }

            r = (int) (v.getY() / 270f);
            c = (int) (v.getX() / 270f);

            //继承了Activity的onTouchEvent方法，直接监听点击事件
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                //当手指按下的时候
                x1 = event.getX();
                y1 = event.getY();
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {
                //当手指离开的时候
                x2 = event.getX();
                y2 = event.getY();
                if (y1 - y2 > 50) //"向上滑:"
                {
                    switch (type) {
                        case 1:
                            if (r > 0 && BG[r - 1][c] == 0) {
                                SetPos(v, r - 1, c);
                                BG[r - 1][c] = 1;
                                BG[r][c] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 2:
                            if (r > 0 && BG[r - 1][c] == 0) {
                                SetPos(v, r - 1, c);
                                BG[r - 1][c] = 1;
                                BG[r + 1][c] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 3:
                            if (r > 0 && BG[r - 1][c] == 0 && BG[r - 1][c + 1] == 0) {
                                SetPos(v, r - 1, c);
                                BG[r - 1][c] = BG[r - 1][c + 1] = 1;
                                BG[r][c] = BG[r][c + 1] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 4:
                            if (r > 0 && BG[r - 1][c] == 0 && BG[r - 1][c + 1] == 0) {
                                SetPos(v, r - 1, c);
                                BG[r - 1][c] = BG[r - 1][c + 1] = 1;
                                BG[r + 1][c] = BG[r + 1][c + 1] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                            }
                            break;

                    }

                } else if (y2 - y1 > 50) { //向下滑
                    switch (type) {
                        case 1:
                            if (r < 4 && BG[r + 1][c] == 0) {
                                SetPos(v, r + 1, c);
                                BG[r + 1][c] = 1;
                                BG[r][c] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 2:
                            if (r < 3 && BG[r + 2][c] == 0) {
                                SetPos(v, r + 1, c);
                                BG[r + 2][c] = 1;
                                BG[r][c] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                            }

                            break;
                        case 3:
                            if (r < 4 && BG[r + 1][c] == 0 && BG[r + 1][c + 1] == 0) {
                                SetPos(v, r + 1, c);
                                BG[r + 1][c] = BG[r + 1][c + 1] = 1;
                                BG[r][c] = BG[r][c + 1] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 4:
                            if (r < 3 && BG[r + 2][c] == 0 && BG[r + 2][c + 1] == 0) {
                                SetPos(v, r + 1, c);
                                BG[r + 2][c] = BG[r + 2][c + 1] = 1;
                                BG[r][c] = BG[r][c + 1] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                                if (r + 1 == 3 && c == 1) {
                                    step_text.setText("你赢了！共用" + Step + "步！");
                                }
                            }
                            break;
                    }
                } else if (x1 - x2 > 50) //向左滑
                {
                    switch (type) {
                        case 1:
                            if (c > 0 && BG[r][c - 1] == 0) {
                                SetPos(v, r, c - 1);
                                BG[r][c - 1] = 1;
                                BG[r][c] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 2:
                            if (c > 0 && BG[r][c - 1] == 0 && BG[r + 1][c - 1] == 0) {
                                SetPos(v, r, c - 1);
                                BG[r][c - 1] = 1;
                                BG[r + 1][c - 1] = 1;
                                BG[r][c] = 0;
                                BG[r + 1][c] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 3:
                            if (c > 0 & BG[r][c - 1] == 0) {
                                SetPos(v, r, c - 1);
                                BG[r][c - 1] = 1;
                                BG[r][c + 1] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 4:
                            if (c > 0 && BG[r][c - 1] == 0 && BG[r + 1][c - 1] == 0) {
                                SetPos(v, r, c - 1);
                                BG[r][c - 1] = BG[r + 1][c - 1] = 1;
                                BG[r][c + 1] = BG[r + 1][c + 1] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                                if (r == 3 && c - 1 == 1) {
                                    step_text.setText("你赢了！共用" + Step + "步！");
                                }
                            }
                            break;
                    }
                } else if (x2 - x1 > 50) //向右滑
                {
                    switch (type) {
                        case 1:
                            if (c < 3 && BG[r][c + 1] == 0) {
                                SetPos(v, r, c + 1);
                                BG[r][c + 1] = 1;
                                BG[r][c] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 2:
                            if (c < 3 & BG[r][c + 1] == 0 && BG[r + 1][c + 1] == 0) {
                                SetPos(v, r, c + 1);
                                BG[r][c + 1] = 1;
                                BG[r + 1][c + 1] = 1;
                                BG[r][c] = 0;
                                BG[r + 1][c] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 3:
                            if (c < 2 && BG[r][c + 2] == 0) {
                                SetPos(v, r, c + 1);
                                BG[r][c + 2] = 1;
                                BG[r][c] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 4:
                            if (c < 2 && BG[r][c + 2] == 0 && BG[r + 1][c + 2] == 0) {
                                SetPos(v, r, c + 1);
                                BG[r][c + 2] = BG[r + 1][c + 2] = 1;
                                BG[r][c] = BG[r + 1][c] = 0;
                                Step++;
                                step_text.setText("你已经走了：" + Step + "步！");
                                if (r  == 3 && c + 1 == 1) {
                                    step_text.setText("你赢了！共用" + Step + "步！");
                                }
                            }
                            break;
                    }
                }
            }
            return true;
        }
    }
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    void SetSize(Button v, int w, int h, String txt) {
        v.setWidth(w * dp2px(getApplicationContext(),SW/4));
        v.setHeight(h * dp2px(getApplicationContext(), SW / 4));
        v.setText(txt);
    }

    void SetPos(View v, int r, int c) {
        v.setX(c * SW / 4f);
        v.setY(r * SW / 4f);
    }

    void init() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 4; j++)
                BG[i][j] = 1;
        BG[4][1] = 0;
        BG[4][2] = 0;
        SetSize(chess[0], 1, 1, "兵");
        SetPos(chess[0], 4, 0);
        SetSize(chess[1], 1, 1, "兵");
        SetPos(chess[1], 3, 1);
        SetSize(chess[2], 1, 1, "兵");
        SetPos(chess[2], 3, 2);
        SetSize(chess[3], 1, 1, "兵");
        SetPos(chess[3], 4, 3);

        SetSize(chess[4], 1, 2, "张飞");
        SetPos(chess[4], 0, 0);
        SetSize(chess[5], 1, 2, "赵云");
        SetPos(chess[5], 0, 3);
        SetSize(chess[6], 1, 2, "马超");
        SetPos(chess[6], 2, 0);
        SetSize(chess[7], 1, 2, "黄忠");
        SetPos(chess[7], 2, 3);

        SetSize(chess[8], 2, 1, "关羽");
        SetPos(chess[8], 2, 1);
        SetSize(chess[9], 2, 2, "曹操");
        SetPos(chess[9], 0, 1);

        SetPos(step_text, 5, 0);
        SetPos(refresh, 5, 3);
    }

    void init_2() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 4; j++)
                BG[i][j] = 1;
        BG[4][0] = 0;
        BG[4][1] = 0;
        SetSize(chess[0], 1, 1, "兵");
        SetPos(chess[0], 0, 0);
        SetSize(chess[1], 1, 1, "兵");
        SetPos(chess[1], 0, 1);
        SetSize(chess[2], 1, 1, "兵");
        SetPos(chess[2], 0, 2);
        SetSize(chess[3], 1, 1, "兵");
        SetPos(chess[3], 0, 3);

        SetSize(chess[4], 1, 2, "张飞");
        SetPos(chess[4], 1, 2);
        SetSize(chess[5], 1, 2, "赵云");
        SetPos(chess[5], 1, 3);
        SetSize(chess[6], 1, 2, "马超");
        SetPos(chess[6], 3, 2);
        SetSize(chess[7], 1, 2, "黄忠");
        SetPos(chess[7], 3, 3);

        SetSize(chess[8], 2, 1, "关羽");
        SetPos(chess[8], 3, 0);
        SetSize(chess[9], 2, 2, "曹操");
        SetPos(chess[9], 1, 0);

        SetPos(step_text, 5, 0);
        SetPos(refresh, 5, 3);
    }

    void init_3() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 4; j++)
                BG[i][j] = 1;
        BG[4][1] = 0;
        BG[4][2] = 0;
        SetSize(chess[0], 1, 1, "兵");
        SetPos(chess[0], 0, 0);
        SetSize(chess[1], 1, 1, "兵");
        SetPos(chess[1], 0, 3);
        SetSize(chess[2], 1, 1, "兵");
        SetPos(chess[2], 3, 1);
        SetSize(chess[3], 1, 1, "兵");
        SetPos(chess[3], 3, 2);

        SetSize(chess[4], 1, 2, "张飞");
        SetPos(chess[4], 1, 0);
        SetSize(chess[5], 1, 2, "赵云");
        SetPos(chess[5], 1, 3);
        SetSize(chess[6], 1, 2, "马超");
        SetPos(chess[6], 3, 0);
        SetSize(chess[7], 1, 2, "黄忠");
        SetPos(chess[7], 3, 3);

        SetSize(chess[8], 2, 1, "关羽");
        SetPos(chess[8], 2, 1);
        SetSize(chess[9], 2, 2, "曹操");
        SetPos(chess[9], 0, 1);

        SetPos(step_text, 5, 0);
        SetPos(refresh, 5, 3);
    }

    void init_4() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 4; j++)
                BG[i][j] = 1;
        BG[4][0] = 0;
        BG[4][2] = 0;
        SetSize(chess[0], 1, 1, "兵");
        SetPos(chess[0], 1, 2);
        SetSize(chess[1], 1, 1, "兵");
        SetPos(chess[1], 2, 0);
        SetSize(chess[2], 1, 1, "兵");
        SetPos(chess[2], 3, 0);
        SetSize(chess[3], 1, 1, "兵");
        SetPos(chess[3], 2, 1);

        SetSize(chess[4], 1, 2, "张飞");
        SetPos(chess[4], 1, 3);
        SetSize(chess[5], 1, 2, "赵云");
        SetPos(chess[5], 2, 2);
        SetSize(chess[6], 1, 2, "马超");
        SetPos(chess[6], 3, 1);
        SetSize(chess[7], 1, 2, "黄忠");
        SetPos(chess[7], 3, 3);

        SetSize(chess[8], 2, 1, "关羽");
        SetPos(chess[8], 0, 2);
        SetSize(chess[9], 2, 2, "曹操");
        SetPos(chess[9], 0, 0);

        SetPos(step_text, 5, 0);
        SetPos(refresh, 5, 3);
    }
}


