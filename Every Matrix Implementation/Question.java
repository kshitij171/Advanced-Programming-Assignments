package com.company ;
import java.util.*;
import java.io.*;
// check Stackk class for details about inherition from abstract class .and Queue class for association in classes.
// 0 is order for [] 1 is order for 2x2 matrix and 2 for 3x3 matrix
interface scalarInter{
    void changeEle(int newValue);
    int[][] getMat();
    char getId();
    LinkedList<String> getTags();
}
abstract class scalarAbs{
    private char id;
    private int [] arr = new int[2];
    private LinkedList<String> tags = new LinkedList<>();
    public scalarAbs(char id,int value, int order){
        this.arr[0] = value;
        this.arr[1] = order;
        this.id = id;
    }
    public LinkedList<String> getTags(){
        return this.tags;
    }
    public void changeEle(int newValue){// a setter
        this.arr[0] = newValue;
    }
    public char getId(){return this.id;}
    public int[][] getMat(){ // a getter
        if(this.arr[1] == 0){
            int[][] mat = new int[1][1];
            mat[0][0] = this.arr[0];
            return mat;
        }
        int[][] mat = new int[2][2];
        if(this.arr[1] == 2){
            mat = new int[3][3];
        }
        for(int i =0;i< mat.length;i++){
            for(int j =0;j< mat[0].length;j++){
                if(i == j) mat[i][j] = this.arr[0];
            }
        }
        return mat;
    }
}
class scalar  extends scalarAbs implements scalarInter{
    public scalar(char id,int value, int order){
        super(id,value,order);
    }
}
interface NullInter{
    int[][] getMat();
    char getId();
    LinkedList<String> getTags();
}
abstract class NullAbs {
    private char id;
    private int row ;
    private int clmn;
    private LinkedList<String> tags = new LinkedList<>();
    public NullAbs(char id,int row,int clmn){
        this.row = row;
        this.clmn =clmn;
        this.id = id;
    }
    public LinkedList<String> getTags(){
        return this.tags;
    }
    public NullAbs(char id){
        this.id = id;
    }
    public char getId(){return this.id;}
    public int[][] getMat(){
        int[][] mat = new int[row][clmn];
        return mat;
    }
}
class Null extends NullAbs implements NullInter{
    public Null(char id,int row , int clmn){ super(id,row, clmn);}
    public Null(char id){super(id);}
}
interface diaInter{
    char getId();
    int[][] getMat();
    LinkedList<String> getTags();
    void changeEle(int row, int clmn,int value);
}
interface rowInter{
    char getId();
    int[][] getMat();
    LinkedList<String> getTags();
}
abstract class rowAbs{
    private char id;
    private int[] arr;
    private LinkedList<String> tags = new LinkedList<>();
    public rowAbs(char id, int[] arr){
        this.id = id;
        this.arr =arr;
    }
    public rowAbs(char id){
        this.id = id;
    }
    public char getId(){
        return this.id;
    }
    public void changeEle(int ci, int value){
        this.arr[ci] = value;
    }
    public LinkedList<String> getTags(){
        return this.tags;
    }
    public int[][] getMat(){
        int[][] mat = new int[1][arr.length];
        for(int  i =0;i<arr.length;i++){
            mat[0][i] = arr[i];
        }
        return mat;
    }
}
class Row extends rowAbs implements rowInter{
    public Row(char id, int[] arr){
        super(id,arr);
    }
    public Row(char id){
        super(id);
    }
}
class clmn extends Row {
    private int[] arr;
    public clmn(char id,int[] arr){
        super(id);
        this.arr= arr;
    }
    @Override
    public void changeEle(int ri,int value){
        this.arr[ri] = value;
    }
    @Override
    public int[][] getMat(){
        int[][] mat = new int[arr.length][1];
        for(int i =0;i< arr.length;i++){
            mat[i][0] = arr[i];
        }
        return mat;
    }
}
abstract class diaAbs{
    private char id;
    private int order;
    private int[] arr;
    private LinkedList<String> tags = new LinkedList<>();
    public diaAbs(char id  ,int[] arr){
        this.id = id;
        if(arr.length == 2)this.order = 1;
        else this.order = 2;
        this.arr = arr;
    }
    public LinkedList<String> getTags(){
        return this.tags;
    }
    public diaAbs(char id ){
        this.id = id;
    }
    public char getId(){return this.id;}
    public void changeEle(int ri, int ci,int value){
        if(ri == 0 && ci == 0){
            this.arr[0] =value; }
        else if(ri == 1 && ci == 1){
            this.arr[1] = value;
        }else if(ri == 2 && ci == 2){
            this.arr[2] =value;
        }
    }
    public int[][] getMat(){
        if(this.order == 0){
            int [][] mat = new int[1][1];
            mat[0][0] = this.arr[0];
            return mat;
        }
        int[][] mat = new int[2][2];
        if(this.order == 2){
            mat = new int[3][3];
        }
        for (int i =0;i< mat.length;i++){
            for (int j =0;j< mat[0].length;j++){
                if(i == j) {
                    if(i== 0)mat[i][j] = this.arr[0];
                    if(i== 1)mat[i][j] = this.arr[1];
                    if(i== 2)mat[i][j] = this.arr[2];
                }
            }
        }return mat;
    }
}
class dia extends diaAbs implements diaInter{
    public dia(char id ,int[] arr){
        super(id,arr);
    }
    public dia(char id){super(id);}
}
class skew extends dia{
    private int order;
    private int[] arr;
    private LinkedList<String> tags  = new LinkedList<>();
    public skew(char id , int[] arr){
        super(id);
        this.arr = arr;
        if(arr.length == 3)this.order =2;
        if(arr.length == 1)this.order =1;
    }
    public LinkedList<String> getTags(){
        return this.tags;
    }
    @Override
    public void changeEle(int ri,int ci,int value){
        if(this.order == 1){
            if(ri == 0&& ci == 1)this.arr[0]= value;
        }else if(this.order == 2){
            if(ri == 0 && ci == 1)this.arr[0] = value;
            if(ri == 0 && ci == 2)this.arr[1] = value;
            if(ri == 1 && ci == 2)this.arr[2] = value;
        }
    }
    @Override
    public int[][] getMat(){
        if(this.order == 1){
            int[][] mat = new int[2][2];
            mat[0][0]= 0;
            mat[0][1] = this.arr[0];
            mat[1][0]  = -1*(this.arr[0]);
            mat[1][1] = 0;
            return mat;
        }else{
            int[][] mat = new int[3][3];
            mat[0][0] = 0;
            mat[1][1] = 0;
            mat[2][2] = 0;
            mat[1][0] = -1*(this.arr[0]);
            mat[0][1] = this.arr[0];
            mat[0][2] = this.arr[1];
            mat[2][0] = -1*(this.arr[1]);
            mat[1][2] = this.arr[2];
            mat[2][1] = -1*(this.arr[2]);
            return mat;
        }
    }
}
class identity implements NullInter{
    private int order;
    private char id;
    private LinkedList<String> tags = new LinkedList<>();
    public identity(char id , int order){
        this.id = id;
        this.order = order;
    }
    public LinkedList<String> getTags(){
        return this.tags;
    }
    public char getId(){return this.id;}
    public int[][] getMat(){
        if(this.order == 0){
            int[][] mat = new int[1][1];
            mat[0][0] =1;
            return mat;
        }
        int[][] mat = new int[2][2];
        if(order == 2){
            mat  = new int[3][3];
        }
        for(int i =0;i< mat.length;i++){
            for(int j =0;j< mat[0].length;j++){
                if(i == j) mat[i][j] =1;
            }
        }return mat;
    }
}
class singleton implements scalarInter{
    private char id;
    private int value;
    private LinkedList<String> tags = new LinkedList<>();
    public singleton(char id , int value){
        this.id = id;
        this.value = value;
    }
    public LinkedList<String> getTags(){
        return this.tags;
    }
    public char getId(){return this.id;}
    public void changeEle(int newValue){
        this.value = newValue;
    }
    public int[][] getMat(){
       int [][] mat = new int[1][1];
       mat[0][0] = this.value;
       return mat;
    }
}
class ones extends Null{ //not necessarily a square matrix
    private int row;
    private LinkedList<String> tags = new LinkedList<>();
    private int clmn;
    public ones(char id, int row, int clmn){
        super(id);
        this.row = row;
        this.clmn = clmn;
    }
    public LinkedList<String> getTags(){
        return this.tags;
    }
    @Override
    public int[][] getMat(){
        int[][] mat = new int[row][clmn];
        for(int i =0;i< mat.length;i++){
            for(int j =0;j< mat[0].length;j++){
                mat[i][j] =1;
            }
        }
        return mat;
    }
}
class upper extends dia {
    private int[] arr ;
    private int order;
    private LinkedList<String> tags = new LinkedList<>();
    public upper(char id, int[] arr){
        super(id);
        this.arr = arr;
        if(arr.length == 3)this.order =1;
        if(arr.length == 6)this.order =2;
    }
    public upper(char id){
        super(id);
    }
    public LinkedList<String> getTags(){
        return this.tags;
    }
    @Override
    public void changeEle(int row ,int clmn, int value){
        if(order == 2){
        if(row == 0){
            this.arr[clmn] = value;
        }else if(row == 1 && clmn == 1 || clmn == 2){
            this.arr[2+clmn] = value;
        }else if(row == 2 && clmn == 2) this.arr[arr.length-1] =value;}
        else if(order == 1){
            if(row == 1 && clmn == 1){
                this.arr[arr.length-1] = value;
            }else if(row == 0 )this.arr[clmn] = value;
        }
    }
    @Override
    public int[][] getMat(){
        int[][] mat = new int[2][2];
        if(this.order == 2){
            mat = new int[3][3];
            mat[0][0] = arr[0];
            mat[0][1] = arr[1];
            mat[0][2] = arr[2];
            mat[1][1] = arr[3];
            mat[1][2] = arr[4];
            mat[2][2] = arr[5];
        }else if(this.order == 1){
            mat[0][0] = arr[0];
            mat[0][1] = arr[1];
            mat[1][1] = arr[2];
        }
        return mat;
    }
}
class lower extends upper{
    private int[] arr ;
    private int order;
    private LinkedList<String> tags  = new LinkedList<>();
    public lower(char id, int [] arr){
        super(id);
        this.arr =arr;
        if(arr.length == 3)this.order =1;
        if(arr.length == 6)this.order =2;
    }
    public LinkedList<String> getTags(){
        return this.tags;
    }
    public void changeELe(int ri,int ci, int value){
        if(order == 1){
            if(ri == 0 && ci == 0)this.arr[0] = value;
            else if(ri == 1)this.arr[ci+1] = value;
        }else if(order == 2){
            if(ri ==2){
                this.arr[ci+3] = value;
            }else if(ri == 1 && ci == 0 ||ci == 1){
                if(ci == 0)this.arr[1] = value;
                else this.arr[2] = value;
            }if(ri == 0 && ci == 0)this.arr[0] = value;
        }
    }
    @Override
    public int[][] getMat(){
        int[][] mat = new int[2][2];
        if(this.order ==2 ){
            mat = new int[3][3];
            mat[0][0] = arr[0];
            mat[1][0]  = arr[1];
            mat[1][1] = arr[2];
            mat[2][0] = arr[3];
            mat[2][1] = arr[4];
            mat[2][2] = arr[5];
        }
        else if (this.order == 1){
            mat[0][0] = arr[0];
            mat[1][0] = arr[1];
            mat[1][1] = arr[2];
        }
        return mat;
    }
}
class symmetric extends upper{
    private int[] arr ;
    private int order;
    private LinkedList<String> tags =new LinkedList<>();
    public symmetric(char id, int [] arr){
        super(id);
        this.arr =arr;
        if(arr.length == 3)this.order =1;
        if(arr.length == 6)this.order =2;
    }
    public LinkedList<String> getTags(){
        return this.tags;
    }
    @Override
    public int[][] getMat(){
        int[][] mat = new int[2][2];
        if(this.order ==2 ){
            mat = new int[3][3];
            mat[0][0] = arr[0];
            mat[0][1] = arr[1];
            mat[1][0] = arr[1];
            mat[0][2] = arr[2];
            mat[2][0] = arr[2];
            mat[1][1] = arr[3];
            mat[1][2] = arr[4];
            mat[2][1] = arr[4];
            mat[2][2]=arr[5];
        }
        else if (this.order == 1){
           mat[0][0]=arr[0];
           mat[0][1]=arr[1];
           mat[1][0]=arr[1];
           mat[1][1] =arr[2];
        }
        return mat;
    }
}

