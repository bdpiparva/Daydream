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

package com.bdpiparva.daydream.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.CheckBox;

import com.bdpiparva.daydream.R;
import com.bdpiparva.models.CalenderInfo;
import com.bdpiparva.providers.CalenderProvider;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by bhupendrakumar on 2/18/18.
 */
@RunWith(AndroidJUnit4.class)
public class CalenderAdaptorTest {

	@Test
	@UiThreadTest
	public void shouldUpdateSharedPrefWithSelectedCalenderIds() throws Exception {
		final Context context = InstrumentationRegistry.getTargetContext();
		final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		final CalenderProvider calenderProvider = CalenderProvider.getInstance(context.getContentResolver());
		calenderProvider.clear();
		calenderProvider.add(new CalenderInfo(1111, "Foo", "Foo Bar", null));

		final CalenderAdaptor calenderAdaptor = new CalenderAdaptor(context, R.layout.calender_row, calenderProvider);
		View view = calenderAdaptor.getView(0, null, null);
		final CheckBox checkBox = view.findViewById(R.id.calender_id);

		assertThat(getCalenderSelection(sharedPreferences), hasSize(0));

		checkBox.setChecked(true);
		assertThat(getCalenderSelection(sharedPreferences), hasSize(1));
		assertThat(getCalenderSelection(sharedPreferences), contains("1111"));

		checkBox.setChecked(false);
		assertThat(getCalenderSelection(sharedPreferences), hasSize(0));
	}

	@NonNull
	private Set<String> getCalenderSelection(SharedPreferences sharedPreferences) {
		return sharedPreferences.getStringSet(CalenderAdaptor.SELECTED_CALENDERS, new HashSet<>());
	}

}