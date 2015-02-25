package zoeb.regen.regen;

import java.util.Random;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.ClipboardManager;
import android.text.method.ScrollingMovementMethod;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {
	
	static Button num,cap,sma,copy,spe,any,cle,ref,abo,del;
	static TextView text,re;
	String revalue,special;
	Handler Handle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		num = (Button) findViewById(R.id.num);
		cap = (Button) findViewById(R.id.cap);
		sma = (Button) findViewById(R.id.sma);
		copy = (Button) findViewById(R.id.copy);
		spe = (Button) findViewById(R.id.spe);
		any = (Button) findViewById(R.id.any);
		cle = (Button) findViewById(R.id.cle);
		ref = (Button) findViewById(R.id.ref);
		abo = (Button) findViewById(R.id.abo);
		del = (Button) findViewById(R.id.del);
		special = "!@#$&*?+-/%.";
		Handle = new Handler();
		text = (TextView) findViewById(R.id.textView1);
		text.setMovementMethod(new ScrollingMovementMethod());
		re = (TextView) findViewById(R.id.textView2);
		re.setMovementMethod(new ScrollingMovementMethod());
		num.setOnClickListener(Handle);
		cap.setOnClickListener(Handle);
		sma.setOnClickListener(Handle);
		spe.setOnClickListener(Handle);
		any.setOnClickListener(Handle);
		cle.setOnClickListener(Handle);
		del.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(re.length()>=1)
				{
				revalue = re.getText().toString();
				revalue = revalue.substring(0, re.length()-1);
				re.setText(revalue);
				}
			}
		});
		copy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClipboardManager clip = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				clip.setText(text.getText());
				Toast.makeText(MainActivity.this,"Text Copied", Toast.LENGTH_SHORT).show();
			}
		});
		ref.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				generate();
			}
		});
		abo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this,"Regen is a Regular Expression based Text|Password Generator\nDeveloped by: Zoeb Chhatriwala", Toast.LENGTH_LONG).show();
			}
		});
		re.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count){
				generate();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				//Do Nothing
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				//Do Nothing
			}
		});
		
	}
	
	public void generate()
	{
		revalue = re.getText().toString();
		text.setText("");
		Random rand = new Random();
		int randvalue;
		char temp='P';
		if (revalue.length() >= 1)
        {
            int x = 0;
            while (x != revalue.length())
            {
            	if (temp=='P')
            	{
                temp = revalue.charAt(x);
            	}
                x++;
                if (temp == '@')
                {
                	randvalue=rand.nextInt(12);
                	char temp2 = special.charAt(randvalue);
                    text.append(String.format("%s",temp2));
                    temp='P';
                }
                else if(temp=='a')
                {
                    randvalue=rand.nextInt(123);
                    if(randvalue<97)
                    {
                    	randvalue=randvalue%26;
                    	randvalue=97+randvalue;
                    }
                    text.append(String.format("%c",randvalue));
                    temp='P';
                }
                else if (temp == 'A')
                {
                    randvalue=rand.nextInt(91);
                    if(randvalue<65)
                    {
                    	randvalue=randvalue%26;
                    	randvalue=65+randvalue;
                    }
                    text.append(String.format("%c",randvalue));
                    temp='P';
                }
                else if (temp == '0')
                {
                    text.append(String.format("%d",rand.nextInt(10)));
                    temp='P';
                }
                else if (temp == '*')
                {
                    char[] any = {'@','a','A','0'};
                    temp = any[rand.nextInt(4)];
                    x--;
                }
            }
        }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return super.onOptionsItemSelected(item);
	}
}


class Handler implements OnClickListener
{
	

	@Override
	public void onClick(View v) {
	if(v.getId()==R.id.cap)
	{
	 MainActivity.re.append("A");
	}
	else if(v.getId()==R.id.sma)
	{
	 MainActivity.re.append("a");
	}
	else if(v.getId()==R.id.spe)
	{
	 MainActivity.re.append("@");
	}
	else if(v.getId()==R.id.num)
	{
	 MainActivity.re.append("0");
	}
	else if(v.getId()==R.id.cle)
	{
	 MainActivity.re.setText("");
	}
	else if(v.getId()==R.id.any)
	{
	 MainActivity.re.append("*");
	}
	}
}
