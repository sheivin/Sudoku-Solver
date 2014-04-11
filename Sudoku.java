class Sudoku
{
    public static void main(String args[])
    {
        int matrix[][] = find_question(args);
        fill_matrix(matrix);
        if (solve_matrix(0,0,matrix))
        {
            fill_matrix(matrix);
        }
        else
        {
            System.out.println("NONE");
        }
    }
    static int[][] find_question(String args[])
    {
        int question[][] = new int[9][9];
        for (int l = 0; l < args.length; l++)
        {
            int i = Integer.parseInt(args[l].substring(0,1));
            int j = Integer.parseInt(args[l].substring(1,2));
            int num = Integer.parseInt(args[l].substring(2,3));
            question[i][j] = num;
        }
        return question;
    }
    static boolean solve_matrix(int i, int j, int cells[][])
    {
        if (i == 9)
        {
            i = 0;
            if (++j == 9)
            {
                return true;
            }
        }
        if (cells[i][j] != 0)
        {
            return solve_matrix(i+1,j,cells);
        }
        for (int num = 1; num <= 9; num++)
        {
            if (check_validity(i,j,num,cells))
            {
                cells[i][j] = num;
                if (solve_matrix(i+1,j,cells))
                {
                    return true;
                }
            }
        }
        cells[i][j] = 0;
        return false;
    }
    static boolean check_validity(int i, int j, int num, int[][] cells)
    {
        for (int k = 0; k < 9; k++)
        {
            if (num == cells[k][j])
            {
                return false;
            }
        }
        for (int k = 0; k < 9; k++)
        {
            if (num == cells[i][k])
            {
                return false;
            }
        }
        int row = (i / 3)*3;
        int column = (j / 3)*3;
        for (int k = 0; k < 3; k++)
        {
            for (int l = 0; l < 3; l++)
            {
                if (num == cells[row+k][column+l])
                {
                    return false;
                }
            }
        }
        return true;
    }
    static void fill_matrix(int solution[][])
    {
        for (int i = 0; i < 9; i++)
        {
            if (i % 3 == 0)
            {
                System.out.println(" ----------------------- ");
            }
            for (int j = 0; j < 9; j++)
            {
                if (j % 3 == 0)
                {
                    System.out.print("| ");
                }
                System.out.print((solution[i][j] == 0)?" ":Integer.toString(solution[i][j]));
                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(" ----------------------- ");
    }
}