package com.divel.tu.eres.el.protagonista;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

@SuppressLint("NewApi")
public class TheEnd extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
        	setTheme(android.R.style.Theme_Holo);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }else{
        	setTheme(android.R.style.Theme_Black);
        }
		setContentView(R.layout.the_end);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.the_end, menu);
		return true;
	}
	public void shareEnd(View view)
	{
		/*Intent emailIntent = new Intent(Intent.ACTION_SEND);
		// The intent does not have a URI, so declare the "text/plain" MIME type
		emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jon@example.com"}); // recipients
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
		emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
		emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));*/
		Intent shareIntent=new Intent(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(Intent.EXTRA_TEXT, "I was playing Tu eres el protagonista, it's funny");
		startActivity(Intent.createChooser(shareIntent,"Share content with..."));
	}
	public void quitNow(View view)
	{
		finish();
	}
	public void restartNow(View view)
	{
		finish();
	}

}
