package pl.edu.agh.fis.model.activity.user;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.builder.dto.user.UserDTOBuilder;
import pl.edu.agh.fis.rest.user.UserClient;

/**
 * Created by wemstar on 2016-04-03.
 */
@EActivity(R.layout.activity_create_user)
public class CreateUserActivity extends AppCompatActivity {

    @ViewById
    EditText login;

    @ViewById
    EditText password;

    @ViewById
    EditText passwordRepeat;

    @ViewById
    EditText email;

    @RestService
    UserClient userClient;

    @Click(R.id.register_button)
    void registerClicked() {
        validateForm();
        registerUser();
    }

    void validateForm(){

    }

    @Background
    void registerUser() {
        Object obj = userClient.createUser(UserDTOBuilder.anUserDTO()
                .login(login.getText().toString())
                .password(password.getText().toString())
                .email(email.getText().toString())
                .build());
    }

}
