package com.ai.cwf.changeicon;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/*
参考文章
* http://mp.weixin.qq.com/s?__biz=MzA3MDMyMjkzNg==&mid=2652262152&idx=1&sn=afc655b9bd560610f378727b31b0e687&chksm=84dc709fb3abf989372d7346386dfba26fb18621e0b5ac713a191314b5e37596cb0380c0a772&mpshare=1&scene=23&srcid=1230AcE9RUGKOqisEOc05p55#rd
*
* */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ComponentName mDefault;
    private ComponentName mDoubleW1;
    private ComponentName mDoubleW2;
    private ComponentName mDoubleM1;
    private ComponentName mDoubleM2;
    private PackageManager mPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDefault = getComponentName();
        mDoubleW1 = new ComponentName(
                getBaseContext(),
                "com.ai.cwf.changeicon.TestW1");
        mDoubleW2 = new ComponentName(
                getBaseContext(),
                "com.ai.cwf.changeicon.TestW2");
        mDoubleM1 = new ComponentName(
                getBaseContext(),
                "com.ai.cwf.changeicon.TestM1");
        mDoubleM2 = new ComponentName(
                getBaseContext(),
                "com.ai.cwf.changeicon.TestM1");
        mPm = getApplicationContext().getPackageManager();
        findViewById(R.id.reset).setOnClickListener(this);
        findViewById(R.id.setw).setOnClickListener(this);
        findViewById(R.id.setm).setOnClickListener(this);
        findViewById(R.id.addw).setOnClickListener(this);
        findViewById(R.id.addm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset:
                disableComponent(mDoubleW1);
                disableComponent(mDoubleW2);
                disableComponent(mDoubleM1);
                disableComponent(mDoubleM2);
                enableComponent(mDefault);
                break;
            case R.id.setw:
                disableComponent(mDefault);
                disableComponent(mDoubleM1);
                enableComponent(mDoubleW1);
                break;
            case R.id.setm:
                disableComponent(mDefault);
                disableComponent(mDoubleW1);
                enableComponent(mDoubleM1);
                break;
            case R.id.addw:
                enableComponent(mDoubleW2);
                break;
            case R.id.addm:
                enableComponent(mDoubleM2);
                break;
        }
    }

    private void enableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void disableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
