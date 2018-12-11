package blog.techanimate.sakib.foodorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText nameedittext,emailidtext,usernameeditText,passwordedittext;
    private Button sgup;
    UserDetails userDetails;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        nameedittext=(EditText)findViewById(R.id.nameedittextid);
        emailidtext=(EditText)findViewById(R.id.emailedittextid);
        usernameeditText=(EditText)findViewById(R.id.usernameedittextid);
        passwordedittext=(EditText)findViewById(R.id.passwordedittextid);
        sgup=(Button)findViewById(R.id.sgupid1);
        databaseHelper = new DatabaseHelper(this);

        userDetails = new UserDetails();
        sgup.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        String name = nameedittext.getText().toString();
        String email = emailidtext.getText().toString();
        String username = usernameeditText.getText().toString();
        String password = passwordedittext.getText().toString();

        userDetails.setName(name);
        userDetails.setEmail(email);
        userDetails.setUsername(username);
        userDetails.setPassword(password);



            long rowId = databaseHelper.insertData(userDetails);
            if (rowId > 0) {
                Toast.makeText(getApplicationContext(), "You are Successfully Registered", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_LONG).show();
            }



    }
}
