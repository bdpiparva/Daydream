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

/**
 * Created by bhupendrakumar on 2/24/18.
 */

public class SeekBarPreference extends Preference implements SeekBar.OnSeekBarChangeListener {
	private TextView value;
	private SeekBar seekBar;
	private Integer mCurrentValue;
	private Integer defaultValue = 0;

	public SeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public SeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public SeekBarPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SeekBarPreference(Context context) {
		super(context);
	}

	@Override
	protected View onCreateView(ViewGroup parent) {
		super.onCreateView(parent);
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View inflatedView = li.inflate(R.layout.seek_bar_preference, parent, false);

		seekBar = inflatedView.findViewById(R.id.seekbar);
		value = inflatedView.findViewById(R.id.value);
		seekBar.setOnSeekBarChangeListener(this);

		return inflatedView;
	}

	@Override
	protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
		if (restorePersistedValue) {
			mCurrentValue = (Integer) defaultValue;
		} else {
			mCurrentValue = (Integer) defaultValue;
			persistInt(mCurrentValue);
		}

		if (mCurrentValue != null) {
			value.setText(String.valueOf(mCurrentValue));
			seekBar.scrollTo(mCurrentValue, 0);
		}
	}

	@Override
	protected Object onGetDefaultValue(TypedArray a, int index) {
		Integer defaultValue = a.getInteger(index, 3);
		setDefaultValue(defaultValue);
		return defaultValue;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		value.setText(String.valueOf(progress));
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}
}
