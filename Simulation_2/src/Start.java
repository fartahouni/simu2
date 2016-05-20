import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Start {
	static ArrayList<Double> reserve_arrival_time = new ArrayList<>();
	static ArrayList<Integer>  reserve_ticket_number = new ArrayList<>();
	static ArrayList<Double> reserve_queuee_left = new ArrayList<>();
	static ArrayList<Boolean> is_left = new ArrayList<>();
	static ArrayList<Double> cancel_arrival_time = new ArrayList<>();
	
	public static void main(String[] args) {
		//readFromFile("input1.txt");
		RandomNumberGenerator obj = new RandomNumberGenerator();
		obj.generateRandomNumbers(2, 18, 9 , 0.2 , 180);
		reserve_arrival_time = obj.reserve_arrival_time;
		cancel_arrival_time.add(1.0);
		cancel_arrival_time.add(3.0);
		cancel_arrival_time.add(5.0);
		double sell_time = 2.0;
		double show_time = 10.0;
		
//		cancel_arrival_time = obj.cancel_arrival_time;
		reserve_ticket_number = obj.reserve_ticket_number;
		reserve_queuee_left = obj.reserve_queuee_left;
		is_left = obj.is_left;
//		double sell_time = 60;
//		double show_time = 180;
		double cancel_duration = 2;
		int server_num = 2;
		
		ArrayList<ticketCanceling> c_list = new ArrayList<>();
		
		double counter = 1.0;
		int index = 0;
		double index_time = cancel_arrival_time.get(index);
		while (true){
			System.out.println("sss");
			if(c_list.size() != 0) System.out.println(c_list.size());
			if(counter == index_time){
				c_list.add(new ticketCanceling(sell_time, show_time, cancel_duration, server_num));
				index++;
				if( cancel_arrival_time.size() != index){
					index_time = cancel_arrival_time.get(index);
				}
			}
			if(counter >= sell_time){
				for (int i = 0; i < c_list.size(); i++) {
					if(c_list.get(i).condition() && i < c_list.get(i).get_idle()){
						c_list.get(i).startActions();
					}else break;
				}
			}
			if (counter >= show_time) {
				break;
			}
			counter ++;
		}
		
			
	}//end main
	
	public static void readFromFile(String name){
		Pattern p = Pattern.compile("\\d+");
		Matcher m;
		int arrival_mean=0; int cancel_mean=0; int exit_mean=0; double exit_rate=0; int show_time=0;
		int sell_time=0; int agent_num=0; int cancel_duration=0; int sell_duration=0; int tickets_count=0;
		try {
    		File file = new File(name);
            Scanner sc = new Scanner(file);

            int counter  = 0;
            while (sc.hasNextLine()) {
                m =  p.matcher(sc.nextLine());
                if(m.find()){
                	System.out.println(m.group());
                	switch (counter){
                	case 0: arrival_mean = Integer.parseInt(m.group()); 
                	case 1: cancel_mean = Integer.parseInt(m.group());
                	case 2: exit_mean = Integer.parseInt(m.group());
                	case 3: exit_rate = Double.parseDouble(m.group()); 
                	case 4: sell_time = changeToMiliS(m.group());
                	case 5: show_time = changeToMiliS(m.group());
                	case 6: agent_num = Integer.parseInt(m.group());
                	case 7: cancel_duration = Integer.parseInt(m.group())*60*1000;//milisecond
                	case 8: sell_duration = Integer.parseInt(m.group())*60*1000;//milisecond
                	case 9: tickets_count = Integer.parseInt(m.group());
                	default: break;
                	}
                	counter ++;
                }
            }
            sc.close();
            
            
            
            
//             = p.matcher("There are more than -2 and less than 12 numbers here");
//            while (m.find()) {
//              System.out.println(m.group());
//            }
            
//            generateRandomNumbers( arrival_mean,  cancel_mean,  exit_mean,  exit_rate, show_time);
            
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}


	private static int changeToMiliS(String str) {
		if(str == "01:00") return 60*60*1000;
		if(str == "03:00") return 180*60*1000;
		return 120;
	}
}
