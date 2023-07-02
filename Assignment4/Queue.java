package com.company;
import java.util.*;
import java.io.*;

interface pixelCkliye{
   public int getRn();
}
class pixelC implements pixelCkliye{
   private int r;
   private int b;
   private int g;
   public pixelC(int r, int b, int g) {
      this.r = r;
      this.b = b;
      this.g = g;
   }

   public int getRn() {
      return 255-r;
   }

   public int getBn() {
      return 255-b;
   }

   public int getGn() {
      return 255-g;
   }
   public int getR() {
      return r;
   }

   public int getB() {
      return b;
   }

   public int getG() {
      return g;
   }

   @Override
   public boolean equals(Object o){
      if(o != null && getClass() == o.getClass()){
         pixelC ref = (pixelC) o;
         return (r == ref.r && b == ref.b && g == ref.g);
      }else return false;
   }
}
interface pixelGkLiye{
   public int getGn();
}
class pixelG implements pixelGkLiye{
   private int g;
   public pixelG(int g) {
      this.g = g;
   }
   public int getGn(){
      return 255-this.g;
   }
   public int getG() {
      return g;
   }

   @Override
   public boolean equals(Object o){
      if(o != null && getClass() == o.getClass()){
         pixelG ref = (pixelG) o;
         return (ref.g == g);
      }else return false;
   }
}
class mat<T>{
   private T[][] img;
   public mat( T[][] img) {
      this.img = img;
   }
   public T[][] getImg() {
      return img;
   }

