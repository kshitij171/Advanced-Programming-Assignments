package com.company;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.*;
interface bookk{
    public int lexi(String a, String b);
}
class Book implements bookk,Comparable<Book>{
    private String title;
    private long isbn;
    private long barC;

    public Book(String title, long isbn, long barC) {
        this.title = title;
        this.isbn = isbn;
        this.barC = barC;
    }

    public String getTitle() {
        return title;
    }

    public long getIsbn() {
        return isbn;
    }

    public long getBarC() {
        return barC;
    }

    public int lexi(String a, String b){
        int l = Math.max(a.length(), b.length());
        for(int i= 0;i< l;i++){
            if(i>= a.length())return 1;
            if(i>= b.length())return -1;
            if(a.charAt(i)<b.charAt(i))return 1;
            if(a.charAt(i)>b.charAt(i))return -1;
        }
        return 0;
    }
    @Override
    public int compareTo(Book o){
        if(lexi(this.title,o.title) != 0){
            return lexi(o.title,this.title);
        }
        if((this.isbn - o.isbn) != 0){
            if(this.isbn -o.isbn >0)return 1;
            return -1;
        }
        if(this.barC - o.barC >0)return 1;
        return -1;
    }
    @Override
    public String  toString(){
        return this.title + " "+ this.isbn +" "+ this.barC;
    }
}
public class SinglyLinkedList {
    public static void main(String[] args) throws IOException {
        Scanner ss =new Scanner(System.in);
        System.out.println("ENTER THE NUMBER OF BOOKS");
        int n = ss.nextInt();
        System.out.println("ENTER THE NUMBER OF ROWS");
        int k = ss.nextInt();
        PriorityQueue<Book> pq=  new PriorityQueue<>();
        int count = 0;
        while (count<n){
            System.out.println("Enter book title");
            ss.nextLine();
            String t = ss.nextLine();
            System.out.println("Enter book isbn code");
            long isb = ss.nextLong();
            System.out.println("Enter book barcode");
            long bar = ss.nextLong();
            pq.add(new Book(t,isb,bar));
            count++;
        }
        Book[]arr = new Book[pq.size()];
        int i = 0;
        while (!pq.isEmpty()){
            arr[i] = pq.remove();
            i++;
        }
        String inp = "yes";

        System.out.println("PRESS -1 TO EXIT THE LIBRARY PROGRAM");
        while (!inp.equals("no")){
            boolean check = false;
            int slot = 1;
            int row = 1;
            System.out.println("----------Enter book details------------");
            System.out.println("Enter book title");
            ss.nextLine();
            String t = ss.nextLine();
            System.out.println("Enter book isbn code");
            long isb = ss.nextLong();
            System.out.println("Enter book barcode");
            long bar = ss.nextLong();
        for(int j = 0;j< arr.length;j++){
            Book book = arr[j];
            if(book.getTitle().equals(t) && book.getIsbn() == isb && book.getBarC() == bar){
                System.out.println("The book's position is at Row "+row+" and slot no. "+slot);
                check = true;
            }
            slot++;
            if(slot> (n/k)){
                slot = 1;
                row++;
            }
        }
        if(!check){
            System.out.println("There is no such book on the floor ");
            check = false;
        }
            System.out.println("Do you want to continue checking position of books? ");
            inp = ss.next();
        }
    }
    }
