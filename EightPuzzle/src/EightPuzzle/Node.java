package EightPuzzle;


import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("rawtypes")
public class Node implements Comparable{
    private int[] num = new int[9];	//存放棋盘上的数字
    private int gValue;    //当前的深度即走到当前状态的步骤g(n)
    private int fValue;   //从起始状态到目标的最小估计值f(n)
    private int hValue;   //到目标的最小估计h(n)
    private Node parent;  //当前节点的父节点即父状态
    
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
     * 求逆序值并判断是否有解，当奇偶性相同才有解
     * @param target
     * @return 有解：true 无解：false
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
     * 判断当前状态是否为目标状态
     * @param target
     * @return
     */
    public boolean isTarget(Node target){
        return Arrays.equals(getNum(), target.getNum());
    }
    
    /**
     * 求f(n) = g(n)+h(n);
     * 初始化状态信息，得到depth 等信息
     * @param target
     */
    public void init(Node target){
        int temp = 0;
        for(int i=0;i<9;i++){
            if(num[i]!=target.getNum()[i])
                temp++;
        }
        this.setHvalue(temp);//得到当前节点的H值即与目标节点有多少个不同的数字个数
        
        if(this.getParent()==null){
            this.setGvalue(0);//初始点
        }else{
            this.gValue = this.parent.getGvalue()+1;//当前节点的深度为父节点深度加上1
        }
        this.setFvalue(this.getGvalue()+this.getHvalue());
    }
    
    
    @Override
    public int compareTo(Object o) {
        Node c = (Node) o;
        return this.fValue-c.getFvalue();//默认排序为f(n)由小到大排序
    }
    
    /**
     * @return 返回0在八数码中的位置
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
     * @param open状态集合
     * @return 判断当前状态是否存在于open表中，返回在open表中的位置， 每个状态都用数组表示
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
     * 说明空格在第一行不能往上移
     * @return 小于3的不能上移返回false
     */
    public boolean isMoveUp() {
        int position = getZeroPosition();
        if(position<=2){
            return false;
        }
        return true;
    }
    /**
     * 说明空格在最后一行不能往下移
     * @return 大于6不能往下移返回false
     */
    public boolean isMoveDown() {
        int position = getZeroPosition();
        if(position>=6){
            return false;
        }
        return true;
    }
    /**
     * 说明空格在最左列不能左移
     * @return 0，3，6返回false
     */
    public boolean isMoveLeft() {
        int position = getZeroPosition();
        if(position%3 == 0){
            return false;
        }
        return true;
    }
    /**
     * 说明空格在最右列不能右移
     * @return 2，5，8不能右移返回false
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
     * @param move 0：上，1：下，2：左，3：右
     * @return 返回移动后的状态
     */
    public Node moveUp(int move){
        Node temp = new Node();
        int[] tempnum = (int[])num.clone();//复制当前状态的num到tempnum
        temp.setNum(tempnum);
        int position = getZeroPosition();    //0的位置
        int p=0;                            //与0换位置的位置
        switch(move){
            case 0://相当于空格上移
                p = position-3;
                temp.getNum()[position] = num[p];
                break;
            case 1://相当于空格下移
                p = position+3;
                temp.getNum()[position] = num[p];
                break;
            case 2://相当于空格左移
                p = position-1;
                temp.getNum()[position] = num[p];
                break;
            case 3://相当于空格右移
                p = position+1;
                temp.getNum()[position] = num[p];
                break;
        }
        temp.getNum()[p] = 0;
        return temp;
    }
    
    
    /**
     * 
     * @param open open表
     * @param close close表
     * @param parent 父状态
     * @param target 目标状态
     */
    public void operation(ArrayList<Node> open,ArrayList<Node> close,Node parent,Node target){
        if(this.isContains(close) == -1){//当前状态不在close表中
            int position = this.isContains(open);//判断当前状态是否已将添加进open表中
            if(position == -1){//当前状态不在open表也不在close表中
                this.parent = parent;//更新当前节点的父亲状态
                this.init(target);//求当前状态对应的F函数值
                open.add(this);//将当前状态插入OPEN表中
            } else {//当前状态在open表中不在close表中
                if(this.getGvalue() < open.get(position).getGvalue()){//该状态到目标的步骤比之前的少说明比较优化，更新换成优化的状态
                    open.remove(position);
                    this.parent = parent;
                    this.init(target);
                    open.add(this);
                }
            }
        }
    }
    
    
    /**
     * 按照八数码的格式输出，在控制台输出一个九宫格
     */
    public void print(){
        for(int i=0;i<9;i++){
            if(i%3 == 2){
                System.out.println(this.num[i]);//每三个换行
            }else{
                System.out.print(this.num[i]+"  ");
            }
        }
    }
    
    /**
     * 反序列的输出状态
     */
    public void printRoute(){
        Node temp = null;
        int count = 0;
        temp = this;
        while(temp!=null){
            temp.print();
            System.out.println("----------分割线----------");
            temp = temp.getParent();
            count++;
        }
        System.out.println("步骤数："+(count-1));
    }
    
    
    
}

