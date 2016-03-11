/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author acer
 */
public class RiverCrossingDFA {
    static State state = new State();
    public static void main(String[] args) throws FileNotFoundException, IOException {       
        File file = new File("mp2.out");
        file.createNewFile();    
        FileWriter fw = new FileWriter(file);
        
        FileInputStream readFile = new FileInputStream("mp2.in");
        BufferedReader br = new BufferedReader(new InputStreamReader(readFile));
        
        String line;
        
        while((line = br.readLine()) != null){
            for(int i=0; i < line.length(); i++){
                char crosser = line.charAt(i);
                if(!(validMove(crosser))){
                    fw.write("NG\n");
                    break;
                }               
                if(invalidState()){
                    fw.write("NG\n");
                    break;
                }
            }  
            if(state.eastBank.hasCarrot &&
                state.eastBank.hasLion &&
                state.eastBank.hasMan &&
                state.eastBank.hasRabbit)                    
            fw.write("OK\n");
            
            state = reset();
        }    
        fw.flush();
        fw.close();    
    }

    private static boolean validMove(char crosser) {
        if(crosser == 'N'){            
            if(state.westBank.hasMan){
                state.westBank.hasMan = false;
                state.eastBank.hasMan = true;
                return true;
                
            }
            else if(state.eastBank.hasMan){
                state.eastBank.hasMan = false;
                state.westBank.hasMan = true;
                return true;
            }
            else
                return false;   
        }
        else if(crosser == 'L'){
            if(state.westBank.hasLion && state.westBank.hasMan){
                state.westBank.hasLion = false;
                state.eastBank.hasLion = true;
                state.westBank.hasMan = false;
                state.eastBank.hasMan = true;
                return true;
            }
            else if(state.eastBank.hasLion && state.eastBank.hasMan){
                state.eastBank.hasLion = false;
                state.westBank.hasLion = true;
                state.eastBank.hasMan = false;
                state.westBank.hasMan = true;  
                return true;
            }
            else
                return false;
        }
        else if(crosser == 'R'){
            if(state.westBank.hasRabbit && state.westBank.hasMan){
                state.westBank.hasRabbit = false;
                state.eastBank.hasRabbit = true;
                state.westBank.hasMan = false;
                state.eastBank.hasMan = true;
                return true;
            }
            else if(state.eastBank.hasRabbit && state.eastBank.hasMan){
                state.eastBank.hasRabbit = false;
                state.westBank.hasRabbit = true;
                state.eastBank.hasMan = false;
                state.westBank.hasMan = true;  
                return true;
            }
            else
                return false;
        }
        else if(crosser == 'C'){
            if(state.westBank.hasCarrot && state.westBank.hasMan){
                state.westBank.hasCarrot = false;
                state.eastBank.hasCarrot = true;
                state.westBank.hasMan = false;
                state.eastBank.hasMan = true;
                return true;
            }
            else if(state.eastBank.hasCarrot && state.eastBank.hasMan){
                state.eastBank.hasCarrot = false;
                state.westBank.hasCarrot = true;
                state.eastBank.hasMan = false;
                state.westBank.hasMan = true;  
                return true;
            }
            else
                return false;
        }
        return false;
    }

    private static boolean invalidState() {
        if(state.westBank.hasLion && 
                state.westBank.hasRabbit && 
                state.westBank.hasCarrot && 
                state.eastBank.hasMan)
            return true;
        else if(state.westBank.hasLion &&
                state.westBank.hasRabbit &&
                state.eastBank.hasCarrot &&
                state.eastBank.hasMan)               
            return true;
        else if (state.westBank.hasRabbit &&
                state.westBank.hasCarrot && 
                state.eastBank.hasLion &&
                state.eastBank.hasMan)
            return true;
        else if(state.westBank.hasCarrot &&
                state.eastBank.hasLion &&
                state.eastBank.hasRabbit &&
                state.westBank.hasMan)
            return true;
        else if(state.westBank.hasLion &&
                state.eastBank.hasRabbit &&
                state.eastBank.hasCarrot &&
                state.westBank.hasMan)
            return true;
        else if(state.eastBank.hasLion &&
                state.eastBank.hasRabbit &&
                state.eastBank.hasCarrot &&
                state.westBank.hasMan)
            return true;
        
        return false;    
    }

    private static State reset() {
        return state.reset();
    }
}
