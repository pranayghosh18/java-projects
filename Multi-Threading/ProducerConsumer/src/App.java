public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("started by " + Thread.currentThread().getName());
        SharedResousers resourse = new SharedResousers(4);

        // fast producer, slow consumer
        Thread producer = new Thread(new ProducerTask(resourse, 4));
        Thread consumer = new Thread(new ConsumerTask(resourse, 8));

        producer.start();
        consumer.start();
    }
}
