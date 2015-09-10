/**
 * 
 */
package cnblogs.jcli.calendar;

import java.text.ParseException;
import java.util.Date;

import net.caiban.utils.DateUtil;

/**
 * @author parox
 *
 */
public class CalendarTest {

	public static void main(String[] args) throws ParseException, InterruptedException {
		Event e = new Event();
		e.setCalendarType(0);
		e.setRule("DAILY;UNTIL=20170102T170000Z;INTERVAL=1");
		e.setStartDate(DateUtil.getDate("2015-09-01 12:00:00", "yyyy-MM-dd HH:mm:ss"));
		e.setEndDate(DateUtil.getDate("2015-10-01 13:00:00", "yyyy-MM-dd HH:mm:ss"));
		e.setIntervelSec((int)(e.getEndDate().getTime()-e.getEndDate().getTime())/1000);
		
		Rule rule = RuleFactory.createRule(e);
		
//		Date date = rule.nextOccurDate(new Date());
		
		int i=0;
		Date current = new Date();
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
