package hw_5;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SearchSchool {
    static List<Node> list = new ArrayList<>();
    static Hashtable<Integer,Integer> table=new Hashtable<>(); 

    public static void main(String[] args) {
        readFile();
        Scanner scn=new Scanner(System.in);
        System.out.print("1.BinarySearch 2.HashMap ？");
        int choose=scn.nextInt();
        while(true){
            if(choose==1){
                SearchSchool_BinarySearch();
            }else if(choose==2){
                SearchSchool_HashTable();
            }else{
                break;
            }
        }
    }
    
    //BinarySearch()搜尋
    private static void SearchSchool_BinarySearch() {
        String StNum;
        Scanner scn=new Scanner(System.in);
        
        while(true){
            System.out.print("查詢學號(請輸入AXXX)(不想查則輸入0000):");
            StNum=scn.next();
            int number=Integer.parseInt(StNum.substring(1,4));
         
            if(number==0){
                break;
            }else if(number<0||number>500){
                System.out.println("無此人！");
            }else{
                int result=BinarySearch(number-1,0,list.size());
                if(result==-1){
                    System.out.println("未錄取學校！");
                }else{
                    System.out.println("錄取："+result);
                }
            }    
        }
        scn.close();
    } 
    
    //HashTable()搜尋
    private static void SearchSchool_HashTable() {
        String StNum;
        Scanner scn=new Scanner(System.in);
        while(true){
            System.out.print("查詢學號(請輸入AXXX)(不想查則輸入0000):");
            StNum=scn.next();
            int number=Integer.parseInt(StNum.substring(1,4));
         
            if(number==0){
                break;
            }else if(number<0||number>500){
                System.out.println("無此人！");
            }else{
                int result=table.get(number-1);
                if(result==-1){
                    System.out.println("未錄取學校！");
                }else{
                    System.out.println("錄取："+result);
                }
            }    
        }
        scn.close();
    }
    
    //實作BinarySearch
    private static int BinarySearch(int target,int start,int end){
        int middle=(start+end)/2;
        if(target==middle){
            return list.get(middle).result;
        }else if(target>middle){
            return BinarySearch(target,middle,end);
        }else{
            return BinarySearch(target,start,middle);
        }
    }
    
    //讀txt檔
    public static void readFile() {        
        try {
            File f = new File("D:\\querylist.txt");
            Scanner scn = new Scanner(f);
            int i=0;
            while(scn.hasNextLine()) {
		String da = scn.nextLine();
		String[] d=da.split(" ");
                Node n=new Node(d[0],d[1]);
                int result=Integer.parseInt(d[1]);
                list.add(n);
                table.put(i, result);
                i++;
            }
            scn.close();
	}catch(FileNotFoundException e){
            System.out.println("Error occured");
            e.printStackTrace();
	}       
    }  
}
