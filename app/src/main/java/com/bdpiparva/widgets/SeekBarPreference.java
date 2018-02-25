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

package com.bdpiparva.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bdpiparva.daydream.R;

public class SeekBarPreference extends Preference implements SeekBar.OnSeekBarChangeListener {
	private TextView value;
	private SeekBar seekBar;
	private Integer mCurrentValue;
	private int max = 10;
	private final LayoutInflater layoutInflater;

	public SeekBarPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		init(context, attrs, 0, 0);
	}

	private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		final TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.SeekBarPreference, defStyleAttr, defStyleRes);

		for (int i = styledAttributes.getIndexCount() - 1; i >= 0; i--) {
			int attr = styledAttributes.getIndex(i);
			switch (attr) {
				case R.styleable.SeekBarPreference_max:
					max = styledAttributes.getInteger(attr, 10);
					break;
			}
		}
	}

	@Override
	protected View onCreateView(ViewGroup parent) {
		super.onCreateView(parent);
		View inflatedView = layoutInflater.inflate(R.layout.seek_bar_preference, parent, false);

		seekBar = inflatedView.findViewById(R.id.seekbar);
		seekBar.setMax(max);
		value = inflatedView.findViewById(R.id.value);
		seekBar.setOnSeekBarChangeListener(this);
		updateView();

		return inflatedView;
	}

	@Override
	protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
		final String value = restorePersistedValue ? String.valueOf(getPersistedInt(0)) : String.valueOf(defaultValue);

		if (value == null || value.trim().isEmpty()) {
			mCurrentValue = 0;
		} else {
			mCurrentValue = Integer.parseInt(value);
		}
	}

	@Override
	protected Object onGetDefaultValue(TypedArray a, int index) {
		return a.getInteger(index, 3);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		mCurrentValue = progress;
		updateView();
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}

	private void updateView() {
		value.setText(String.valueOf(mCurrentValue));
		seekBar.setProgress(mCurrentValue);
		persistInt(mCurrentValue);
	}
}
