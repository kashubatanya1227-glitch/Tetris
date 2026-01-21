import java.util.Scanner;

public class Tetris{
    static class Figure{
        int[][] blocks = {
                {1,1},
                {1,1}
        };
        int x=4;
        int y=0;
        void left() {x--;}
        void right() {x++;}
        void down() {y++;}
        void rotate() {x--;y--;}
    }
    static class Field{
        int height =10;
        int width=10;
        int[][] board=new int[height][width];

        boolean canMove(Figure f,int dx,int dy) {
            for (int i = 0; i < f.blocks.length; i++) {
                for (int j = 0; j < f.blocks[i].length; j++) {
                    if (f.blocks[i][j] == 1) {
                        int nx = f.x + i + dx;
                        int ny = f.y + i + dy;

                        if (nx < 0 || ny < 0 || nx >= width || ny >= height) return false;
                        if (ny >= 0 && board[ny][nx] == 1) return false;
                    }
                }
            }
            return true;
        }
        void place(Figure f,int dx,int dy) {
            for (int i = 0; i < f.blocks.length; i++) {
                for (int j = 0; j < f.blocks[i].length; j++) {
                    if (f.blocks[i][j] == 1) {
                        board[f.y+1][f.x+1]=1;
                    }
                }
            }
        }
        void removeFullLines(){
            for (int i = height-1; i>=0;i--) {
                boolean full=true;
                for (int j=0;j<width;j++){
                    if (board[i][j]==0){
                        full=false;
                        break;
                    }
                }
                if (full){
                    for (int k=1;k>0;k--){
                        board [k]=board [k-1].clone();
                    }
                    board[0]=new int[width];
                    i++;
                }
            }
        }
        void draw(Figure f){
            for (int i = 0; i <height; i++) {
                for (int j = 0; j <width; j++) {
                    boolean isFigure=false;

                    for (int fi=0;fi<f.blocks.length;fi++) {
                        for (int fj=0;fj<f.blocks[fi].length;fj++) {
                            if (f.blocks[fi][fj]==1 &&
                                    f.y+fi==1 &&
                                    f.x+fj==j){
                                System.out.print("$");
                                isFigure=true;
                            }
                        }
                    }
                    if (!isFigure){
                        System.out.print(board[i][j]==1?"$" : "_");
                    }
                }
                System.out.println();
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);

        System.out.println("Play");
        System.out.println("Exit");

        int choise=sc.nextInt();
        sc.nextLine();

        if (choise==1){
            gameLoop();
        } else {
            System.out.println("Exit the game");
        }
    }
    static void gameLoop() throws Exception {
        System.out.println("Game started");
    }
}