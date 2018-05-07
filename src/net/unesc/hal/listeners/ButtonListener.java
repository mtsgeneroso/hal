/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unesc.hal.listeners;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author mtsgeneroso
 */
public class ButtonListener implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getComponent().isEnabled())
            e.getComponent().setBackground(new Color(19, 71, 84));
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getComponent().isEnabled())
            e.getComponent().setBackground(new Color(19, 71, 84));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getComponent().isEnabled())
            e.getComponent().setBackground(new Color(19, 71, 84));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getComponent().isEnabled())
            e.getComponent().setBackground(new Color(19, 71, 84));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getComponent().isEnabled())
            e.getComponent().setBackground(new Color(7,54,66));
        
    }


    
}