   public void update(T o, int r, int c) {
      try {
         img[r][c] = o;
      }catch (Exception e){
         System.out.println("Enter valid row and column number ");
      }
   }

}
class Queue {
   public static void negative(Object o){
      try{
      mat<pixelG> a= (mat<pixelG>)o;
      pixelG[][] toBe = (pixelG[][]) a.getImg();
         System.out.println("Gray ->");

         for(int i =0;i< toBe.length;i++){
            for(int  j = 0;j< toBe[0].length;j++){
               System.out.print(toBe[i][j].getGn()+" ");
            }
            System.out.println();
         }
      }
      catch (Exception e){
         mat<pixelC> a= (mat<pixelC>)o;
         pixelC[][] toBe = (pixelC[][]) a.getImg();
         System.out.println("Red ->");
         for(int i =0;i< toBe.length;i++){
            for(int  j = 0;j< toBe[0].length;j++){
               System.out.print(toBe[i][j].getRn()+" ");
            }
            System.out.println();
         }
         System.out.println("Blue ->");
         for(int i =0;i< toBe.length;i++){
            for(int  j = 0;j< toBe[0].length;j++){
               System.out.print(toBe[i][j].getBn()+" ");
            }
            System.out.println();
         }
         System.out.println("Green ->");
         for(int i =0;i< toBe.length;i++){
            for(int  j = 0;j< toBe[0].length;j++){
               System.out.print(toBe[i][j].getGn()+" ");
            }
            System.out.println();
         }
      }
   }
   public static void main (String args[]) {
      Scanner ss = new Scanner(System.in);
      int n = 34;
      pixelC[][] cpy = new pixelC[1][1];
      cpy[0][0]  =new pixelC(-1,-1,-1);
      mat<pixelC> colorMat = new mat<>(cpy);
      pixelG[][] cpy1 = new pixelG[1][1];
      cpy1[0][0]  =new pixelG(-1);
      mat<pixelG> grayMat = new mat<>(cpy1);
      while (n != 5){
      System.out.println("Choose your operation\n"+"0.Compute negative\n1.input\n2.create\n3.update\n4.display the images\n5.exit" );
      n = ss.nextInt();
      if(n == 1){
         System.out.println("Enter no. of rows");
         int r=  ss.nextInt();
         System.out.println("Enter no. of columns");
         int c =ss.nextInt();
         System.out.println("1.Colour Matrix\n2.GrayScale Matrix");
         int opn = ss.nextInt();
         if(opn == 1){
            pixelC[][] mat1 = new pixelC[r][c];
         for(int i = 0;i< r;i++){
            for(int j =0 ;j< c;j++){
               System.out.println("Enter red value");
               int red = ss.nextInt();
               System.out.println("Enter blue value");
               int blu = ss.nextInt();
               System.out.println("Enter green value");
               int gre = ss.nextInt();
               mat1[i][j] = new pixelC(red,blu,gre);
            }
         }
         colorMat = new mat<>(mat1);
         }else if(opn == 2){
            pixelG[][] mat1 = new pixelG[r][c];
            for(int i = 0;i< r;i++){
               for(int j =0 ;j< c;j++){
                  System.out.println("Enter gray value");
                  int gray = ss.nextInt();
                  mat1[i][j] = new pixelG(gray);
               }
            }
            grayMat = new mat<>(mat1);
         }else System.out.println("you did not enter a valid opn");
      }else if (n == 2){
         System.out.println("Enter no. of rows");
         int r=  ss.nextInt();
         System.out.println("Enter no. of columns");
         int c =ss.nextInt();
         System.out.println("1.Colour Matrix\n2.GrayScale Matrix");
         int opn = ss.nextInt();
         if(opn == 1){
            pixelC[][] mat1 = new pixelC[r][c];
            for(int i = 0;i< r;i++){
               for(int j =0 ;j< c;j++){
                  mat1[i][j] = new pixelC(0,0,0);
               }
            }
            colorMat = new mat<>(mat1);
         }else if(opn == 2){
            pixelG[][] mat1 = new pixelG[r][c];
            for(int i = 0;i< r;i++){
               for(int j =0 ;j< c;j++){
                  mat1[i][j] = new pixelG(0);
               }
            }
            grayMat = new mat<>(mat1);
         }else System.out.println("You did not enter a valid opn");
      }else if(n == 3){
         System.out.println("Enter the row of the matrix you want to update");
         int r= ss.nextInt();
         System.out.println("Enter the column of the matrix you want to update");
         int c= ss.nextInt();
         if(colorMat.getImg()[0][0].getB() != -1){
            System.out.println("Enter red value");
            int red = ss.nextInt();
            System.out.println("Enter blue value");
            int blu = ss.nextInt();
            System.out.println("Enter green value");
            int gre = ss.nextInt();
            colorMat.update(new pixelC(red,blu,gre),r-1,c-1);
         }else if(grayMat.getImg()[0][0].getG() != -1){
            System.out.println("Enter gray value");
            int gre = ss.nextInt();
            grayMat.update(new pixelG(gre),r-1,c-1);
         }else System.out.println("Kindly input a matrix first");
      }else if (n == 4){
         if(colorMat.getImg()[0][0].getB() != -1){
            pixelC[][]  toBe = colorMat.getImg();
            System.out.println("Red ->");
            for(int i =0;i< toBe.length;i++){
               for(int  j = 0;j< toBe[0].length;j++){
                  System.out.print(toBe[i][j].getR()+" ");
               }
               System.out.println();
            }
            System.out.println("Blue ->");
            for(int i =0;i< toBe.length;i++){
               for(int  j = 0;j< toBe[0].length;j++){
                  System.out.print(toBe[i][j].getB()+" ");
               }
               System.out.println();
            }
            System.out.println("Green ->");
            for(int i =0;i< toBe.length;i++){
               for(int  j = 0;j< toBe[0].length;j++){
                  System.out.print(toBe[i][j].getG()+" ");
               }
               System.out.println();
            }
         }if(grayMat.getImg()[0][0].getG() != -1){
            System.out.println("Gray ->");
            pixelG[][] toBe = grayMat.getImg();
            for(int i =0;i< toBe.length;i++){
               for(int  j = 0;j< toBe[0].length;j++){
                  System.out.print(toBe[i][j].getG()+" ");
               }
               System.out.println();
            }
         }if(colorMat.getImg()[0][0].getB() == -1&& grayMat.getImg()[0][0].getG() == -1) System.out.println("Kindly input a matrix first");
      }else if(n == 5)break;
      else if (n == 0){
         if(colorMat.getImg()[0][0].getB() != -1){
            negative(colorMat);
         }if(grayMat.getImg()[0][0].getG() != -1){
            negative(grayMat);
         }if(colorMat.getImg()[0][0].getB() == -1&& grayMat.getImg()[0][0].getG() == -1) System.out.println("Kindly input a matrix first");
      }
      else System.out.println("Enter a valid option");
      }
   }}