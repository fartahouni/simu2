import java.util.ArrayList;
import java.util.Random;


public class RandomNumberGenerator {
	ArrayList<Double> reserve_arrival_time = new ArrayList<>();
	ArrayList<Integer>  reserve_ticket_number = new ArrayList<>();
	ArrayList<Double> reserve_queuee_left = new ArrayList<>();
	ArrayList<Boolean> is_left = new ArrayList<>();
	ArrayList<Double> cancel_arrival_time = new ArrayList<>();
	
	public void generateRandomNumbers(int arrival_mean, int cancel_mean, int exit_mean, double exit_rate,int show_time){
		Random u = new Random();
		u.setSeed(System.currentTimeMillis());
		
		
		//reserve_interarrival_time.add((double) 0);
		reserve_arrival_time.add((double) 0);
		int n2 = u.nextInt(2) + 1;
		reserve_ticket_number.add(n2);
		double n3 = generateExpVar(exit_mean, u);
		reserve_queuee_left.add(round(n3,3));
		boolean left = u.nextBoolean();
		is_left.add(left);

		while (true){
			if(reserve_arrival_time.get(reserve_arrival_time.size()-1) < show_time){
				//generate exponential for reserve_interarrival_time
				//u.setSeed(0);
				double n1 = generateExpVar(arrival_mean, u);
				double arrival_time = reserve_arrival_time.get(reserve_arrival_time.size()-1) + n1;
				reserve_arrival_time.add(round(arrival_time,3));
				
				//u.setSeed(0);
				//generate unit for reserve_ticket_number
				n2 = u.nextInt(2) + 1;
				reserve_ticket_number.add(n2);
				
				//generate exponential for reserve_queuee_left
				n3 = generateExpVar(exit_mean, u);
				double n4 = n3 + reserve_arrival_time.get(reserve_arrival_time.size()-1);
				reserve_queuee_left.add(round(n4,3));
				
				//generate unit for is_left
				double nn = u.nextDouble();
				if (nn > exit_rate){
					left = false;
				}else
					left = true;
				is_left.add(left);
			}
			
			//generate exponential for cancel_interarrival_time
			if(cancel_arrival_time.size() == 0){
				double n5 = generateExpVar(cancel_mean, u);
				cancel_arrival_time.add(round(n5,3));
			}
			
			if(cancel_arrival_time.size() > 0 && cancel_arrival_time.get(cancel_arrival_time.size()-1) < show_time){
				double n5 = generateExpVar(cancel_mean, u);
				double c_arrival_time = cancel_arrival_time.get(cancel_arrival_time.size()-1) + n5;
				cancel_arrival_time.add(round(c_arrival_time,3));
			}
			
			if((cancel_arrival_time.size() > 0 && cancel_arrival_time.get(cancel_arrival_time.size()-1) >= show_time) && reserve_arrival_time.get(reserve_arrival_time.size()-1) >= show_time){
				break;
			}
		}//end while
	}//end generateRandomNumbers
	
	public double generateExpVar(int mean,Random u ){
		return Math.log(1-u.nextDouble())/(-mean);
	}
	
	
	public double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	


}
