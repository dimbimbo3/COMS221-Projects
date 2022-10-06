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
    
    public int findShortest(){
        curpos = first;
    	
        int shortest = first.burst_time; //sets first node's burst_time to lowest
    	while (curpos != null){
            if (curpos.burst_time < shortest){
    		shortest = curpos.burst_time;
            }
            curpos = curpos.next;
    	}
    	
    	return shortest;
    }
    
     public String search(int burst){
    	String id = "";
    	curpos = first;
    	
    	while (curpos != null){
            if (curpos.burst_time == burst){
    		id = curpos.process_id;
    		curpos = null;
            }
            else{
    		curpos = curpos.next;
            }
    	}
        
        return id;
     }
    
    public void delete(int burst){
    	curpos = prev = first;
    	
        //checks if the first node has the shortest burst time
        //if so, moves the first pointer to the next node
        if(first.burst_time == burst){
                first = first.next;
                curpos = null;
        }
    	while (curpos != null){
            if(curpos.burst_time == burst){
    		prev.next = curpos.next;
    		curpos = null;
            }
            else
            {
    		prev = curpos;
    		curpos = curpos.next;
            }
    	}
    }
    
    public void FCFS(){
        curpos = first;
    	int length = 0;
        
        for(; curpos != null; length++){
            curpos = curpos.next;
        }
        
        int current_turnaround = 0;
        int total_turnaround = 0;
        int total_wait = 0;
        
        curpos = first;
    	while (curpos != null){
            current_turnaround += curpos.burst_time;
            total_turnaround += current_turnaround;
            curpos.turnaround_time = current_turnaround;
            curpos.wait_time = current_turnaround - curpos.burst_time;
            total_wait += curpos.wait_time;
            curpos = curpos.next;
    	}
        
        System.out.println("FCFS");
        print();
        
        System.out.println("\nTotal Turnaround Time: " + total_turnaround);
        System.out.println("Total Wait Time: " + total_wait);
        
        double average_turnaround = (double)total_turnaround / length;
        double average_wait = (double)total_wait / length;
        
        System.out.println("\nAverage Wait Time: " + average_wait);
        System.out.println("Average Turnaround Time: " + average_turnaround);
    }
    
    public void SJF(){
        curpos = first;
        int length = 0;
        
        for(; curpos != null; length++){
            curpos = curpos.next;
        }

        String currentID = "";
        int burst = 0;
        int turnaround = 0;
        int wait = 0;
        int total_turnaround = 0;
        int total_wait = 0;
        int counter = 0;
        
        System.out.println("SJF");
    	while (counter < length){
            burst = findShortest();
            currentID = search(burst);
            turnaround += burst;
            total_turnaround += turnaround;
            wait = turnaround - burst;
            total_wait += wait;
            System.out.print("Process ID: P" + currentID);
            System.out.print(" Burst Time: " + burst);
            System.out.print(" Wait Time: " + wait);
            System.out.print(" Turnaround Time: " + turnaround);
            System.out.println("");
            delete(burst);
            counter++;
    	}
        
        System.out.println("\nTotal Turnaround Time: " + total_turnaround);
        System.out.println("Total Wait Time: " + total_wait);
        
        double average_turnaround = (double)total_turnaround / length;
        double average_wait = (double)total_wait / length;
        
        System.out.println("\nAverage Wait Time: " + average_wait);
        System.out.println("Average Turnaround Time: " + average_turnaround);
    }
}

public class LinkedList_project3 {
    public static void main(String[] args){
        list process_list = new list();
        
        process_list.add("1", 50, 0, 0, null);
        process_list.add("2", 45, 0, 0, null);
        process_list.add("3", 40, 0, 0, null);
        process_list.add("4", 35, 0, 0, null);
        process_list.add("5", 30, 0, 0, null);
        process_list.add("6", 25, 0, 0, null);
        process_list.add("7", 20, 0, 0, null);
        process_list.add("8", 15, 0, 0, null);
        process_list.add("9", 10, 0, 0, null);
        process_list.add("10", 5, 0, 0, null);
        
        process_list.SJF();
    }
}
