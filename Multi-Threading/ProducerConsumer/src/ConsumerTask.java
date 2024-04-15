public class ConsumerTask implements Runnable {
    SharedResousers resourse;
    long cooldownTime;

    ConsumerTask(SharedResousers res, long cooldownTime){
        this.resourse = res;
        this.cooldownTime = cooldownTime;
    }

    @Override
    public void run() {
        try{
            System.out.println("consuming 3 elems in one go");

            for(int i=0; i<3; i++){
                this.resourse.consume();
                System.out.println("sleeping for " + this.cooldownTime +" sec");
                Thread.sleep(cooldownTime*1000);
            }
        }
        catch(Exception e){
            System.out.println("Exception Msg : " + e.toString());
        }
    }
}
