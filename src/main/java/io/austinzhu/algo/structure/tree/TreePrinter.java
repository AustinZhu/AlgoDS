package io.austinzhu.algo.structure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class TreePrinter<T> {

    private Function<T, String> getLabel;
    private Function<T, T> getLeft;
    private Function<T, T> getRight;

    private StringBuilder stringBuilder = new StringBuilder();

    private boolean squareBranches = false;
    private boolean lrAgnostic = false;
    private int hspace = 2;

    public TreePrinter(Function<T, String> getLabel, Function<T, T> getLeft, Function<T, T> getRight) {
        this.getLabel = getLabel;
        this.getLeft = getLeft;
        this.getRight = getRight;
    }

    public void setHspace(int hspace) { this.hspace = hspace; }

    public void setTspace(int tspace) { this.hspace = tspace; }

    /*
        Prints ascii representation of binary tree.
        Parameter hspace is minimum number of spaces between adjacent node labels.
        Parameter squareBranches, when set to true, results in branches being printed with ASCII box
        drawing characters.
     */
    public void printTree(T root) {
        List<TreeLine> treeLines = buildTreeLines(root);
        printTreeLines(treeLines);
    }

    public void printTreeLines(List<TreeLine> treeLines) {
        if (treeLines.size() > 0) {
            int minLeftOffset = minLeftOffset(treeLines);
            int maxRightOffset = maxRightOffset(treeLines);
            for (TreeLine treeLine : treeLines) {
                int leftSpaces = -(minLeftOffset - treeLine.leftOffset);
                int rightSpaces = maxRightOffset - treeLine.rightOffset;
                stringBuilder.append(spaces(leftSpaces)).append(treeLine.line).append(spaces(rightSpaces)).append('\n');
            }
        }
    }

    private List<TreeLine> buildTreeLines(T root) {
        if (root == null) return Collections.emptyList();
        else {
            String rootLabel = getLabel.apply(root);
            List<TreeLine> leftTreeLines = buildTreeLines(getLeft.apply(root));
            List<TreeLine> rightTreeLines = buildTreeLines(getRight.apply(root));

            int leftCount = leftTreeLines.size();
            int rightCount = rightTreeLines.size();
            int minCount = Math.min(leftCount, rightCount);
            int maxCount = Math.max(leftCount, rightCount);

            // The left and right subtree print representations have jagged edges, and we essentially we have to
            // figure out how close together we can bring the left and right roots so that the edges just meet on
            // some line.  Then we add hspace, and round up to next odd number.
            int maxRootSpacing = 0;
            for (int i = 0; i < minCount; i++) {
                int spacing = leftTreeLines.get(i).rightOffset - rightTreeLines.get(i).leftOffset;
                if (spacing > maxRootSpacing) maxRootSpacing = spacing;
            }
            int rootSpacing = maxRootSpacing + hspace;
            if (rootSpacing % 2 == 0) rootSpacing++;
            // rootSpacing is now the number of spaces between the roots of the two subtrees

            List<TreeLine> allTreeLines = new ArrayList<>();

            // add the root and the two branches leading to the subtrees

            allTreeLines.add(new TreeLine(rootLabel, -(rootLabel.length() - 1) / 2, rootLabel.length() / 2));

            // also calculate offset adjustments for left and right subtrees
            int leftTreeAdjust = 0;
            int rightTreeAdjust = 0;

            if (leftTreeLines.isEmpty()) {
                if (!rightTreeLines.isEmpty()) {
                    // there's a right subtree only
                    if (squareBranches) {
                        if (lrAgnostic) {
                            allTreeLines.add(new TreeLine("\u2502", 0, 0));
                        } else {
                            allTreeLines.add(new TreeLine("\u2514\u2510", 0, 1));
                            rightTreeAdjust = 1;
                        }
                    } else {
                        allTreeLines.add(new TreeLine("\\", 1, 1));
                        rightTreeAdjust = 2;
                    }
                }
            } else if (rightTreeLines.isEmpty()) {
                // there's a left subtree only
                if (squareBranches) {
                    if (lrAgnostic) {
                        allTreeLines.add(new TreeLine("\u2502", 0, 0));
                    } else {
                        allTreeLines.add(new TreeLine("\u250C\u2518", -1, 0));
                        leftTreeAdjust = -1;
                    }
                } else {
                    allTreeLines.add(new TreeLine("/", -1, -1));
                    leftTreeAdjust = -2;
                }
            } else {
                // there's a left and right subtree
                if (squareBranches) {
                    int adjust = (rootSpacing / 2) + 1;
                    String horizontal = String.join("", Collections.nCopies(rootSpacing / 2, "\u2500"));
                    String branch = "\u250C" + horizontal + "\u2534" + horizontal + "\u2510";
                    allTreeLines.add(new TreeLine(branch, -adjust, adjust));
                    rightTreeAdjust = adjust;
                    leftTreeAdjust = -adjust;
                } else {
                    if (rootSpacing == 1) {
                        allTreeLines.add(new TreeLine("/ \\", -1, 1));
                        rightTreeAdjust = 2;
                        leftTreeAdjust = -2;
                    } else {
                        for (int i = 1; i < rootSpacing; i += 2) {
                            String branches = "/" + spaces(i) + "\\";
                            allTreeLines.add(new TreeLine(branches, -((i + 1) / 2), (i + 1) / 2));
                        }
                        rightTreeAdjust = (rootSpacing / 2) + 1;
                        leftTreeAdjust = -((rootSpacing / 2) + 1);
                    }
                }
            }

            // now add joined lines of subtrees, with appropriate number of separating spaces, and adjusting offsets

            for (int i = 0; i < maxCount; i++) {
                TreeLine leftLine, rightLine;
                if (i >= leftTreeLines.size()) {
                    // nothing remaining on left subtree
                    rightLine = rightTreeLines.get(i);
                    rightLine.leftOffset += rightTreeAdjust;
                    rightLine.rightOffset += rightTreeAdjust;
                    allTreeLines.add(rightLine);
                } else if (i >= rightTreeLines.size()) {
                    // nothing remaining on right subtree
                    leftLine = leftTreeLines.get(i);
                    leftLine.leftOffset += leftTreeAdjust;
                    leftLine.rightOffset += leftTreeAdjust;
                    allTreeLines.add(leftLine);
                } else {
                    leftLine = leftTreeLines.get(i);
                    rightLine = rightTreeLines.get(i);
                    int adjustedRootSpacing = (rootSpacing == 1 ? (squareBranches ? 1 : 3) : rootSpacing);
                    TreeLine combined = new TreeLine(leftLine.line + spaces(adjustedRootSpacing - leftLine.rightOffset + rightLine.leftOffset) + rightLine.line,
                            leftLine.leftOffset + leftTreeAdjust, rightLine.rightOffset + rightTreeAdjust);
                    allTreeLines.add(combined);
                }
            }
            return allTreeLines;
        }
    }

    private static int minLeftOffset(List<TreeLine> treeLines) {
        return treeLines.stream().mapToInt(l -> l.leftOffset).min().orElse(0);
    }

    private static int maxRightOffset(List<TreeLine> treeLines) {
        return treeLines.stream().mapToInt(l -> l.rightOffset).max().orElse(0);
    }

    private static String spaces(int n) {
        return String.join("", Collections.nCopies(n, " "));
    }

    private static class TreeLine {
        String line;
        int leftOffset;
        int rightOffset;

        TreeLine(String line, int leftOffset, int rightOffset) {
            this.line = line;
            this.leftOffset = leftOffset;
            this.rightOffset = rightOffset;
        }
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}