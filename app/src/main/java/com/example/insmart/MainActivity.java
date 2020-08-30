package com.example.insmart;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.MediaStore;
import android.text.Layout;
import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //각 메뉴에 따른 URL 전역변수(iter로 시작하는 페이지는 임의로 제작된 페이지)
    public static String 여행지도_url="http://iter7.jbnu.ac.kr/daewon/good/map.html";
    public static String 스토리_url="http://iter7.jbnu.ac.kr/daewon/good/two2.html";
    public static String 추천코스_url="http://iter7.jbnu.ac.kr/daewon/good/three.html";
    public static String 축제_url="http://iter7.jbnu.ac.kr/daewon/good/four.html";
    public static String 쿠폰_url="http://in-smart.kr/new/coupon.html";
    public static String 월간_url="http://in-smart.kr/ebook/hanokmaeul48/index.html";
    public static String 메인_url="http://iter7.jbnu.ac.kr/daewon/good/map.html";

    private long time= 0;
    private WebView mWebView;
    private String myUrl = 메인_url;
    //test를 위한 간단한 페이지

    private Button main_1,main_2,main_3,main_4,main_5,main_6;
    private Button sub_1,sub_2,sub_3,sub_4,sub_5,sub_6;
    private TextView content_head;
    private LinearLayout submenu;
    private LinearLayout mainmenu;
    private String menu_status = "sub_close";
    FloatingActionButton fab;
    //메뉴클릭시 수행되는 함수 정의
//    menu_mode =1)  메인메뉴는 열어라 , 서브메뉴는 닫혀있다, menu_status = sub_close 이다.
//    menu_mode =0) 위와 반대
//    url ==  각 메뉴마다 필요한 url 을 넣으면 된다.

    public void menuClick_Event(int menu_mode ,String content_name){

        String url="";

        if (menu_mode == 1){
            mainmenu.setVisibility(mainmenu.VISIBLE);
            submenu.setVisibility(submenu.GONE);
            menu_status = "sub_close";
        }
        else{
            mainmenu.setVisibility(mainmenu.GONE);
            submenu.setVisibility(submenu.VISIBLE);
            menu_status = "sub_open";
        }


        //head text값 변경
        if(content_name == "메인메뉴"){
            content_head.setText("[이벤트]앱 오픈 여행지도 무료");
            //나중에 db에서 가져올 값 넣기
        }
        else{
            content_head.setText(content_name);
        }
        //각 content_name에따른 fab설정, url 설정
        switch (content_name) {
            case "여행지도":
                fab.show();
                url = 여행지도_url;
                break;
            case"스토리여행":
                url =스토리_url;
                fab.hide();
                break;
            case"추천코스":
                url =추천코스_url;
                fab.hide();
                break;
            case"축제 & 행사":
                url =축제_url;
                fab.hide();
                break;
            case"E-쿠폰":
                url =쿠폰_url;
                fab.hide();
                break;
            case"월간한옥마을":
                url =월간_url;
                fab.hide();
                break;
            case"메인메뉴":
                url =메인_url;
                fab.hide();
                break;
            default:
                Toast.makeText(getApplicationContext(),"잘못된 접근 입니다.",Toast.LENGTH_SHORT).show();
                break;
        }

        //url load
        if(url == myUrl){
            //Toast.makeText(getApplicationContext(),"중복 페이지",Toast.LENGTH_SHORT).show();
            //중복페이지 처리
        }
        else{
            myUrl = url;
            mWebView.loadUrl(myUrl);
            //Toast.makeText(getApplicationContext(),"다른 페이지",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //웹뷰 세팅
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl(myUrl); // 접속 URL
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClientClass());

        //버튼 find 하기
        main_1 = (Button) findViewById(R.id.main_btn_1);
        main_2 = (Button) findViewById(R.id.main_btn_2);
        main_3 = (Button) findViewById(R.id.main_btn_3);
        main_4 = (Button) findViewById(R.id.main_btn_4);
        main_5 = (Button) findViewById(R.id.main_btn_5);
        main_6 = (Button) findViewById(R.id.main_btn_6);

        sub_1 = (Button) findViewById(R.id.sub_btn_1);
        sub_2 = (Button) findViewById(R.id.sub_btn_2);
        sub_3 = (Button) findViewById(R.id.sub_btn_3);
        sub_4 = (Button) findViewById(R.id.sub_btn_4);
        sub_5 = (Button) findViewById(R.id.sub_btn_5);
        sub_6 = (Button) findViewById(R.id.sub_btn_6);

        //메뉴
        submenu = (LinearLayout)findViewById(R.id.submenu);
        mainmenu = (LinearLayout)findViewById(R.id.mainmenu);

        //컨텐츠 헤더
        content_head = (TextView)findViewById(R.id.content_head);



        //햄버거 메뉴
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        //메인메뉴  클릭시 이벤트
        main_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick_Event(0,"여행지도");
            }
        });
        main_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick_Event(0,"스토리여행");
            }
        });
        main_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick_Event(0,"추천코스");
            }
        });
        main_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick_Event(0,"축제 & 행사");
            }
        });
        main_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick_Event(0,"E-쿠폰");
            }
        });
        main_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick_Event(0,"월간한옥마을");
            }
        });

        //서브 메뉴 클릭시 이벤트
        sub_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick_Event(0,"여행지도");
            }
        });
        sub_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick_Event(0,"스토리여행");
            }
        });
        sub_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick_Event(0,"추천코스");
            }
        });
        sub_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick_Event(0,"축제 & 행사");
            }
        });
        sub_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick_Event(0,"E-쿠폰");
            }
        });
        sub_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick_Event(0,"월간한옥마을");
            }
        });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(menu_status == "sub_open"){
//            Toast.makeText(getApplicationContext(),"메인메뉴",Toast.LENGTH_SHORT).show();
            menuClick_Event(1,"메인메뉴");
        }
        else {
            //두번 누를시 종료
            if(System.currentTimeMillis()-time>=2000){
                time=System.currentTimeMillis();
                Toast.makeText(getApplicationContext(),"뒤로 버튼을 한번 더 누르면 종료합니다.",Toast.LENGTH_SHORT).show();
            }else if(System.currentTimeMillis()-time<2000){
                finish();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //햄버거 메뉴 클릭시
        if (id == R.id.map) {
            menuClick_Event(0,"여행지도");
        } else if (id == R.id.story) {
            menuClick_Event(0,"스토리여행");
        } else if (id == R.id.recommend) {
            menuClick_Event(0,"추천코스");
        } else if (id == R.id.festivel) {
            menuClick_Event(0,"축제 & 행사");
        } else if (id == R.id.coupon) {
            menuClick_Event(0,"E-쿠폰");
        } else if (id == R.id.month) {
            menuClick_Event(0,"월간한옥마을");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("check URL",url);
            view.loadUrl(url);
            return true;
        }
    }

}
