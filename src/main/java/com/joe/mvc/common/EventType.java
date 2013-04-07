package com.joe.mvc.common;


public enum EventType {
	
	ENTER("ENTER"), LOCATION("LOCATION");
	
	private String name;

	private EventType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
	public static EventType valueOfName(String name) {
		for (EventType e : EventType.values()) {
			if (e.getName().equals(name)) {
				return e;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.print(EventType.valueOfName("*"));
	}
	
}
