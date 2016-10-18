package training.tasks.unit1.task4;

public class Task4 {

    /*
     * На вход подается переменное количество аргументов
     * в виде числовых значений
     */
    public static void main(String[] args) {

        if (args.length > 1) {
            final double a[] = new double[args.length];

            for (int i = 0; i < a.length; i++) {
                a[i] = Double.valueOf(args[i]);
            }

            System.out.println("Результат: " + max(a));
        } else {
            System.out.println("Требуется как минимум 1 аргумент");
        }
    }

	
	/*
     * Дана последовательность:
	 * a(1) + a(2*n), a(2) + a(2*n-1) ,..., a(n) + a(n + 1)
	 * 
	 * Опишем функцию, соответствующую данной последовательности
	 * f(x) = sqr(x)
	 * 
	 * Данный метод вычисляет функцию f(x), аргументом которой является
	 * элемент массива и возвращает максимальное значение функции среди 
	 * этих элементов
	 * 
	 * @param array массив элементов
	 * 
	 * @return максимальное значение функции f(x)
	 */

    private static double max(double[] array) {

        double max = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < array.length; i++) {
            double fx = Math.pow(array[i], 2);

            if (fx > max) {
                max = fx;
            }
        }

        return max;
    }

}
