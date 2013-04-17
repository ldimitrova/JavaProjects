package hairdresser.main;

public enum ServiceType {
	MAN_HAIRCUT(30), WOMEN_HAIRCUT(45);
	private int duration;

	ServiceType(int duration){
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

	
}
