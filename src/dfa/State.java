/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfa;

/**
 *
 * @author acer
 */
public class State {
    WestBank westBank;
    EastBank eastBank;
    
    State(){
       westBank = new WestBank();
       eastBank = new EastBank();
    }
    
    State reset() {
        return new State();
    }
}
