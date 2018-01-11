package id.co.imastudio.retrofitauthentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import id.co.imastudio.retrofitauthentication.model.TwitterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TwitterActivity extends AppCompatActivity {

    private static final String TAG = "TwitterActivity";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        String token = getIntent().getStringExtra("TOKEN");
        Log.d(TAG, "onCreate: " + token);

        listView = (ListView) findViewById(R.id.tweetsList);

        final String bearer = "Bearer " + token;

        Call<List<TwitterResponse>> tokenCall = TwitterRest.createService().getStatuses(bearer);

        tokenCall.enqueue(new Callback<List<TwitterResponse>>() {
            @Override
            public void onResponse(Call<List<TwitterResponse>> call, Response<List<TwitterResponse>> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
                if (response.isSuccessful()) {

                    List<TwitterResponse> data = response.body();
                    TwitterAdapter adapter = new TwitterAdapter(data, getApplicationContext());
                    listView.setAdapter(adapter);

                } else {
                    Toast.makeText(TwitterActivity.this, "Error not success" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TwitterResponse>> call, Throwable t) {
                Toast.makeText(TwitterActivity.this, "Error failure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
