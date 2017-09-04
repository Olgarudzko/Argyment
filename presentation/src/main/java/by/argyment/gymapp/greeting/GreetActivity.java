package by.argyment.gymapp.greeting;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import by.argyment.gymapp.R;
import by.argyment.gymapp.base.BaseActivity;
import by.argyment.gymapp.databinding.ActivityGreetBinding;
import by.argyment.gymapp.profile.ProfileActivity;

/**
 * @author Olga Rudzko
 *         This class provides registration and authorization
 */

public class GreetActivity extends BaseActivity {

    private EditText username;
    private EditText email;
    private EditText password;
    private TextView message;
    private GreetModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        model = new GreetModel();
        this.viewModel = model;
        ActivityGreetBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_greet);
        binding.setGreet(model);
        super.onCreate(savedInstanceState);
        username = binding.username;
        email = binding.email;
        password = binding.password;
        message = binding.notifyMessage;
    }

    public void login(View view) {
        Editable b;
        Editable c;
        if ((b = email.getText()) != null
                && (c = password.getText()) != null) {
            model.setEmail(b.toString());
            model.setPassword(c.toString());
            model.findUser();
        } else {
            message.setText(R.string.email_password);
        }
    }

    public void signIn(View view){
        Editable a;
        Editable b;
        Editable c;
        if ((a = username.getText()) != null
                && (b = email.getText()) != null
                && (c = password.getText()) != null) {

            if (a.toString().matches("\\w{4,10}") && c.toString().matches("\\w{5,10}")) {
                if (b.toString().matches("[-\\w\\.]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}")) {
                    model.setName(a.toString());
                    model.setEmail(b.toString());
                    model.setPassword(c.toString());
                    model.addUser();
                    model.done.set(true);
                } else {
                    message.setText(R.string.check_email);
                }
            } else {
                message.setText(R.string.name_password);
            }

        } else {
            message.setText(R.string.fill_fields);
        }
    }

    public void loadProfile(View view) {
        boolean isCheckedIn=false;
        if (view.getId()==R.id.checkin) isCheckedIn=true;
        Intent intent = new Intent(GreetActivity.this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.USERMAIL, model.email.get());
        intent.putExtra(ProfileActivity.CHECKIN, isCheckedIn);
        goTo(intent);
    }
}
