package pl.edu.agh.fis;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;

import pl.edu.agh.fis.activity.main.MainActivity_;
import pl.edu.agh.fis.activity.user.CreateUserActivity_;
import pl.edu.agh.fis.rest.login.LoginClient;
import pl.edu.agh.fis.utils.TokenKeeper;

/**
 * A login screen that offers login via email/password.
 */
@EActivity(R.layout.activity_login)
@OptionsMenu(R.menu.global)
public class LoginActivity extends AppCompatActivity {

    // UI references.
    @ViewById(R.id.email)
    AutoCompleteTextView mEmailView;
    @ViewById(R.id.password)
    EditText mPasswordView;
    @ViewById(R.id.login_progress)
    View mProgressView;
    @ViewById(R.id.login_form)
    View mLoginFormView;

    @RestService
    LoginClient loginClient;
    @Bean
    TokenKeeper tokenKeeper;

    @Click(R.id.email_sign_in_button)
    void onEmailSingInButtonClicked() {
        attemptLogin();
    }

    @Click(R.id.register_button)
    void onRegisterButtonClicked() {
        showRegisterActivity();
    }

    @EditorAction(R.id.password)
    public boolean onPasswordEditorAction(TextView tv, int actionId) {
        if (actionId == R.id.login || actionId == EditorInfo.IME_NULL) {
            attemptLogin();
            return true;
        }
        return false;
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();

        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            performLogin();
        }
    }

    @Background
    void performLogin() {
        loginClient.setHttpBasicAuth(mEmailView.getText().toString(), mPasswordView.getText().toString());
        ResponseEntity<Void> response = loginClient.login();
        String token = response.getHeaders().get("x-auth-token").get(0);
        tokenKeeper.setToken(token);
        showProgress(false);
        goToNewPage();
    }


    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    //region GUI

    /**
     * Shows the progress UI and hides the login form.
     */
    @UiThread
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, user these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    private void showRegisterActivity() {
        Intent intent = new Intent(this, CreateUserActivity_.class);
        startActivity(intent);
    }

    @UiThread
    void goToNewPage() {
        Intent intent = new Intent(this, MainActivity_.class);
        startActivity(intent);
    }
    //endregion
}

