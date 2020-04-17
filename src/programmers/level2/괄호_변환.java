package programmers.level2;

import java.util.Stack;

public class 괄호_변환 {
    static char OPEN_BRACKET = '(';
    static char CLOSED_BRACKET =')';

    public static void main(String[] args) {
        System.out.println(solution("(()(())))("));
    }
    public static String solution(String p) {
        String answer = "";
        if(p.length() == 0) return answer;
        if(isValid(p)){
            return p;
        }else {
            answer = reStructString(p);
        }

        return answer;
    }

    public static boolean isCouple(char cur, char peek){
        boolean res = false;
           if(cur == ')' && peek == '('){
               res = true;
           }
       return res;
    }

    public static boolean isPair(char cur, char peek){
        boolean res = false;

        if(cur == OPEN_BRACKET && peek == CLOSED_BRACKET){
            res = true;
        }else if(cur == CLOSED_BRACKET && peek == OPEN_BRACKET){
            res = true;
        }

        return res;
    }
    public static String reStructString(String p){
        int idx = 0;
        Stack<Character> stack = new Stack<>();
        String temp = "";

        if(isValid(p)) return p;

        while (p.length()>0){
            String u="";
            idx = 0;
            stack.clear();
            do{
                char c = p.charAt(idx);
                if(stack.isEmpty()){
                    stack.push(p.charAt(idx));
                }else {
                    if(isPair(c, stack.peek())){
//                        u += (stack.peek()+c);
                        stack.pop();
                        if(stack.isEmpty()){
                            u = p.substring(0, idx+1);
                            p = p.substring(idx+1);
                            break;
                        }
                    }else {
                        stack.push(c);
                    }
                }
                idx++;
            }
            while (idx < p.length());

            if(!isValid(u)){
                temp += "(";
                p = reStructString(p);
                temp += p + ")";
                p="";
                u = u.substring(1, u.length()-1);
                temp += getReversedBracket(u);
            }else {
                temp += u;
            }
        }
        return temp;
    }

    public static boolean isValid(String p){
        if(p.length()==0) return true;
        Stack<Character> stack = new Stack<>();
        boolean res = false;

        int idx = 0;
        do{
            char c = p.charAt(idx);
            if(stack.isEmpty()){
                stack.push(p.charAt(idx));
            }else {
                if(isCouple(c, stack.peek())){
                    stack.pop();
                }else {
                    stack.push(c);
                }
            }
            idx++;
        }
        while (idx < p.length());

        if(stack.isEmpty()){
            res = true;
        }

        return res;
    }

    public static String getReversedBracket(String p){
        String acc = "";
        for(int i=0; i< p.length(); i++){
            if(p.charAt(i) == OPEN_BRACKET ){
                acc += Character.toString(CLOSED_BRACKET);
            }else {
                acc += Character.toString(OPEN_BRACKET);
            }
        }
        return acc;
    }
}
