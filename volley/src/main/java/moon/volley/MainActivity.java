package moon.volley;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getRequest(View view){
        Intent intent = new Intent(this,GetRequestActivity.class) ;
        startActivity(intent);
    }
    public void postRequest(View view){
        Intent intent = new Intent(this,PostRequestActivity.class) ;
        startActivity(intent);
    }
    public void postForm(View view){
        Intent intent = new Intent(this,PostFormActivity.class) ;
        startActivity(intent);
    }
    public void postUpload(View view){
        Intent intent = new Intent(this,PostUploadActivity.class) ;
        startActivity(intent);
    }
}
