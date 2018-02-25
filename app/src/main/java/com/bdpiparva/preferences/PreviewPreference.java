package com.bdpiparva.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bdpiparva.daydream.R;
import com.bdpiparva.widgets.WidgetPreference;

public class PreviewPreference extends Preference implements SharedPreferences.OnSharedPreferenceChangeListener {

	private final SharedPreferences preferences;
	private final LayoutInflater layoutInflater;
	private TextView eventTitle;
	private TextView colorBar;
	private TextView eventTime;
	private LinearLayout row;

	public PreviewPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
		preferences.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected View onCreateView(ViewGroup parent) {
		super.onCreateView(parent);
		final LinearLayout linearLayout = new LinearLayout(getContext());
		final View inflatedView = layoutInflater.inflate(R.layout.calender_widget_row_2, parent, false);
		linearLayout.addView(inflatedView);

		eventTitle = inflatedView.findViewById(R.id.event_title);
		colorBar = inflatedView.findViewById(R.id.calender_color);
		eventTime = inflatedView.findViewById(R.id.event_time);
		row = inflatedView.findViewById(R.id.event_row);

		linearLayout.setBackgroundResource(R.drawable.tile_background);

		updatePreview(new WidgetPreference(preferences, (int) getContext().getResources().getDisplayMetrics().density));

		return linearLayout;
	}

	private void updatePreview(WidgetPreference preference) {
		eventTitle.setTextColor(preference.getTextColor());
		eventTime.setTextColor(preference.getTextColor());
		colorBar.setWidth(preference.getCalenderColorBarWidth());
		colorBar.setBackgroundColor(preference.getColorWithAlpha(new Color("#ff0000").colorInt()));

		row.setBackgroundColor(preference.getBackgroundColor());
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		updatePreview(new WidgetPreference(preferences, (int) getContext().getResources().getDisplayMetrics().density));
	}
}
