import java.lang.reflect.Constructor;
import java.util.ArrayList;


public abstract class Activity {
	double sell_time;
	double show_time;
	double duration;
	int server = 0;
	String[] server_status ;
	
//	ArrayList<ticketCanceling> c_list = new ArrayList<>();
//	ArrayList<> r_list = new ArrayList<>();
	
	public Activity(double sell_time , double show_time , double duration , int server){
		this.sell_time = sell_time;
		this.show_time = show_time;
		this.duration = duration;
		server_status = new String[server];
		for (int i = 0; i < server_status.length; i++) {
			server_status[i] = "i";
		}
	}
	public abstract boolean condition();
	public abstract void startActions();
	public abstract double duration();
	public abstract void finishActions();
	
//	public void add(double time, String kind){
//		if (kind == "c"){
//			c_list.add(new ticketCanceling(sell_time, show_time, duration, server));
//		}//else
////			r_list.add(time);
//	}
	
	public double time() {
		return System.currentTimeMillis();
	}
	
	

}


class ticketCanceling extends Activity{
	
	public ticketCanceling(double sell_time, double show_time , double duration,int server) {
		super(sell_time, show_time, duration, server);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {
				
		if(this.time() >= sell_time && this.time()+this.duration <= show_time){
			if( get_idle() != 0){
				return true;
			}
		}
		return false;
	}

	@Override
	public void startActions() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public double duration() {
		// TODO cancel_duration ra bayad bargardaned
		return this.duration;
	}

	@Override
	public void finishActions() {
		// TODO Auto-generated method stub
		
	}
	
	public int get_idle(){
		int idle_num = 0;
		for (int i = 0; i < server_status.length; i++) {
			if(server_status[i] == "i")
				idle_num ++;
		}
		return idle_num;
	}
	
	
}

//class ticketReserving extends Activity{
//	
//}
//
//class leaveQueuee extends Activity{
//	
//}
//
//class waitForCancel extends Activity{
//	
//}
//
//class waitForReserve extends Activity{
//	
//}


