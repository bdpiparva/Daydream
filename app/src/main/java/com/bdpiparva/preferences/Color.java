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

/**
 * Created by bhupendrakumar on 2/25/18.
 */

public class Color {
	private int red, green, blue, alpha;

	public Color(int color) {
		red = android.graphics.Color.red(color);
		green = android.graphics.Color.green(color);
		blue = android.graphics.Color.blue(color);
		alpha = android.graphics.Color.alpha(color);
	}

	public Color(String hex) {
		final int color = android.graphics.Color.parseColor(hex);
		red = android.graphics.Color.red(color);
		green = android.graphics.Color.green(color);
		blue = android.graphics.Color.blue(color);
		alpha = android.graphics.Color.alpha(color);
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public int colorInt() {
		return android.graphics.Color.argb(alpha, red, green, blue);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Color color = (Color) o;

		if (red != color.red) return false;
		if (green != color.green) return false;
		if (blue != color.blue) return false;
		return alpha == color.alpha;
	}

	@Override
	public int hashCode() {
		int result = red;
		result = 31 * result + green;
		result = 31 * result + blue;
		result = 31 * result + alpha;
		return result;
	}
}
