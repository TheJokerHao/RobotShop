package jiubeirobot.com.robotshop.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.data.net.INetResult;
import jiubeirobot.com.robotshop.utils.L;
import jiubeirobot.com.robotshop.utils.T;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/18.
 * 创建内容：定义的Dialogfragment的基类
 * ==========================
 */

public abstract class BaseDialogFragment extends DialogFragment implements INetResult {
    private Dialog dialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(
                layout(), null);
        dialog = new Dialog(getContext(), R.style.DeleteStyle);
        dialog.setContentView(view);
        ButterKnife.bind(this, view);
        view.findViewById(R.id.lLayout_bg).setLayoutParams(setLayoutParams());
        initView();

        return dialog;
    }

    @Override
    public void requestBefore(int flag) {
        ((BaseActivity) getActivity()).showHUD();
    }

    @Override
    public void onError(String error, int flag) {
        ((BaseActivity) getActivity()).dismissHUD();
        T.showToCenter(error);
    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {

    }

    @Override
    public void onResume() {
        super.onResume();
        L.i("----当前运行的类：" + getClass().getName());//The Current Runing Class
    }

    /**
     * 描述      :Dialog布局
     * 方法名    :  layout
     * param    :   void
     * 返回类型  : int 布局ID
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
    protected abstract int layout();

    /**
     * 描述      :设置布局参数
     * 方法名    :setLayoutParams
     * param    :void
     * 返回类型  :ViewGroup.LayoutParams 布局参数
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
    protected abstract ViewGroup.LayoutParams setLayoutParams();

    /**
     * 描述      :初始化页面
     * 方法名    :initView
     * param    :void
     * 返回类型  :void
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
    protected abstract void initView();

}
