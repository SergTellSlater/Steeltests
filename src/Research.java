import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Research {

    //Данная функция создаёт массив  из введённых чисел пользователем
    public void startResearch() {
        Scanner scanner = new Scanner(System.in);
        ChemicalElement[] elements = {new Carbon(), new Silicium(), new Manganum(), new Chromium(), new Niccolum(), new Molybdaenum(), new Titanium()};
        String[] elementNames = {"Углерод", "Кремний", "Марганец", "Хром", "Никель", "Молибден", "Титан"};

        for (int j = 0; j < elements.length; j++) {
            // Пользователь вводит размер массива
            System.out.println("Введите количество обжогов элемента " + elementNames[j] + ": ");
            int arraySize = 0;

            // Цикл для проверки корректного ввода размера массива
            while (true) {
                String input = scanner.nextLine();  // Чтение строки
                if (input.isEmpty()) {
                    System.out.println("Вы ничего не ввели! Введите количество обжогов.");
                } else {
                    try {
                        arraySize = Integer.parseInt(input);  // Преобразуем строку в int
                        if (arraySize > 0) break;  // Если размер массива корректный, выходим из цикла
                        else System.out.println("Количество обжогов должно быть больше нуля.");
                    } catch (NumberFormatException e) {
                        System.out.println("Введено некорректное число. Попробуйте ещё раз.");
                    }
                }
            }

            // Создаём новый массив для текущего элемента
            double[] array = new double[arraySize];
            System.out.println("Введите значения обжогов для элемента " + elementNames[j] + ": ");

            for (int i = 0; i < arraySize; i++) {
                while (true) {
                    try {
                        array[i] = scanner.nextDouble();
                        if (array[i] < 0) {
                            System.out.println("Ошибка: значение не может быть отрицательным. Введите снова.");
                        } else {
                            break;  // Если введено корректное значение, выходим из цикла
                        }
                    } catch (Exception e) {
                        System.out.println("Ошибка при вводе числа. Введите корректное значение.");
                        scanner.next();  // Очищаем некорректный ввод
                    }
                }
            }
            scanner.nextLine();  // Очистка после nextDouble()

            // Вызываем функции сравнения и обработки массива
            printFramesValue(elements[j]);
            comparisonArraysDigits(array, elements[j]);
            arrayFrame(array, elements[j]);
            System.out.println();
        }
        System.out.println("Вы произвели проверку по всем контролируемым элементам стандартного образца стали УГ4К. Поздравляю, вы большой молодец!");
    }

    // Данная функция сравнивает значения массива с максимальным и минимальным значениями допуска, заданным в
    //переменных в классе каждого элемента.
    public void comparisonArraysDigits(double[] array, ChemicalElement standardElement){
        for (int i = 0; i < array.length; i++){
            if (array[i] < standardElement.getMinDigitOfFrame() || array[i] > standardElement.getMaxDigitOfFrame()) {
                System.out.println("Значение " + array[i] + " вне допуска!!!");
            } else {
                System.out.println("Значения массивов в диапазоне допуска max/min");
            }
        }
    }

    // Данная функция вычитает минимальное значение массива из максимальго значения массива и сравнивает входит ли
    // получившийся результат в диапазон заданный значением в переменнную difference
    public void arrayFrame(double[] array,  ChemicalElement standardElement){
        double maxArray = array[0];
        double minArray = array[0];
        double differenceArray;
        for(int i = 0; i < array.length; i++) {
            if (array[i] > maxArray) {
                maxArray = array[i];
            }
            if (array[i] < minArray) {
                minArray = array[i];
            }
        }
        differenceArray = maxArray - minArray;
        BigDecimal roundedNumber = new BigDecimal(differenceArray).setScale(4, RoundingMode.HALF_UP);
        differenceArray = roundedNumber.doubleValue();
        System.out.println("Вычисленное значение дельты (разницы максимального и минимального значения обжогов): " + differenceArray);
        if(differenceArray <= standardElement.getDifference() && differenceArray >= 0){
            System.out.println("Значения в диапазоне сравнения. Всё хорошо!");
        } else if (differenceArray > standardElement.getDifference()){
            System.out.println("Значения не в диапазоне сравнения. Увы, не проходит!");
        } else if(differenceArray < 0){
            System.out.println("Вы допустили ошибку при вводе");
        }
    }
    public void printFramesValue(ChemicalElement standardElement){
        System.out.println("Для элемента " + standardElement.getName() + " установлено значение максимумального значения " + standardElement.getMaxDigitOfFrame());
        System.out.println("Для элемента " + standardElement.getName() + " установлено значение минимального значения " + standardElement.getMinDigitOfFrame());
        System.out.println("Для элемента " + standardElement.getName() + " установлено значение дельты " + standardElement.getDifference());
    }
}
