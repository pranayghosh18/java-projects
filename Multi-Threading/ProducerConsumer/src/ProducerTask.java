public class ProducerTask implements Runnable {
    SharedResousers resourse;
    long cooldownTime;

    ProducerTask(SharedResousers res, long cooldownTime){
        this.resourse = res;
        this.cooldownTime = cooldownTime;
    }

    @Override
    public void run() {
        try{
            System.out.println("pushing 5 elems in one go");

            for(int i=0; i<5; i++){
                this.resourse.produce();
                System.out.println("sleeping for "+ this.cooldownTime +" sec");
                Thread.sleep(this.cooldownTime*1000);
            }
        }
        catch(Exception e){
            System.out.println("Exception Msg : " + e.toString());
        }
    }
}
