package by.argyment.gymapp.greeting;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import by.argyment.gymapp.R;
import by.argyment.gymapp.base.BaseActivity;
import by.argyment.gymapp.databinding.ActivityGreetBinding;

/**
 * @author Olga Rudzko
 *         This class provides registration and authorization
 */

public class GreetActivity extends BaseActivity {

    EditText username;
    EditText email;
    EditText password;
    TextView message;
    GreetModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        model = new GreetModel();
        this.viewModel = model;
        ActivityGreetBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_greet);
        binding.setGreet(model);
        super.onCreate(savedInstanceState);
        GreetHandler handler=new GreetHandler(this, model);
        binding.setHandler(handler);
        username = binding.username;
        email = binding.email;
        password = binding.password;
        message = binding.notifyMessage;
    }


    @Override
    public void onBackPressed() {
        Intent bye = new Intent(Intent.ACTION_MAIN);
        bye.addCategory( Intent.CATEGORY_HOME );
        bye.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(bye);
    }
}
