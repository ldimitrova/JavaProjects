package hairdresser.data;

public class WomanHaircut implements ServiceType{

	
	private String serviceName = "woman haircut";
	private int duration = 45;
	
	@Override
	public String getServiceName() {
		return serviceName;
	}

	@Override
	public int getServiceDuration() {
		return duration;
	}



}
