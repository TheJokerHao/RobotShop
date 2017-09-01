package jiubeirobot.com.robotshop.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import jiubeirobot.com.robotshop.data.net.INetResult;
import jiubeirobot.com.robotshop.data.net.IUIBase;
import jiubeirobot.com.robotshop.utils.L;
import jiubeirobot.com.robotshop.utils.T;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/18.
 * 创建内容：这是fragment的基类  用以对所有的fragment进行监控
 * ==========================
 */

public abstract class BaseFragment extends Fragment implements IUIBase,INetResult {
    BaseActivity activity;
    private View theContentView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (BaseActivity) getActivity();
        theContentView = inflater.inflate(pageLayout(), null);
        ButterKnife.bind(this, theContentView);
        initTitle();
        initView();
        theContentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (getActivity().getCurrentFocus() != null && getActivity().getCurrentFocus().getWindowToken() != null) {
                        manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                return false;
            }
        });
        return theContentView;
    }


    @Override
    public void onResume() {
        super.onResume();
        L.i("---------------------当前运行的类"+ getClass().getName());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void requestBefore(int flag) {
        showHUD();
    }

    @Override
    public void onError(String error, int flag) {
        dismissHUD();
        T.showLong(error);
    }

    @Override
    public void dismissHUD() {
        if (activity != null) {
            activity.dismissHUD();
        }
    }

    @Override
    public void showHUD() {
        if (activity != null) {
            activity.showHUD();
        }
    }

}
