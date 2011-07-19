package core;

import net.rim.blackberry.api.invoke.Invoke;
import net.rim.blackberry.api.invoke.PhoneArguments;

public class PhoneManager {
	/**
	 * 
	 * @param number String con el numero de telefono a marcar
	 */
	public static void call(String number){
		Invoke.invokeApplication(Invoke.APP_TYPE_PHONE,new PhoneArguments(PhoneArguments.ARG_CALL, number));
	}
}
