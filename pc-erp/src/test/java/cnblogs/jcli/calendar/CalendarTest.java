/**
 * 
 */
package cnblogs.jcli.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import net.caiban.utils.DateUtil;
import net.sf.json.JSONArray;

/**
 * @author parox
 *
 */
public class CalendarTest {
	
	public static void main(String[] args) throws ParseException, InterruptedException {
		
		System.out.println(DateUtil.toString(new Date(DateUtil.getTheDayZero(new Date(), 0)*1000l+86399000l), "HH:mm:ss"));
		System.exit(1);
		
		String[] timezones = TimeZone.getAvailableIDs();
		System.out.println(JSONArray.fromObject(timezones));
		String[] ids = TimeZone.getAvailableIDs();
		System.out.println(JSONArray.fromObject(ids));
		SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateformatter.setTimeZone(TimeZone.getTimeZone("Canada/Mountain"));
		System.out.println(dateformatter.format(new Date()));
		System.exit(1);
		
		Event e = new Event();
		e.setCalendarType(1);
		String until = DateUtil.toString(DateUtil.getDate("2015-10-02 17:00:00", "yyyy-MM-dd HH:mm:ss"), "yyyyMMdd'T'HHmmss'Z'");
		e.setRule("DAILY;UNTIL="+until+";INTERVAL=1");
		e.setStartDate(DateUtil.getDate("2015-09-30 12:00:00", "yyyy-MM-dd HH:mm:ss"));
		e.setEndDate(DateUtil.getDate("2015-09-30 13:00:00", "yyyy-MM-dd HH:mm:ss"));
		e.setIntervelSec((int)(e.getEndDate().getTime()-e.getEndDate().getTime())/1000);
		
		Rule rule = RuleFactory.createRule(e);
		
//		Date date = rule.nextOccurDate(new Date());
		
		int i=0;
		Date current = DateUtil.getDate("2015-10-01", "yyyy-MM-dd");
		long start=System.currentTimeMillis();
		do{
			
			i++;
			current = rule.nextOccurDate(current);
			if(current == null){
				break;
			}
			System.out.println(i+":"+DateUtil.toString(current, "yyyy-MM-dd HH:mm:ss"));
			
//			if(i<30){
//				Thread.sleep(30);
//			}
			
		}while(i<(365+365));
		long end=System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	
	
//	
//	public static Date nextOccurDate(Date currentDate){
//		
//	}
}
