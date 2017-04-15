package com.javarush.task.task36.task3604;

import javax.swing.*;
import java.awt.*;

/*
Разбираемся в красно-черном дереве
*/
public class Solution extends JFrame {
    private JPanel field = new JPanel()
    {
        private void paintArrow(final Graphics g, final int x1, final int y1, final int x2, final int y2)
        {
            final float k = 0.25f;
            g.setColor(Color.blue);

            final int xBeg = Math.round(x1 + k * (x2 - x1));
            final int yBeg = Math.round(y1 + k * (y2 - y1));
            final int xEnd = Math.round(x2 - k * (x2 - x1));
            final int yEnd = Math.round(y2 - k * (y2 - y1));

            g.drawLine(xBeg, yBeg, xEnd, yEnd);
        }

        private void paintNode(final RedBlackTree.Node node, final Graphics g, final int x, final int y, final int length) throws Exception
        {
            final Color color = "black".equals(NodeHelperTestSolution.getNodeColor(node).toString().toLowerCase()) ?
                    Color.black : Color.red;

            g.setColor(color);
            g.fillRect(x - 5, y - 5, 10, 10);
            g.drawString(Integer.valueOf(NodeHelperTestSolution.getNodeIntValue(node)).toString(), x, y - 8);

            final RedBlackTree.Node leftNode = NodeHelperTestSolution.getNodeValue("left", node);
            if (leftNode != NodeHelperTestSolution.getEmptyNode())
            {
                paintArrow(g, x, y, x - length, y + 30);
                paintNode(leftNode, g, x - length, y + 30, length / 2);
            }

            final RedBlackTree.Node rightNode = NodeHelperTestSolution.getNodeValue("right", node);
            if (rightNode != NodeHelperTestSolution.getEmptyNode())
            {
                paintArrow(g, x, y, x + length, y + 30);
                paintNode(rightNode, g, x + length, y + 30, length / 2);
            }

        }

        @Override
        public void paint(final Graphics g)
        {
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());

            try
            {
                final RedBlackTree.Node node = NodeHelperTestSolution.getFromTreeNodeByName("header", tree);
                paintNode(node, g, 20, 20, 400);
            }
            catch (final Exception e)
            {
                e.printStackTrace();
            }
        }
    };

    private Solution()
    {
        add(field);
        setSize(1000, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tree = new RedBlackTree();
    }

    private RedBlackTree tree;

    private static void insertValue(final Solution solution, final int value) throws InterruptedException
    {
        JOptionPane.showMessageDialog(solution, value);
        solution.tree.insert(value);
        solution.field.repaint();
    }

    public static void main(final String[] args) throws Exception {
        final Solution solution = new Solution();

        final int[] ints = {10, 1, 15, 9, 18, 7, 14, 25, 10, 16, 28, 20, 8, 2, 17, 24, 25, 13, 6};

        for (final int i : ints)
            insertValue(solution, i);

        solution.field.repaint();
    }
}
