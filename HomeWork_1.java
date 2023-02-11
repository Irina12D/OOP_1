import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HomeWork_1 {

    public static void main(String[] args) {

       race(racingParticipants());
        
    }
    
    // метод для создания участников гонки	
    static ArrayList<Car>  racingParticipants(){
        // 9 racing cars
        String[] modelList = {"Bugatti", "Koenigsegg", "Hennessey", "Ford", "AstonMartin", "McLaren", "Pagani", "Lamborghini", "Ferrari"};
        int[] yearList = {2020, 2019, 2021, 2022, 2020, 2019, 2022, 2020, 2017};
        int[] costList = {3900000, 2800000, 1800000, 500000, 3000000, 2000000, 3400000, 3600000, 625000};
        String[] colorList = {"black", "blue", "yellow", "silver", "black", "white", "gold", "azure", "red"};
        int[] powerList = {1600, 1625, 1844, 655, 1176, 1070, 802, 819, 1000};
        int[] maxSpeedList = {490, 531, 500, 347, 400, 403, 379, 350, 340};
        String[] VINList = {"111BGT111Chiron20", "222KGG222Jesko019", "333HNS333VenomF51", "444FRD444GTV62022", "555AM555Valkyrie", "666MSD666Speed019", "777PGN777HuayraBC", "888LBG888Roadster", "999FRI999Stradale"}; 

        ArrayList<Car> racingCars = new ArrayList<>();
        int n = (int)(Math.random()*(9 - 2)) + 2;
        Set<Integer> rndNum = new HashSet<>();
        for(int i = 0; i < n; i++)
        {
            Integer indCar;
            do
            {
                indCar = (int)(Math.random()*(9));    
            } while (rndNum.contains(indCar));
            rndNum.add(indCar);
            Car raceCar = new Car(modelList[indCar], yearList[indCar], costList[indCar], colorList[indCar], powerList[indCar], maxSpeedList[indCar]);
            raceCar.setVIN(VINList[indCar]);
            raceCar.start();
            racingCars.add(raceCar);
        }
        return racingCars;        
    }

    // вспомогательный метод для разбиения трассы длины length на parts частей случайным образом	
    static int[] routeSplit(int length, int parts)
    {
        int[]result = new int[parts];
        int sum = 0;
        for (int i = 0; i < parts; i++)
        {
            int value = (int)(Math.random()*(1500-350))+350;
            result[i] = value;
            sum += value;
        }
        double norma = (double)(length) / sum;
        sum = 0;
        for (int i = 0; i < parts; i++)
        {
            result[i] = (int)(result[i] * norma);
            sum += result[i];
        }
        if (sum < length) result[0] += (length-sum);
        return result;
    }

    // вспомогательный метод для нахождения номера лидера (по времени)	
    static int leader(double[] arr)
    {
        int minind = 0;
        for (int i = 1; i < arr.length; i++)
        {
            if(arr[i]  < arr[minind])
                minind = i;
        }
        return minind;
    }

    // метод организации гонки	
    static void race(ArrayList<Car>  participants)
    {
        System.out.println("Ladies and gentlemen, the race begins!\nWe present to you the participants of the race:");
        for(Car car : participants){
            System.out.println("  " + car.toString());
        }
        System.out.println();

        // raceRoute:
           // race track length = from 5 000 km to 15 000 km
           // number of sections of the race track = from 5 to 10
        int raceRouteLength = (int)(Math.random() * (15000 - 5000 + 1)) + 5000;
        int section = (int)(Math.random() * (10 - 5 + 1)) + 5;
        int[] sections = routeSplit(raceRouteLength, section);

        System.out.println("The race takes place on a " + raceRouteLength + " km long track consisting of " + section + " sections");
        System.out.println("Now Let's Go!");

        double[] times = new double [participants.size()];
        for (int i = 0; i < section; i++) 
        {
            int num = 0;
            for(Car car : participants)
            {
                int speed = (int)(Math.random() * (car.maxSpeed - 90)) + 90;
                times[num++] = sections[i] / (double) (speed);
                if(i == section-1)
                    participants.get(num-1).stop();                
            }
            //System.out.println(Arrays.toString(times));
            int numLeader = leader(times);
            if (i < section - 1)
                System.out.println("   on the " + (i+1) + " section of the route " + participants.get(numLeader).toString() +  " is in the lead");
            else 
                System.out.println("   and the winner of the race is " + participants.get(numLeader).toString() +  "!!!");
        }
        raceResult(participants, times);
    }

    // метод для формирования и вывода финальной таблицы гонки	
    static void raceResult(ArrayList<Car>  participants, double[] resultingTime)
    {
        boolean obmen = true;
        int n = resultingTime.length;
        int k = 0; 
        for (; k < n - 1 && obmen; k++)
	    {
            obmen = false;
            for(int i = 0; i < n - k - 1; i++)
                if (resultingTime[i] > resultingTime[i + 1])
                {
                    double temp = resultingTime[i];
                    resultingTime[i] = resultingTime[i + 1];
                    resultingTime[i + 1] = temp;
                    Collections.swap(participants, i, i + 1);
                    obmen = true;
                }
     	}
        System.out.println("Final table:");
        for(int i = 0; i < resultingTime.length; i++)
          System.out.println(String.format("   %d. %-10s\t %10.3f hours",i+1, participants.get(i).toString(), resultingTime[i]));
        System.out.println("That's it. See you soon...\n");
    }
}
