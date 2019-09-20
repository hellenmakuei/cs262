package edu.calvin.cs262.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0; //we added this to initialize the int to 0
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
    }

    //showToast makes it so that when you click on Toast, the message shows up
    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }
//countUp makes it so that when you click on the count button, the counter goes up
    public void countUp(View view) {
        ++mCount;
        if (mShowCount != null)
                mShowCount.setText(Integer.toString(mCount));
    }
}
