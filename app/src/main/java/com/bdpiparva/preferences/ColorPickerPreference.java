/*
 * Copyright 2018 Bhupendrakumar Piprava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bdpiparva.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bdpiparva.daydream.R;

public class ColorPickerPreference extends DialogPreference implements SeekBar.OnSeekBarChangeListener {

	private SeekBar redSeekBar;
	private SeekBar greenSeekBar;
	private SeekBar blueSeekBar;
	private SeekBar alphaSeekBar;
	private TextView redValue;
	private TextView greenValue;
	private TextView blueValue;
	private TextView alphaValue;
	private View preview;
	private Integer mCurrentValue = -1090519041;

	public ColorPickerPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		setDialogLayoutResource(R.layout.color_picker);
	}


	@Override
	public void onBindDialogView(View view) {
		redSeekBar = view.findViewById(R.id.red_seek_bar);
		greenSeekBar = view.findViewById(R.id.green_seek_bar);
		blueSeekBar = view.findViewById(R.id.blue_seek_bar);
		alphaSeekBar = view.findViewById(R.id.alpha_seek_bar);

		redValue = view.findViewById(R.id.red_value);
		greenValue = view.findViewById(R.id.green_value);
		blueValue = view.findViewById(R.id.blue_value);
		alphaValue = view.findViewById(R.id.alpha_value);

		preview = view.findViewById(R.id.preview);
		updateView();

		redSeekBar.setOnSeekBarChangeListener(this);
		greenSeekBar.setOnSeekBarChangeListener(this);
		blueSeekBar.setOnSeekBarChangeListener(this);
		alphaSeekBar.setOnSeekBarChangeListener(this);

		super.onBindDialogView(view);
	}

	@Override
	protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
		final String value = restorePersistedValue ? String.valueOf(getPersistedInt(0)) : String.valueOf(defaultValue);

		if (value == null || value.trim().isEmpty()) {
			mCurrentValue = -1090519041;
		} else {
			mCurrentValue = Integer.parseInt(value);
		}
	}

	@Override
	protected Object onGetDefaultValue(TypedArray a, int index) {
		return a.getInteger(index, -1090519041);
	}

	private void updateView() {
		Color color = new Color(mCurrentValue);
		redSeekBar.setProgress(color.getRed());
		greenSeekBar.setProgress(color.getGreen());
		blueSeekBar.setProgress(color.getBlue());
		alphaSeekBar.setProgress(color.getAlpha());

		redValue.setText(String.valueOf(color.getRed()));
		greenValue.setText(String.valueOf(color.getGreen()));
		blueValue.setText(String.valueOf(color.getBlue()));
		alphaValue.setText(String.valueOf(color.getAlpha()));

		preview.setBackgroundColor(color.colorInt());
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		Color color = new Color(mCurrentValue);

		switch (seekBar.getId()) {
			case R.id.red_seek_bar:
				color.setRed(progress);
				break;
			case R.id.green_seek_bar:
				color.setGreen(progress);
				break;
			case R.id.blue_seek_bar:
				color.setBlue(progress);
				break;
			case R.id.alpha_seek_bar:
				color.setAlpha(progress);
				break;
		}

		mCurrentValue = color.colorInt();
		persistInt(mCurrentValue);
		updateView();
	}

	@Override
	protected void onDialogClosed(boolean positiveResult) {
		if (positiveResult) {
			persistInt(mCurrentValue);
		}
		super.onDialogClosed(positiveResult);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}
}
