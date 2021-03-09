package dc.test.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import butterknife.BindArray;
import butterknife.OnClick;
import dc.android.common.BridgeContext;
import dc.android.common.BridgeOpcode;
import dc.android.common.NetContext;
import dc.android.common.StatContext;
import dc.android.common.handler.CrashHandler;
import dc.android.common.net.WebUtils;
import dc.android.common.utils.AbsClickListener;
import dc.android.common.utils.AbsSingleClickListener;
import dc.android.common.utils.KeepInstance;
import dc.android.common.utils.ResourceUtils;
import dc.android.common.utils.SharePreferencesUtils;
import dc.android.common.utils.TaskUtils;
import dc.android.libs.PermissionUtils;
import dc.android.libs.permission.AbsPermissionCallback;
import dc.android.libs.stat.BehaviorInstance;
import dc.android.libs.stat.HooksStatUtils;
import dc.android.libs.stat.SlackInstance;
import dc.common.Logger;
import dc.test.sample.bridge.BaseSampleActivity;
import dc.test.sample.dog.view.activity.DogListActivity;
import dc.test.sample.domain.DogBean;

/**
 * @author senrsl
 * @ClassName: SampleActivity
 * @Package: dc.test.sample
 * @CreateTime: 2018/11/18 9:52 PM
 */
public class SampleActivity extends BaseSampleActivity {

    @BindArray(R.array.dogs)
    String[] dogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        KeepInstance.getInstance().setStatus(BridgeOpcode.YES);
        setShowStatus(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        setImmersiveStatusBar(true, Color.WHITE);
        //AutoMarginUtils.auto(mFrameLayoutContent);
        initBindView();
    }

    @Override
    protected void initLayout() {
        super.initLayout();
        tvTitle.setText(R.string.app_name);
        Logger.w(this, ResourceUtils.getString(R.string.app_name), 800);

        findViewById(R.id.btn_click).setOnClickListener(new AbsClickListener() {
            @Override
            protected void onSingleClick(View v) {
                Logger.w(SampleActivity.this, "single", 300);
            }

            @Override
            protected void onFastClick(View v) {
                Logger.w(SampleActivity.this, "fast", 300);
            }
        });

        findViewById(R.id.btn_click_single).setOnClickListener(new AbsSingleClickListener(3000) {
            @Override
            protected void onSingleClick(View v) {
                Logger.w(SampleActivity.this, "single", 300);
            }
        });
    }

    @Override
    protected void initData() {
        generateFromXml();
        initCrash();
    }


