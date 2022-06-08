package server;

public class Log {
    private static Log instance = null;
    //private static ListArray<Request>=new ListArray<Request>();
    private Log()
    {
      
    }
    public static Log getInstance()
    {
        if (instance == null)
            instance = new Log();
  
        return instance;
    }

}
