package blog.techanimate.sakib.foodorder;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    DatabaseHelper databaseHelper;
    private Button sgin,sgup;
    private EditText usernameedittext;
    private EditText passwordedittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sgin =(Button) findViewById(R.id.sginid);
        sgup =(Button) findViewById(R.id.sgupid);

        usernameedittext = (EditText) findViewById(R.id.usernameedittextid);
        passwordedittext = (EditText) findViewById(R.id.passwordedittextid);

        databaseHelper =new DatabaseHelper(this);
        SQLiteDatabase db =databaseHelper.getWritableDatabase();

        sgin.setOnClickListener(this);
        sgup.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        String username=usernameedittext.getText().toString();
        String password=passwordedittext.getText().toString();

        if(view.getId()==R.id.sginid)  {

            Boolean result=databaseHelper.findPassword(username,password);

            if (result==true)
            {

                Intent intent = new Intent(MainActivity.this,Menu.class);
                startActivity(intent);


            }else {
                Toast.makeText(getApplicationContext(),"Username and Password didn't match",Toast.LENGTH_LONG).show();
            }

        }
        else if(view.getId()==R.id.sgupid){
            Intent intent = new Intent(MainActivity.this,SignupActivity.class);
            startActivity(intent);

        }


    }
}
