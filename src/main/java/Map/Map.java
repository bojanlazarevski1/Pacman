package Map;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Media.Media;
import Painter.Scaler;
import Settings.*;

public class Map extends JPanel {
    private ArrayList<Edge> edges;
    private ArrayList<Node> nodes;
    
    public Map() {
        createMap();
        setBounds(0,0,Scaler.getNewSize(),Scaler.getNewSize());
        setOpaque(false);
        repaint();
    }
    
    /**
     * Paints the map in its swing Container.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor((Color)Settings.get(EParam.line_color));
        int line_thickness = (int)Settings.get(EParam.line_thickness);
        
        ((Graphics2D) g).setStroke(new BasicStroke(line_thickness));
        
        //Paints all the edges
        int offset = Scaler.scale((int)Settings.get(EParam.path_width))/2;
        for (Edge e : edges) {
            Node n1 = e.getFrom();
            Node n2 = e.getTo();
            
            int x1 = n1.getX(); int y1 = n1.getY();
            int x2 = n2.getX(); int y2 = n2.getY();
            
    
            
            switch (e.getOrientation()) {
                case HORIZONTAL:
                    g.drawLine(x1-offset,y1+offset,x2+offset,y2+offset);
                    g.drawLine(x1-offset,y1-offset,x2+offset,y2-offset);
                    break;
                case VERTICAL:
                    g.drawLine(x1+offset,y1-offset,x2+offset,y2+offset);
                    g.drawLine(x1-offset,y1-offset,x2-offset,y2+offset);
                    break;
            }
        }
        
        //Paints all the nodes
        g.setColor((Color)Settings.get(EParam.background_color));
        for (Node n : nodes) {
    
            int x = n.getX() - offset;
            int y = n.getY() - offset;
            int line_lenght = Scaler.scale((int)Settings.get(EParam.path_width)) - line_thickness;
    
            if (n.getLeft() != null) {
                g.drawLine(x,y+line_thickness,x,y+line_lenght);
            }
            if (n.getRight() != null) {
                g.drawLine(x+offset*2,y+line_thickness,x+offset*2,y+line_lenght);
            }
            if (n.getUp() != null) {
                g.drawLine(x+line_thickness,y,x+line_lenght,y);
            }
            if (n.getDown() != null) {
                g.drawLine(x+line_thickness,y+offset*2,x+line_lenght,y+offset*2);
            }
        }
        
        Toolkit.getDefaultToolkit().sync();
        super.paintComponent(g);
    }
    
    private void addEdge(Node from, Node to, EDirection fromDirection, EDirection toDirection) {
        if (!nodes.contains(from))
            nodes.add(from);
        if (!nodes.contains(to))
            nodes.add(to);
        
        Edge edge = new Edge(from, to);
        from.setEdge(edge, fromDirection);
        to.setEdge(edge, toDirection);
        edges.add(edge);
    }
    
    
    private void createMap() {
        edges = new ArrayList<Edge>();
        nodes = new ArrayList<Node>();
        
        Node n11 = new Node(Scaler.scale(100),Scaler.scale(100));
        Node n12 = new Node(Scaler.scale(400),Scaler.scale(100));
        addEdge(n11, n12, EDirection.RIGHT, EDirection.LEFT);
    
        Node n13 = new Node(Scaler.scale(600),Scaler.scale(100));
        addEdge(n12,  n13, EDirection.RIGHT,EDirection.LEFT);
    
        Node n14 = new Node(Scaler.scale(900),Scaler.scale(100));
        addEdge(n13,  n14, EDirection.RIGHT,EDirection.LEFT);
    
    
    
    
    
        Node n21 = new Node(Scaler.scale(100),Scaler.scale(300));
        Node n22 = new Node(Scaler.scale(400),Scaler.scale(300));
        addEdge(n21, n22, EDirection.RIGHT,EDirection.LEFT);
    
        Node n23 = new Node(Scaler.scale(600),Scaler.scale(300));
    
        Node n24 = new Node(Scaler.scale(900),Scaler.scale(300));
        addEdge(n23, n24, EDirection.RIGHT,EDirection.LEFT);
    
        addEdge(n11,n21,EDirection.DOWN,EDirection.UP);
        addEdge(n12,n22,EDirection.DOWN,EDirection.UP);
        addEdge(n13,n23,EDirection.DOWN,EDirection.UP);
        addEdge(n14,n24,EDirection.DOWN,EDirection.UP);
    
    
    
    
        Node n32 = new Node(Scaler.scale(400),Scaler.scale(500));
        Node n33 = new Node(Scaler.scale(600),Scaler.scale(500));
        addEdge(n32, n33, EDirection.RIGHT,EDirection.LEFT);
    
        addEdge(n22,n32,EDirection.DOWN,EDirection.UP);
        addEdge(n23,n33,EDirection.DOWN,EDirection.UP);
    
    
    
    
    
        Node n41 = new Node(Scaler.scale(100),Scaler.scale(700));
        Node n42 = new Node(Scaler.scale(400),Scaler.scale(700));
        addEdge(n41, n42, EDirection.RIGHT, EDirection.LEFT);
    
        Node n43 = new Node(Scaler.scale(600),Scaler.scale(700));
    
        Node n44 = new Node(Scaler.scale(900),Scaler.scale(700));
        addEdge(n43,  n44, EDirection.RIGHT,EDirection.LEFT);
    
        addEdge(n41,n21,EDirection.UP,EDirection.DOWN);
        addEdge(n42,n32,EDirection.UP,EDirection.DOWN);
        addEdge(n43,n33,EDirection.UP,EDirection.DOWN);
        addEdge(n44,n24,EDirection.UP,EDirection.DOWN);
    
    
    
    
    
        Node n51 = new Node(Scaler.scale(100),Scaler.scale(900));
        Node n52 = new Node(Scaler.scale(400),Scaler.scale(900));
        addEdge(n51, n52, EDirection.RIGHT,EDirection.LEFT);
    
        Node n53 = new Node(Scaler.scale(600),Scaler.scale(900));
        addEdge(n52, n53, EDirection.RIGHT,EDirection.LEFT);
    
        Node n54 = new Node(Scaler.scale(900),Scaler.scale(900));
        addEdge(n53, n54, EDirection.RIGHT,EDirection.LEFT);
    
        addEdge(n41,n51,EDirection.DOWN,EDirection.UP);
        addEdge(n42,n52,EDirection.DOWN,EDirection.UP);
        addEdge(n43,n53,EDirection.DOWN,EDirection.UP);
        addEdge(n44,n54,EDirection.DOWN,EDirection.UP);
    }
    
    //////////////////
    // Getters and Setters below
    
}