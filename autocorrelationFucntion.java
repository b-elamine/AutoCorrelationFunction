import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import smile.timeseries;


/**
 * autocorrelationFucntion
 * https://github.com/b-elamine
 * You can make the code better by using generic data types 
 * I use this code for my personal project that's why it is kind of specific
 * I'll do more updates later..
 */

public class autocorrelationFucntion{


    public List<Float> afc(List<Float> sequence, float lags){
        float sum = 0, sumEcarts=0;
        float var;
        List<Float> ecarts = new ArrayList<>(); //Deviation (Every data instance minus the mean)
        List<Float> afc = new ArrayList<>(); //Autocorrelation output 

        //Variance calculation
        //First we calculate the sum of our data set
        for (int i = 0; i<sequence.size(); i++){
            sum +=sequence.get(i);
        }

        //Deviation
        for (int i=0; i<sequence.size();i++){
            ecarts.add((float)(sequence.get(i)-(sum /sequence.size())));
        }

        //Sum ecarts contains now the standard deviation
        for(int i=0; i < ecarts.size(); i++){
            sumEcarts +=Math.pow(ecarts.get(i),2);
        }

        //We have all to calculate the variance..
        var = (float)sumEcarts/ecarts.size();

        //Autocorrelation function 
        for (int i = 0; i< lags; i++){
            sum = 0;
            if (i==0){afc.add((float)1);}
            else {
                for(int j =0; j < ecarts.size()-i ; j++){
                    sum += ecarts.get(j+i)*ecarts.get(j);
                }
                afc.add(sum / (sequence.size()-i) / var);
            }

        }

        return afc;
    }

    //Execution example for test 

    public static void main(String[] args) {
        float sum = 0, sumEcarts=0, lags;
        float var;
        List<Float> sequence = new ArrayList<>(); 
        List<Float> ecarts = new ArrayList<>(); 

        //Adding some initial data
        sequence.add((float)4); sequence.add((float)6);
        sequence.add((float)3); sequence.add((float)8);
        sequence.add((float)9); sequence.add((float)7);
        sequence.add((float)4); sequence.add((float)6);
        sequence.add((float)8);

        //For the test we choosed lags to be the size of our data [0,1,2....,(size of our data)]
        lags = sequence.size();
        autocorrelationFucntion afc = new autocorrelationFucntion();

            for (int i = 0; i<afc.afc(sequence, lags).size(); i++){
                System.out.println(afc.afc(sequence, lags).get(i));
            }

        }
}
