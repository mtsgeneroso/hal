/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unesc.hal.lexico.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
/**
 *
 * @author mtsgeneroso
 */
public class TextPanelHighLight extends JTextPane  {
        public TextPanelHighLight() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
            try {
                Rectangle rect = modelToView(getCaretPosition());
                if (rect != null) {
                    g.setColor(new java.awt.Color(19, 71, 84));
                    g.fillRect(0, rect.y, getWidth(), rect.height);
                }
            } catch (BadLocationException e) {
            }
            super.paintComponent(g);
        }

        @Override
        public void repaint(long tm, int x, int y, int width, int height) {
            super.repaint(tm, 0, 0, getWidth(), getHeight());
        }
}