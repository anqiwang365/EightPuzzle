package EightPuzzle;


import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("rawtypes")
public class Node implements Comparable{
    private int[] num = new int[9];	//��������ϵ�����
    private int gValue;    //��ǰ����ȼ��ߵ���ǰ״̬�Ĳ���g(n)
    private int fValue;   //����ʼ״̬��Ŀ�����С����ֵf(n)
    private int hValue;   //��Ŀ�����С����h(n)
    private Node parent;  //��ǰ�ڵ�ĸ��ڵ㼴��״̬
    
    public int[] getNum() {
        return num;
    }
    public void setNum(int[] num) {
        this.num = num;
    }
    public int getGvalue() {
        return gValue;
    }
    public void setGvalue(int gValue) {
        this.gValue = gValue;
    }
    public int getFvalue() {
        return fValue;
    }
    public void setFvalue(int fValue) {
        this.fValue = fValue;
    }
    public int getHvalue() {
        return hValue;
    }
    public void setHvalue(int hValue) {
        this.hValue = hValue;
    }
    public Node getParent() {
        return parent;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    /**
     * ������ֵ���ж��Ƿ��н⣬����ż����ͬ���н�
     * @param target
     * @return �н⣺true �޽⣺false
     */
    public boolean isSolvable(Node target){
        int reverse = 0;
        for(int i=0;i<9;i++){
            for(int j=0;j<i;j++){
                if(num[j]>num[i])
                    reverse++;
                if(target.getNum()[j]>target.getNum()[i])
                    reverse++;
            }
        }
        
        if(reverse % 2 == 0)
            return true;
        return false;
    }
    
    /**
     * �жϵ�ǰ״̬�Ƿ�ΪĿ��״̬
     * @param target
     * @return
     */
    public boolean isTarget(Node target){
        return Arrays.equals(getNum(), target.getNum());
    }
    
    /**
     * ��f(n) = g(n)+h(n);
     * ��ʼ��״̬��Ϣ���õ�depth ����Ϣ
     * @param target
     */
    public void init(Node target){
        int temp = 0;
        for(int i=0;i<9;i++){
            if(num[i]!=target.getNum()[i])
                temp++;
        }
        this.setHvalue(temp);//�õ���ǰ�ڵ��Hֵ����Ŀ��ڵ��ж��ٸ���ͬ�����ָ���
        
        if(this.getParent()==null){
            this.setGvalue(0);//��ʼ��
        }else{
            this.gValue = this.parent.getGvalue()+1;//��ǰ�ڵ�����Ϊ���ڵ���ȼ���1
        }
        this.setFvalue(this.getGvalue()+this.getHvalue());
    }
    
    
    @Override
    public int compareTo(Object o) {
        Node c = (Node) o;
        return this.fValue-c.getFvalue();//Ĭ������Ϊf(n)��С��������
    }
    
    /**
     * @return ����0�ڰ������е�λ��
     */
    public int getZeroPosition(){
        int position = -1;
        for(int i=0;i<9;i++){
            if(this.num[i] == 0){
                position = i;
            }
        }
        return position;
    }
    
    /**
     * 
     * @param open״̬����
     * @return �жϵ�ǰ״̬�Ƿ������open���У�������open���е�λ�ã� ÿ��״̬���������ʾ
     */
    public int isContains(ArrayList<Node> open){
        for(int i=0;i<open.size();i++){
            if(Arrays.equals(open.get(i).getNum(), getNum())){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * ˵���ո��ڵ�һ�в���������
     * @return С��3�Ĳ������Ʒ���false
     */
    public boolean isMoveUp() {
        int position = getZeroPosition();
        if(position<=2){
            return false;
        }
        return true;
    }
    /**
     * ˵���ո������һ�в���������
     * @return ����6���������Ʒ���false
     */
    public boolean isMoveDown() {
        int position = getZeroPosition();
        if(position>=6){
            return false;
        }
        return true;
    }
    /**
     * ˵���ո��������в�������
     * @return 0��3��6����false
     */
    public boolean isMoveLeft() {
        int position = getZeroPosition();
        if(position%3 == 0){
            return false;
        }
        return true;
    }
    /**
     * ˵���ո��������в�������
     * @return 2��5��8�������Ʒ���false
     */
    public boolean isMoveRight() {
        int position = getZeroPosition();
        if((position)%3 == 2){
            return false;
        }
        return true;
    }
    /**
     * 
     * @param move 0���ϣ�1���£�2����3����
     * @return �����ƶ����״̬
     */
    public Node moveUp(int move){
        Node temp = new Node();
        int[] tempnum = (int[])num.clone();//���Ƶ�ǰ״̬��num��tempnum
        temp.setNum(tempnum);
        int position = getZeroPosition();    //0��λ��
        int p=0;                            //��0��λ�õ�λ��
        switch(move){
            case 0://�൱�ڿո�����
                p = position-3;
                temp.getNum()[position] = num[p];
                break;
            case 1://�൱�ڿո�����
                p = position+3;
                temp.getNum()[position] = num[p];
                break;
            case 2://�൱�ڿո�����
                p = position-1;
                temp.getNum()[position] = num[p];
                break;
            case 3://�൱�ڿո�����
                p = position+1;
                temp.getNum()[position] = num[p];
                break;
        }
        temp.getNum()[p] = 0;
        return temp;
    }
    
    
    /**
     * 
     * @param open open��
     * @param close close��
     * @param parent ��״̬
     * @param target Ŀ��״̬
     */
    public void operation(ArrayList<Node> open,ArrayList<Node> close,Node parent,Node target){
        if(this.isContains(close) == -1){//��ǰ״̬����close����
            int position = this.isContains(open);//�жϵ�ǰ״̬�Ƿ��ѽ���ӽ�open����
            if(position == -1){//��ǰ״̬����open��Ҳ����close����
                this.parent = parent;//���µ�ǰ�ڵ�ĸ���״̬
                this.init(target);//��ǰ״̬��Ӧ��F����ֵ
                open.add(this);//����ǰ״̬����OPEN����
            } else {//��ǰ״̬��open���в���close����
                if(this.getGvalue() < open.get(position).getGvalue()){//��״̬��Ŀ��Ĳ����֮ǰ����˵���Ƚ��Ż������»����Ż���״̬
                    open.remove(position);
                    this.parent = parent;
                    this.init(target);
                    open.add(this);
                }
            }
        }
    }
    
    
    /**
     * ���հ�����ĸ�ʽ������ڿ���̨���һ���Ź���
     */
    public void print(){
        for(int i=0;i<9;i++){
            if(i%3 == 2){
                System.out.println(this.num[i]);//ÿ��������
            }else{
                System.out.print(this.num[i]+"  ");
            }
        }
    }
    
    /**
     * �����е����״̬
     */
    public void printRoute(){
        Node temp = null;
        int count = 0;
        temp = this;
        while(temp!=null){
            temp.print();
            System.out.println("----------�ָ���----------");
            temp = temp.getParent();
            count++;
        }
        System.out.println("��������"+(count-1));
    }
    
    
    
}

