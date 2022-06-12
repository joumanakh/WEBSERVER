package server;
import java.util.ArrayList;
public class Log {
    private static Log instance= new Log();;
    private static ArrayList<Request> requests=new ArrayList<Request>();
    private Log()
    {
      
    }
    public static Log getInstance()
    {
        return instance;
    }
    public static void saveRequest(Request request) {
    	requests.add(request);
    }

}
