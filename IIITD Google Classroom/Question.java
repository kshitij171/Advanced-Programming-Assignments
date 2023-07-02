package com.company ;
import java.io.*;
import java.util.*;

class lecture_pair{
    private String title;
    private LinkedList<String> content ;
    private Date currentDate;

    public lecture_pair(String title,LinkedList<String> content,Date currentDate) {
        this.currentDate = currentDate;
        this.title = title;
        this.content = content;
    }
    public String getTitle(){return this.title;}
    public Date getCurrentDate(){return this.currentDate;}
    public LinkedList<String> getContent(){return this.content;}
}
 class video_pair{
    private String title;
    private Date currentDate;
    private String videoFile;
    public video_pair( Date currentDate,String title, String videoFile){
        this.currentDate  = currentDate;
        this.title = title;
        this.videoFile = videoFile;
    }
    public String getTitle(){return this.title; }
    public Date getCurrentDate(){return this.currentDate;}
    public String getVideoFile(){return this.videoFile;}
}
interface assignment {
    String status = "";
    void changeStatus(String status);
    void changeOpenOr(int n);
    void setMarksGot(int n );
    void setMarksByWhom(String s);
}
 class quiz_pair implements assignment{
    private String question;
    private String status;
    private int openOrClose ;
    private int marksGot ;
    private String MarksByWhom;
    public quiz_pair(String question, String status, int openOrClose, int marksGot, String MarksByWhom){
        this.question = question;
        this.status = status;
        this.openOrClose = openOrClose;
        this.marksGot = marksGot;
        this.MarksByWhom = MarksByWhom;
    }
    public void changeStatus(String status){
        this.status = status;
    }
    public void changeOpenOr(int n){
        this.openOrClose = n;
    }
    public void setMarksGot(int n ){
        this.marksGot = n;
    }
    public void setMarksByWhom(String s){
        this.MarksByWhom = s;
    }
    public String getQuestion(){
        return this.question;
    }
    public String getStatus(){return this.status;}
    public int getMarksGot(){return this.marksGot;}
    public int getOpenOrClose(){return  this.openOrClose;}
    public String getMarksByWhom(){return this.MarksByWhom;}
}
class assignment_pair implements assignment{
    private String title;
    private String status;
    private int maxMarks;
    private int openOrClose ;
    private int marksGot ;
    private String MarksByWhom;
    public assignment_pair(String title, String status,int marksGot, int maxMarks, int openOrClose, String MarksByWhom){
        this.title = title;
        this.status = status;
        this.openOrClose = openOrClose;
        this.marksGot = marksGot;
        this.MarksByWhom = MarksByWhom;
        this.maxMarks = maxMarks;
    }
    public String getTitle(){
        return this.title;
    }
    public void changeStatus(String status){
        this.status = status;
    }
    public void changeOpenOr(int n){
        this.openOrClose = n;
    }
    public int getOpenOrClose(){return this.openOrClose;}
    public void setMarksGot(int n ){
        this.marksGot = n;
    }
    public void setMarksByWhom(String s){
        this.MarksByWhom = s;
    }
    public String getStatus(){return  this.status;}
    public int getMarksGot(){return this.marksGot;}
    public String getMarksByWhom(){return this.MarksByWhom;}
    public int getMaxMarks() {
        return this.maxMarks;
    }
}
interface person{
    String name = "";
    void display();
}
class instruct_pair implements person {
    private String name;
    private LinkedList<lecture_pair> lp;
    private LinkedList<video_pair> vp;
    public instruct_pair(String name, LinkedList<lecture_pair> lp, LinkedList<video_pair> vp){
        this.name = name;
        this.lp = lp;
        this.vp = vp;
    }
    public void display() {
        for (lecture_pair i : lp) {
            System.out.println("Title: " + i.getTitle());
            LinkedList<String> kk = i.getContent();
            int i1 = 1;
            for (String j : kk) {
                System.out.println("Slide " + i1 + " " + j);
                i1++;
            }
            System.out.println("Number of slides: " + (i1 - 1));
            System.out.println("Date of upload: " + i.getCurrentDate());
            System.out.println("Uploaded by: " + this.name);
        }
        for (video_pair i : vp) {
            System.out.println("Title of video: " + i.getTitle());
            System.out.println("Video file: " + i.getVideoFile());
            System.out.println("Date of upload: " + i.getCurrentDate());
            System.out.println("Uploaded by: " + this.name);
        }
    }
    public void addLecture(lecture_pair a){this.lp.addLast(a);}
    public void addVideo(video_pair a){this.vp.addLast(a);}
    public String getName(){return  this.name;}

}

 class student_pair implements person{
    private String name;
    private LinkedList<assignment_pair> ap ;
    private LinkedList<quiz_pair> qp;
    public student_pair(String name , LinkedList<quiz_pair> qp, LinkedList<assignment_pair> ap){
        this.name = name;
        this.ap = ap;
        this.qp = qp;
    }
    public void statusChange(int index,String s){
        this.ap.get(index).changeStatus(s);
    }
    public String getName(){return this.name;}
    public assignment_pair AssignPairDedo(int index){ return this.ap.get(index);}
    public quiz_pair quesPairDedo(int index){return this.qp.get(index);}
    public void changeOpenAssign(int index){
        this.ap.get(index).changeOpenOr(0);
    }
    public void changeOpenQuiz(int index){
        this.qp.get(index).changeOpenOr(0);
    }
    public int sizeOfAP(){return this.ap.size();}
     public int sizeOfQP(){return this.qp.size();}
    public void display(){
        int itr = 0;
        for(assignment_pair i : this.ap){
            System.out.println("ID: "+itr+"Assignment: "+i.getTitle()+" Max Marks:"+i.getMaxMarks());
            itr++;
        }
        System.out.println("-------------------------------------------------------------------------");
        for(quiz_pair i: this.qp) {
            System.out.println("ID: " + itr + " Question:" + i.getQuestion());
            itr++;
        }System.out.println("-------------------------------------------------------------------------");

    }
    public void add_quiz(quiz_pair e){
        this.qp.addLast(e);
    }
    public void add_assign(assignment_pair e){
        this.ap.addLast(e);
    }
    public void funTogradeAssign(int assignId,int mark) {
        assignment_pair mu = this.ap.get(assignId);
        mu.setMarksGot(mark);
    }
    public void funTogradeQuiz(int assignId,int mark) {
        assignment_pair mu = this.ap.get(assignId);
        mu.setMarksGot(mark);
    }
    public void viewGrades(){
        System.out.println("Graded submissions");
        for(assignment_pair i: this.ap){
            if(i.getMarksGot()!= -1){
                System.out.println("Submission: "+i.getStatus());
                System.out.println("Marks scored:"+i.getMarksGot());
                System.out.println("Graded by: "+i.getMarksByWhom() );
            }
        }
        for(quiz_pair i: this.qp){
            if(i.getMarksGot()!= -1){
                System.out.println("Submission: "+i.getStatus());
                System.out.println("Marks scored:"+i.getMarksGot());
                System.out.println("Graded by: "+i.getMarksByWhom() );
            }
        }
        System.out.println("----------------------------\n" + "Ungraded submissions");
        for(assignment_pair i: this.ap){
            if(i.getMarksGot()== -1 && !(i.getStatus().equals(""))){
                System.out.println("Submission: "+i.getStatus());
            }
        }
        for(quiz_pair i: this.qp){
            if(i.getMarksGot()== -1 && !(i.getStatus().equals(""))){
                System.out.println("Submission: "+i.getStatus());
            }
        }
    }

}
public class Question {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static HashMap<Integer, Integer> hmForOpen = new HashMap<>();