class rect extends Null{
    private int[][] mat ;
    private LinkedList<String> tags = new LinkedList<>();
    public rect(char id, int[][] mat){
        super(id);
        this.mat =mat;
    }
    public LinkedList<String> getTags() {
        return this.tags;
    }

    public void changeEle(int ri, int ci, int value){
        this.mat[ri][ci] = value;
    }
    @Override
    public int[][] getMat(){
        return this.mat;
    }
}
class square extends rect{
    public square(char id, int[][] mat){
        super(id,mat);
    }
}
class singular extends square{
    public singular(char id, int[][] mat){super(id, mat);}
}
class Question {
    public static void add(int [][] mat1, int [][] mat2){
        int r1 = mat1.length;
        int c1 = mat1[0].length;
        int r2 = mat2.length;
        int c2 = mat2[0].length;
        if(r1 != r2 || c1 != c2 ){
            System.out.println("operation cannot be performed");
            return;
        }
        for(int i = 0;i< mat1.length;i++ ){
            for(int  j =0;j< mat1[0].length;j++){
                System.out.print((mat1[i][j]+mat2[i][j])+" ");
            }
            System.out.println();
        }
    }
    public static void sub(int [][] mat1, int [][] mat2){
        int r1 = mat1.length;
        int c1 = mat1[0].length;
        int r2 = mat2.length;
        int c2 = mat2[0].length;
        if(r1 != r2 || c1 != c2 ){
            System.out.println("operation cannot be performed");
            return;
        }
        for(int i = 0;i< mat1.length;i++ ){
            for(int  j =0;j< mat1[0].length;j++){
                System.out.print((mat1[i][j]-mat2[i][j])+" ");
            }
            System.out.println();
        }
    }
    public static void mulEleWise(int [][] mat1, int [][] mat2){
        int r1 = mat1.length;
        int c1 = mat1[0].length;
        int r2 = mat2.length;
        int c2 = mat2[0].length;
        if(r1 != r2 || c1 != c2 ){
            System.out.println("operation cannot be performed");
            return;
        }
        for(int i = 0;i< mat1.length;i++ ){
            for(int  j =0;j< mat1[0].length;j++){
                System.out.print((mat1[i][j]*mat2[i][j])+" ");
            }
            System.out.println();
        }
    }
    public static boolean isfNull(float[][] mat){
        for(int i =0;i< mat.length;i++){
            for(int j =0;j<mat[0].length;j++ ){
                if(mat[i][j] != 0)return false;
            }
        }
        return true;
    }
    public static float[][] mulToRes(float [][] mat1, float [][] mat2){
        int r1 = mat1.length;
        int c1 = mat1[0].length;
        int r2 = mat2.length;
        int c2 = mat2[0].length;
        if( c1 != r2 ){
            System.out.println("operation cannot be performed");
            float[][] t =new float[1][1];
            t[0][0] = -1;
            return t;
        }
        float[][] res = new float[r1][c2];
        if(isfNull(mat1) || isfNull(mat2)){
            return res;
        }
        float s = 0;
        for(int i = 0;i< r1;i++ ){
            for(int  j =0;j< c2;j++){
                for(int k = 0;k< r2;k++){
                    s+= mat1[i][k]*mat2[k][j];
                }
                res[i][j] = s;
                s =0;
            }
        }
        return res;
    }
    public static void mul(float [][] mat1, float [][] mat2){
        int r1 = mat1.length;
        int c1 = mat1[0].length;
        int r2 = mat2.length;
        int c2 = mat2[0].length;
        if( c1 != r2 ){
            System.out.println("operation cannot be performed");
            return;
        }
        float[][] res = new float[r1][c2];
        float s = 0;
        for(int i = 0;i< r1;i++ ){
            for(int  j =0;j< c2;j++){
                for(int k = 0;k< r2;k++){
                    s+= mat1[i][k]*mat2[k][j];
                }
                res[i][j] = s;
                s =0;
            }
        }
        for(int i  =0;i< res.length;i++){
            for(int j =0;j< res[0].length;j++){
                System.out.print(res[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static boolean zeroH(int[][] mat){
        for(int i =0;i< mat.length;i++){
            for(int j =0;j<mat[0].length;j++ ){
                if(mat[i][j] == 0)return true;
            }
        }
        return false;
    }
    public static void divEleWise(int [][] mat1, int [][] mat2){
        int r1 = mat1.length;
        int c1 = mat1[0].length;
        int r2 = mat2.length;
        int c2 = mat2[0].length;
        if(r1 != r2 || c1 != c2 ){
            System.out.println("operation cannot be performed");
            return;
        }
        if(zeroH(mat2)){
            System.out.println("Operation cannot be performed as there is 0 is the other matrix");
            return;
        }
        for(int i = 0;i< mat1.length;i++ ){
            for(int  j =0;j< mat1[0].length;j++){
                System.out.print((float)(mat1[i][j]/mat2[i][j])+" ");
            }
            System.out.println();
        }
    }
    public static int deter(int[][] mat){
//        if(!isSquare(mat)){
//            System.out.println("operation cannot be performed");
//            return -1;
//        }
        int r = mat.length;
        int c = mat[0].length;
        if(r == 2 && c == 2){
            int a = (mat[0][0]*mat[1][1] -mat[1][0]*mat[0][1]);
            return a;
        }else if (r == 3 && c == 3){
            int sum = 0;
            int a= mat[1][1]* mat[2][2] - mat[2][1]*mat[1][2];
            sum+= mat[0][0]*a;
            int b = mat[1][0]*mat[2][2] -mat[2][0]*mat[1][2];
            sum -= mat[0][1]* b;
            int bb =mat[1][0]*mat[2][1] - mat[2][0]*mat[1][1];
            sum += mat[0][2]*bb;
            return sum;
        }else return mat[0][0];
    }
    public static boolean isSquare(int[][] mat){
        if(mat.length == mat[0].length)return true;
        return false;
    }
    public static float[][] inverse(int[][] mat){
        if( !isSquare(mat)){
            System.out.println("Matrix is not a square one");
            float[][] cc = new float[1][1];
            cc[0][0]  = -1;
            return cc;
        }
        int r = mat.length;
        if(r == 1){
            float[][] toBe  = new float[1][1];
            toBe[0][0] =( float)1/mat[0][0];
            return toBe;
        }else if(r == 2){
            float[][] ref = new float[mat.length][mat[0].length];
            ref[0][0] = mat[1][1];
            ref[1][1] = mat[0][0];
            ref[0][1] =-mat[0][1];
            ref[1][0] = -mat[1][0];
            int d = deter(mat);
            if( d== 0){
                System.out.println("The determinant is 0 so the operation cannot be performed");
                float[][] cc = new float[1][1];
                cc[0][0]  = -1;
                return cc;
            }
            for (int i =0;i< ref.length;i++){
                for (int j = 0;j< ref[0].length;j++){
                    ref[i][j]  /= d;
                }
            }
            return ref;
        }else {
            float[][] ref = new float[mat.length+2][mat[0].length+2];
            for(int i =0;i< mat.length;i++){
                for (int j =0;j< mat[0].length;j++){
                    ref[i][j] = mat[i][j];
                }
            }
            ref[0][3]= mat[0][0];
            ref[0][4] = mat[0][1];
            ref[1][3]= mat[1][0];
            ref[1][4] = mat[1][1];
            ref[2][3]= mat[2][0];
            ref[2][4] = mat[2][1];
            ref[3][3]= mat[0][0];
            ref[3][4] = mat[0][1];
            ref[4][3]= mat[1][0];
            ref[4][4] = mat[1][1];
            float[][] toBe = new float[3][3];
            for(int i =1;i< 4;i++){
                for(int j =1;j<4;j++){
                    toBe[i-1][j-1] = (ref[i][j]*ref[i+1][j+1]-(ref[i+1][j]*ref[i][j+1]));
                }
            }
            int d = deter(mat);
            if( d== 0){
                System.out.println("The determinant is 0 so the operation cannot be performed");
                float[][] cc = new float[1][1];
                cc[0][0]  = -1;
                return cc;
            }
            for (int i =0;i< ref.length;i++){
                for (int j = 0;j< ref[0].length;j++){
                    ref[i][j]  /= d;
                }
            }
            return ref;
        }
    }
    public static void div(int[][] mat1,int[][] mat2 ){
        if(!isSquare(mat2)){
            System.out.println("operation cannot be performed");
            return;
        }
        float[][] ref =inverse(mat2);
        float[][] mat1C = new float[mat1.length][mat1[0].length];
        for (int i =0;i< mat1.length;i++){
            for (int j = 0;j< mat1[0].length;j++){
                mat1C[i][j] = mat1[i][j];
            }
        }
        mul(mat1C,ref);
    }
    public static int[][] transpose(int[][] mat){
        int[][] ref = new int[mat[0].length][mat.length];
        for(int i =0;i< mat.length;i++){
            for(int j =0;j<mat[0].length;j++ ){
                ref[j][i] = mat[i][j];
            }
        }
        return ref;
    }
    public static void AplusAtran(int[][] mat){
        int[][] at = transpose(mat);
        add(mat,at);
    }
    public static boolean isNull(int[][] mat){
        for(int i =0;i< mat.length;i++){
            for(int j =0;j<mat[0].length;j++ ){
                if(mat[i][j] != 0)return false;
            }
        }
        return true;
    }
    public static boolean isOnes(int[][] mat){
        for(int i =0;i< mat.length;i++){
            for(int j =0;j<mat[0].length;j++ ){
                if(mat[i][j] != 1)return false;
            }
        }
        return true;
    }
    public static boolean isDia(int[][] mat){
        if(!isSquare(mat))return false;
        for(int i =0;i< mat.length;i++){
            for(int j =0;j<mat[0].length;j++ ){
               if(i!= j && mat[i][j]!= 0)return false;
            }
        }
        return true;
    }

    public static boolean isIdent(int[][] mat){
        if(!isDia(mat))return false;
        for(int i =0;i< mat.length;i++){
            for(int j =0;j<mat[0].length;j++ ){
                if(i == j && mat[i][j] != 1)return false;
            }
        }return true;
    }
    public static boolean equalHai(int[][] mat1, int[][]mat2){
        int r1 = mat1.length;
        int c1 = mat1[0].length;
        int r2 = mat2.length;
        int c2 = mat2[0].length;
        if(r1 != r2 || c1 != c2 ){
            return false;
        }
        for(int i =0;i< mat1.length;i++){
            for(int j =0;j<mat1[0].length;j++ ){
                if(mat1[i][j]!= mat2[i][j])return false;
            }
        }return true;
    }
    public static boolean isSkew(int[][] mat){
        if(!isSquare(mat))return false;
        int[][] bt = transpose(mat);
        for(int i =0;i< mat.length;i++){
            for(int j =0;j<mat[0].length;j++ ){
                mat[i][j] *= -1;
            }
        }
        if(!equalHai(bt, mat))return false;
        return true;
    }
    public static boolean isScalar(int[][] mat){
        if(!isDia(mat))return false;
        int prev =mat[0][0];
        for (int i =0;i< mat.length;i++){
            for (int j = 0;j< mat[0].length;j++){
                if(i == j && mat[i][j]!= prev )return false;
            }
        }
        return true;
    }
    public static boolean isUpper(int[][] mat){
        if(!isSquare(mat))return false;
        if(mat.length == 1)return true;
        if(mat.length == 2){
            if(mat[1][0] != 0)return false;
        }
        if(mat.length == 3){
            if(mat[1][0] != 0 || mat[2][0]!= 0 || mat[2][1] != 0)return false;
        }
        return true;
    }
    public static boolean isLower(int[][] mat){
        if(!isSquare(mat))return false;
        if(mat.length == 1)return true;
        if(mat.length == 2){
            if(mat[0][1] != 0)return false;
        }
        if(mat.length == 3){
            if(mat[0][1] != 0 || mat[0][2]!= 0 || mat[1][2] != 0)return false;
        }
        return true;
    }
    public static boolean isSymmetric(int[][] mat){
        if(!isSquare(mat))return false;
        if(mat.length == 1)return true;
        if(mat.length == 2){
            if(mat[0][1] != mat[1][0])return false;
        }
        if(mat.length == 3){
            if(mat[0][1] != mat[1][0] || mat[0][2]!= mat[2][0] || mat[1][2] != mat[2][1])return false;
        }
        return true;
    }

    public static LinkedList<String> tagsDede(int[][] aa){
        int[][] mat= new int[aa.length][aa[0].length];
        for(int i =0;i<aa.length;i++){
            for(int j = 0;j<aa[0].length;j++){
                mat[i][j] = aa[i][j];
            }
        }
        LinkedList<String> toBe = new LinkedList<>();
        if(mat.length == 1 && mat[0].length == 1)toBe.addLast("Singleton Matrix");
        if(mat.length == 1)toBe.addLast("Row Matrix");
        if(mat[0].length == 1)toBe.addLast("Column matrix");
        if(isDia(mat))toBe.addLast("Diagonal Matrix");
        if(isScalar(mat))toBe.addLast("Scalar Matrix");
        if(isNull(mat))toBe.addLast("Null Matrix");
        if(isSkew(mat))toBe.addLast("Skew Matrix");
        if(isIdent(mat))toBe.addLast("Identity Matrix");//
        if(isOnes(mat))toBe.addLast("Ones Matrix");//
        if(isUpper(mat))toBe.addLast("Upper Matrix");
        if(isLower(mat))toBe.addLast("Lower Matrix");
        if(isSymmetric(mat))toBe.addLast("Symmetric Matrix");
        if(deter(mat) == 0)toBe.addLast("Singular Matrix");
        if(isSquare(mat))toBe.addLast("Square Matrix");
        else toBe.addLast("Rectangular Matrix");//
        return toBe;
    }
    public static boolean same(LinkedList<String> a, LinkedList<String> b){
        if(a.size() != b.size())return false;
        for(int i =0 ;i<a.size();i++){
            if(!a.get(i).equals(b.get(i)))return false;
        }return true;
    }
    public static float hcf(float a, float b){
        while(b %a != 0){
            float r = b%a;
            b = a;
            a = r;
        }
        return a;
    }
    public  static  void eigen(int[][] mat){
        if(mat.length !=  2 || mat[0].length != 2){
            System.out.println("The matrix isn't 2X2");
            return;
        }
        int c =deter(mat);
        int b = -(mat[0][0]+mat[1][1]);
        float res1 = (float) ((-b+ Math.sqrt(b*b -4*c))/2);
        float res2 = (float) ((-b- Math.sqrt(b*b -4*c))/2);
        System.out.println("The eigen values are "+res1+" "+res2);
        float e1 =mat[0][0]-res1;
        float e2 = -(mat[0][1]);
        float h = hcf(e1,e2);
        e1 /= h;
        e2 /= h;
        System.out.println("The eigen vector for "+res1+":\n"+e1+"\n"+e2);
        e1 =mat[0][0]-res2;
        e2 =-(mat[0][1]);
        h = hcf(e1,e2);
        e1 /= h;
        e2 /= h;
        System.out.println("The eigen vector for "+res2+":\n"+e1+"\n"+e2);
    }
    static char latest = 'a';
    static LinkedList<singleton> singletonKaList =new LinkedList<>();
    static LinkedList<square> sqKaList = new LinkedList<>();
    static LinkedList<clmn> clmnKaList = new LinkedList<>();
    static LinkedList<scalar> scalarKaList = new LinkedList<>();
    static LinkedList<Null> nullKaList = new LinkedList<>();
    static LinkedList<dia> diaKaList = new LinkedList<>();
    static LinkedList<rect> rectKaList = new LinkedList<>();
    static LinkedList<upper> upperKaList = new LinkedList<>();
    static LinkedList<singular> singularKaList = new LinkedList<>();
    static LinkedList<lower> lowerKaList = new LinkedList<>();
    static LinkedList<identity> idenKaList = new LinkedList<>();
    static LinkedList<Row> rowKaList = new LinkedList<>();
    static LinkedList<ones> onesKaList = new LinkedList<>();
    static LinkedList<skew> skewKaList = new LinkedList<>();
    static LinkedList<symmetric> symmeKaList =new LinkedList<>();

    public static void main(String[] args) throws IOException{

        int inp =0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (inp != -1){
        while(inp != 16){
            System.out.println("1. Take matrices as input and label \n" +
                    "2. Create matrices of requested matrix-types \n" +
                    "3. Change the elements of a matrix \n" +
                    "4. Display all the matrix-type labels of a requested matrix.\n" +
                    "5. Perform addition, subtraction, multiplication & division.\n" +
                    "6. Perform element-wise operations.\n" +
                    "7. Transpose matrices.\n" +
                    "8. Inverse matrices.\n" +
                    "9. Compute means: row-wise mean, column-wise mean, mean of all the elements.\n" +
                    "10. Compute determinants.\n" +
                    "11. Use singleton matrices as scalars, if requested.\n" +
                    "12. Compute A+AT\n" +
                    "13. Compute Eigen vectors and values.\n" +
                    "14. Solve sets of linear equations using matrices.\n" +
                    "15. Retrieve all the existing matrices");
            try{
            inp = Integer.parseInt(br.readLine());}
            catch (Exception e){
                inp = Integer.parseInt(br.readLine());
            }
            if(inp == -1)break;
            if(inp == 16)break;
            if(inp == 14){
                System.out.println("Choose a square matrix from "+(char)(latest-1)+"-"+"a");
                char a = (char)br.read();
                int[][] mat =new int[1][1];
                System.out.println("Choose a column matrix from a-"+(char)(latest-1)+"with same no. of rows");
                br.read();
                char b = (char)br.read();
                int[][] mat2 = new int[1][1];
                mat[0][0] = Integer.MAX_VALUE;
                mat2[0][0] = Integer.MAX_VALUE;
                for(singleton s :singletonKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(square s :sqKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(clmn s :clmnKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(scalar s :scalarKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(Null s :nullKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(dia s :diaKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(rect s :rectKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(upper s :upperKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(lower s :lowerKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(symmetric s :symmeKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(singular s :singularKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(identity s :idenKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(ones s :onesKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(skew s :skewKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(Row s :rowKaList){
                    if( a== s.getId())mat = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                if(mat[0][0] == Integer.MAX_VALUE || mat2[0][0] == Integer.MAX_VALUE){
                    System.out.println("Matrices weren't found");
                    break;
                }
                if(mat.length != mat2.length){
                    System.out.println("No. of rows weren't the same");
                    break;
                }
                if(deter(mat)== 0){
                    float[][] one = inverse(mat);
                    boolean saare0H  = true;
                    float[][] mat2C = new float[mat2.length][mat2[0].length];
                    for (int i =0;i< mat2.length;i++){
                        for (int j = 0;j< mat2[0].length;j++){
                            mat2C[i][j] = mat2[i][j];
                        }
                    }
                    float[][] krna = mulToRes(one,mat2C);
                    for (int i =0;i< krna.length;i++){
                        for (int j = 0;j< krna[0].length;j++){
                            if(krna[i][j] !=0)saare0H   = false;
                        }
                    }
                    if(saare0H)System.out.println("The system has infinite solutions ");
                        else System.out.println("The system is inconsistent");
                        break;
                }else{
                    float[][] ref = new float[mat2.length][mat2[0].length];
                    for(int i =0;i<mat2.length;i++){
                        for (int j =0;j< mat2[0].length;j++){
                            ref[i][j] = mat2[i][j];
                        }
                    }
                    float[][] mat1 = inverse(mat);
                    mul(mat1,ref);
                }
            }else if(inp == 1){
                System.out.println("Enter the no. of rows ");
                int r = Integer.parseInt(br.readLine());
                System.out.println("Enter the no. of columns");
                int c = Integer.parseInt(br.readLine());
                int[][] mat = new int[r][c];
                for(int i =0;i<r;i++){
                    String[] parts = br.readLine().split(" ");
                    for(int j = 0;j< parts.length;j++){
                        mat[i][j] = Integer.parseInt(parts[j]);
                    }
                }
                LinkedList<String> l = tagsDede(mat);
                if(l.contains("Singleton Matrix")){
                    singleton s = new singleton(latest,mat[0][0]);
                    latest = (char) (latest+1);
                    LinkedList<String> q = s.getTags();
                    for(String slide :l)q.addLast(slide);
                    singletonKaList.addLast(s);
                }else if(l.contains("Identity Matrix")){
                    identity i = new identity(latest,mat.length);
                    latest = (char) (latest+1);
                    LinkedList<String> q = i.getTags();
                    for(String slide :l)q.addLast(slide);
                    idenKaList.addLast(i);
                }else if(l.contains("Null Matrix")){
                    Null n = new Null(latest,mat.length,mat[0].length);
                    latest = (char) (latest+1);
                    LinkedList<String> q = n.getTags();
                    for(String slide :l)q.addLast(slide);
                    nullKaList.addLast(n);
                }else if(l.contains("Ones Matrix")){
                    ones o = new ones(latest, mat.length,mat[0].length);
                    latest = (char) (latest+1);
                    LinkedList<String> q = o.getTags();
                    for(String slide :l)q.addLast(slide);
                    onesKaList.addLast(o);
                }else if(l.contains("Skew Matrix")){
                    int[] arr = new int[1];
                    if(mat.length == 2){
                        arr[0] = mat[0][1];
                    }else{
                        arr = new int[3];
                        arr[0] = mat[0][1];
                        arr[1] = mat[0][2];
                        arr[2] = mat[1][2];
                    }
                    skew s = new skew(latest,arr);
                    latest = (char) (latest+1);
                    LinkedList<String> q = s.getTags();
                    for(String slide :l)q.addLast(slide);
                    skewKaList.addLast(s);
                }else if(l.contains("Diagonal Matrix")){
                    int[] arr = new int[2];
                    if(mat.length == 2){
                        arr[0] = mat[0][0];
                        arr[1] = mat[1][1];
                    }else{
                        arr = new int[3];
                        arr[0] = mat[0][0];
                        arr[1] = mat[1][1];
                        arr[2] = mat[2][2];
                    }
                    dia d = new dia(latest,arr);
                    latest = (char) (latest+1);
                    LinkedList<String> q = d.getTags();
                    for(String slide :l)q.addLast(slide);
                    diaKaList.addLast(d);
                }else if(l.contains("Upper Matrix")){
                    int[] arr = new int[3];
                    if(mat.length ==2){
                        arr[0] = mat[0][0];
                        arr[1] = mat[0][1];
                        arr[2] = mat[1][1];
                    }else {
                        arr  = new int[6];
                        arr[0] = mat[0][0];
                        arr[1] =mat[0][1];
                        arr[2] =mat[0][2];
                        arr[3] = mat[1][1];
                        arr[4] = mat[1][2];
                        arr[5] = mat[2][2];
                    }
                    upper u = new upper(latest,arr);
                    latest = (char) (latest+1);
                    LinkedList<String> q = u.getTags();
                    for(String slide :l)q.addLast(slide);
                    upperKaList.addLast(u);
                }else if(l.contains("Lower Matrix")){
                    int[] arr = new int[3];
                    if(mat.length ==2){
                        arr[0] = mat[0][0];
                        arr[1] = mat[1][0];
                        arr[2] = mat[1][1];
                    }else {
                        arr  = new int[6];
                        arr[0] = mat[0][0];
                        arr[1] =mat[1][0];
                        arr[2] =mat[2][0];
                        arr[3] = mat[1][1];
                        arr[4] = mat[2][1];
                        arr[5] = mat[2][2];
                    }
                    lower s = new lower(latest,arr);
                    latest = (char) (latest+1);
                    LinkedList<String> q = s.getTags();
                    for(String slide :l)q.addLast(slide);
                    lowerKaList.addLast(s);
                }else if(l.contains("Symmetric Matrix")){
                    int[] arr = new int[3];
                    if(mat.length ==2){
                        arr[0] = mat[0][0];
                        arr[1] = mat[0][1];
                        arr[2] = mat[1][1];
                    }else {
                        arr  = new int[6];
                        arr[0] = mat[0][0];
                        arr[1] =mat[0][1];
                        arr[2] =mat[0][2];
                        arr[3] = mat[1][1];
                        arr[4] = mat[1][2];
                        arr[5] = mat[2][2];
                    }
                    symmetric u = new symmetric(latest,arr);
                    latest = (char) (latest+1);
                    LinkedList<String> q = u.getTags();
                    for(String slide :l)q.addLast(slide);
                    symmeKaList.addLast(u);
                }else if (l.contains("Row Matrix")){
                    int [] arr = new int[2];
                    if(mat[0].length == 3)arr =new int[3];
                    for(int i =0;i<mat[0].length;i++)arr[i] = mat[0][i];
                    Row ss = new Row(latest,arr);
                    latest = (char) (latest+1);
                    LinkedList<String> q = ss.getTags();
                    for(String slide :l)q.addLast(slide);
                    rowKaList.addLast(ss);
                }else if(l.contains("Column Matrix")){
                    int [] arr = new int[2];
                    if(mat.length == 3)arr =new int[3];
                    for(int i =0;i<mat.length;i++)arr[i] = mat[i][0];
                    clmn cc = new clmn(latest,arr);
                    latest = (char) (latest+1);
                    LinkedList<String> q = cc.getTags();
                    for(String slide :l)q.addLast(slide);
                    clmnKaList.addLast(cc);
                }else if(l.contains("Square Matrix")){
                    square sq =new square(latest,mat);
                    latest = (char) (latest+1);
                    LinkedList<String> q = sq.getTags();
                    for(String slide :l)q.addLast(slide);
                    sqKaList.addLast(sq);
                }else if(l.contains("Rectangular Matrix")){
                    rect rr = new rect(latest,mat);
                    latest = (char) (latest+1);
                    LinkedList<String> q = rr.getTags();
                    for(String slide :l)q.addLast(slide);
                    rectKaList.addLast(rr);
                }
            }else if(inp == 2){
                System.out.println("Enter the key for the kind of matrix you would like to create" +
                        "1. Rectangular Matrix\n" + "2. Row Matrix\n" + "3. Column Matrix\n" + "4. Square Matrix\n" + "5. Symmetric Matrix\n" +
                        "6. Skew-symmetric Matrix\n" + "7. Upper-triangular Matrix\n" + "8. Lower-triangular Matrix\n" + "9. Singular Matrix\n" +
                        "10. Diagonal Matrix\n" + "11. Scalar Matrix\n" + "12. Identity Matrix\n" + "13. Singleton Matrix\n" + "14. Ones Matrix\n" +
                        "15. Null Matrix");
                int n = Integer.parseInt(br.readLine());
                if(n ==12 ||n == 14 ){
                    int[][] mat = {{1}};
                    LinkedList<String> l  = tagsDede(mat);
                    if(n == 12){
                        identity i = new identity(latest,mat.length);
                        latest = (char) (latest+1);
                        LinkedList<String> q = i.getTags();
                        for(String slide :l)q.addLast(slide);
                        idenKaList.addLast(i);
                    }if(n == 14){
                        ones i = new ones(latest,1,1);
                        latest = (char) (latest+1);
                        LinkedList<String> q = i.getTags();
                        for(String slide :l)q.addLast(slide);
                        onesKaList.addLast(i);
                    }
                }else if(n == 1){
                    int[][] mat = {{1,2}};
                    LinkedList<String> l = tagsDede(mat);
                    rect i = new rect(latest,mat);
                    latest = (char) (latest+1);
                    LinkedList<String> q = i.getTags();
                    for(String slide :l)q.addLast(slide);
                    rectKaList.addLast(i);
                }else{
                    if(n == 2){
                        int[] arr = {1,2};
                        Row i = new Row(latest,arr);
                        LinkedList<String> l = tagsDede(i.getMat());
                        LinkedList<String> q = i.getTags();
                        for(String slide :l)q.addLast(slide);
                        latest = (char) (latest+1);
                        rowKaList.addLast(i);
                    }else if(n == 3){
                        int[] arr = {1,2};
                        clmn i = new clmn(latest,arr);
                        LinkedList<String> l = tagsDede(i.getMat());
                        LinkedList<String> q = i.getTags();
                        for(String slide :l)q.addLast(slide);
                        latest = (char) (latest+1);
                        clmnKaList.addLast(i);
                    }else if(n == 4){
                        int[][] mat = {{1,2},{1,2}};
                        square i = new square(latest,mat);
                        LinkedList<String> l = tagsDede(i.getMat());
                        LinkedList<String> q = i.getTags();
                        for(String slide :l)q.addLast(slide);
                        latest = (char) (latest+1);
                        sqKaList.addLast(i);
                    }else if (n == 5){
                        int[]arr = {1,2,3};
                        symmetric i = new symmetric(latest,arr);
                        LinkedList<String> l = tagsDede(i.getMat());
                        LinkedList<String> q = i.getTags();
                        for(String slide :l)q.addLast(slide);
                        latest = (char) (latest+1);
                        symmeKaList.addLast(i);
                    }else if(n ==6){
                        int[] arr ={2};
                        skew i = new skew(latest,arr);
                        LinkedList<String> l = tagsDede(i.getMat());
                        LinkedList<String> q = i.getTags();
                        for(String slide :l)q.addLast(slide);
                        latest = (char) (latest+1);
                        skewKaList.addLast(i);
                    }else if(n == 7){
                        int[] arr ={1,2,3};
                        upper i = new upper(latest,arr);
                        LinkedList<String> l = tagsDede(i.getMat());
                        LinkedList<String> q = i.getTags();
                        for(String slide :l)q.addLast(slide);
                        latest = (char) (latest+1);
                        upperKaList.addLast(i);
                    }else if(n == 8){
                        int[] arr ={1,2,3};
                        lower i = new lower(latest,arr);
                        LinkedList<String> l = tagsDede(i.getMat());
                        LinkedList<String> q = i.getTags();
                        for(String slide :l)q.addLast(slide);
                        latest = (char) (latest+1);
                        lowerKaList.addLast(i);
                    }else if(n == 9){
                        int[][] mat = {{1,2},{1,2}};
                        singular i = new singular(latest,mat);
                        LinkedList<String> l = tagsDede(i.getMat());
                        LinkedList<String> q = i.getTags();
                        for(String slide :l)q.addLast(slide);
                        latest = (char) (latest+1);
                        singularKaList.addLast(i);
                    }else if(n == 10){
                        int[] arr ={1,2};
                        dia i = new dia(latest,arr);
                        LinkedList<String> l = tagsDede(i.getMat());
                        LinkedList<String> q = i.getTags();
                        for(String slide :l)q.addLast(slide);
                        latest = (char) (latest+1);
                        diaKaList.addLast(i);
                    }else if(n == 11){
                        scalar i = new scalar(latest,2,1);
                        LinkedList<String> l = tagsDede(i.getMat());
                        LinkedList<String> q = i.getTags();
                        for(String slide :l)q.addLast(slide);
                        latest = (char) (latest+1);
                        scalarKaList.addLast(i);
                    }else if(n == 13){
                        singleton i = new singleton(latest,2);
                        LinkedList<String> l = tagsDede(i.getMat());
                        LinkedList<String> q = i.getTags();
                        for(String slide :l)q.addLast(slide);
                        latest = (char) (latest+1);
                        singletonKaList.addLast(i);
                    }else if(n == 15){
                        Null i = new Null(latest,2,2);
                        LinkedList<String> l = tagsDede(i.getMat());
                        LinkedList<String> q = i.getTags();
                        for(String slide :l)q.addLast(slide);
                        latest = (char) (latest+1);
                        nullKaList.addLast(i);
                    }
                }
            }else if(inp == 3){
                System.out.println("Choose matrix from a-"+(char)(latest-1));
                char a =(char) br.read();
                br.read();
                int[][] mat1 = new int[1][1];
                mat1[0][0] = Integer.MAX_VALUE;
                for(singleton s :singletonKaList){
                    if( a== s.getId()){
                        mat1 = s.getMat();
                        LinkedList<String> l1 =tagsDede(mat1);
                        System.out.println("Enter new elements for the matrix of dimension "+mat1.length+"X"+mat1[0].length);
                        int[][] refMat =new int[mat1.length][mat1[0].length];
                        for(int i =0;i< mat1.length;i++){
                            String[] parts = br.readLine().split(" ");
                            for(int j = 0;j< parts.length;j++){
                                refMat[i][j] = Integer.parseInt(parts[j]);
                            }
                        }
                        LinkedList<String> l2 =tagsDede(refMat);
                        if(!same(l1,l2)){
                            System.out.println("Editing cannot be performed due to change of some labels");
                            break;
                        }
                        for(int i = 0;i< refMat.length;i++){
                            for(int j =0;j<refMat[0].length ;j++){
                                s.changeEle(refMat[i][j]);
                            }
                        }
                    }
                }
                for(square s :sqKaList){
                    if( a== s.getId()){
                        mat1 = s.getMat();
                        LinkedList<String> l1 =tagsDede(mat1);
                        System.out.println("Enter new elements for the matrix of dimension "+mat1.length+"X"+mat1[0].length);
                        int[][] refMat =new int[mat1.length][mat1[0].length];
                        for(int i =0;i< mat1.length;i++){
                            String[] parts = br.readLine().split(" ");
                            for(int j = 0;j< parts.length;j++){
                                refMat[i][j] = Integer.parseInt(parts[j]);
                            }
                        }
                        LinkedList<String> l2 =tagsDede(refMat);
                        if(!same(l1,l2)){
                            System.out.println("Editing cannot be performed due to change of some labels");
                            break;
                        }
                        for(int i = 0;i< refMat.length;i++){
                            for(int j =0;j<refMat[0].length ;j++){
                                s.changeEle(i,j,refMat[i][j]);
                            }
                        }
                    }
                }
                for(clmn s :clmnKaList){
                    if( a== s.getId()){
                        mat1 = s.getMat();
                        LinkedList<String> l1 =tagsDede(mat1);
                        System.out.println("Enter new elements for the matrix of dimension "+mat1.length+"X"+mat1[0].length);
                        int[][] refMat =new int[mat1.length][mat1[0].length];
                        for(int i =0;i< mat1.length;i++){
                            String[] parts = br.readLine().split(" ");
                            for(int j = 0;j< parts.length;j++){
                                refMat[i][j] = Integer.parseInt(parts[j]);
                            }
                        }
                        LinkedList<String> l2 =tagsDede(refMat);
                        if(!same(l1,l2)){
                            System.out.println("Editing cannot be performed due to change of some labels");
                            break;
                        }
                        for(int i = 0;i< refMat.length;i++){
                            for(int j =0;j<refMat[0].length ;j++){
                                s.changeEle(i,refMat[i][j]);
                            }
                        }
                    }
                }
                for(scalar s :scalarKaList){
                    if( a== s.getId()){
                        mat1 = s.getMat();
                        LinkedList<String> l1 =tagsDede(mat1);
                        System.out.println("Enter new elements for the matrix of dimension "+mat1.length+"X"+mat1[0].length);
                        int[][] refMat =new int[mat1.length][mat1[0].length];
                        for(int i =0;i< mat1.length;i++){
                            String[] parts = br.readLine().split(" ");
                            for(int j = 0;j< parts.length;j++){
                                refMat[i][j] = Integer.parseInt(parts[j]);
                            }
                        }
                        LinkedList<String> l2 =tagsDede(refMat);
                        if(!same(l1,l2)){
                            System.out.println("Editing cannot be performed due to change of some labels");
                            break;
                        }
                        for(int i = 0;i< refMat.length;i++){
                            for(int j =0;j<refMat[0].length ;j++){
                                s.changeEle(refMat[i][j]);
                            }
                        }
                    }
                }
                for(Null s :nullKaList){
                    if( a== s.getId()){
                        System.out.println("Elements of a null matrix cannot be edited");
                        break;
                    }
                }
                for(dia s :diaKaList){
                    if( a== s.getId()){
                        mat1 = s.getMat();
                        LinkedList<String> l1 =tagsDede(mat1);
                        System.out.println("Enter new elements for the matrix of dimension "+mat1.length+"X"+mat1[0].length);
                        int[][] refMat =new int[mat1.length][mat1[0].length];
                        for(int i =0;i< mat1.length;i++){
                            String[] parts = br.readLine().split(" ");
                            for(int j = 0;j< parts.length;j++){
                                refMat[i][j] = Integer.parseInt(parts[j]);
                            }
                        }
                        LinkedList<String> l2 =tagsDede(refMat);
                        if(!same(l1,l2)){
                            System.out.println("Editing cannot be performed due to change of some labels");
                            break;
                        }
                        for(int i = 0;i< refMat.length;i++){
                            for(int j =0;j<refMat[0].length ;j++){
                                s.changeEle(i,j,refMat[i][j]);
                            }
                        }
                    }
                }
                for(rect s :rectKaList){
                    if( a== s.getId()){
                        mat1 = s.getMat();
                        LinkedList<String> l1 =tagsDede(mat1);
                        System.out.println("Enter new elements for the matrix of dimension "+mat1.length+"X"+mat1[0].length);
                        int[][] refMat =new int[mat1.length][mat1[0].length];
                        for(int i =0;i< mat1.length;i++){
                            String[] parts = br.readLine().split(" ");
                            for(int j = 0;j< parts.length;j++){
                                refMat[i][j] = Integer.parseInt(parts[j]);
                            }
                        }
                        LinkedList<String> l2 =tagsDede(refMat);
                        if(!same(l1,l2)){
                            System.out.println("Editing cannot be performed due to change of some labels");
                            break;
                        }
                        for(int i = 0;i< refMat.length;i++){
                            for(int j =0;j<refMat[0].length ;j++){
                                s.changeEle(i,j,refMat[i][j]);
                            }
                        }
                    }
                }
                for(upper s :upperKaList){
                    if( a== s.getId()){
                        mat1 = s.getMat();
                        LinkedList<String> l1 =tagsDede(mat1);
                        System.out.println("Enter new elements for the matrix of dimension "+mat1.length+"X"+mat1[0].length);
                        int[][] refMat =new int[mat1.length][mat1[0].length];
                        for(int i =0;i< mat1.length;i++){
                            String[] parts = br.readLine().split(" ");
                            for(int j = 0;j< parts.length;j++){
                                refMat[i][j] = Integer.parseInt(parts[j]);
                            }
                        }
                        LinkedList<String> l2 =tagsDede(refMat);
                        if(!same(l1,l2)){
                            System.out.println("Editing cannot be performed due to change of some labels");
                            break;
                        }
                        for(int i = 0;i< refMat.length;i++){
                            for(int j =0;j<refMat[0].length ;j++){
                                s.changeEle(i,j,refMat[i][j]);
                            }
                        }
                    }
                }
                for(lower s :lowerKaList){
                    if( a== s.getId()){
                        mat1 = s.getMat();
                        LinkedList<String> l1 =tagsDede(mat1);
                        System.out.println("Enter new elements for the matrix of dimension "+mat1.length+"X"+mat1[0].length);
                        int[][] refMat =new int[mat1.length][mat1[0].length];
                        for(int i =0;i< mat1.length;i++){
                            String[] parts = br.readLine().split(" ");
                            for(int j = 0;j< parts.length;j++){
                                refMat[i][j] = Integer.parseInt(parts[j]);
                            }
                        }
                        LinkedList<String> l2 =tagsDede(refMat);
                        if(!same(l1,l2)){
                            System.out.println("Editing cannot be performed due to change of some labels");
                            break;
                        }
                        for(int i = 0;i< refMat.length;i++){
                            for(int j =0;j<refMat[0].length ;j++){
                                s.changeEle(i,j,refMat[i][j]);
                            }
                        }
                    }
                }
                for(symmetric s :symmeKaList){
                    if( a== s.getId()){
                        mat1 = s.getMat();
                        LinkedList<String> l1 =tagsDede(mat1);
                        System.out.println("Enter new elements for the matrix of dimension "+mat1.length+"X"+mat1[0].length);
                        int[][] refMat =new int[mat1.length][mat1[0].length];
                        for(int i =0;i< mat1.length;i++){
                            String[] parts = br.readLine().split(" ");
                            for(int j = 0;j< parts.length;j++){
                                refMat[i][j] = Integer.parseInt(parts[j]);
                            }
                        }
                        LinkedList<String> l2 =tagsDede(refMat);
                        if(!same(l1,l2)){
                            System.out.println("Editing cannot be performed due to change of some labels");
                            break;
                        }
                        for(int i = 0;i< refMat.length;i++){
                            for(int j =0;j<refMat[0].length ;j++){
                                s.changeEle(i,j,refMat[i][j]);
                            }
                        }
                    }
                }
                for(singular s :singularKaList){
                    if( a== s.getId()){
                        mat1 = s.getMat();
                        LinkedList<String> l1 =tagsDede(mat1);
                        System.out.println("Enter new elements for the matrix of dimension "+mat1.length+"X"+mat1[0].length);
                        int[][] refMat =new int[mat1.length][mat1[0].length];
                        for(int i =0;i< mat1.length;i++){
                            String[] parts = br.readLine().split(" ");
                            for(int j = 0;j< parts.length;j++){
                                refMat[i][j] = Integer.parseInt(parts[j]);
                            }
                        }
                        LinkedList<String> l2 =tagsDede(refMat);
                        if(!same(l1,l2)){
                            System.out.println("Editing cannot be performed due to change of some labels");
                            break;
                        }
                        for(int i = 0;i< refMat.length;i++){
                            for(int j =0;j<refMat[0].length ;j++){
                                s.changeEle(i,j,refMat[i][j]);
                            }
                        }
                    }
                }
                for(identity s :idenKaList){
                    if( a== s.getId()){
                        System.out.println("Elements of a identity matrix cannot be edited");
                        break;
                    }
                }
                for(ones s :onesKaList){
                    if( a== s.getId()){
                        System.out.println("Elements of a ones matrix cannot be edited");
                        break;

                    }
                }
                for(skew s :skewKaList){
                    if( a== s.getId()){
                        mat1 = s.getMat();
                        LinkedList<String> l1 =tagsDede(mat1);
                        System.out.println("Enter new elements for the matrix of dimension "+mat1.length+"X"+mat1[0].length);
                        int[][] refMat =new int[mat1.length][mat1[0].length];
                        for(int i =0;i< mat1.length;i++){
                            String[] parts = br.readLine().split(" ");
                            for(int j = 0;j< parts.length;j++){
                                refMat[i][j] = Integer.parseInt(parts[j]);
                            }
                        }
                        LinkedList<String> l2 =tagsDede(refMat);
                        if(!same(l1,l2)){
                            System.out.println("Editing cannot be performed due to change of some labels");
                            break;
                        }
                        for(int i = 0;i< refMat.length;i++){
                            for(int j =0;j<refMat[0].length ;j++){
                                s.changeEle(i,j,refMat[i][j]);
                            }
                        }
                    }
                }
                for(Row s :rowKaList){
                    if( a== s.getId()){
                        mat1 = s.getMat();
                        LinkedList<String> l1 =tagsDede(mat1);
                        System.out.println("Enter new elements for the matrix of dimension "+mat1.length+"X"+mat1[0].length);
                        int[][] refMat =new int[mat1.length][mat1[0].length];
                        for(int i =0;i< mat1.length;i++){
                            String[] parts = br.readLine().split(" ");
                            for(int j = 0;j< parts.length;j++){
                                refMat[i][j] = Integer.parseInt(parts[j]);
                            }
                        }
                        LinkedList<String> l2 =tagsDede(refMat);
                        if(!same(l1,l2)){
                            System.out.println("Editing cannot be performed due to change of some labels");
                            break;
                        }
                        for(int i = 0;i< refMat.length;i++){
                            for(int j =0;j<refMat[0].length ;j++){
                                s.changeEle(j,refMat[i][j]);
                            }
                        }
                    }
                }

            }else if(inp == 4){
                System.out.println("Choose matrix from a-"+(char)(latest-1));
                char a =(char)br.read();
                for(singleton s :singletonKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
                for(square s :sqKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
                for(clmn s :clmnKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
                for(scalar s :scalarKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
                for(Null s :nullKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
                for(dia s :diaKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
                for(rect s :rectKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
                for(upper s :upperKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
                for(lower s :lowerKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
                for(symmetric s :symmeKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
                for(singular s :singularKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
                for(identity s :idenKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
                for(ones s :onesKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
                for(skew s :skewKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
                for(Row s :rowKaList){
                    if( a== s.getId()){
                        for(String ss : s.getTags()) System.out.println(ss);
                    }
                }
            }else if(inp == 5){
                System.out.println("Enter id for operation\n1.addition\n2.subtraction\n3.multiplication\n.4division");
                int id = Integer.parseInt(br.readLine());
                System.out.println("Choose 2 matrices from a-"+(char)(latest-1));
                char a = (char) br.read();
                br.read();
                char b = (char) br.read();
                int[][] mat1 = new int[1][1];
                int[][] mat2 = new int[1][1];
                mat1[0][0] = Integer.MAX_VALUE;
                mat2[0][0] =Integer.MAX_VALUE;
                for(singleton s :singletonKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(square s :sqKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(clmn s :clmnKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(scalar s :scalarKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(Null s :nullKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(dia s :diaKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(rect s :rectKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(upper s :upperKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(lower s :lowerKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(symmetric s :symmeKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(singular s :singularKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(identity s :idenKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(ones s :onesKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(skew s :skewKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(Row s :rowKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                if(mat1[0][0] == Integer.MAX_VALUE || mat2[0][0] == Integer.MAX_VALUE){
                    System.out.println("Matrices weren't found");
                    break;
                }
                if(id == 1){
                    add(mat1,mat2);
                }else if(id == 2){
                    sub(mat1,mat2);
                }else if(id == 3){
                    float[][] ref1 = new float[mat1.length][mat1[0].length];
                    for(int i =0;i<mat1.length;i++){
                        for (int j =0;j< mat1[0].length;j++){
                            ref1[i][j] = mat1[i][j];
                        }
                    }
                    float[][] ref2 = new float[mat2.length][mat2[0].length];
                    for(int i =0;i<mat2.length;i++){
                        for (int j =0;j< mat2[0].length;j++){
                            ref2[i][j] = mat2[i][j];
                        }
                    }
                    mul(ref1,ref2);
                }else if(id == 4){
                    div(mat1, mat2);
                }else {
                    System.out.println("Enter a valid id ");
                    break;
                }
            }else if(inp== 6){
                System.out.println("Enter id for operation\n1.addition\n2.subtraction\n3.multiplication\n4.division");
                int id = Integer.parseInt(br.readLine());
                System.out.println("Choose matrices from a-"+(char)(latest-1));
                char a = (char) br.read();
                br.read();
                char b = (char) br.read();
                int[][] mat1 = new int[1][1];
                int[][] mat2 = new int[1][1];
                mat1[0][0] = Integer.MAX_VALUE;
                mat2[0][0] =Integer.MAX_VALUE;
                for(singleton s :singletonKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(square s :sqKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(clmn s :clmnKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(scalar s :scalarKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(Null s :nullKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(dia s :diaKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(rect s :rectKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(upper s :upperKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(lower s :lowerKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(symmetric s :symmeKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(singular s :singularKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(identity s :idenKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(ones s :onesKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(skew s :skewKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(Row s :rowKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                if(mat1[0][0] == Integer.MAX_VALUE || mat2[0][0] == Integer.MAX_VALUE){
                    System.out.println("Id's weren't found");
                    break;
                }
                if(id == 1){
                    add(mat1,mat2);
                }else if(id == 2){
                    sub(mat1,mat2);
                }else if(id == 3){

                    mulEleWise(mat1,mat2);
                }else if(id == 4){
                    divEleWise(mat1, mat2);
                }else {
                    System.out.println("Enter a valid id ");
                    break;
                }
            }else if(inp == 7){
                System.out.println("Choose matrix from a-"+(char)(latest-1));
                char a = (char) br.read();
                int[][] mat1 = new int[1][1];
                mat1[0][0] = Integer.MAX_VALUE;

                for(singleton s :singletonKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(square s :sqKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(clmn s :clmnKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(scalar s :scalarKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(Null s :nullKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(dia s :diaKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(rect s :rectKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(upper s :upperKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(lower s :lowerKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(symmetric s :symmeKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(singular s :singularKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(identity s :idenKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(ones s :onesKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(skew s :skewKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(Row s :rowKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                if(mat1[0][0] == Integer.MAX_VALUE){
                    System.out.println("Id weren't found");
                    break;
                }
                int[][] toBe = transpose(mat1);
                for(int i =0;i<toBe.length;i++){
                    for (int j =0;j< toBe[0].length;j++){
                        System.out.print(toBe[i][j]+" ");
                    }
                    System.out.println();
                }
            }else if(inp == 8){
                System.out.println("Choose matrix from a-"+(char)(latest-1));
                char a = (char) br.read();
                int[][] mat1 = new int[1][1];
                mat1[0][0] = Integer.MAX_VALUE;

                for(singleton s :singletonKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(square s :sqKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(clmn s :clmnKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(scalar s :scalarKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(Null s :nullKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(dia s :diaKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(rect s :rectKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(upper s :upperKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(lower s :lowerKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(symmetric s :symmeKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(singular s :singularKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(identity s :idenKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(ones s :onesKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(skew s :skewKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(Row s :rowKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                if(mat1[0][0] == Integer.MAX_VALUE){
                    System.out.println("Id weren't found");
                    break;
                }
                if(!isSquare(mat1)){
                    System.out.println("Matrix wasn't a square one");
                    break;
                }
                float[][] toBe = inverse(mat1);
                for(int i =0;i<toBe.length;i++){
                    for (int j =0;j< toBe[0].length;j++){
                        System.out.print(toBe[i][j]+" ");
                    }
                    System.out.println();
                }
            }else if(inp == 9){
                System.out.println("Choose matrix from a-"+(char)(latest-1));
                char a = (char) br.read();
                int[][] mat1 = new int[1][1];
                mat1[0][0] = Integer.MAX_VALUE;

                for(singleton s :singletonKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(square s :sqKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(clmn s :clmnKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(scalar s :scalarKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(Null s :nullKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(dia s :diaKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(rect s :rectKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(upper s :upperKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(lower s :lowerKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(symmetric s :symmeKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(singular s :singularKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(identity s :idenKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(ones s :onesKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(skew s :skewKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(Row s :rowKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                if(mat1[0][0] == Integer.MAX_VALUE){
                    System.out.println("Id weren't found");
                    break;
                }
                System.out.println("1.row wise mean\n2.column wise mean\n3.mean of all elements");
                br.read();
                int id = Integer.parseInt(br.readLine());
                if(id == 1){
                float[][] toBe = new float[mat1.length][1];
                float sum = 0f;
                int count = 0;
                for(int i =0;i<mat1.length;i++){
                    for (int j =0;j< mat1[0].length;j++){
                        sum+= mat1[i][j];
                        count++;
                    }
                    toBe[i][0] = (sum/count) ;
                    sum = 0;
                    count = 0;
                }
                for(int i =0;i<toBe.length;i++){
                    for (int j =0;j< toBe[0].length;j++){
                        System.out.print(toBe[i][j]+" ");
                    }
                    System.out.println();
                }
            }else if (id == 2){
                    float[][] toBe = new float[1][mat1[0].length];
                    float sum = 0f;
                    int count = 0;
                    for(int i =0;i<mat1[0].length;i++){
                        for (int j =0;j< mat1.length;j++){
                            sum+= mat1[j][i];
                            count++;
                        }
                        toBe[0][i] = (sum/count) ;
                        sum = 0;
                        count = 0;
                    }
                    for(int i =0;i<toBe.length;i++){
                        for (int j =0;j< toBe[0].length;j++){
                            System.out.print(toBe[i][j]+" ");
                        }
                        System.out.println();
                    }
                }else if(id == 3){
                    float sum = 0f;
                    int count = 0;
                    for(int i =0;i<mat1.length;i++){
                        for (int j =0;j< mat1[0].length;j++){
                            sum+= mat1[i][j];
                            count++;
                        }
                    }
                    System.out.println("The mean of all elements is "+sum/count);
                }else {
                    System.out.println("enter a valid id ");
                    break;
                }
            }else if(inp == 10){
                System.out.println("Choose matrix from a-"+(char)(latest-1));
                char a = (char) br.read();
                br.read();
                int[][] mat1 = new int[1][1];
                mat1[0][0] = Integer.MAX_VALUE;

                for(singleton s :singletonKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(square s :sqKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(clmn s :clmnKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(scalar s :scalarKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(Null s :nullKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(dia s :diaKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(rect s :rectKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(upper s :upperKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(lower s :lowerKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(symmetric s :symmeKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(singular s :singularKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(identity s :idenKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(ones s :onesKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(skew s :skewKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(Row s :rowKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                if(mat1[0][0] == Integer.MAX_VALUE){
                    System.out.println("Unable to find the  matrix");
                    break;
                }
                if(!isSquare(mat1)){
                    System.out.println("The matrix is not a square matrix");
                    break;
                }
                int toBe  = deter(mat1);
                System.out.println("The determinant is "+toBe);
            }else if (inp == 11){
                System.out.println("Do you allow using singleton matrices as a scalar value?");
                String sss = br.readLine();
                if(sss.equals("no"))break;
                System.out.println("Choose matrix from a-"+(char)(latest-1));
                char a = (char) br.read();
                int[][] mat1 = new int[1][1];
                mat1[0][0] = Integer.MAX_VALUE;
                br.read();
                char b = (char) br.read();
                int[][] mat2 = new int[1][1];
                mat2[0][0] = Integer.MAX_VALUE;
                for(singleton s :singletonKaList){
                    if(a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(square s :sqKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(clmn s :clmnKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(scalar s :scalarKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(Null s :nullKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(dia s :diaKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(rect s :rectKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(upper s :upperKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(lower s :lowerKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(symmetric s :symmeKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(singular s :singularKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(identity s :idenKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(ones s :onesKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(skew s :skewKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                for(Row s :rowKaList){
                    if( a== s.getId())mat1 = s.getMat();
                    if(b == s.getId())mat2  = s.getMat();
                }
                if(mat1[0][0] == Integer.MAX_VALUE || mat2[0][0] == Integer.MAX_VALUE){
                    System.out.println("Id's weren't found");
                    break;
                }
                if(mat2.length != 1 || mat2[0].length != 1){
                    System.out.println("2nd Matrix wasn't a singleton matrix");
                    break;
                }
                System.out.println("1.Multiplication\n2.Division");
                br.read();
                int idd = Integer.parseInt(br.readLine());
                int val  = mat2[0][0];
                if(idd == 1){
                    for(int i =0;i<mat1.length;i++){
                    for (int j =0;j< mat1[0].length;j++){
                        System.out.print((mat1[i][j]*val)+" ");
                    }
                    System.out.println();
                }
                }else if(idd == 2){
                    float[][] toBe  = new float[mat1.length][mat1[0].length];
                    for(int i =0;i<toBe.length;i++){
                    for (int j =0;j< toBe[0].length;j++){
                        toBe[i][j] = mat1[i][j];
                    }
                }
                    for(int i =0;i<toBe.length;i++){
                    for (int j =0;j< toBe[0].length;j++){
                        System.out.print((toBe[i][j]/val)+" ");
                    }
                    System.out.println();
                }
                }else {
                    System.out.println("Enter a valid id");
                    break;
                }
            }else if(inp == 12){
                System.out.println("Choose matrix from a-"+(char)(latest-1));
                char a = (char) br.read();
                int[][] mat1 = new int[1][1];
                mat1[0][0] = Integer.MAX_VALUE;

                for(singleton s :singletonKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(square s :sqKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(clmn s :clmnKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(scalar s :scalarKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(Null s :nullKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(dia s :diaKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(rect s :rectKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(upper s :upperKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(lower s :lowerKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(symmetric s :symmeKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(singular s :singularKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(identity s :idenKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(ones s :onesKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(skew s :skewKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(Row s :rowKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                if(mat1[0][0] == Integer.MAX_VALUE){
                    System.out.println("Id weren't found");
                    break;
                }
                AplusAtran(mat1);
            }else if(inp == 15){
                System.out.println("Enter the type of matrix");
                String str = br.readLine();
                int[][] mat1 = new int[1][1];
                mat1[0][0] = Integer.MAX_VALUE;

                for(singleton s :singletonKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                for(square s :sqKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                for(clmn s :clmnKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                for(scalar s :scalarKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                for(Null s :nullKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                for(dia s :diaKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                for(rect s :rectKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                for(upper s :upperKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                for(lower s :lowerKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                for(symmetric s :symmeKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                for(singular s :singularKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                for(identity s :idenKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                for(ones s :onesKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                for(skew s :skewKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                for(Row s :rowKaList){
                    if(s.getTags().contains(str)){
                        System.out.println(s.getId()+"  =  ");
                        mat1 = s.getMat();
                        for(int i =0;i<mat1.length;i++){
                            for (int j =0;j< mat1[0].length;j++){
                                System.out.print(mat1[i][j]+" ");
                            }
                            System.out.println();
                        }
                    }
                }
                if(mat1[0][0] == Integer.MAX_VALUE){
                    System.out.println("Matrix weren't found");
                    break;
                }
            }else if(inp == 13){
                System.out.println("Choose a square matrix from a-"+(char)(latest-1));
                char a = (char) br.read();
                br.read();
                int[][] mat1 = new int[1][1];
                mat1[0][0] = Integer.MAX_VALUE;

                for(singleton s :singletonKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(square s :sqKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(clmn s :clmnKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(scalar s :scalarKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(Null s :nullKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(dia s :diaKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(rect s :rectKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(upper s :upperKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(lower s :lowerKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(symmetric s :symmeKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(singular s :singularKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(identity s :idenKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(ones s :onesKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(skew s :skewKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                for(Row s :rowKaList){
                    if( a== s.getId())mat1 = s.getMat();
                }
                if(mat1[0][0] == Integer.MAX_VALUE){
                    System.out.println("Unable to find the  matrix");
                    break;
                }
                if(!isSquare(mat1)){
                    System.out.println("The matrix is not a square matrix");
                    break;
                }
                eigen(mat1);
            }

        }}

    }

}