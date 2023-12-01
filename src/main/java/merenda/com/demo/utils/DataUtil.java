package merenda.com.demo.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DataUtil {

	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
}
