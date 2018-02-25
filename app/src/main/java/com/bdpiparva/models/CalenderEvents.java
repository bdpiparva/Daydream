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

package com.bdpiparva.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CalenderEvents {
	private Set<CalenderEvent> calenderEvents = new HashSet<>();

	public boolean addEvent(CalenderEvent calenderEvent) {
		return calenderEvents.add(calenderEvent);
	}

	public boolean hasEvent(CalenderEvent calenderEvent) {
		return calenderEvents.contains(calenderEvent);
	}

	public boolean removeEvent(CalenderEvent calenderEvent) {
		return calenderEvents.remove(calenderEvent);
	}

	public List<CalenderEvent> listAndSort() {
		return list().stream().sorted(Comparator.comparingLong(CalenderEvent::getStart)).collect(Collectors.toList());
	}

	public List<CalenderEvent> list() {
		return new ArrayList<>(calenderEvents);
	}
}
