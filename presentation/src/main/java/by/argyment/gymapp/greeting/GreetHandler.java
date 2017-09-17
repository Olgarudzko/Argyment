package by.argyment.gymapp.greeting;

import android.content.Intent;
import android.text.Editable;
import android.view.View;

import by.argyment.gymapp.R;
import by.argyment.gymapp.extra.Strings;
import by.argyment.gymapp.profile.ProfileActivity;

/**
 * @author Olga Rudzko
 */

public class GreetHandler {

    private GreetActivity activity;
    private GreetModel model;

    public GreetHandler(GreetActivity activity, GreetModel model){
        this.activity=activity;
        this.model=model;
    }


    public void login(View view) {
        Editable b;
        Editable c;
        if ((b = activity.email.getText()) != null
                && (c = activity.password.getText()) != null) {

            if (model.users.containsKey(b.toString())){
                if (model.users.get(b.toString()).equals(c.toString())){
                    model.setEmail(b.toString());
                    model.setPassword(c.toString());
                    model.done.set(true);
                    if(model.checkins.get(b.toString())!=0) {
                        model.checkin.set((int) (System.currentTimeMillis() - model.checkins.get(b.toString())));
                    } else {
                        model.checkin.set(81000000);
                    }
                } else{
                    activity.message.setText(R.string.wrong_password);
                }
            }else{
                activity.message.setText(R.string.email_not_exists);
            }
        } else {
            activity.message.setText(R.string.email_password);
        }
    }

    public void signIn(View view){
        Editable a;
        Editable b;
        Editable c;
        if ((a = activity.username.getText()) != null
                && (b = activity.email.getText()) != null
                && (c = activity.password.getText()) != null) {

            if (a.toString().matches(Strings.NAME_REGEX) && c.toString().matches(Strings.NAME_REGEX)) {
                if (b.toString().matches(Strings.EMAIL_REGEX)) {
                    if (!model.users.containsKey(b.toString())) {
                        model.setName(a.toString());
                        model.setEmail(b.toString());
                        model.setPassword(c.toString());
                        model.addUser();
                        model.checkin.set(81000000);
                        model.done.set(true);
                    }else{
                        activity.message.setText(R.string.email_exists);
                    }
                } else {
                    activity.message.setText(R.string.check_email);
                }
            } else {
                activity.message.setText(R.string.name_password);
            }

        } else {
            activity.message.setText(R.string.fill_fields);
        }
    }

    public void loadProfile(View view) {
        boolean isCheckedIn=false;
        if (view.getId()==R.id.checkin) isCheckedIn=true;
        Intent intent = new Intent(activity, ProfileActivity.class);
        intent.putExtra(ProfileActivity.USERMAIL, model.email.get());
        intent.putExtra(ProfileActivity.CHECKIN, isCheckedIn);
        activity.goTo(intent);
    }
}
