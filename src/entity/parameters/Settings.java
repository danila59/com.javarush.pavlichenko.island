package entity.parameters;

public class Settings {

    public static int columnsCount;
    public static int rowsCount;
    public int number_animals_in_one_field;

    public Settings(int columnsCount,  int rowsCount,int number_animals_in_one_field) {
        this.columnsCount = columnsCount;
        this.rowsCount = rowsCount;
        this.number_animals_in_one_field = number_animals_in_one_field;

    }
}
