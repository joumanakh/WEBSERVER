package server;
import java.util.ArrayList;
public class Log {
    private static Log instance = null;
    private ArrayList<Request> requests=new ArrayList<Request>();
    private Log()
    {
      
    }
    public static Log getInstance()
    {
        if (instance == null)
            instance = new Log();
  
        return instance;
    }
    public  void saveRequest(Request request) {
    	this.requests.add(request);
    }

}
