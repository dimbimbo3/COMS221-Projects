import java.util.*;

class node{
    public String process_id;
    public int burst_time;
    public int wait_time;
    public int turnaround_time;
    public node next;
    
    public node(String process_id, int burst_time, int wait_time, int turnaround_time, node next){
        this.process_id = process_id;
        this.burst_time = burst_time;
        this.wait_time = wait_time;
        this.next = next;
    }
}

class list extends node{
    public node first;
    public node curpos;
    public node prev;
    
    list(){
        super(" ", 0, 0, 0, null);//calls node constructor
        
        first = null;
        curpos = null;
    }
    
    public void add(String process_id, int burst_time, int wait_time, int turnaround_time, node next){
        node newnode = new node(process_id, burst_time, wait_time, turnaround_time, next);
        
        if(first == null){
            first = newnode;
            curpos = newnode;
        }
        else{
            curpos.next = newnode;
            curpos = newnode;
        }
    }
    
    public void print(){
        curpos = first;
         while(curpos != null){
             System.out.print("Process ID: P" + curpos.process_id);
             System.out.print(" Burst Time: " + curpos.burst_time);
             System.out.print(" Wait Time: " + curpos.wait_time);
             System.out.print(" Turnaround Time: " + curpos.turnaround_time);
             System.out.println("");
             curpos = curpos.next;
         }
    }
    
    public int search(String input){
        curpos = first;
        boolean found = false;
        
        //priming read - START
        int pos = 0;
        if(curpos.process_id.equals(input))
            found = true;
        curpos = curpos.next;
        //priming read - END
        while(curpos != null && !found){
            pos++;
            if(curpos.process_id.equals(input))
                found = true;
            curpos = curpos.next;
        }
        
        if(found == true){
            System.out.println("Process was found.");
            System.out.println("");
        }
        else{
            pos = -1;
        }
        
        return pos;
    }
    
    public int delete(int pos){
        curpos = first;
        int delete_verify = 1;
        
        if(pos == -1){
            System.out.println("Process was not found");
            System.out.println("");
            delete_verify = 0;
        }
        else if(pos == 0){
            first = first.next;
        }
        else{
            for(int i = 0; i < pos - 1; i++){
                curpos = curpos.next;
            }
            curpos.next = curpos.next.next;
        }
        
        return delete_verify;
    }
    
    public void FCFS(){
        curpos = first;
    	int length = 0;
        
        for(; curpos != null; length++){
            curpos = curpos.next;
        }
        
        int[] burst = new int[length];
        int current_turnaround = 0;
        int total_turnaround = 0;
        int total_wait = 0;
        int counter = 0;
        
        curpos = first;
    	while (curpos != null){
            burst[counter] = curpos.burst_time;
            current_turnaround += burst[counter];
            total_turnaround += current_turnaround;
            curpos.turnaround_time = current_turnaround;
            curpos.wait_time = current_turnaround - curpos.burst_time;
            total_wait += curpos.wait_time;
            curpos = curpos.next;
            counter++;
    	}
        
        print();
        
        System.out.println("\nTotal Turnaround Time: " + total_turnaround);
        System.out.println("Total Wait Time: " + total_wait);
        
        double average_turnaround = (double)total_turnaround / length;
        double average_wait = (double)total_wait / length;
        
        System.out.println("\nAverage Wait Time: " + average_wait);
        System.out.println("Average Turnaround Time: " + average_turnaround);
    }
}

public class LinkedList_project2 {
    public static void main(String[] args){
        list process_list = new list();
        
        process_list.add("1", 5, 0, 0, null);
        process_list.add("2", 10, 0, 0, null);
        process_list.add("3", 15, 0, 0, null);
        process_list.add("4", 20, 0, 0, null);
        process_list.add("5", 25, 0, 0, null);
        process_list.add("6", 30, 0, 0, null);
        process_list.add("7", 35, 0, 0, null);
        process_list.add("8", 40, 0, 0, null);
        process_list.add("9", 45, 0, 0, null);
        process_list.add("10", 50, 0, 0, null);
        
        process_list.FCFS();
    }
}
