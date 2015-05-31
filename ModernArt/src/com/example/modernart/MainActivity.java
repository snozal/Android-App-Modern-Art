package com.example.modernart;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// setup the view.
		setContentView(R.layout.activity_main);

		// get the seek bar and assign the listener
		SeekBar mSeekBar = (SeekBar) findViewById(R.id.my_seek_bar);
		mSeekBar.setOnSeekBarChangeListener(this);
	}

	// Create Options Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	// Process clicks on Options Menu items
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// if(item.isChecked()) {

		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setMessage(R.string.alertCompleteText);

		alert.setNegativeButton(R.string.button2,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {

					}
				});
		alert.setPositiveButton(R.string.button1,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						Intent browsable = new Intent(Intent.ACTION_VIEW, Uri
								.parse("http://www.moma.org"));
						startActivity(browsable);
					}
				});
		
		AlertDialog dialog = alert.create();
		dialog.show();

		TextView messageText = (TextView) dialog
				.findViewById(android.R.id.message);
		messageText.setGravity(Gravity.CENTER);

		return true;

		// }

	}

	// required by the interface
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

		// getting the views
		View greenView = (View) findViewById(R.id.greenView);
		View purpleView = (View) findViewById(R.id.purpleView);
		View orangeView = (View) findViewById(R.id.orangeView);
		View blueView = (View) findViewById(R.id.blueView);

		// modifying them
		greenView.setBackgroundColor(color(0x34CC12, 0xFF6633, progress));
		purpleView.setBackgroundColor(color(0x551122, 0x00FF99, progress));
		orangeView.setBackgroundColor(color(0xFFAB00, 0x6600CC, progress));
		blueView.setBackgroundColor(color(0x1ABAB0, 0xFF0099, progress));
	}

	// Method for update view
	private void updateView() {

	}

	// Method for changing colors
	public int color(int color1, int color2, int progress) {
		int deltaRed = Color.red(color2) - Color.red(color1);
		int deltaGreen = Color.green(color2) - Color.green(color1);
		int deltaBlue = Color.blue(color2) - Color.blue(color1);

		int red = Color.red(color1) + (deltaRed * progress / 100);
		int green = Color.green(color1) + (deltaGreen * progress / 100);
		int blue = Color.blue(color1) + (deltaBlue * progress / 100);

		return Color.rgb(red, green, blue);
	}

	// required by the interface
	public void onStartTrackingTouch(SeekBar seekBar) {
		// unused
	}

	// required by the interface
	public void onStopTrackingTouch(SeekBar seekBar) {
		// unused
	}
}
