package by.argyment.gymapp.greeting;

import by.argyment.gymapp.GymApplication;
import by.argyment.gymapp.R;
import by.argyment.gymapp.base.BaseViewModel;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.AddUserUseCase;
import by.argyment.gymapp.domain.interactions.GetProfileUseCase;
import by.argyment.gymapp.extra.Strings;
import by.argyment.gymapp.profile.ProfileActivity;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * @author Olga Rudzko
 */

public class GreetModel implements BaseViewModel {

    public static final String SHARED = Strings.SHARED;

    @Inject
    GetProfileUseCase getUser;
    @Inject
    AddUserUseCase addUser;

    GreetActivity activity;
    SharedPreferences preferences;


    public static final String NAME = Strings.KEY_NAME;
    public static final String PASS = Strings.PASSWORD;

    public ObservableField<String> name = new ObservableField<>();
    public ObservableBoolean done = new ObservableBoolean(false);
    public ObservableBoolean isLoading = new ObservableBoolean(true);
    public ObservableBoolean problem;

    public ObservableInt checkin = new ObservableInt();
    ObservableField<String> email = new ObservableField<>();
    private ObservableField<String> password = new ObservableField<>();

    public GreetModel(GreetActivity activity) {
        this.activity = activity;
        GymApplication.appComponent.injectGreetModel(this);
    }

    private BroadcastReceiver connectCheck = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info == null || !info.isConnectedOrConnecting()) {
                problem.set(true);
                done.set(false);
            } else {
                problem.set(false);
                loadPreferences();
            }
        }
    };

    public void setName(String name) {
        this.name.set(name);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Override
    public void init() {
        problem = new ObservableBoolean(false);
    }

    @Override
    public void resume() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        activity.registerReceiver(connectCheck, filter);

        if (name.get() == null) {
            done.set(false);
        }
        loadPreferences();
    }

    private void loadPreferences() {
        isLoading.set(true);
        preferences = activity.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        final String savedMail = preferences.getString(GreetModel.NAME, null);
        final String savedPass = preferences.getString(GreetModel.PASS, null);
        if (savedMail != null && savedPass != null) {
            getUser.makeRequest(savedMail, new DisposableObserver<UserProfile>() {
                @Override
                public void onNext(@NonNull UserProfile userProfile) {
                    if (userProfile.getPassword().equals(savedPass)) {
                        email.set(savedMail);
                        password.set(savedPass);
                        checkin.set((int) (System.currentTimeMillis() - userProfile.getTimeCheckin()));
                        isLoading.set(false);
                        done.set(true);
                        Log.d("---Loading false", "Done true");
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    isLoading.set(false);
                    Log.d("---Loading false", "on error");
                }

                @Override
                public void onComplete() {

                }
            });
        } else {
            isLoading.set(false);
            Log.d("---Loading false", "empty preferences");
        }
    }

    public void login(View view) {
        final String b = activity.email.getText().toString();
        final String c = activity.password.getText().toString();
        if (!b.isEmpty() && !c.isEmpty() && b.matches(Strings.EMAIL_REGEX) && c.matches(Strings.PASSW_REGEX)) {
            getUser.makeRequest(b, new DisposableObserver<UserProfile>() {
                @Override
                public void onNext(@NonNull UserProfile userProfile) {
                    if (userProfile.getPassword().equals(c)) {
                        setEmail(b);
                        setPassword(c);
                        preferences.edit().putString(GreetModel.NAME, b)
                                .putString(GreetModel.PASS, c).apply();
                        checkin.set((int) (System.currentTimeMillis() - userProfile.getTimeCheckin()));
                        done.set(true);
                    } else {
                        Toast.makeText(activity, R.string.wrong_password, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Toast.makeText(activity, R.string.email_not_exists, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onComplete() {

                }
            });
        } else {
            Toast.makeText(view.getContext(), R.string.wrong_format, Toast.LENGTH_SHORT).show();
        }
    }

    public void signIn(View view) {
        final String a = activity.username.getText().toString();
        final String b = activity.email.getText().toString();
        final String c = activity.password.getText().toString();
        if (!a.isEmpty() && !b.isEmpty() && !c.isEmpty()) {
            if (a.matches(Strings.NAME_REGEX) && c.matches(Strings.PASSW_REGEX)) {
                if (b.matches(Strings.EMAIL_REGEX)) {
                    UserProfile newUser = new UserProfile();
                    newUser.setUsername(name.get());
                    newUser.setEmail(email.get());
                    newUser.setPassword(password.get());
                    addUser.makeRequest(newUser, new DisposableObserver<UserProfile>() {
                        @Override
                        public void onNext(@NonNull UserProfile userProfile) {
                            setName(a);
                            setEmail(b);
                            setPassword(c);
                            checkin.set(81000000);
                            done.set(true);
                            preferences.edit().putString(GreetModel.NAME, b)
                                    .putString(GreetModel.PASS, c).apply();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Toast.makeText(activity, R.string.email_exists, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

                } else {
                    Toast.makeText(activity, R.string.check_email, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(activity, R.string.name_password, Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(activity, R.string.fill_fields, Toast.LENGTH_SHORT).show();
        }
    }

    public void loadProfile(View view) {
        boolean isCheckedIn = false;
        if (view.getId() == R.id.checkin) isCheckedIn = true;
        Intent intent = new Intent(activity, ProfileActivity.class);
        intent.putExtra(ProfileActivity.USERMAIL, email.get());
        intent.putExtra(ProfileActivity.CHECKIN, isCheckedIn);
        activity.goTo(intent);
    }

    @Override
    public void pause() {
        activity.unregisterReceiver(connectCheck);
    }

    @Override
    public void release() {
        getUser.dispose();
        addUser.dispose();
    }


}
