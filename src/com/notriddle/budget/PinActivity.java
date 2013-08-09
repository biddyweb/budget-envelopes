/*
 * This file is a part of Budget with Envelopes.
 * Copyright 2013 Anatolij Zelenin <az@azapps.de>
 *
 * Budget is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Budget is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Budget. If not, see <http://www.gnu.org/licenses/>.
 */

package com.notriddle.budget;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class PinActivity extends Activity {
	private EditText pin;
	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		preferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		if (preferences.getString("pin", "").equals("")) {
			start();
		}

		setContentView(R.layout.activity_pin);
		setTitle(R.string.pin_name);

		pin = (EditText) findViewById(R.id.pin);
		pin.setOnEditorActionListener(new OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					check();
					return true;
				}
				return false;
			}
		});
		Button pinEnter = (Button) findViewById(R.id.pinEnter);
		pinEnter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				check();
			}
		});

	}

	private void check() {
		if (pin.getText().toString().equals(preferences.getString("pin", ""))) {
			start();
		} else {
			Toast.makeText(getApplicationContext(), R.string.pin_label_wrong,
					Toast.LENGTH_LONG).show();
		}
	}

	private void start() {
		Intent intent = new Intent(PinActivity.this, EnvelopesActivity.class);
		startActivity(intent);
	}

}
