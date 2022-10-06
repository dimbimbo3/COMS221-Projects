import java.util.*;

class node{
    public String item;
    public String process_id;
    public String time_to_run;
    public String priority;
    public String time_of_arrival;
    public node next;
    
    public node(String item, String process_id, String time_to_run, String priority, String time_of_arrival, node next){
        this.item = item;
        this.process_id = process_id;
        this.time_to_run = time_to_run;
        this.priority = priority;
        this.time_of_arrival = time_of_arrival;
        this.next = next;
    }
}

class list extends node{
    public node first;
    public node curpos;
    
    list(){
        super(" ", " ", " ", " ", " ", null);//calls node constructor
        
        first = null;
        curpos = null;
    }
    
    public void add(String item, String process_id, String time_to_run, String priority, String time_of_arrival, node next){
        node newnode = new node(item, process_id, time_to_run, priority, time_of_arrival, next);
        
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
             System.out.print("Item: " + curpos.item);
             System.out.print(" Process ID: " + curpos.process_id);
             System.out.print(" Time to run: " + curpos.time_to_run);
             System.out.print(" Priority: " + curpos.priority);
             System.out.print(" Time of arrival: " + curpos.time_of_arrival);
             System.out.println("");
             curpos = curpos.next;
         }
    }
    
    public int search(String input){
        curpos = first;
        boolean found = false;
        
        //priming read - START
        int pos = 0;
        if(curpos.item.equals(input))
            found = true;
        curpos = curpos.next;
        //priming read - END
        while(curpos != null && !found){
            pos++;
            if(curpos.item.equals(input))
                found = true;
            curpos = curpos.next;
        }
        
        if(found == true){
            System.out.println("Item was found.");
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
            System.out.println("Item was not found");
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
}

public class LinkedList_lab2 {
    public static void main(String[] args){
        list shoppinglist = new list();
        
        shoppinglist.add("carrots", "ID-1", "Run-1", "Priority-1", "Arrival-1", null);
        shoppinglist.add("milk", "ID-2", "Run-2", "Priority-2", "Arrival-2", null);
        shoppinglist.add("eggs", "ID-3", "Run-3", "Priority-3", "Arrival-3", null);
        shoppinglist.add("lettuce", "ID-4", "Run-4", "Priority-4", "Arrival-4", null);
        
        shoppinglist.print();
        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("");
        System.out.print("Please input the item you want to delete from the list:");
        String input = keyboard.nextLine();
        
        int delete_verify = shoppinglist.delete(shoppinglist.search(input));
        
        if(delete_verify == 1)
            shoppinglist.print();
    }
}
