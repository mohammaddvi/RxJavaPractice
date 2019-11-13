package com.practice.rxjavatest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

import static android.text.TextUtils.isEmpty;
import static android.util.Patterns.EMAIL_ADDRESS;

public class FormValidationFragment extends Fragment {
    @BindView(R.id.btn_demo_form_valid)
    TextView _btnValidIndicator;

    @BindView(R.id.demo_combl_email)
    EditText _email;

    @BindView(R.id.demo_combl_password)
    EditText _password;

    @BindView(R.id.demo_combl_num)
    EditText _number;

    private DisposableSubscriber<Boolean> _disposableObserver = null;
    private Flowable<CharSequence> _emailChangeObservable;
    private Flowable<CharSequence> _numberChangeObservable;
    private Flowable<CharSequence> _passwordChangeObservable;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.form_validation_fragment, container, false);
        unbinder = ButterKnife.bind(this, layout);
        _emailChangeObservable = RxTextView.textChanges(_email).skip(1).toFlowable(BackpressureStrategy.LATEST);
        _passwordChangeObservable = RxTextView.textChanges(_password).skip(1).toFlowable(BackpressureStrategy.LATEST);
        _numberChangeObservable = RxTextView.textChanges(_number).skip(1).toFlowable(BackpressureStrategy.LATEST);

        _combineLatestEvent();
        return layout;

    }

    private void _combineLatestEvent() {
        _disposableObserver = new DisposableSubscriber<Boolean>() {
            @Override
            public void onNext(Boolean formValid) {
                if (formValid) {
                    _btnValidIndicator.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else
                    _btnValidIndicator.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray));
            }

            @Override
            public void onError(Throwable e) {
                //TODO complete this

            }

            @Override
            public void onComplete() {
                //TODO complete this

            }
        };
        Flowable.combineLatest(_emailChangeObservable,
                _passwordChangeObservable,
                _numberChangeObservable,
                (newEmail, newPassword, newNumber) -> {
                    boolean emailValid = !isEmpty(newEmail) && EMAIL_ADDRESS.matcher(newEmail).matches();
                    if (!emailValid) {
                        _email.setError("Invalid Email");
                    }
                    boolean passValid = !isEmpty(newPassword) && newPassword.length() > 8;
                    if (!passValid) {
                        _password.setError("Invalid Email");
                    }
                    boolean numValid = !isEmpty(newNumber);
                    if (numValid) {
                        int num = Integer.parseInt(newNumber.toString());
                        numValid = num > 0 && num <= 100;
                    }
                    if (!numValid) {
                        _number.setError("Invalid Number!");
                    }
                    return emailValid && passValid && numValid;

                }).subscribe(_disposableObserver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        _disposableObserver.dispose();
    }
}
