package by.argyment.gymapp.greeting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import by.argyment.gymapp.R;
import by.argyment.gymapp.base.BaseActivity;
import by.argyment.gymapp.databinding.ActivityGreetBinding;
import by.argyment.gymapp.extra.Strings;

/**
 * @author Olga Rudzko
 *         This class provides registration and authorization
 */

public class GreetActivity extends BaseActivity {

    SharedPreferences preferences;
    public static final String SHARED= Strings.SHARED;

    GreetModel model;
    EditText username;
    EditText email;
    EditText password;
    TextView message;

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
    protected void onResume() {
        super.onResume();
        preferences=getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        model.savedMail=preferences.getString(GreetModel.NAME, null);
        model.savedPass=preferences.getString(GreetModel.PASS, null);
    }

    @Override
    public void onBackPressed() {
        Intent bye = new Intent(Intent.ACTION_MAIN);
        bye.addCategory(Intent.CATEGORY_HOME );
        bye.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(bye);
    }
}
