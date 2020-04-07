import java.io.*;
import java.util.*;

public class VadacadPatch {

    public static void main(String[] args) {

        Map<String, String> pointNames = new HashMap<>(); //мапа для хранения пар пункты вадакада - пользовательские пункты (ключ - значение)
        List<String> outFile = new ArrayList<>();         // для хранения выходных данных
        int i = 0;                                         //счетчик пунктов вадакада



        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("stations.txt"))){
            String temp = null;

            while ((temp = bufferedReader.readLine())!=null){
                String tempValue = "TX1D"+i;
                pointNames.put(temp.split(" ")[0], tempValue.toString());
                i++;
            }
        }
        catch (IOException e){
            e.printStackTrace();

        }


        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("out.rdf"))){
            String temp;
            String newTemp = null;

            while ((temp = bufferedReader.readLine())!=null){
                String[] strings = temp.split(","); //пункты вадакада идут вторым элеметом в массиве

                for (Map.Entry<String,String> entry : pointNames.entrySet()){

                    if(strings[1].equals(entry.getValue())){
                        temp = temp.replace(entry.getValue(),entry.getKey());
                        break;
                    }

                }
                outFile.add(temp);

            }
        }
        catch (IOException e){
            e.printStackTrace();

        }


       try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("newOut.rdf"))){

           for (String s :
                   outFile) {
               bufferedWriter.write(s);
               bufferedWriter.newLine();
           }
       }

       catch (IOException e) {
           e.printStackTrace();
       }


    }


}
