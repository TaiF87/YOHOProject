package com.example.dllo.yohomix.login;

import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseFragment;
import com.wevey.selector.dialog.DialogOnItemClickListener;
import com.wevey.selector.dialog.MDSelectionDialog;
import com.wevey.selector.dialog.NormalAlertDialog;

import java.util.ArrayList;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/12/6.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener, TextWatcher {
    private boolean seeShow = false;
    private NormalAlertDialog normalAlertDialog;
    private EditText etNum, etCoded;
    private TextView tvInLogin, tvForgetCoded;
    private LinearLayout llDialog, llShow,llRegistered;
    private ImageView ivClose, ivBackNum, ivBackPrassword,
            ivVisibility, ivWechat, ivWeibo, ivQQ, ivMore, ivF, ivUp;
    private MDSelectionDialog mMdSelectionDialog;

    @Override
    protected int setLayout() {
        return R.layout.login_fragment;
    }

    @Override
    protected void initView(View view) {
        etNum = bindView(R.id.et_login_num);
        etCoded = bindView(R.id.et_login_coded);
        tvInLogin = bindView(R.id.tv_inlogin);
        tvForgetCoded = bindView(R.id.forget_coded);
        llDialog = bindView(R.id.ll_login_dialog);
        llShow = bindView(R.id.ll_show);
        llRegistered = bindView(R.id.ll_login_registered);
        ivClose = bindView(R.id.iv_login_close);
        ivBackNum = bindView(R.id.iv_back_num);
        ivBackPrassword = bindView(R.id.iv_back_prassword);
        ivVisibility = bindView(R.id.iv_see_show);
        ivWechat = bindView(R.id.wechat);
        ivWeibo = bindView(R.id.weibo);
        ivQQ = bindView(R.id.qq);
        ivMore = bindView(R.id.more);
        ivF = bindView(R.id.f);
        ivUp = bindView(R.id.up);
    }

    @Override
    protected void initData() {
        setClick(this, etCoded, tvInLogin, tvForgetCoded, llDialog,
                llRegistered, ivClose, ivBackNum, ivBackPrassword,
                ivVisibility, ivWechat, ivWeibo, ivQQ, ivMore, ivF, ivUp);
        etNum.addTextChangedListener(this);
        etCoded.addTextChangedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_inlogin:
                break;
            case R.id.forget_coded:
//                codedDialog();
                break;
            case R.id.ll_login_dialog:
                loginDialog();
                break;
            case R.id.ll_login_registered:
                break;
            case R.id.iv_login_close:
                getActivity().finish();
                break;
            case R.id.iv_back_num:
                etNum.setText("");
                break;
            case R.id.iv_back_prassword:
                etCoded.setText("");
                break;
            case R.id.iv_see_show:
                seeShow();
                break;
            case R.id.wechat:
                break;
            case R.id.weibo:
                break;
            case R.id.qq:
                login();
                break;
            case R.id.more:
                llShow.setVisibility(View.VISIBLE);
                ivMore.setVisibility(View.GONE);
                ivQQ.setVisibility(View.VISIBLE);
                break;
            case R.id.f:
                break;
            case R.id.up:
                llShow.setVisibility(View.GONE);
                ivQQ.setVisibility(View.GONE);
                ivMore.setVisibility(View.VISIBLE);
                break;

        }
    }

    private void login() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.authorize();
        qq.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                PlatformDb platformDb = platform.getDb();
                Intent intent = new Intent();
                intent.putExtra("name", platformDb.getUserName());
                intent.putExtra("icon", platformDb.getUserIcon());
                getActivity().setResult(1, intent);
                getActivity().finish();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
    }

    private void seeShow() {
        seeShow = !seeShow;
        if (seeShow) {
            ivVisibility.setImageResource(R.mipmap.login_password_see_icon);
            etCoded.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//显示密码
            etCoded.setSelection(etCoded.length());

        } else {
            ivVisibility.setImageResource(R.mipmap.login_password_unsee_icon);
            etCoded.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            etCoded.setSelection(etCoded.length());

        }

    }

    private void codedDialog() {
        final MDSelectionDialog.Builder builder = new MDSelectionDialog.Builder(getContext())
                .setCanceledOnTouchOutside(true)
                .setItemTextColor(R.color.black_light)
                .setItemHeight(50).setItemWidth(0.8f).setItemTextSize(20)
                .setCanceledOnTouchOutside(true)
                .setOnItemListener(new DialogOnItemClickListener() {
                    @Override
                    public void onItemClick(Button button, int position) {

                    }
                });
        ArrayList<String> data = new ArrayList<>();
        data.add("忘记密码");   data.add("通过手机找回密码");   data.add("通过邮箱找回密码");

    }

    private void loginDialog() {
        NormalAlertDialog.Builder builder = new NormalAlertDialog.Builder(getContext())
                .setHeight(0.29f).setWidth(0.85f).setTitleVisible(true)
                .setTitleText("YoHo!Family").setTitleTextSize(32).setTitleTextColor(R.color.black)
                .setContentText("Yoho!Family账号可登录YohoBuy!有货、Yoho!Now及SHOW").setContentTextColor(R.color.black)
                .setSingleMode(true).setTitleTextSize(16).setTitleTextColor(R.color.black)
                .setSingleButtonText("确定").setTitleTextColor(R.color.black).setCanceledOnTouchOutside(true)
                .setSingleListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        normalAlertDialog.dismiss();
                    }
                });

        normalAlertDialog = new NormalAlertDialog(builder);
        normalAlertDialog.show();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (!etNum.getText().toString().isEmpty()) {
            ivBackNum.setVisibility(View.VISIBLE);
        } else {
            ivBackNum.setVisibility(View.INVISIBLE);
        }

        if (!etCoded.getText().toString().isEmpty()) {
            ivBackPrassword.setVisibility(View.VISIBLE);
        } else {
            ivBackPrassword.setVisibility(View.INVISIBLE);
        }

        if (!etNum.getText().toString().isEmpty() && !etCoded.getText().toString().isEmpty()) {
            tvInLogin.setBackgroundColor(Color.GREEN);
        } else {
            tvInLogin.setBackgroundColor(Color.GRAY);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
