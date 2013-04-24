package hairdresser.data;

public class ManHaircut implements ServiceType {
	
	private String serviceName = "man haircut";
	private int duration = 30;
	
	@Override
	public String getServiceName() {
		return serviceName;
	}
	
	@Override
	public int getServiceDuration() {
		return duration;
	}
	
}
