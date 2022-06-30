import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;


public class Sender implements Runnable {
    public void run() {
        try {
            // Создание connectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://172.27.74.99:61616");
            // Создание подключения
            Connection connection = connectionFactory.createConnection();
            connection.start();
            // Создание сессии
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Создание очереди. Чего ждать связному?
            Destination destination = session.createQueue("ROCKET");
            // Создание генератора сообщений
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // Инициализация тела сообщения
            int i = 0;
            while (++i < 100) {
                // Создание сообщения
                TextMessage message = session.createTextMessage("Sender sent: "+i);
                // Отправка сообщения
                producer.send(message);
            }
            // Сборка мусора
            session.close();
            connection.close();
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }
}

