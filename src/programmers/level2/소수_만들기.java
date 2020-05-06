class 소수_만들기 {
    public int solution(int[] nums) {
        int answer = 0;
        int count = nums.length;
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        
        for(int i=0; i<count-2; i++){
            for(int j=i+1; j<count-1; j++){
                for(int k=j+1; k<count; k++){
                    int n = nums[i]+nums[j]+nums[k];
                    boolean sosu = true;
                    for(int s=2; s<n; s++){
                        if(n%s == 0){
                            sosu = false;
                            break;
                        }
                    }
                    
                    if(sosu){ answer++;}
                }
            }
        }
        return answer;
    }
}
