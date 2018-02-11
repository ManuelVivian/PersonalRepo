package tris.manuel;

/**
 * Created by manuel on 05/01/2018.
 */

public class Tris {

    private int riga;
    private int colonna;
    private int[][] matrice;
    private int totali;
    private int liberi;

    private int winner;

    public Tris(){
        this.riga = 3;
        this.colonna = 3;
        this.liberi = riga*colonna;
        this.totali = liberi;
        matrice = new int[riga][colonna];
    }

    public boolean isLibero(int row, int column){
        return matrice[row][column] == 0;
    }

    public void setTurno(int row, int column, int player){
        matrice[row][column] = player;
        liberi--;
    }

    public int getWinner(){
        return winner;
    }

    public int getTotali(){
        return totali;
    }

    public int getLiberi() {
        return liberi;
    }

    public int getRow() {
        return riga;
    }

    public int getColumn() {
        return colonna;
    }

    public int[][] getMatrice(){
        return matrice;
    }

    public void stampaMatrice(){
        for(int i=0; i<riga; i++){
            for(int j=0; j<colonna; j++){
                System.out.println(matrice[i][j]+"  ");
            }
            System.out.println("\n");
        }
    }

    public boolean hasWin(){
        boolean r = false;
        //Controllo se vittoria orizzontale
        for(int i=0; i<riga; i++){
            for(int j=0; j<colonna; j++){
                if(matrice[i][j] == matrice[i][0] && matrice[i][j] != 0)
                    r = true;
                else {
                    r = false;
                    break;
                }
            }
            if(r) {
                winner = matrice[i][0];
                return r;
            }
        }
        //Controllo se vittoria verticale
        for(int i=0; i<colonna; i++){
            for(int j=0; j<riga; j++){
                if(matrice[j][i] == matrice[0][i] && matrice[j][i] != 0)
                    r = true;
                else {
                    r = false;
                    break;
                }
            }
            if(r) {
                winner = matrice[0][i];
                return r;
            }
        }
        //Controllo se vittoria obliquo da as a bd
        for(int i=0; i<colonna; i++){
            if(matrice[i][i] == matrice[0][0] && matrice[i][i] != 0)
                r = true;
            else {
                r = false;
                break;
            }
        }
        if(r) {
            winner = matrice[0][0];
            return r;
        }
        //Controllo se vittoria obliquo da ad a bs
        for(int i=0, j=colonna-1; i<colonna; i++, j--){
            if(matrice[i][j] == matrice[0][colonna-1] && matrice[i][j] != 0)
                r = true;
            else {
                r = false;
                break;
            }
        }
        if(r) {
            winner = matrice[0][colonna-1];
            return r;
        }
        return r;
    }
}
