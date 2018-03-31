package iot.lviv.ua.educationproject;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CorruptionReport> corruptionReportList = new LinkedList<>();

    //Firebase variables
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corruption);

        //Firebase init
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();


        //Processing CORRUPTION reports
        TextView corruptionSend = (TextView) findViewById(R.id.corruption_send);
        corruptionSend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                CorruptionReport corruptionReport = new CorruptionReport();

                EditText editText = (EditText) findViewById(R.id.corruption_student);
                corruptionReport.setStudentName(editText.getText().toString());
                editText.setText("");

                editText = (EditText) findViewById(R.id.corruption_lector);
                corruptionReport.setLecturerName(editText.getText().toString());
                editText.setText("");

                editText = (EditText) findViewById(R.id.corruption_text);
                corruptionReport.setReportText(editText.getText().toString());
                editText.setText("");

                mDatabaseReference.child("CorruptionReports").push().setValue(corruptionReport);

                corruptionReportList.add(corruptionReport);
                Toast.makeText(MainActivity.this, "Thank you for your report! <3",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