    static LinkedList<student_pair> globalSpKaList = new LinkedList<>();

    static LinkedList<instruct_pair> gloablInstrucKaList  = new LinkedList<>();

    static LinkedList<String> cmntSection  = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        gloablInstrucKaList.addLast(new instruct_pair("I0", new LinkedList<>(),new LinkedList<>()));
        gloablInstrucKaList.addLast(new instruct_pair("I1", new LinkedList<>(),new LinkedList<>()));
        globalSpKaList.addLast(new student_pair("S0",new LinkedList<>(), new LinkedList<>()));
        globalSpKaList.addLast(new student_pair("S1",new LinkedList<>(), new LinkedList<>()));
        globalSpKaList.addLast(new student_pair("S2",new LinkedList<>(), new LinkedList<>()));
        int mainOpn = -1;
            while (mainOpn != 3){
                System.out.println("Welcome to Backpack\n" + "1. Enter as instructor\n" + "2. Enter as student\n" + "3. Exit");
                mainOpn = Integer.parseInt(br.readLine());
                if(mainOpn == 1){
                    System.out.println("Instructors:\n" + "0 - I0\n" + "1 - I1\n" + "Choose id:");
                    int chooseId  = Integer.parseInt(br.readLine());
                    if(chooseId<0 || chooseId>= gloablInstrucKaList.size()){
                        System.out.println("Enter a valid id ");
                        continue;
                    }
                    instruct_pair guru= gloablInstrucKaList.get(chooseId);
                    int opn = -1;
                    while (opn != 9){
                        System.out.println("Welcome"+ guru.getName());
                        System.out.println("1. Add class material\n" + "2. Add assessments\n" + "3. View lecture materials\n" + "4. View assessments\n" +
                                "5. Grade assessments\n" + "6. Close assessment\n" + "7. View comments\n" + "8. Add comments\n" + "9. Logout");
                        opn = Integer.parseInt(br.readLine());
                        if(opn == 1){
                            System.out.println("1. Add Lecture Slide\n" + "2. Add Lecture Video");
                            int n = Integer.parseInt(br.readLine());
                            if(n == 1){
                                System.out.println("Enter topic of slides: ");
                                String inp = br.readLine();
                                System.out.println("Enter number of slides: ");
                                int number  = Integer.parseInt(br.readLine());
                                System.out.println("Enter content of slides");
                                LinkedList<String> slides = new LinkedList<>();
                                for(int j = 0;j< number;j++){
                                    System.out.println("Content of slide " +(j+1)+" : ");
                                    slides.addLast(br.readLine());
                                }
                                guru.addLecture(new lecture_pair(inp,slides,new Date()));
                            }else if (n ==2){
                                System.out.println("Enter topic of video: ");
                                String inp = br.readLine();
                                System.out.println("Enter filename of video: ");
                                String fileName = br.readLine();
                                if(!fileName.endsWith(".mp4")){
                                    System.out.println("The filename dosen't end with .mp4");
                                    continue;
                                }
                                video_pair a = new video_pair(new Date(),inp, fileName);
                                guru.addVideo(a);
                            }else {
                                System.out.println("you did not enter a valid option ");
                                continue;
                            }
                        }else if(opn ==2){
                            System.out.println("1. Add Assignment\n" + "2. Add Quiz");
                            int n = Integer.parseInt(br.readLine());
                            if( n==1){
                                System.out.println("Enter problem statement:");
                                String pbst = br.readLine();
                                System.out.println("Enter max marks:");
                                int mark = Integer.parseInt(br.readLine());
                                for(student_pair dk : globalSpKaList) dk.add_assign(new assignment_pair(pbst,"",-1,mark,1, guru.getName()));
                            }else if( n == 2){
                                System.out.println("Enter quiz question:");
                                String ques = br.readLine();
                                for(student_pair dk : globalSpKaList) dk.add_quiz(new quiz_pair(ques,"",1,-1, guru.getName()));
                            }else {
                                System.out.println("Entered a invalid option ");
                                break;
                            }
                        }else if(opn == 8){
                            System.out.println("Enter comment: ");
                            String s =br.readLine() ;
                            s+= "- "+guru.getName() +"\n"+new Date();
                            cmntSection.addLast(s);
                        }else if (opn == 9)break;
                        else if(opn == 5){
                            student_pair thi = globalSpKaList.get(0);
                            person k = globalSpKaList.get(0);
                            System.out.println("List of assessments");
                            k.display();
                            System.out.println("Enter ID of assessment to view submissions:");
                            int id = Integer.parseInt(br.readLine());
                            if(id >=(thi.sizeOfAP()+thi.sizeOfQP()) || id <0){
                                System.out.println("Entered id of assessment was not shown above ");
                                continue;
                            }
                            int cc = -1;
                            System.out.println("Choose ID from these ungraded submissions");
                            HashMap<Integer, Integer> justIncase = new HashMap<>();
                            for(int lal = 0; lal< globalSpKaList.size();lal++){
                                if(id< globalSpKaList.get(lal).sizeOfAP()){
                                    assignment_pair jj = globalSpKaList.get(lal).AssignPairDedo(id);
                                    if(!jj.getStatus().equals("") && jj.getMarksGot() == -1){
                                        cc++;
                                        System.out.println(cc+" "+globalSpKaList.get(lal).getName());
                                        justIncase.put(cc,lal);
                                    }
                                }else {
                                    quiz_pair jj =globalSpKaList.get(lal).quesPairDedo(id-globalSpKaList.get(lal).sizeOfAP());
                                    if(!jj.getStatus().equals("") && jj.getMarksGot() == -1){
                                        cc++;
                                        System.out.println(cc+" "+globalSpKaList.get(lal).getName());
                                        justIncase.put(cc,lal);
                                    }
                                }
                            }
                            if(cc == -1){
                                System.out.println("No submissions found ");
                                continue;
                            }
                            int idForStudent = Integer.parseInt(br.readLine());
                            if(idForStudent < 0 || idForStudent >cc){
                                System.out.println("No submissions for this id ");
                                continue;
                            }
                            System.out.print("Submission : ");
                            student_pair u = globalSpKaList.get(justIncase.get(idForStudent));
                            if(id < u.sizeOfAP()){
                                assignment_pair mu = u.AssignPairDedo(id);
                                System.out.println(mu.getStatus());
                                if(mu.getStatus().equals(""))continue;
                                if(mu.getMarksGot() != -1){
                                    System.out.println("Already graded ");
                                    continue;
                                }
                                System.out.println("--------------------------------------------");
                                System.out.println("Max Marks: "+mu.getMaxMarks());
                                System.out.print("Marks scored: ");
                                int mark = Integer.parseInt(br.readLine());
                                mu.setMarksGot(mark);
                                mu.setMarksByWhom(guru.getName());

                            }else {
                                quiz_pair mu= u.quesPairDedo(id-u.sizeOfAP());
                                System.out.println(mu.getStatus());
                                if(mu.getStatus().equals(""))continue;
                                if(mu.getMarksGot() != -1){
                                    System.out.println("Already graded ");
                                    continue;
                                }
                                System.out.println("--------------------------------------------");
                                System.out.println("Max Marks: 1");
                                System.out.print("Marks scored: ");
                                int mark = Integer.parseInt(br.readLine());
                                mu.setMarksGot(mark);
                                mu.setMarksByWhom(guru.getName());
                            }
                        }else if (opn == 6){
                            System.out.println("List of Open Assignments:");
                            student_pair a= globalSpKaList.get(0);


                            int itr = 0;
                            for(int i =0;i< a.sizeOfAP();i++){
                                if(a.AssignPairDedo(i).getOpenOrClose() == 1){
                                    System.out.println("ID: "+itr+"Assignment: "+a.AssignPairDedo(i).getTitle()+" Max Marks:"+a.AssignPairDedo(i).getMaxMarks());
                                    hmForOpen.put(itr,i);
                                    itr++;}
                            }
                            System.out.println("-------------------------------------------------------------------------");
                            for(int i =0;i< a.sizeOfQP();i++) {
                                if(a.quesPairDedo(i).getOpenOrClose() == 1){
                                    System.out.println("ID: " + itr + " Question:" + a.quesPairDedo(i).getQuestion());
                                    hmForOpen.put(itr,i+ a.sizeOfAP());
                                    itr++;}
                            }System.out.println("-------------------------------------------------------------------------");



                            System.out.println("Enter id of assignment to close:");
                            int n = Integer.parseInt(br.readLine());
                            boolean check = false;
                            for(int i :hmForOpen.keySet()){
                                if(i == n){
                                    check = true;
                                    break;
                                }
                            }
                            if(!check){
                                System.out.println("Entered id was invalid ");
                                continue;
                            }
                            int val = hmForOpen.get(n);
                            for(student_pair dk: globalSpKaList){
                                if(val < dk.sizeOfAP()){
                                    dk.changeOpenAssign(val);
                                }else dk.changeOpenQuiz(val- dk.sizeOfAP());
                            }
                        }else if(opn == 7){
                            for(String i : cmntSection) System.out.println(i);
                        }else if(opn == 3){
                            for(person a : gloablInstrucKaList){
                                 a.display();
                            }
                        }else if(opn == 4){
                            person a= globalSpKaList.get(0);
                            a.display();
                        }
                        else {
                            System.out.println("Enter a valid option ");
                        }
                    }
                }else if(mainOpn == 2){
                    System.out.println("Students:\n" + "0 - S0\n" + "1 - S1\n" + "2 - S2\n" + "Choose id:");
                    int n = Integer.parseInt(br.readLine());
                    if(n<0 || n>= globalSpKaList.size()){
                        System.out.println("Choose a valid id");
                        continue;
                    }
                    student_pair balak = globalSpKaList.get(n);
                    int opn =-1;
                    while (opn != 7){
                        System.out.println("Welcome :"+ balak.getName());
                        System.out.println("1. View lecture materials\n" + "2. View assessments\n" + "3. Submit assessment\n"
                                + "4. View grades\n" + "5. View comments\n" + "6. Add comments\n" + "7. Logout");
                        opn = Integer.parseInt(br.readLine());
                        if(opn == 1){
                            for(person a : gloablInstrucKaList){
                                a.display();
                            }
                        }else if(opn == 2){
                            person a= globalSpKaList.get(0);
                            a.display();
                        }else if(opn == 3){
                            int c = 0;
                            for(int i =0;i< balak.sizeOfAP();i++){
                                if(balak.AssignPairDedo(i).getStatus().equals("") && balak.AssignPairDedo(i).getOpenOrClose() == 1){
                                    c++;}
                            }
                            for(int i =0;i< balak.sizeOfQP();i++){
                                if(balak.quesPairDedo(i).getStatus().equals("") && balak.quesPairDedo(i).getOpenOrClose() == 1 ){
                                    c++;}
                            }
                            if(c == 0){
                                System.out.println("No Pending assignments ");
                                continue;
                            }
                            System.out.println("Pending assessments");
                            int itr = 0;
                            HashMap<Integer, Integer> kk = new HashMap<>();
                            for(int i =0;i< balak.sizeOfAP();i++){
                                if(balak.AssignPairDedo(i).getStatus().equals("") &&balak.AssignPairDedo(i).getOpenOrClose() == 1){
                                    System.out.println("ID: "+itr+"Assignment: "+balak.AssignPairDedo(i).getTitle()+" Max Marks:"+balak.AssignPairDedo(i).getMaxMarks());
                                    kk.put(itr,i);
                                    itr++;}
                            }
                            for(int i =0;i< balak.sizeOfQP();i++){
                                if(balak.quesPairDedo(i).getStatus().equals("") && balak.quesPairDedo(i).getOpenOrClose() == 1 ){
                                    System.out.println("ID: "+itr+" Question:"+balak.quesPairDedo(i).getQuestion());
                                    kk.put(itr,i+balak.sizeOfAP());
                                    itr++;}
                            }

                            System.out.println("Enter ID of assessment:");
                            int a  = Integer.parseInt(br.readLine());
                            boolean check = false;
                            for(int i : kk.keySet()){
                                if(a== i){
                                    check = true;
                                    break;
                                }
                            }
                            if(!check){
                                System.out.println("you did not enter a valid id ");
                                continue;
                            }
                            int val = kk.get(a);
                            if(val < balak.sizeOfAP()){
                                System.out.println("Enter filename of assignment:");
                                String s = br.readLine();
                                if(!s.endsWith(".zip")){
                                    System.out.println("You did not enter a valid submission file ");
                                    continue;
                                }else{
                                    balak.statusChange(val,s);
                                }
                            }else {
                                quiz_pair u = balak.quesPairDedo(val-balak.sizeOfAP());
                                System.out.println(u.getQuestion());
                                String s = br.readLine();
                                u.changeStatus(s);
                            }

                        }else if (opn == 4){
                            balak.viewGrades();
                        }else if (opn == 5){
                            for(String i : cmntSection) System.out.println(i);
                        }else if(opn == 6){
                            System.out.println("Enter comment: ");
                            String s =br.readLine() ;
                            s+= "- "+balak.getName() +"\n"+new Date();
                            cmntSection.addLast(s);
                        }else if(opn == 7)break;
                        else {
                            System.out.println("Enter a valid id");
                        }
                    }
                }else if(mainOpn == 3)break;
                else System.out.println("Enter a valid option");
            }
        }}

