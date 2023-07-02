package com.company ;
import java.io.*;
import java.util.*;

public class Question {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class vacPair{
        String name ;
        int noOfDoses;
        int gap;
        public vacPair(String name, int noOfDoses,int gap){
            this.name = name;
            this.noOfDoses = noOfDoses;
            this.gap = gap;
        }
    }
    static HashMap<Integer, vacPair> aV = new HashMap<>();
    static int avId = 0;
    public static void addVacine() throws IOException{
        System.out.print("Vaccine Name: ");
        String name = br.readLine();
        for(vacPair i: aV.values()){
            if(i.name.equals(name)){
                System.out.println("This vaccine is already registered");
                return;
            }
        }
            System.out.print("Number of Doses: ");
            int numOfDoses = Integer.parseInt(br.readLine());
            if(numOfDoses<=0){
                System.out.println("Please enter valid number of doses");
                return;
            }
            int gap = 0;
            if(numOfDoses>1){
                System.out.print("Gap between Doses: ");
                gap = Integer.parseInt(br.readLine());
                if(gap<=0){
                    System.out.println("Please enter a valid gap between doses");
                    return;
                }
            }
            System.out.println(" Vaccine Name: "+name+" Number of Doses: "+numOfDoses+" Gap between Doses: "+ gap);
            vacPair pr = new vacPair(name,numOfDoses,gap);
            aV.put(avId,pr);
            avId++;
    }
    static class slot_pair implements Comparable<slot_pair>{
        int day;
        int quant;
        int vac_id;
        public slot_pair(int day,int quant, int vac_id){
            this.day = day;
            this.quant = quant;
            this.vac_id = vac_id;
        }
        public int compareTo(slot_pair o){
            return this.day- o.day;
        }
    }
    static class pair_hosp{
        String name ;
        long pincode;
        slot_pair[] slotInfo;
        public pair_hosp(String name, long pincode, slot_pair[] slotInfo){
            this.name = name;
            this.pincode = pincode;
            this.slotInfo = slotInfo;
        }
    }
    static long uniqueIdHosp = 100000;
    static HashMap<Long, pair_hosp> aH = new HashMap<>();
    public static  void registerHosp() throws IOException{
        System.out.print("Hospital Name: ");
        String name = br.readLine();
        System.out.print("PinCode: ");
        long pincode = Long.parseLong(br.readLine());
        for(pair_hosp i :aH.values()){
            if(i.name.equals(name) && i.pincode == pincode){
                System.out.println("The Hospital is already registered.");
                return;
            }
        }
        pair_hosp ph = new pair_hosp(name, pincode,new slot_pair[0]);
        aH.put(uniqueIdHosp,ph);
        System.out.println("Hospital Name: "+name+" PinCode: "+pincode+" Unique ID: "+uniqueIdHosp);

        uniqueIdHosp++;
    }
    static class citizen_pair{
        String name;
        int age;
        int vaccineKonsalga ;
        int kitniBaarLga ;
        int aglaKabLagnaH;
        public citizen_pair(String name , int age, int vaccineKonsalga, int kitniBaarLga, int aglaKabLagnaH){
            this.age = age;
            this.name = name;
            this.kitniBaarLga = kitniBaarLga;
            this.aglaKabLagnaH = aglaKabLagnaH;
            this.vaccineKonsalga = vaccineKonsalga;
        }
    }
    static HashMap<Long, citizen_pair> cP = new HashMap<>();
    public static boolean validateAdhaar(String s){
        if(s.length()!= 12)return false;
        try{
            long i = Long.parseLong(s);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public static void registerCitiz() throws IOException{
        System.out.print(" Citizen Name: ");
        String name = br.readLine();
        System.out.print(" Age: ");
        int age = Integer.parseInt(br.readLine());
        System.out.print(" Unique ID:");
        String adharStr= br.readLine();
        long adhaarId = Long.parseLong(adharStr);
        if(!validateAdhaar(adharStr)){
            System.out.println(" Entered unique id was invalid ");
            return;
        }
        System.out.println(" Citizen Name: "+name+" Age: "+age+" Unique ID: "+adharStr);
        if(age<18){

            System.out.println("Only above 18 are allowed");
            return;
        }
        for (long i :cP.keySet()){
            if(i == adhaarId){

                System.out.println(" You are already registered ");
                return;
            }
        }
        citizen_pair cpp = new citizen_pair(name,age,-1,0,-1);
        cP.put(adhaarId,cpp);
    }
    public static void createSlot() throws IOException{
        System.out.println("Enter Hospital ID:");
        long id = Long.parseLong(br.readLine());
        boolean check1 = false;
        for(long i:aH.keySet()){
            if(i == id){
                check1 = true;
                break;
            }
        }
        if(!check1){
            System.out.println("This hospital is not registered");
            return;
        }
        System.out.println("Enter number of Slots to be added: ");
        int numOfSlots = Integer.parseInt(br.readLine());
        if(aH.get(id).slotInfo.length == 0){
            int n = numOfSlots;
            aH.get(id).slotInfo= new slot_pair[numOfSlots];
            int itr = 0;
            while (n>0){
                System.out.println("Enter Day Number: ");
                int dayNumber = Integer.parseInt(br.readLine());
                System.out.println("Enter Quantity:");
                int quan = Integer.parseInt(br.readLine());
                if(quan<=0){
                    System.out.println("Please enter a valid quantity ");
                    aH.get(id).slotInfo= new slot_pair[0];
                    return;
                }
                System.out.println("Select Vaccine");
                if(aV.isEmpty()){
                    System.out.println("No vaccines are available ");
                    aH.get(id).slotInfo= new slot_pair[0];
                    return;
                }
                for(int i :aV.keySet()){
                    System.out.println(i+". "+aV.get(i).name);
                }
                int vaccineNumber = Integer.parseInt(br.readLine());
                if(vaccineNumber<0 || vaccineNumber>= aV.size()){
                    System.out.println("The slot number entered was not correct");
                    aH.get(id).slotInfo= new slot_pair[0];
                    return;
                }
                for(slot_pair s:aH.get(id).slotInfo){
                    try{
                        if(s.day == dayNumber && s.vac_id == vaccineNumber){
                            System.out.println("Booking for this day and vaccine has already been done by this hospital");
                            aH.get(id).slotInfo= new slot_pair[0];
                            return;
                        }}catch (Exception e){
                        continue;
                    }}
                aH.get(id).slotInfo[itr] = new slot_pair(dayNumber,quan,vaccineNumber);
                itr++;
                System.out.println("Slot added by Hospital "+id+" for Day: "+dayNumber+", Available Quantity: "+quan+" of Vaccine "+ aV.get(vaccineNumber).name);
                n--;
            }Arrays.sort(aH.get(id).slotInfo);
        }else{
            System.out.println("The slots have already been added ");
        }
    }
    public static void bookSlot() throws IOException{
        System.out.print("Enter patient Unique ID:");
        String adhaarStr = br.readLine();
        long adhaarId = Long.parseLong(adhaarStr);
        if(!validateAdhaar(adhaarStr)){
            System.out.println("Enter unique id was invalid ");
            return;
        }
        System.out.print("1. Search by area\n" + "2. Search by Vaccine\n" + "3. Exit\n"+"Enter option:");
        int op = Integer.parseInt(br.readLine());
        String vac_name = "";
        long id;
        if(op==3)return;
        else if(op== 1 || op == 2){
            if(op == 1){
            System.out.print("Enter PinCode: ");
            long pincode= Long.parseLong(br.readLine());
            int c =0;
            for(long i : aH.keySet()){
                if(aH.get(i).pincode == pincode){
                    System.out.println(i+" "+aH.get(i).name);
                    c++;
                }
            }
            if(c== 0){
                System.out.println("No hospital of this pincode available ");
                return;
            }

            System.out.print("Enter hospital id: ");
            id = Long.parseLong(br.readLine());
            boolean flag = false;
            for(long i:aH.keySet()){
                if(i == id && aH.get(i).pincode == pincode)flag = true;
            }
            if(!flag){
                System.out.println("The entered id of Hospital was not listed above");
                return;
            }}else{
//                ************************************************************************************************************
                System.out.print("Enter Vaccine name:");
                vac_name = br.readLine();
                int c =0;
                for(long i : aH.keySet()){
                    slot_pair[] temarr = aH.get(i).slotInfo;
                    for(slot_pair itr :temarr){
                        if(aV.get(itr.vac_id).name.equals(vac_name)){
                        System.out.println(i+" "+aH.get(i).name);
                        c++;
                        break;}
                    }}

                if(c== 0){
                    System.out.println("This vaccine is not available ");
                    return;
                }

                System.out.print("Enter hospital id: ");
                id = Long.parseLong(br.readLine());
                boolean flag = false;
                for(long i:aH.keySet()){
                    if(i == id ){
                        slot_pair[] temarr = aH.get(i).slotInfo;
                        for(slot_pair itr :temarr){
                            if(aV.get(itr.vac_id).name.equals(vac_name))flag = true;
                        }
                    }
                }
                if(!flag){
                    System.out.println("The entered id of Hospital was not listed above");
                    return;
                }
            }
            ArrayList<Integer> ar = new ArrayList<>();
            slot_pair[] arr = aH.get(id).slotInfo;
            if(cP.get(adhaarId) == null){
                System.out.println("You have not registered on the portal");
                return;
            }
            citizen_pair ref = cP.get(adhaarId);

            if(ref.vaccineKonsalga== -1){
                for(int i =0;i< aH.get(id).slotInfo.length;i++) {
                    if(op == 2){
                        if(aV.get(arr[i].vac_id).name.equals(vac_name)){
                        System.out.println(i + "-> Day: " + arr[i].day + " Available Qty:" + arr[i].quant + " Vaccine: " + aV.get(arr[i].vac_id).name);
                        ar.add(i);}
                    }else{
                    System.out.println(i + "-> Day: " + arr[i].day + " Available Qty:" + arr[i].quant + " Vaccine: " + aV.get(arr[i].vac_id).name);
                    ar.add(i);}
                }}else{
                if(ref.kitniBaarLga == aV.get(ref.vaccineKonsalga).noOfDoses){
                    System.out.println("You are Fully vaccinated");
                    return;
                }

                    for(int i=0;i<aH.get(id).slotInfo.length;i++){
                        if(ref.vaccineKonsalga == arr[i].vac_id && ref.aglaKabLagnaH == arr[i].day && arr[i].quant>0){
                            System.out.println(i + "-> Day: " + arr[i].day + " Available Qty:" + arr[i].quant + " Vaccine: " + aV.get(arr[i].vac_id).name);
                            ar.add(i);
                        }
                    }
            }
            if(ar.size()==0){
                System.out.println("No slots available ");
                return;
            }
            System.out.println("Choose slot : ");
            int choose_slot = Integer.parseInt(br.readLine());
            boolean check = false;
            for(int r:ar){
                if(r== choose_slot){
                    check = true;
                    break;
                }
            }
            if(!check){
                System.out.println("You did not choose a valid slot");
                return;
            }
            if(ref.vaccineKonsalga ==-1){
                ref.vaccineKonsalga = arr[choose_slot].vac_id;
            }
            ref.kitniBaarLga++;
            if(ref.aglaKabLagnaH== -1){
                ref.aglaKabLagnaH = arr[choose_slot].day;
                ref.aglaKabLagnaH+= aV.get(arr[choose_slot].vac_id).gap;
            }else ref.aglaKabLagnaH += aV.get(arr[choose_slot].vac_id).gap;
            arr[choose_slot].quant--;
//            Marrion vaccinated with covax
            System.out.println(ref.name+" vaccinated with "+aV.get(ref.vaccineKonsalga).name);
        }
        else{
            System.out.println("You did not enter a valid option ");
            return;
        }
    }
    public static void displayHosp() throws IOException{
        System.out.print("Enter Hospital Id: ");
        long id = Long.parseLong(br.readLine());
        if(aH.get(id) == null){
            System.out.println("No Hospital with this id is registered");
            return;
        }
        pair_hosp ap = aH.get(id);
        for(slot_pair i : ap.slotInfo){
            System.out.println("Day: "+i.day+" Vaccine: "+aV.get(i.vac_id).name+" Available Qty: "+i.quant);
        }
    }
    public static void citizStatus()throws  IOException{
        System.out.println("Enter Patient ID:");
        long adharId = Long.parseLong(br.readLine());
        if(cP.get(adharId)== null){
            System.out.println("This id is not registered");
            return;
        }
        citizen_pair ref = cP.get(adharId);
        if(ref.kitniBaarLga == 0) System.out.println("REGISTERED");
        else if(ref.kitniBaarLga == aV.get(ref.vaccineKonsalga).noOfDoses) System.out.println("FULLY VACCINATED");
        else System.out.println("PARTIALLY VACCINATED");
        if(ref.kitniBaarLga != 0){
            System.out.println("Vaccine Given: "+ aV.get(ref.vaccineKonsalga).name);
            System.out.println("Number of Doses given: "+ref.kitniBaarLga);
            if(ref.kitniBaarLga != aV.get(ref.vaccineKonsalga).noOfDoses)System.out.println("Next Dose due date: "+ref.aglaKabLagnaH);}
    }

    public static void main(String[] args) throws IOException {
        System.out.println("CoWin Portal initialized....\n" + "---------------------------------");
        while (true){
            System.out.println("1. Add Vaccine\n" + "2. Register Hospital\n" + "3. Register Citizen\n" +
                    "4. Add Slot for Vaccination\n" + "5. Book Slot for Vaccination\n" + "6. List all slots for a hospital\n" +
                    "7. Check Vaccination Status\n" + "8. Exit\n" + "---------------------------------\n");
            int n = Integer.parseInt(br.readLine());
            if(n == 1){
                addVacine();
                System.out.println("---------------------------------");
            }
            else if(n == 2){
                registerHosp();
                System.out.println("---------------------------------");
            }
            else if(n ==3){
                registerCitiz();
                System.out.println("---------------------------------");
            }
            else if (n ==4){
                createSlot();
                System.out.println("---------------------------------");
            }
            else if(n == 5){
                bookSlot();
                System.out.println("---------------------------------");
            }
            else if(n == 6){
                displayHosp();
                System.out.println("---------------------------------");
            }
            else if(n ==7){
                citizStatus();
                System.out.println("---------------------------------");
            }
            else if(n== 8)break;
            else System.out.println("Enter a valid option");


        }
        }}

