package com.bdpiparva.daydream.models;

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
