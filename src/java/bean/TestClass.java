/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Walter
 */
public class TestClass {
    public static void main(String args[]) {
    
        List<String> a = new ArrayList<String>();
        List<String> b = new ArrayList<String>();
        a.add("Java");
        a.add("Mava");
        a.add("Lava");
        a.add("Php");
        a.add("Big");
        a.add("Dig");
        a.add("Pig");
        
        b.add("Mava");
        b.add("Php");
        b.add("Fig");
        
        List<String> c = new ArrayList<String> (b.size());
        c.addAll(b);
        c.removeAll(a);
        
        for(int i = 0 ; i < c.size() ; i++) {
            System.out.println(c.get(i));
        }
    }
    
}
