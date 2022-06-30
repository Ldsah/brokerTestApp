import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
public class BrokerApp {
    public static void main(String[] args) throws Exception {
        ThreadExecutor(new Sender(), false);
        ThreadExecutor(new Receiver(), false);
    }
    public static void ThreadExecutor(Runnable runnable, boolean daemon) {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }

    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }





}
