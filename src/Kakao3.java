import java.util.ArrayList;
import java.util.LinkedList;

public class Kakao3 {
    public static void main(String[] args) {
        solution(new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"});
    }

    static public int[] solution(String[] info, String[] query) {
        int[] answer = {};
        Tree tree = new Tree();

        for (String i : info) {
            tree.add(i.split(" "), 0);
        }

        for (int i = 0; i < query.length; i++) {
            String[] q = query[i].split(" and | ");
            int res = tree.find(q, 0);
            System.out.println(res);
        }


        return answer;
    }
}

class Tree {
    Node root;

    public Tree() {
        this.root = new Node();
        root.child.add(new Node(0));
        root.child.add(new Node(1));
        root.child.add(new Node(2));
    }

    public int find(String[] query, int level) {
        int sum = 0;
        if (query[level].equals("-")) {
            for (int i = 0; i < 3; i++) {
                sum += findChild(root.child.get(i), query, level + 1);
            }
        } else {
            sum += findChild(root.child.get(lanToInt(query[level])), query, level + 1);
        }
        return sum;
    }


    private int ConvertWithLevel(int level, String query) {
        if (level == 1) return jobToInt(query);
        else if (level == 2) return typeToInt(query);
        else if (level == 3) return foodToInt(query);
        else return lanToInt(query);
    }

    public int findChild(Node parent, String[] query, int level) {
        int sum = 0;
        if (level == 4) {
            int minScore = Integer.parseInt(query[level]);
            if (parent.score == null) return 0;
            return (int) parent.score.stream().filter(s -> s >= minScore).count();
        }
        if (query[level].equals("-")) {
            for (int i = 0; i < 2; i++) {
                if (parent.child == null || parent.child.size() <= i) continue;
                sum += findChild(parent.child.get(i), query, level + 1);
            }
        } else {
            if (parent.child != null && parent.child.size() > ConvertWithLevel(level, query[level]))
                sum += findChild(parent.child.get(ConvertWithLevel(level, query[level])), query, level + 1);
        }
        return sum;
    }


    private int lanToInt(String lan) {
        if (lan.equals("java")) return 0;
        else if (lan.equals("cpp")) return 1;
        else return 2;
    }

    private int jobToInt(String job) {
        if (job.equals("backend")) return 0;
        else return 1;
    }

    private int typeToInt(String type) {
        if (type.equals("junior")) return 0;
        else return 1;
    }

    private int foodToInt(String food) {
        if (food.equals("pizza")) return 0;
        else return 1;
    }

    public void add(String[] info, int level) {

        addChild(info, root.child.get(lanToInt(info[0])), level + 1);
    }

    private void addChild(String[] info, Node parent, int level) {
        if (parent.child.size() == 0) {
            for (int i = 0; i < 2; i++)
                parent.child.add(new Node(i));
        }
        if (level == 1) {
            addChild(info, parent.child.get(jobToInt(info[level])), level + 1);
        } else if (level == 2) {
            addChild(info, parent.child.get(typeToInt(info[level])), level + 1);
        } else if (level == 3) {
            addChild(info, parent.child.get(foodToInt(info[level])), level + 1);
        } else {
            if (parent.score == null)
                parent.score = new ArrayList<>();
            parent.score.add(Integer.parseInt(info[level]));
        }
    }
}

class Node {
    int type;
    LinkedList<Node> child;
    ArrayList<Integer> score;

    public Node() {
        this.child = new LinkedList<>();
    }

    public Node(int type) {
        this.type = type;
        this.child = new LinkedList<>();
    }
}