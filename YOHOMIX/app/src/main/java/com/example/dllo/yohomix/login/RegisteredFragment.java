package com.example.dllo.yohomix.login;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseFragment;

/**
 * Created by dllo on 16/12/6.
 */

public class RegisteredFragment extends BaseFragment implements View.OnClickListener, TextWatcher {
    private ImageView ivBack, ivDelNum;
    private EditText etNum;
    private TextView tvNext;

    @Override
    protected int setLayout() {
        return R.layout.registered_fragment;
    }

    @Override
    protected void initView(View view) {
        ivBack = bindView(R.id.iv_back_registered);
        ivDelNum = bindView(R.id.iv_registered_del_num);
        etNum = bindView(R.id.et_registered_num);
        tvNext = bindView(R.id.tv_next);

    }

    @Override
    protected void initData() {
        setClick(this,ivBack,ivDelNum);
        etNum.addTextChangedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_registered:
                getActivity().finish();
                break;
            case R.id.iv_registered_del_num:
                etNum.setText("");
                break;
            case R.id.et_registered_num:
                break;
            case R.id.tv_next:
                break;
        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (!etNum.getText().toString().isEmpty()) {
            ivDelNum.setVisibility(View.VISIBLE);
            tvNext.setBackgroundColor(Color.GREEN);
        } else {
            ivDelNum.setVisibility(View.INVISIBLE);
            tvNext.setBackgroundColor(Color.GRAY);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
