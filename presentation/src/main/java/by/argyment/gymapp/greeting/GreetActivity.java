package by.argyment.gymapp.greeting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import by.argyment.gymapp.R;
import by.argyment.gymapp.base.BaseActivity;
import by.argyment.gymapp.databinding.ActivityGreetBinding;
import by.argyment.gymapp.extra.Strings;

/**
 * @author Olga Rudzko
 *         This class provides registration and authorization
 */

public class GreetActivity extends BaseActivity {

    ActivityGreetBinding binding;

    GreetModel model;
    EditText username;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        model = new GreetModel(this);
        this.viewModel = model;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_greet);
        binding.setGreet(model);
        super.onCreate(savedInstanceState);
        Glide.with(this).load(R.drawable.loading).into(new GlideDrawableImageViewTarget(binding.loading));
        username = binding.username;
        email = binding.email;
        password = binding.password;
    }

    @Override
    public void onBackPressed() {
        Intent bye = new Intent(Intent.ACTION_MAIN);
        bye.addCategory(Intent.CATEGORY_HOME );
        bye.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(bye);
    }
}
