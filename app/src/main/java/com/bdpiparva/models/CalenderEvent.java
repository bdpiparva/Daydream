package com.bdpiparva.models;

import java.text.SimpleDateFormat;

/**
 * Created by bhupendrakumar on 2/18/18.
 */
public class CalenderEvent {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
	private Long calendarId;
	private String title;
	private String description;
	private Long start;
	private Long end;
	private String eventLocation;
	private boolean allDay;

	public CalenderEvent(Long calendarId, String title, String description, Long start, Long end, String eventLocation, boolean allDay) {
		this.calendarId = calendarId;
		this.title = title;
		this.description = description;
		this.start = start;
		this.end = end;
		this.eventLocation = eventLocation;
		this.allDay = allDay;
	}

	public Long getCalendarId() {
		return calendarId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Long getStart() {
		return start;
	}

	public Long getEnd() {
		return end;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public String getStartTime() {
		return toStringTime(getStart());
	}

	public boolean isCurrent() {
		return System.currentTimeMillis() >= start && System.currentTimeMillis() < end;
	}

	public boolean isPassed() {
		return System.currentTimeMillis() > end;
	}

	public boolean isUpcoming() {
		return System.currentTimeMillis() < start;
	}

	private String toStringTime(Long millis) {
		if (millis == null) {
			return "";
		}
		return sdf.format(millis);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CalenderEvent that = (CalenderEvent) o;

		if (allDay != that.allDay) return false;
		if (calendarId != null ? !calendarId.equals(that.calendarId) : that.calendarId != null)
			return false;
		if (title != null ? !title.equals(that.title) : that.title != null) return false;
		if (description != null ? !description.equals(that.description) : that.description != null)
			return false;
		if (start != null ? !start.equals(that.start) : that.start != null) return false;
		if (end != null ? !end.equals(that.end) : that.end != null) return false;
		return eventLocation != null ? eventLocation.equals(that.eventLocation) : that.eventLocation == null;
	}

	@Override
	public int hashCode() {
		int result = calendarId != null ? calendarId.hashCode() : 0;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (start != null ? start.hashCode() : 0);
		result = 31 * result + (end != null ? end.hashCode() : 0);
		result = 31 * result + (eventLocation != null ? eventLocation.hashCode() : 0);
		result = 31 * result + (allDay ? 1 : 0);
		return result;
	}
}
