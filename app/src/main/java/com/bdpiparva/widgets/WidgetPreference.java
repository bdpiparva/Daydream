package com.bdpiparva.widgets;

import android.content.SharedPreferences;

import com.bdpiparva.preferences.Color;

import static com.bdpiparva.widgets.fragments.CalenderWidgetPreferenceFragment.CALENDER_COLOR_BAR_TRANSPARENCY;
import static com.bdpiparva.widgets.fragments.CalenderWidgetPreferenceFragment.CALENDER_COLOR_BAR_WIDTH;
import static com.bdpiparva.widgets.fragments.CalenderWidgetPreferenceFragment.CALENDER_WIDGET_BACKGROUND_COLOR;
import static com.bdpiparva.widgets.fragments.CalenderWidgetPreferenceFragment.CALENDER_WIDGET_TEXT_COLOR;

public class WidgetPreference {
	private final int textColor;
	private final int backgroundColor;
	private final int calenderColorBarTransparency;
	private final int calenderColorBarWidth;
	private final int density;

	public WidgetPreference(SharedPreferences preferences, int density) {
		this.density = density;

		textColor = preferences.getInt(CALENDER_WIDGET_TEXT_COLOR, new Color("#7E57C2").colorInt());
		backgroundColor = preferences.getInt(CALENDER_WIDGET_BACKGROUND_COLOR, new Color("#80000000").colorInt());
		calenderColorBarTransparency = preferences.getInt(CALENDER_COLOR_BAR_TRANSPARENCY, 80);
		calenderColorBarWidth = convertPixels(preferences.getInt(CALENDER_COLOR_BAR_WIDTH, 3));
	}

	public int getTextColor() {
		return textColor;
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}

	public int getCalenderColorBarTransparency() {
		return calenderColorBarTransparency;
	}

	public int getCalenderColorBarWidth() {
		return calenderColorBarWidth;
	}

	public int getDensity() {
		return density;
	}

	public int getColorWithAlpha(int colorInInteger) {
		Color color = new Color(colorInInteger);
		color.setAlpha(getCalenderColorBarTransparency());
		return color.colorInt();
	}

	private int convertPixels(int widthInDp) {
		return (widthInDp * density);
	}
}
