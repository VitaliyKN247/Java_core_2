package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int WIN_COUNT = 3;  // условия знаков подряд для победы
    private static final char HUMAN_SIGN = 'X';  // знак человека
    private static final char AI_SIGN = '0';  // знак компьютера
    private static final char DOT_EMPTY = '*';  // знак пустого поля
    private static Scanner scanner = new Scanner(System.in);  // принятие данных от пользователя
    private static Random random = new Random();  // рандом (случайная комбинация)
    private static char[][] field; // игровое поле
    private static int fieldSizeX; // размерность игрового поля по горизонтали
    private static int fieldSizeY;  // размерность игрового поля по вертикали


    public static void main(String[] args) {
        field = new char[3][3];

//        init();
//        printField();
//
//        checkWin('3');
        while (true) {
            initial();
            printField();
            while (true) {
                humanMove();
                printField();
                if (checkGameStatus(HUMAN_SIGN, "Вы победили"))
                    break;
                aiTurn();
                printField();
                if (checkGameStatus(AI_SIGN, "Победа компьютера!"))
                    break;
            }
            System.out.println("Начать игру заново? \n(Y - да)");
            if(!scanner.next().equalsIgnoreCase("Y"));
                break;
        }
    }


    /**
     * заполнение поля пустыми (*) точками
     */
    private static void initial() {
        fieldSizeX = 3;
        fieldSizeY = 3;
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * распечатка поля
     */
    private static void printField() {
        System.out.print(".");
        for (int x = 0; x < fieldSizeX * 2 + 1; x++) {
            if (x % 2 == 0) System.out.print("-");
            else System.out.print(x / 2 + 1 );
        }
        System.out.println();

        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print(x + 1 + "|");
            for (int y = 0; y < fieldSizeY; y++)
                System.out.print(field[x][y] + "|");
            System.out.println();
        }

        for (int x = 0; x < fieldSizeX * 2 + 2; x++)
            System.out.print("-");
        System.out.println();
    }

    /**
     * ход человека
     */

    private static void humanMove() {
        int x_move, y_move;
        do {
            while (true) {
                System.out.print("Введите координату Х от 1 до 3: ");
                if (scanner.hasNextInt()) {
                    x_move = scanner.nextInt() - 1;
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Ошибка! Введите координату X в диапазоне: ");
                    scanner.nextLine();
                }
            }

            while (true) {
                System.out.print("Введите координату У от 1 до 3: ");
                if (scanner.hasNextInt()) {
                    y_move = scanner.nextInt() - 1;
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Ошибка! Введите координату Y в диапазоне: ");
                }
            }
        }
            while (!isDotValid(x_move, y_move) || !isDotEmpty(x_move, y_move)) ;
            field[x_move][y_move] = HUMAN_SIGN;
    }

    /**
     * проверка на пустоту ячейки
     * @param x_move точка по горизонтали
     * @param y_move точка по вертикали
     * @return пустое значение
     */
    private static boolean isDotEmpty (int x_move, int y_move) { return field[x_move][y_move] == DOT_EMPTY;}


    /**
     * проверка корректности ввода
     * @param x_move
     * @param y_move
     * @return
     */
    private static boolean isDotValid (int x_move, int y_move) {
        return x_move >= 0 && x_move < fieldSizeX && y_move >= 0 && y_move < fieldSizeY;
    }

    /**
     * проверка хода компьютера
     */
    private static void aiTurn (){
        int x_move, y_move;
        do {
            x_move = random.nextInt(fieldSizeX);
            y_move = random.nextInt(fieldSizeY);
        } while (!isDotEmpty(x_move,y_move));
        field [x_move][y_move] = AI_SIGN;
    }

    /**
     * проверка условий победы
     * @param dot точка
     */

    private static boolean checkWin (char dot) {
        // Проверка по трем горизонталям
        if (field[0][0] == dot && field[0][1] == dot && field[0][2] == dot) return true;
        if (field[1][0] == dot && field[1][1] == dot && field[1][2] == dot) return true;
        if (field[2][0] == dot && field[2][1] == dot && field[2][2] == dot) return true;

        // Проверка по трем вертикалям
        if (field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;
        if (field[0][1] == dot && field[1][1] == dot && field[2][1] == dot) return true;
        if (field[0][2] == dot && field[1][2] == dot && field[2][2] == dot) return true;

//        // Проверка по диагоналям
        if (field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) return true;
        if (field[0][2] == dot && field[1][1] == dot && field[2][0] == dot) return true;
        return false;
    }

    /**
     * проверка на победу по диагонали
     * @param dot
     * @return
     */
    private static boolean diagonalCheckWin(char dot) {
        boolean diagonalWin = true;
//        for (int x = 0; x < fieldSizeX; x ++){
//            for (int y = 0; y < fieldSizeY; y ++){
//                System.out.println(field[x][y]);
//            }
//            if(field[x][x] != dot){
//                diagonalWin = false;
//                break;
//            }
//        }
//        if (diagonalWin){
//            return true;
//        }




        for (int x = 0; x < fieldSizeX + 2; x ++) {
            for (int y = 0; y < fieldSizeY + 2; y++) {
                if (field[x][y] == dot && field[x + 1][y] == dot && field[x + 2][y] == dot) return true;
            }
        }
        return false;
    }

    /**
     * проверка по побочной диагонали
     * @param dot
     * @return
     */
    private static boolean secondaryDiagonalWin (char dot){
        boolean secondaryDiagWin = true;
        for (int x = 0 ; x < fieldSizeX; x ++){
            if (field[x][4 - x] != dot){
                secondaryDiagWin = false;
                break;
            }
        }
        if (secondaryDiagWin){
            return true;
        }
        return false;
    }

    /**
     * проверка на того, кто именно победил
     */
    private static boolean checkGameStatus (char dot, String congratulation) {
        if (checkWin(dot)) {
            System.out.println(congratulation);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья");
            return true;
        }
    return false;
    }

    /**
     * проверка на ничью
     */
    private static boolean checkDraw(){
        for(int x = 0; x < fieldSizeX; x ++){
            for (int y = 0; y < fieldSizeY; y ++){
                if(isDotEmpty(x,y)) return false;
            }
        }
        return true;
    }
}
