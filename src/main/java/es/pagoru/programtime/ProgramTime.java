package es.pagoru.programtime;

/**
 * Created by Pablo on 22/10/2016.
 */
public class ProgramTime {

    public static void main(String[] args){

        while(true){

            System.out.println(ActiveWindow.getActiveWindowProcess());
            Programs.incrementProgram(ActiveWindow.getActiveWindowProcess());

            try{
                Thread.sleep(1000);
            } catch (Exception e){
                e.printStackTrace();
            }

        }

    }

}