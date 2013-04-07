package com.joe.mvc.common;


public enum MsgType {
	
	TEXT("text"), IMAGE("image"), LOCATION("location"),
	LINK("link"), EVENT("event"), MUSIC("music"), NEWS("news");
	
	private String name;

	private MsgType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
	public static MsgType valueOfName(String name) {
		for (MsgType e : MsgType.values()) {
			if (e.getName().equals(name)) {
				return e;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.print(MsgType.valueOfName("*"));
	}
	
}
