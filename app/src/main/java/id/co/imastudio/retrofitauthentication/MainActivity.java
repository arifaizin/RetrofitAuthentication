package id.co.imastudio.retrofitauthentication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import id.co.imastudio.retrofitauthentication.model.TokenResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button buttonToken;

    public static final String CONSUMER_KEY="yK6BBBzl2VWZnVOSjnoGnrOJs";
    public static final String CONSUMER_SECRET="SL9SAKnWBTxNUuebnUUV9DUvDZMyDxFLK5uvPQljyz9qQ5Q2JK";
    public static final String CONTENT_TYPE="application/x-www-form-urlencoded;charset=UTF-8";
    private static final String CREDENTIALS = "client_credentials";

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonToken = (Button) findViewById(R.id.buttonToken);
        buttonToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String credentials = CONSUMER_KEY + ":" + CONSUMER_SECRET;
                final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

                Log.d(TAG, "onClick: "+ basic);
                Call<TokenResponse> tokenCall = TwitterRest.createService().getAuthenticationToken(
                        CONTENT_TYPE,
                        basic,
                        CREDENTIALS);

                tokenCall.enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        Log.d(TAG, "onResponse: "+ response.body());
                        if (response.isSuccessful()){
                            final String token = response.body().getAccessToken().toString();
                            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                            dialog.setTitle("Token Kamu");
                            dialog.setMessage(token);
                            dialog.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent pindah = new Intent(MainActivity.this, TwitterActivity.class);
                                    pindah.putExtra("TOKEN",token);
                                    startActivity(pindah);
                                }
                            });
                            dialog.show();

                            Log.d(TAG, "onResponse: "+ response.body().getAccessToken().toString());
                        } else {
                            Toast.makeText(MainActivity.this, "Error not success"+ response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error failure"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
