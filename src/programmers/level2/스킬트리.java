package programmers.level2;

public class 스킬트리 {
    public static void main(String[] args) {
    String[] skills = {"BACDE", "CBADF", "AECB", "BDA"};
        System.out.println(solution("CBD", skills));
    }

    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(String skillTree: skill_trees){
            String skillCopy = skill;
            boolean APPROVE = true;
            for (int i=0; i< skillTree.length(); i++){
                String temp = skillTree.substring(i, i+1);

                if(skillCopy.contains(temp)){
                    if(skillCopy.substring(0,1).equals(skillTree.substring(i, i+1))){
                        skillCopy = skillCopy.substring(1);
                    }else {
                        APPROVE = false;
                        break;
                    }
                }
            }
            if(APPROVE) answer++;
        }
        return answer;
    }
}