    @OnClick({R.id.btn_list_dog, R.id.btn_list_dog_wrapper, R.id.btn_add_dog, R.id.btn_display, R.id.btn_crash,
            R.id.btn_hooks, R.id.btn_ding, R.id.btn_ding_markdown, R.id.btn_slack_file, R.id.btn_tasks, R.id.btn_swipe,
            R.id.btn_permission, R.id.btn_permission_check, R.id.btn_net_post_json, R.id.btn_net_post, R.id.btn_net_get, R.id.btn_net_test,
            R.id.btn_banner, R.id.btn_call})
    public void onViewClickedContent(View v) {
        switch (v.getId()) {
            case R.id.btn_list_dog:
                DogListActivity.start(this, Constants.LIST_TYPE_SWIPE);
                break;
            case R.id.btn_list_dog_wrapper:
                DogListActivity.start(this, Constants.LIST_TYPE_SWIPE_WRAPPER);
                break;
            case R.id.btn_add_dog:
                break;
            case R.id.btn_crash:
                int i = 1 / 0;
                break;
            case R.id.btn_hooks:
                //先crash，再hook才会有数据
                BridgeContext.isDebug = false;
                BridgeContext.isReport = true;
                HooksStatUtils.hooks(getApplication());
                break;
            case R.id.btn_ding:
//                new SendManager(this, message -> Logger.w(message)).sendDingTalk(getClass().getCanonicalName());
//                new SendManager(this, message -> Logger.w(message)).sendDingTalk(new CollectManager(this).collect(getClass().getCanonicalName()));
                BridgeContext.isReport = true;
                //BehaviorInstance.getInstance().setEnable(false);
                BehaviorInstance.getInstance().init();
                BehaviorInstance.getInstance().sendDingTalk(getClass().getCanonicalName(), true);
                break;
            case R.id.btn_ding_markdown:
                BridgeContext.isReport = true;
                BehaviorInstance.getInstance().init();
                BehaviorInstance.getInstance().sendDingTalk(StatContext.KEY_DING_TYPE_MARKDOWN, generateMarkdown(), true);
                break;
            case R.id.btn_slack_file:
                SlackInstance.getInstance().setEnable(true);
                SlackInstance.getInstance().init(getApplicationContext());

//                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SENRSL/test.png";
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SENRSL/PXL_20210306_102224614.jpg";
                SlackInstance.getInstance().send("msg from sample", new File(path));
                break;
            case R.id.btn_tasks:
                BridgeContext.CLS_LOGIN = DisplayActivity.class.getCanonicalName();
                new TaskUtils().startLogin(this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP, R.string.app_name);
                break;
            case R.id.btn_swipe:
//                TestSwipeActivity.start(this);
                break;
            case R.id.btn_permission:
                //dc.android.libs.permission.TestActivity.start(this);
                PermissionUtils.with(this)
                        .permisson(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA)
                        .callback(new AbsPermissionCallback() {
                            @Override
                            public void onResult(boolean isAllGrant, boolean hasDenied, boolean hasRationale) {
                                Logger.w(getClass().getName(), isAllGrant, hasDenied, hasRationale, listGrant, listDenied, listRationale);
                            }
                        }).request();
                break;
            case R.id.btn_permission_check:
                boolean isAllGrant = PermissionUtils.with(this)
                        .permisson(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA})
                        .callback(new AbsPermissionCallback() {
                            @Override
                            public void onResult(boolean isAllGrant, boolean hasDenied, boolean hasRationale) {
                                Logger.w(getClass().getName(), isAllGrant, hasDenied, hasRationale, listGrant, listDenied, listRationale);
                            }
                        }).check();
                Logger.w(getClass().getSimpleName(), isAllGrant);
                break;
            case R.id.btn_net_post_json:
                new Thread(() -> {
                    try {
                        String result = WebUtils.doPostAsJson("https://hooks.slack.com/services/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F", "{'text':'啊啊啊啊'}");
                        Logger.w(getClass().getName(), result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
                break;
            case R.id.btn_net_post:
                new Thread(() -> {
                    try {
//                        String result = WebUtils.doPost("https://free-api.heweather.net/s6/weather/now", "location=auto_ip&key=b272a27633424bb59a4b12acbfb4ab39");
                        Map<String, Object> map = new HashMap<>();
                        map.put("location", "auto_ip");
                        map.put("key", "b272a27633424bb59a4b12acbfb4ab39");
                        map.put("sb", 1);
                        String result = WebUtils.doPost("https://free-api.heweather.net/s6/weather/now", map);
                        Logger.w(getClass().getName(), result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
                break;
            case R.id.btn_net_get:
                new Thread(() -> {
                    try {
//                        String result = WebUtils.doGet("https://free-api.heweather.net/s6/weather/now?location=auto_ip&key=b272a27633424bb59a4b12acbfb4ab39");
                        Map<String, Object> map = new HashMap<>();
                        map.put("location", "auto_ip");
                        map.put("key", "b272a27633424bb59a4b12acbfb4ab39");
                        map.put("sb", 1);
                        String result = WebUtils.doGet("https://free-api.heweather.net/s6/weather/now", map);
                        Logger.w(getClass().getName(), result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
                break;
            case R.id.btn_net_test:
                new Thread(() -> {
                    try {
                        //doGet
//                        String result = WebUtils.doGet("https://api.myip.com");
//                        Logger.w("get仅地址:" + result);
//                        String result1 = WebUtils.doGet("https://free-api.heweather.net/s6/weather/now?location=auto_ip&key=b272a27633424bb59a4b12acbfb4ab39");
//                        Logger.w("get参数直挂:" + result1);
//                        String result2 = WebUtils.doGet("https://free-api.heweather.net/s6/weather/now", "location=auto_ip&key=b272a27633424bb59a4b12acbfb4ab39", null);
//                        Logger.w("get参数msg:" + result2);
//                        Map<String, Object> map3 = new HashMap<>();
//                        map3.put("location", "auto_ip");
//                        map3.put("key", "b272a27633424bb59a4b12acbfb4ab39");
//                        map3.put("sb", 1);
//                        String result3 = WebUtils.doGet("https://free-api.heweather.net/s6/weather/now", map3);
//                        Logger.w("get参数map:" + result3);
//                        String result4 = WebUtils.doGet("https://free-api.heweather.net/s6/weather/now", "aaaaa=1&bbbb=2&cccc=3", map3);
//                        Logger.w("get参数map和msg:" + result4);
//                        String result5 = WebUtils.doGet("https://free-api.heweather.net/s6/weather/now?1=2", "aaaaa=1&bbbb=2&cccc=3", map3);
//                        Logger.w("get参数map和msg:" + result5);

                        //do post
//                        String result6 = WebUtils.doPost("https://free-api.heweather.net/s6/weather/now", "location=auto_ip&key=b272a27633424bb59a4b12acbfb4ab39");
//                        Logger.w("post msg" + result6);
//                        String result7 = WebUtils.doPost("https://free-api.heweather.net/s6/weather/now", map3);
//                        Logger.w("post map" + result7);
//                        String result8 = WebUtils.doPost(NetContext.CONTENT_TYPE_FORM_ENCODED, "https://free-api.heweather.net/s6/weather/now", "aaaa=1&bbb=2&ccc=3", map3);
//                        Logger.w("post msg and map" + result8);

                        Map<String, Object> map4 = new HashMap<>();
                        map4.put("sb", 1);
                        map4.put("token", "xoxb-71037xxxxx-18xxx5xxxx-Ko5x6zeNZFxxxxxxxG");
                        map4.put("content", "xxxx内容");
                        map4.put("channels", "C01QM5GRJxQ4");
                        map4.put("initial_comment", "initial msg show");
                        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/SENRSL/test.png");
                        Logger.w(file.getCanonicalPath(), file.length());
                        map4.put(file.getName(), file);
                        String result8 = WebUtils.doPost(NetContext.CONTENT_TYPE_FORM_DATA, "https://slack.com/api/files.upload", "aaaa=1&bbb=2&ccc=3", map4);
                        Logger.w("post msg and map" + result8);
//                        String result9 = WebUtils.doPost(NetContext.CONTENT_TYPE_FORM_DATA, "https://slack.com/api/files.upload", "aaaaaa", null);
//                        Logger.w("post msg no map" + result9);
//                        String result10 = WebUtils.doPost(NetContext.CONTENT_TYPE_FORM_DATA, "https://slack.com/api/files.upload", null, map4);
//                        Logger.w("post map no msg" + result10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
                break;
            case R.id.btn_banner:
//                TestBannerActivity.start(this);
                break;
            case R.id.btn_call:
                TaskUtils.call(this, "10086");
                break;
            default:
                DisplayActivity.start(this);
                break;
        }
    }

    //先hook，再crash才会发
    private void initCrash() {
        String env = null;
        JSONObject jo = new JSONObject();
        try {
            jo.put("className", getClass().getName());
            jo.put("currentTime", System.currentTimeMillis());
            env = jo.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharePreferencesUtils sp = new SharePreferencesUtils(this);
        sp.saveSharedPreferencesValue(BridgeContext.KEY_ENV, env);
        //VsfApplication中，先!isDebug，再初始化
        SlackInstance.getInstance().init(getApplication());
        CrashHandler.getInstance().init(getApplicationContext(), cbCrash);
        BridgeContext.CLS_WELCOME = SampleActivity.class.getCanonicalName();
//        SlackInstance.getInstance().send(getClass().getSimpleName());
    }

    private void generateFromXml() {
        Logger.w(dogs);
        int i = 0;
        for (String dog : dogs) {
            //Logger.w(dog);
            String[] dogValue = dog.split(",");
            //Logger.w(dogValue);
            DogBean bean = new DogBean(dogValue[0], dogValue[1] + i++, dogValue[2]);
            Constants.listDogs.add(bean);
        }
    }

    private String generateMarkdown() {
        StringBuilder sb = new StringBuilder();
//        sb.append("#### 杭州天气 @150XXXXXXXX \n> 9度，西北风1级，空气良89，相对温度73%\n> ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n> ###### 10点20分发布 [天气](https://www.dingtalk.com) \n");
        sb.append("## ").append(ResourceUtils.getString(R.string.app_name)).append("\n");
        sb.append(getClass().getCanonicalName()).append("\n");
        sb.append("- ").append(getClass().getName()).append("\n");
        sb.append("- ").append(getClass().getSimpleName()).append("\n");
        sb.append("- ").append(getClass().getPackage().getName()).append("\n");
//        try {
//             图片大于10k就超限了，有啥用
//            ///storage/emulated/0
//            String path = Environment.getExternalStorageDirectory().getCanonicalPath() + "/SENRSL/test.png";
////            String path = Environment.getExternalStorageDirectory().getCanonicalPath() + "/SENRSL/PXL_20210306_102224614.jpg";
//            sb.append("![pic](data:image/png;base64," + pic2base64(path) + ")");
//        } catch (IOException e) {
//            e.printStackTrace();
//            sb.append(e.getMessage());
//        }
        return sb.toString();
    }

    private String pic2base64(String path) throws IOException {
        InputStream is = null;
        byte[] data;
        String result;

        try {
            is = new FileInputStream(path);
            data = new byte[is.available()];
            is.read(data);
            result = Base64.encodeToString(data, Base64.DEFAULT);
        } finally {
            if (null != is) is.close();
        }
        return result;
    }

    private CrashHandler.Callback cbCrash = (ex, info) -> SlackInstance.getInstance().send(getClass().getName() + BridgeContext.TAB + info);

}
